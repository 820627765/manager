package com.znb.service;

import com.znb.pojo.Department;
import com.znb.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther zhangningbo
 * @Date:2019/12/17
 * @Description:com.znb.service
 * @version:1.0
 */
@Service
public class EmployeeService {
    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentService departmentService;

    static{
        employees = new HashMap<Integer,Employee>();
        employees.put(1001,new Employee(1001,"E-AA","aa@123.com",1,new Department(101,"D-AA")));
        employees.put(1002,new Employee(1002,"E-BB","bb@123.com",1,new Department(102,"D-BB")));
        employees.put(1003,new Employee(1003,"E-CC","cc@123.com",0,new Department(103,"D-CC")));
        employees.put(1004,new Employee(1004,"E-DD","dd@123.com",1,new Department(104,"D-DD")));
        employees.put(1005,new Employee(1005,"E-FF","ff@123.com",0,new Department(105,"D-FF")));
    }

    private static Integer initId = 1005;

    public void saveOrUpdate(Employee employee){
        if(employee.getId() == null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentService.getDepartment(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }

    public Collection<Employee> getAll(){
        return employees.values();
    }

    public Employee get(Integer id){
        return employees.get(id);
    }

    public void delete(Integer id){
        employees.remove(id);
    }

}
