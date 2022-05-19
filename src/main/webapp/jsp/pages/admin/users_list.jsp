<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 25-Apr-22
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Title</title>
</head>
<header>
    <jsp:include page="../header/header.jsp"/>
</header>
<body>
    <div class="row">
        <div class="col-2">
        </div>
        <div class="col-10">
            <c:forEach var="user" items="${users_list}">
                <h5>${user.name} ${user.surname}</h5>
                <p>login: ${user.login}</p>
                <p>email: ${user.email}</p>
                <p>phone:
                    <c:choose>
                        <c:when test="${not empty user.mobile_phone or user.mobile_phone eq ''}">
                            ${user.mobile_phone}
                        </c:when>
                        <c:otherwise>
                            not found
                        </c:otherwise>
                    </c:choose>
                </p>
                <a class="btn btn-primary" href="">
            </c:forEach>
        </div>
    </div>
</body>
<footer>
    <jsp:include page="../footer/footer.jsp"/>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</html>
