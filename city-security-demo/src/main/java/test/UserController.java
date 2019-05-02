package test;

import exception.ControllerExceptionHandler;
import exception.UserNotExistException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @ExceptionHandler(UserNotExistException.class)
    @RequestMapping(value = "/user",method=RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public List<User> query(@PageableDefault(page = 2,size = 7,sort = "username,asc")Pageable pageable){

        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());

        List<User> users  = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
//        throw new UserNotExistException("user not found");

        return users;
    }

}
