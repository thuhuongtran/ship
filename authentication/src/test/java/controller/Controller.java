package controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class Controller {
    @GetMapping(value = {"","/"})
    public String hello(HttpServletResponse response){
        return "hello world!";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletResponse res, HttpServletRequest req){
        return "login";
    }


}
