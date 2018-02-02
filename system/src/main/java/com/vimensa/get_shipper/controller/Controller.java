package com.vimensa.get_shipper.controller;

import com.vimensa.get_shipper.model.Order;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class Controller {
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String getShipper(@RequestBody Order order, HttpServletResponse res){
        return "";
    }
}
