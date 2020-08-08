package iwona.pl.modol9baza.start;

import iwona.pl.modol9baza.service.DataService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataStart {

    private DataService dataService;

    public DataStart(DataService dataService) {
        this.dataService = dataService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void read() {
        dataService.read(); //h2 0.014, 0.008, 0.006, 0.014 // mysql 0.007, 0.008, 0.108, 0.006
        dataService.saveAll(dataService.getDataList()); // H2 0.287, 0.269, 0.281, 0.397 // mysql 72.55, 72.329 , 74.786, 77.852
        dataService.find(); //H2 0.13, 0.118, 0.125, 0.125 // mysql 0.73, 0.792, 0.931, 1072
    }
}
