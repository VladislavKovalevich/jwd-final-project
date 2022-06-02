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

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>


<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <title>Title</title>

    <link rel="stylesheet" href="${path}/css/styles.css">
</head>
<header>
    <jsp:include page="../header/header.jsp"/>
</header>
<body class="background-theme">
<section class="container-fluid">
    <div class="row m-lg-5 m-5">
        <div class="col-3"></div>
        <div class="col-9 container-fluid">
            <div class="row">
                <div class="col-10 container-fluid">
                    <c:forEach var="user" items="${users_list}">
                        <div class="row my-4 mx-2 white-background">
                            <div class="col-9">
                                <h5>${user.name} ${user.surname}</h5>
                                <p>login: ${user.login}</p>
                                <p>email: ${user.email}</p>
                                <p>phone:
                                    <c:choose>
                                        <c:when test="${not empty user.mobilePhone or user.mobilePhone eq ''}">
                                            ${user.mobilePhone}
                                        </c:when>
                                        <c:otherwise>
                                            not found
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                            <div class="col-3">
                                <p class="py-3">
                                <c:choose>
                                    <c:when test="${user.banned}">
                                        <a class="btn btn-primary" href="${path}/controller?command=change_user_status&user_id=${user.email}&user_status=false">Unblock</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn btn-danger" href="${path}/controller?command=change_user_status&user_id=${user.email}&user_status=true">Block</a>
                                    </c:otherwise>
                                </c:choose>
                                </p>
                                <p class="py-1">
                                <a class="btn btn-primary" href="#">Get all orders</a>
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
<footer>
    <jsp:include page="../footer/footer.jsp"/>
</footer>
</html>
