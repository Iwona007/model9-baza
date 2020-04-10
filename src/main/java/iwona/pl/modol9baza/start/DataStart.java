package iwona.pl.modol9baza.start;

import iwona.pl.modol9baza.aspect.AfterCheckTime;
import iwona.pl.modol9baza.aspect.AroundCheckTime;
import iwona.pl.modol9baza.aspect.BeforeCheckTime;
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

    @BeforeCheckTime
    @AfterCheckTime
    @AroundCheckTime
    @EventListener(ApplicationReadyEvent.class)
    public void read(){
        dataService.read();
        dataService.saveInDb();
//        dataService.find();
    }


//remote MySql  69.877
// H2 czas 0,256
}
