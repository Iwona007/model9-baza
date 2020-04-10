package iwona.pl.modol9baza.service;

import iwona.pl.modol9baza.model.Data;
import iwona.pl.modol9baza.repository.DataRepo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    private DataRepo dataRepo;
    private final static String PATH = "D:\\Eryka\\PROGRAMOWANIE\\Spring-Boot-Akademia\\Homework\\modol9\\mockdata.csv";
    private List<Data> dataList;

    @Autowired
    public DataService(DataRepo dataRepo) {
        this.dataRepo = dataRepo;
        dataList = new ArrayList<>();
        read();
        saveInDb();
    }

    //    @EventListener(ApplicationReadyEvent.class)
    public void read() {
        try {
            BufferedReader read = new BufferedReader(new FileReader(PATH));
            String nextLine = null;
            int lines = 0;
            while ((nextLine = read.readLine()) != null) {
                String[] data1 = nextLine.split(",");
                Data data = new Data(
//                        Long.parseLong(data1[0]),
                        data1[1],
                        data1[2],
                        data1[3],
                        data1[4],
                        data1[5]);
                dataList.add(data);
//                System.out.println(dataList);
                lines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveInDb() {
        for (int i = 0; i < dataList.size(); i++) {
            dataList.get(i).getId();
            dataList.get(i).getFirstName();
            dataList.get(i).getLastName();
            dataList.get(i).getEmail();
            dataList.get(i).getGender();
            dataList.get(i).getIpAddress();
        }
        dataRepo.saveAll(dataList);
    }
}
