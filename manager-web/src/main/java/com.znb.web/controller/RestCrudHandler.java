package com.znb.web.controller;

import com.znb.pojo.Department;
import com.znb.pojo.Employee;
import com.znb.service.DepartmentService;
import com.znb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther zhangningbo
 * @Date:2019/12/17
 * @Description:com.znb.web.controller
 * @version:1.0
 */
@Controller
public class RestCrudHandler {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * 添加功能：去往添加页面
     */
    @RequestMapping(value = "/emp",method = RequestMethod.GET)
    public String toAddPage(Map<String,Object> map){
        Collection<Department> depts = departmentService.getDepartments();
        map.put("depts",depts);
        //构造页面中生成单选框的数据
        Map<String,String> genders = new HashMap<String,String>();
        genders.put("0","女");
        genders.put("1","男");
        map.put("genders",genders);

        //设置页面中要回显的数据
        map.put("employee",new Employee());
        return "emp-add";
    }

    /**
     * 显示所有的员工信息列表
     */
    @RequestMapping(value = "/emps",method = RequestMethod.GET)
    public String listAllEmps(Map<String,Object> map){
        Collection<Employee> emps = employeeService.getAll();
        map.put("emps",emps);
        return "emp-list";
    }
}
