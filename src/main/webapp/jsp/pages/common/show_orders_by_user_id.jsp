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

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>

<fmt:message key="label.order" var="order_label"/>
<fmt:message key="label.order_type" var="order_type"/>
<fmt:message key="label.order_status" var="order_status"/>
<fmt:message key="label.created_date" var="create_date"/>

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
    <script src="${path}/js/script.js"></script>

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
                        <a class="link-secondary text-decoration-none"
                           href="${path}/controller?command=get_books_by_order_id&order_id=${order.id}"
                           data-toggle="tooltip">
                            <div class="row link" style="background-color:
                            <c:choose>
                            <c:when test="${order.status eq 'CREATED'}">
                                    #ede8a6
                            </c:when>
                            <c:when test="${order.status eq 'RESERVED'}">
                                    #84d194
                            </c:when>
                            <c:when test="${order.status eq 'REJECTED'}">
                                    #f09792
                            </c:when>
                            <c:when test="${order.status eq 'ACCEPTED'}">
                                    #a1aaed
                            </c:when>
                            <c:when test="${order.status eq 'OVERDUE'}">
                                    #eeeeee
                            </c:when>
                                    </c:choose>">
                                <div class="col-12">
                                    <h4>${order_label} #${order.id}</h4>
                                    <div class="my-2 border-top border-bottom">
                                        <p>${create_date}: ${order.createdDate}</p>
                                        <p>${order_type}: ${order.type}</p>
                                        <p>${order_status}: ${order.status}</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div class="col-1 my-1"></div>
                </c:forEach>
            </div>
        </div>
        <div class="col-2"></div>
    </div>
</section>
</body>
<script>
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>
<footer>
    <jsp:include page="../footer/footer.jsp"/>
</footer>
</html>
