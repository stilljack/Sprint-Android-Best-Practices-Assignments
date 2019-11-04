package com.efjava.hard;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

//java spring bean
@RestController
@RequestMapping("/arbitrary")
public class EmployeeEndpointController {

    // localhost:2019/data/allemployees
    @GetMapping(value = "/allemployees",
    produces = {"application/json"})
    public ResponseEntity<?> getAllEmployees()

    {
        HardApplication.ourEmpList.empList.sort((e1,e2)->e1.getFname().compareToIgnoreCase(e2.getFname()));
        return new ResponseEntity<>(HardApplication.ourEmpList.empList, HttpStatus.OK);
    }

    // localhost:2019/data/employee/2
    @GetMapping(value = "/employee/{empid}",
            produces = {"application/json"})
    public ResponseEntity<?>  getEmpDetail(@PathVariable long empid)
    {
        employee rtnemp = HardApplication.ourEmpList.findEmployee(e->(e.getId()==empid));

        return new ResponseEntity<>(rtnemp,HttpStatus.OK);

    }


    // localhost:2019/data/employees/s
   @GetMapping(value = "/employees/{letter}",
            produces = {"application/json"})
    public ResponseEntity<?> getEmpByFirstLetter (@PathVariable char letter)
    {
        ArrayList<employee> rtnEmps = HardApplication.ourEmpList.findEmployees(
                e ->
                        e.getFname().toUpperCase().charAt(0)
                                ==
                        Character.toUpperCase(letter));
        rtnEmps.sort((e1,e2) -> (int)(e1.getSalary() - e2.getSalary()));

        return new ResponseEntity<>(rtnEmps,HttpStatus.OK);
    }

}
