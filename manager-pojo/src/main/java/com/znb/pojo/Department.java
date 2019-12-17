package com.znb.pojo;

/**
 * @Auther zhangningbo
 * @Date:2019/12/17
 * @Description:com.znb.pojo
 * @version:1.0
 */
public class Department {
    private Integer id;
    private String departmentName;

    public Department() {

    }

    public Department(Integer id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
