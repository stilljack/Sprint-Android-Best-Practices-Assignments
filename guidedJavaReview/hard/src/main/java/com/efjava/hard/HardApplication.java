package com.efjava.hard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HardApplication {

    static EmpList ourEmpList;


    public static void main(String[] args) {

        ourEmpList= new EmpList();
        SpringApplication.run(HardApplication.class, args);

    }

}
