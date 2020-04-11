package iwona.pl.modol9baza.service;

import iwona.pl.modol9baza.aspect.AfterCheckTime;
import iwona.pl.modol9baza.aspect.AroundCheckTime;
import iwona.pl.modol9baza.aspect.BeforeCheckTime;
import iwona.pl.modol9baza.model.Data;
import iwona.pl.modol9baza.repository.DataRepo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    private DataRepo dataRepo;
    private final static String PATH = "src/main/resources/mockdata.csv";
    private List<Data> dataList;

    @Autowired
    public DataService(DataRepo dataRepo) {
        this.dataRepo = dataRepo;
        dataList = new ArrayList<>();
    }

    @AroundCheckTime
    @BeforeCheckTime
    @AfterCheckTime
    public void read() {  // h2 0.007  mysql = 0.008
        try {
            BufferedReader read = new BufferedReader(new FileReader(PATH));
            String nextLine = null;
            int lines = 0;
            while ((nextLine = read.readLine()) != null) {

                String[] data1 = nextLine.split(",");
//                Data data = new Data(
//                        Long.parseLong(data1[0]),
//                        data1[1],
//                        data1[2],
//                        data1[3],
//                        data1[4],
//                        data1[5]);
//                dataList.add(data);
                dataList.add(saveToBd(data1));
                lines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AroundCheckTime
    @BeforeCheckTime
    @AfterCheckTime
    public Data saveToBd(String[] CsvData) {
        Data data = new Data();
//        data.setId(CsvData[0]);
        data.setFirstName(CsvData[1]);
        data.setLastName(CsvData[2]);
        data.setEmail(CsvData[3]);
        data.setGender(CsvData[4]);
        data.setIpAddress(CsvData[5]);
        return data;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    @AroundCheckTime
    @BeforeCheckTime
    @AfterCheckTime
    public void saveAll(List<Data> dataList){ // my sql 73.847 // h2 0.288  mysql 73.29
        dataRepo.saveAll(dataList);
    }

//    public void saveAll(){
//        dataRepo.saveAll(dataList);

    @AroundCheckTime
    @BeforeCheckTime
    @AfterCheckTime
    public List<Data> find() { // h2 0.127 mysql //0.768, 0.717
        return dataRepo.findAll();
    }
}
