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
    <title>员工列表</title>
    <script type="text/javascript" src="static/js/jquery-3.4.1.min.js"></script>
    <script>
        $(function(){
            $("._del").click(function(){
                //确认是否要删除
                var flag = window.confirm("是否确定删除？");
                if(flag){
                    var href = $(this).attr("href");
                    $("form").attr("action",href).submit();
                }
                //取消<a> 的默认行为
                return false;
            });
        });
    </script>
</head>
<body>
    <form action="" method="post">
        <input type="hidden" name="_method" value="delete">
    </form>
    <h2>员工列表</h2>
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
                    <a href="emp/${emp.id}">Edit</a>
                    &nbsp;&nbsp;
                    <!--
                        解决思路：
                            因为REST 风格的删除，需要发送的是 delete 请求。而delete
                            的请求首先需要发送的是POST请求再加上_method=delete 的请求项
                            来达到发送 delete 的效果。所以a标签发送的get请求需要被改造。

                            给删除的超链接绑定事件，当触发了点击事件，可以在事件处理函数中
                            获取到要发送的请求URL，再将获取到的请求URL设置到某个表单的action
                            属性上，再将表单提交。最终将<a> 的默认行为取消掉。
                    -->
                    <a href="emp/${emp.id}" class="_del">Delete</a>
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
