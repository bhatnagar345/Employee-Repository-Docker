package com.harsh.springboot.EmployeeRepository.Controller;

import com.harsh.springboot.EmployeeRepository.Entity.Employee;
import com.harsh.springboot.EmployeeRepository.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService)
    {
        employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel)
    {
        List<Employee> theEmployees = employeeService.findAll();
        theModel.addAttribute("employees",theEmployees);
        return "list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormAdd(Model theModel)
    {
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);
        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee)
    {
//         theEmployee.setId(0);
        employeeService.save(theEmployee);

        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") Integer theId, Model theModel )
    {
        Employee theEmployee = employeeService.findById(theId);
        theModel.addAttribute("employee",theEmployee);
        return "employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") Integer theId)
    {
        employeeService.deleteById(theId);
        return "redirect:/employees/list";
    }

}