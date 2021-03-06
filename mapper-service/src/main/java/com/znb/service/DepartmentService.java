package com.znb.service;

import com.znb.pojo.Department;
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
public class DepartmentService {
    private static Map<Integer, Department> departments = null;
    static {
        departments = new HashMap<Integer,Department>();
        departments.put(101,new Department(101,"D-AA"));
        departments.put(102,new Department(102,"D-BB"));
        departments.put(103,new Department(103,"D-CC"));
        departments.put(104,new Department(104,"D-DD"));
        departments.put(105,new Department(105,"D-FF"));
        departments.put(106,new Department(106,"D-MM"));
        departments.put(107,new Department(107,"D-EE"));
        departments.put(108,new Department(108,"D-UU"));
    }

    public Collection<Department> getDepartments(){
        return departments.values();
    }

    public Department getDepartment(Integer id){
        return departments.get(id);
    }
}
