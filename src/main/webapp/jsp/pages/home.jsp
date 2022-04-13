<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 05-Apr-22
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Title</title>
</head>
<header>
    <jsp:include page="header/header.jsp"/>
</header>
<body>
    <hr>
    <div>
        Hello: ${user_name} ${user_surname}
        <br/>
        Email: ${user_email}
        <br/>
        Role: ${user_role}
    </div>
    <hr>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <!-- <a href="controller?command=Logout">Logout</a>-->
</body>
<footer>
    <jsp:include page="footer/footer.jsp"/>
</footer>
</html>
