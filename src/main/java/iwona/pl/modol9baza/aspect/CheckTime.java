package iwona.pl.modol9baza.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckTime {


    private double startTime;

    @Before("@annotation(BeforeCheckTime)")
    public void beforeStart() {
        startTime = System.currentTimeMillis();
        System.out.println("Start: ");
    }

    @After("@annotation(AfterCheckTime)")
    public void afterStart() {
        double duration = (System.currentTimeMillis() - startTime)/1000;
        System.out.println("Czas wczytywania danyc do DB H2: " + duration);
    }

    @Around("@annotation(AroundCheckTime)")
    public void aroundStart(ProceedingJoinPoint joinPoint) throws Throwable {
        startTime = System.currentTimeMillis();
        joinPoint.proceed();
        double duration = (System.currentTimeMillis() - startTime)/1000;
        System.out.println("Czas wczytania danych do DB: " +duration);
    }

}
