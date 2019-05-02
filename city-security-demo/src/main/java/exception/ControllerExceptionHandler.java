package exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    public Map<String,Object> handleUserNotExistException(UserNotExistException ex){

        Map<String,Object> result = new HashMap<>();

        result.put("id",ex.getId());
        result.put("message",ex.getMessage());

        return result;


    }


}
