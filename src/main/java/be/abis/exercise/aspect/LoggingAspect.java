package be.abis.exercise.aspect;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private Logger exceptionLogger = LogManager.getLogger("exceptionLogger");
    private Logger consoleLogger = LogManager.getLogger("Console");

    @AfterThrowing(pointcut="execution(public * be.abis.exercise.*.*.*(..))", throwing = "ex")
    public void logAfterException(Exception ex){
        System.out.println("logger activated");
        exceptionLogger.error(ex.getMessage());
    }

}
