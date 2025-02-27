package community.whatever.onembackendjava;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* community.whatever.onembackendjava..*(..))")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = request.getRequestURI();
        String requestMethod = request.getMethod();

        String requestArgs = Arrays.toString(joinPoint.getArgs());

        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            StringBuffer sb = new StringBuffer();
            sb.append("request uri: ").append(requestUri).append("\n");
            sb.append("http method: ").append(requestMethod).append("\n");
            sb.append("args: ").append(requestArgs).append("\n");
            sb.append("msg: ").append(e.getMessage());

            logger.error(sb.toString());
            throw e;
        }
    }

    private HttpServletRequest getCurrentHttpRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }
}
