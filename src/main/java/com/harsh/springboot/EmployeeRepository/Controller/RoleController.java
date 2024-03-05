package com.harsh.springboot.EmployeeRepository.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleController {
//    @GetMapping("/")
//    public String showHome()
//    {
//        return "home";
//    }

    @GetMapping("/leaders")
    public String showLeaders()
    {
        return "leaders";
    }

    @GetMapping("/systems")
    public String showSystem()
    {
        return "systems";
    }
}
