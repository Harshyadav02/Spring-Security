package com.harsh.springSecurity.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.springSecurity.Entity.Employee;
import com.harsh.springSecurity.Service.EmployeeService;

@RestController
@RequestMapping("/emp/")
public class EmployeeController {
    @Autowired
    private EmployeeService empService;

    @GetMapping
    public ResponseEntity<?> getEmployeeData() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            
            return empService.getEmployeeData(email);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee empDetail) {

        try {
            empService.createEmployee(empDetail);
            return new ResponseEntity<>("Employee created", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }  
    @PutMapping
    public ResponseEntity<?> updateEmployeeData(@RequestBody Employee empDetails){
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
           return empService.updateEmployeeData(email,empDetails);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }  
}
