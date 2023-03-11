package com.managementTool.project.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class AuthController {

    @GetMapping("/login")
    public String userLogin(){
        return "User logged in";
    }

    @GetMapping("/admin")
    public String adminLogin(){
        return "Admin logged in";
    }
}
