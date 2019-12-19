<%--
  Created by IntelliJ IDEA.
  User: 张宁波
  Date: 2019/11/28
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Emp</title>
</head>
    <body>
        <!--
            springMVC 的表单标签：
                1，可以快速开发表单
                2，可以方便的回显数据 ***
            springMVC 表单标签遇到的问题：
                java.lang.IllegalStateException: Neither BindingResult nor plain target
                object for bean name 'command' available as request attribute
                从request的attribute 中去找名字为 command 的bean，没有找到。

                问题原因：springmvc 的表单标签必须要进行数据回显。默认会使用 command 这个key到request中
                找回显数据。如果找不到，则抛出异常。

                问题解决办法：让springMvc可以通过 command 从request 中找到要回显的数据。且这个数据中必须包含
                表单中对应项的path值对应的属性名。
                且：在表单form:form modelAttribute="employee" 中可以通过modelAttribute 来指定一个key 替换默认的 command 。

            表单标签在最终执行时回转化为原始的HTML标签
            ${pageContext.request.contextPath} 该方式会得到项目的根路径，如 /manager
        -->
        <form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
            <input type="hidden" name="_method" value="put">
            <form:hidden path="id"/>
            lastName:
            <form:input path="lastName"/> <!-- path 就相当于 HTML 中 input 标签中的name属性--> <br/>
            Email:
            <form:input path="email"/><br/>
            Gender:
            <form:radiobuttons path="gender" items="${genders}"/><!--form:radiobuttions 可以根据Map数据来生成单选框--><br/>
            deptName:
            <form:select path="department.id" items="${depts}" itemLabel="departmentName" itemValue="id"/>
            <!--itemLabel 对应option 的显示名字，itemValue 对应option 的value值-->
            <br/>
            <input type="submit" value="修改"/>
        </form:form>
    </body>
</html>