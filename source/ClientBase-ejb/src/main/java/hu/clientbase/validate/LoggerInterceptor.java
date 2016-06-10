package hu.clientbase.validate;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
public class LoggerInterceptor {

    @AroundInvoke
    public Object invoke(InvocationContext invocationContext) throws Exception {
        
        final Logger LOGGER = Logger.getLogger(" new method called ");
        
        String methodName = invocationContext.getMethod().getName();
        LOGGER.log(Level.INFO, " this method was called: -- {0}", methodName+" --");
        return invocationContext.proceed();
    }
}
