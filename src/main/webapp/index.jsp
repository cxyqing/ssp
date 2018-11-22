<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/10/27
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index Page</title>
</head>
<body>

<p>
    <a href="springmvc/testRedirect">Test testRedirect</a>
</p>

<p>
    <a href="springmvc/testBeanNameView">Test testBeanNameView</a>
</p>
<p>
    <a href="springmvc/testViewAndViewResolver">Test ViewAndViewResolver</a>
</p>
<p>
<form action="springmvc/testModelAttribute" method="Post">
    <input type="hidden" name="id" value="1"/>
    username: <input type="text" name="username" value="Tom"/>
    <br>
    email: <input type="text" name="email" value="tom@atguigu.com"/>
    <br>
    age: <input type="text" name="age" value="12"/>
    <br>
    <input type="submit" value="Submit"/>
</form>

</p>
<a href="springmvc/testSessionAttributes">Test SessionAttributes</a>
<br><br>

<a href="springmvc/testModelAndView">Test ModelAndView</a>
<br><br>

<a href="springmvc/testServletAPI">Test ServletAPI</a>
<br><br>

<form action="springmvc/testPojo" method="post">
    username: <input type="text" name="username"/>
    <br>
    password: <input type="password" name="password"/>
    <br>
    email: <input type="text" name="email"/>
    <br>
    age: <input type="text" name="age"/>
    <br>
    city: <input type="text" name="address.city"/>
    <br>
    province: <input type="text" name="address.province"/>
    <br>
    <input type="submit" value="Submit"/>
</form>
<br><br>
<a href="springmvc/testCookieValue">Test CookieValue</a>
<br><br>

<a href="springmvc/testRequestHeader">Test RequestHeader</a>
<br><br>

<a href="springmvc/testRequestParam?username=atguigu&age=11">Test RequestParam</a>
<br><br>
<form action="springmvc/testRest/33" method="post">
    <input type="hidden" name="_method" value="put">
    <input type="submit" value="test Put">
</form>
<form action="springmvc/testRest/1" method="post">
    <input type="hidden" name="_method" value="delete">
    <input type="submit" value="test Delete">
</form>

<a href="springmvc/testAntPath/mnxyz/abc">Test AntPath</a>
<br><br>
<a href="springmvc/testParamsAndHeaders?username=atguigu&age=10">Test ParamsAndHeaders</a>
<br><br>
</body>
</html>
