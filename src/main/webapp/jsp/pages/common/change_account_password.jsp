<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 13-Apr-22
  Time: 03:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change account password</title>
</head>
<body>
Данные аккаунта:
<form action="controller">
    <input type="hidden" name="command" value="change_account_password">
    <br/>
    Name: ${user_name}
    <br/>
    Surname: ${user_surname}
    <br/>
    Email: ${user_email}
    <br/>
    <br/>
    Old password:
    <input type="password" name="pass" value="">
    <br/>
    <br/>
    New password:
    <input type="password" name="new_pass" value="">
    <br/>
    <br/>
    Repeat new password:
    <input type="password" name="repeat_new_pass" value="">
    <br/>
    <input type="submit" name="update_pass" value="update password">
</form>
</body>
</html>
