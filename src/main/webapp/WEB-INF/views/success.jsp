<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/10/27
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Success Page</title>
</head>
<body>
<h2>SUCCESS PAGE</h2>
<p>
<fmt:message key="i18n.username"></fmt:message>
</p><p>
<fmt:message key="i18n.password"></fmt:message>
</p>
<p>${requestScope.date}</p>
<p>request user: ${requestScope.user}</p>
<p>session user: ${sessionScope.user}</p>
<p>session name: ${sessionScope.name}</p>

<br><br>
</body>
</html>
