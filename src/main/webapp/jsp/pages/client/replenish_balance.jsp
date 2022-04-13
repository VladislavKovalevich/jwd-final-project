<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 13-Apr-22
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="controller">
    <input type="hidden" name="command" value="replenish_balance">
    <br/>
    Name: ${user_name}
    <br/>
    Surname: ${user_surname}
    <br/>
    Email: ${user_email}
    <br/>
    Current balance: ${user_balance}
</form>
</body>
</html>
