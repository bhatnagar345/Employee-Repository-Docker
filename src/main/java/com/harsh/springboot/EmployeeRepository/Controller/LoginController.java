package com.harsh.springboot.EmployeeRepository.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showLoginPage()
    {
        return "fancy-login";
    }

    //add request mapping for access-denied
    @GetMapping("/access-denied")
    public String accessDenied()
    {
        return "access-denied";
    }
}
