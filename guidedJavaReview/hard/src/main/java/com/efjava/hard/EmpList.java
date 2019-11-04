package com.efjava.hard;

import java.util.ArrayList;

public class EmpList {
public ArrayList<employee> empList = new ArrayList<employee>();

public EmpList() {
    empList.add(new employee(
            "fname",
            "lname",
            33.22,
            false,
            1,
            1)
    );
    empList.add(new employee(
            "fname2",
            "lname2",
            332.22,
            true,
            2,
            2)
    );
};

public employee findEmployee(CheckEmployee tester) {
 for (employee e : empList) {
     if (tester.test(e))
     {
         return e;
     }

 }
    return null;
}

public ArrayList<employee> findEmployees(CheckEmployee tester)
{
    ArrayList<employee> tempemplist = new ArrayList<>();

    for (employee e:empList) {
        if (tester.test(e))
        {
            tempemplist.add(e);
        }
    }

    return tempemplist;
}




}
