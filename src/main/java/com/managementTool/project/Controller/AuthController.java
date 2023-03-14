package com.managementTool.project.Controller;

import com.managementTool.project.Entity.Role;
import com.managementTool.project.Entity.User;
import com.managementTool.project.Repository.RoleRepository;
import com.managementTool.project.Service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class AuthController {

    private final RoleRepository roleRepository;
    private final UserService userService;

    public AuthController(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String userLogin(){
        return "User logged in";
    }

    @GetMapping("/admin")
    public String adminLogin(){
        return "Admin logged in";
    }

    @PostMapping("/addRole")
    public void addRole(@RequestBody Role role) {
         roleRepository.save(role);
    }

    @PostMapping("/addUser")
    public void addRole(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.addUser(user);
    }
}
