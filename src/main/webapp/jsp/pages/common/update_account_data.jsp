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
    <title>Title</title>
</head>
<body>
    Данные аккаунта:
    <form action="controller">
        <input type="hidden" name="command" value="update_user_account_data">
        <br/>
        Имя:
        <input type="text" name="update_name" value="${user_name}">
        <br/>
        <br/>
        Фамилия:
        <input type="text" name="update_surname" value="${user_surname}">
        <br/>
        <br/>
        Email:
        <input type="text" name="update_email" value="${user_email}">
        <br/>
        <br/>
        <input type="submit" name="update" value="update">
    </form>
</body>
</html>
