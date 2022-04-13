<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 12-Apr-22
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="controller">
        <input type="hidden" name="command" value="create_new_account"/>
        <br/>
        <br/>
        Name:
        <input type="text" name="new_name" value="">
        <br/>
        <br/>
        Surname:
        <input type="text" name="new_surname" value="">
        <br/>
        <br/>
        Email:
        <input type="text" name="new_email" value="">
        <br/>
        <br/>
        Password:
        <input type="password" name="new_password" value="">
        <br/>
        <br/>
        Repeat Password
        <input type="password" name="repeated_pass" value="">

        <input type="submit" name="create_account" value="Create new Account">
    </form>
    ${error_msg}
</body>
</html>
