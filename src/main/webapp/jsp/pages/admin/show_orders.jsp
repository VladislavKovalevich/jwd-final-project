<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 17-Jun-22
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <title>title</title>

    <link rel="stylesheet" href="${path}/css/styles.css">
</head>
<header>
    <jsp:include page="../header/header.jsp"/>
</header>
<body class="background-theme">
<section class="container-fluid">
    <div class="row m-lg-5 m-5">
        <div class="col-12 white-background">
            <c:choose>
                <c:when test="${empty orders}">
                    <h5 class="text-center">Пусто</h5>
                </c:when>
                <c:otherwise>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Order id</th>
                            <th scope="col">Name</th>
                            <th scope="col">Type</th>
                            <th scope="col">Status</th>
                            <th scope="col">Created</th>
                            <th scope="col">Reserved</th>
                            <th scope="col">Ordered</th>
                            <th scope="col">Rejected</th>
                            <th scope="col">Returned</th>
                            <th scope="col">Action</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <th scope="row">${order.id}</th>
                                <td>${order.user.name} ${order.user.surname}</td>
                                <td>${order.type}</td>
                                <td>${order.status}</td>
                                <td>${order.createdDate}</td>

                                <c:choose>
                                    <c:when test="${not empty order.reservedDate}">
                                        <td>${order.reservedDate}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>none</td>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${not empty order.orderedDate}">
                                        <td>${order.orderedDate}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>none</td>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${not empty order.rejectedDate}">
                                        <td>${order.rejectedDate}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>none</td>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${not empty order.returnedDate}">
                                        <td>${order.returnedDate}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>none</td>
                                    </c:otherwise>
                                </c:choose>

                                <td>
                                    <form method="get" action="${path}/controller">
                                        <input type="hidden" name="order" value="${order.id},${order.status}">
                                        <input type="hidden" name="command" value="get_books_by_order_id">
                                        <input type="submit" class="btn btn-primary" value="More">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</section>
</body>
<footer>
    <jsp:include page="../footer/footer.jsp"/>
</footer>
</html>
