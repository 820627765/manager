<%@page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>管理框架</title>
        <meta charset="utf-8">
        <meta name="keywords" content="web框架,java web框架">
        <meta name="description" content="一个简单易用安全性能好的java开发框架">
        <link rel="shortcut icon" href="/manager/static/images/favicon.ico">
    </head>
    <body>
        <h2>Hello My Web 框架!</h2>
        <a href="hello">hello Word</a>
        <h2>springMVC REST风格案例</h2>
        <form action="order/1001" method="post">
            <!--隐藏域-->
            <input type="hidden" name="_method" value="delete"/>
            <input type="submit" value="删除id为1001的订单"/>
        </form>
        <br/>
        <a href="order/1001">查询id为1001的订单信息</a>
        <br/>
        <form action="order" method="post">
            <input type="submit" value="提交"/>
        </form>
        <br/>
        <form action="order" method="post">
            <input type="hidden" name="_method" value="put"/>
            <input type="submit" value="修改"/>
        </form>
    </body>
</html>
