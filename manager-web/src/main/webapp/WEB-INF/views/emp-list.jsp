<%--
  Created by IntelliJ IDEA.
  User: 张宁波
  Date: 2019/11/28
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>success</title>
</head>
<body>
    <table border="1px" align="center" width="70%" cellspacing="0px">
        <tr align="center">
            <th>ID</th>
            <th>LastName</th>
            <th>Email</th>
            <th>Gender</th>
            <th>DeptName</th>
            <th>Operation</th>
        </tr>
        <c:forEach items="${emps}" var="emp">
            <tr align="center">
                <td>${emp.id}</td>
                <td>${emp.lastName}</td>
                <td>${emp.email}</td>
                <td>${emp.gender==0?"女":"男"}</td>
                <td>${emp.department.departmentName}</td>
                <td>
                    <a href="#">Edit</a>
                    &nbsp;&nbsp;
                    <a href="#">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h2 align="center">
        <!--
            去往添加页面，不能直接进行简单跳转，因为添加页面中需要用到部门数据
        -->
        <a href="emp">Add New Emp</a>
    </h2>
</body>
</html>
