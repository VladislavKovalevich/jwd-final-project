<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 25-May-22
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="${path}/css/styles.css">

    <title>Title</title>
</head>
<header>
    <jsp:include page="../header/header.jsp"/>
</header>
<body class="background-theme">
<section class="container-fluid">
    <div class="row m-lg-5 m-5">
        <div class="col-2"></div>
        <div class="col-8">
            <div class="row" style="background-color: aliceblue">
                <c:forEach var="order" items="${orders}">
                    <div class="col-1 my-1"></div>
                    <div class="col-10 my-1" style="background-color: #c7b39b; border-color: #333333">
                        <div class="row">
                            <div class="col-9">
                                <p>Order #${order.id}</p>
                                <p>Create date: ${order.createdDate}</p>
                                <p>Order type: ${order.type}</p>
                                <p>Order status: ${order.status}</p>
                            </div>
                            <div class="col-3">
                                <p class="mt-3">
                                    <a href="${path}/controller?command=get_books_by_order_id&order_id=${order.id}"
                                       class="btn btn-primary my-2">Get books list</a>
                                </p>
                                <p class="mb-2">
                                    <c:if test="${order.status eq 'CREATED'}">
                                        <a href="${path}/controller?command=delete_order&order_id=${order.id}"
                                           class=" btn btn-primary my-2">Delete</a>
                                    </c:if>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-1 my-1"></div>
                </c:forEach>
            </div>
        </div>
        <div class="col-2"></div>
    </div>
</section>
</body>
<footer>
    <jsp:include page="../footer/footer.jsp"/>
</footer>
</html>
