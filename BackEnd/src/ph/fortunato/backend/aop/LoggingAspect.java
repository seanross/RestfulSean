/**
 * 
 */
package ph.fortunato.backend.aop;

import java.lang.reflect.Field;
import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Sean Ross
 *
 */
@Aspect
@Component
public class LoggingAspect {
	
	static final Logger LOG = Logger.getLogger(LoggingAspect.class);

	@Before("execution(public void ph.fortunato.backend.*.dao.*.create(..))")  
	public void createAdvice(JoinPoint joinPoint) {
        Object object = joinPoint.getArgs()[0];
        try {
            Field createdDate = object.getClass().getSuperclass().getDeclaredField("createdDate");
            createdDate.setAccessible(true);
            createdDate.set(object, new Date());
        } catch (Exception e) {
            LOG.info("An error has occured in logging aspect.");
            e.printStackTrace();
        }
	}
	
	@Before("execution(public void ph.fortunato.backend.*.dao.*.update(..))")  
	public void updateAdvice(JoinPoint joinPoint) {
        Object object = joinPoint.getArgs()[0];
        try {
            Field updatedDate = object.getClass().getSuperclass().getDeclaredField("updatedDate");
            updatedDate.setAccessible(true);
            updatedDate.set(object, new Date());
        } catch (Exception e) {
            LOG.info("An error has occured in logging aspect.");
            e.printStackTrace();
        }
	}
}
