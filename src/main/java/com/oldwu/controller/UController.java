package com.oldwu.controller;

import com.oldwu.service.UService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UController {
    @Autowired
    private UService uService;

    @RequestMapping("/api/turnOffComputer/{secret}")
    @ResponseBody
    public String turnOffComputer(@PathVariable String secret){
        return uService.turnOffComputer(secret);
    }

}
