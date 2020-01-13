package com.znb.web.controller;

import com.znb.pojo.Department;
import com.znb.pojo.Employee;
import com.znb.service.DepartmentService;
import com.znb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @ModelAttribute
    public void getEmp(@RequestParam(value = "id",required = false) Integer id, Map map){
        System.out.println("执行modelAttribute 方法");
        if(id != null){
            map.put("emp",employeeService.get(id));
        }
    }

    /**
     * 修改功能：
     */
    @RequestMapping(value = "/emp",method = RequestMethod.PUT)
    public String updateEmp(@ModelAttribute(value = "emp") Employee employee,String table){
        employeeService.saveOrUpdate(employee);
        return "redirect:/emps";
    }

    /**
     * 修改功能：去往修改页面
     */
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    public String toUpdatePage(@PathVariable("id") Integer id,Map<String,Object> map){
        //查询要修改的员工信息
        Employee employee = employeeService.get(id);
        map.put("employee",employee);
        //部门数据
        Collection<Department> depts = departmentService.getDepartments();
        map.put("depts",depts);
        //性别数据
        Map<String,String> genders = new HashMap<String,String>();
        genders.put("0","女");
        genders.put("1","男");
        map.put("genders",genders);
        //去往修改页面
        return "emp-update";
    }

    /**
     * 删除功能
     */
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
    public String deleteEmp(@PathVariable("id") Integer id){
        //删除员工
        employeeService.delete(id);
        //重定向到员工列表请求
        return "redirect:/emps";
    }

    /**
     * 添加功能：具体的添加操作
     */
    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    public String addEmp(Employee employee){
        //添加员工
        employeeService.saveOrUpdate(employee);
        //回到列表页面：重定向到显示所有员工信息列表请求
        return "redirect:/emps";
    }

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
