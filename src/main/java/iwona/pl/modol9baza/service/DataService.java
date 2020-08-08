package iwona.pl.modol9baza.service;

import iwona.pl.modol9baza.aspect.AroundCheckTime;
import iwona.pl.modol9baza.model.Data;
import iwona.pl.modol9baza.repository.DataRepo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void read() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String nextLine = null;
            int lines = 0;
            reader.readLine();// dzielki temu moijam nagłowek
            while ((nextLine = reader.readLine()) != null) {
                String[] data1 = nextLine.split(",");
                Data data = new Data(
                        data1[1],
                        data1[2],
                        data1[3],
                        data1[4],
                        data1[5]);
                dataList.add(data);
                lines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Data> getDataList() {
        return dataList;
    }

    @AroundCheckTime
    public void saveAll(List<Data> dataList) {
        dataRepo.saveAll(dataList);
    }

//    public void saveAll(){
//        dataRepo.saveAll(dataList);

    @AroundCheckTime
    public List<Data> find() {
        return dataRepo.findAll();
    }
}
