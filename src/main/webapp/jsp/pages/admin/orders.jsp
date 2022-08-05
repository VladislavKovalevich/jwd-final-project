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

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>

<fmt:message key="title.show_orders" var="title"/>
<fmt:message key="message.empty_list" var="empty_msg"/>
<fmt:message key="label.order_id" var="order_id"/>
<fmt:message key="label.user_name" var="name"/>
<fmt:message key="label.order_type" var="type"/>
<fmt:message key="label.order_status" var="status"/>
<fmt:message key="button.more_button" var="more_btn"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <title>${title}</title>

    <link rel="stylesheet" href="${path}/css/styles.css">
    <script src="${path}/js/script.js"></script>
</head>
<header>
    <jsp:include page="../header/header.jsp"/>
</header>
<body class="background-theme">
<section class="container-fluid">
    <div class="row mx-lg-5 my-lg-5">
        <div class="col-3 mx-2">
            <jsp:include page="../fragment/navigation_account.jsp"/>
        </div>
        <div class="col-8 mx-2 white-background">
            <c:choose>
                <c:when test="${empty orders}">
                    <h5 class="text-center my-2">${empty_msg}</h5>
                </c:when>
                <c:otherwise>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">${order_id}</th>
                            <th scope="col">${name}</th>
                            <th scope="col">${type}</th>
                            <th scope="col">${status}</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <th scope="row">${order.id}</th>
                                <td>${order.user.name} ${order.user.surname}</td>
                                <td>${order.type}</td>
                                <td>${order.status}</td>

                                <td>
                                    <form method="get" action="${path}/controller">
                                        <input type="hidden" name="order_id" value="${order.id}">
                                        <input type="hidden" name="command" value="get_books_by_order_id">
                                        <input type="submit" class="btn btn-primary" value="${more_btn}">
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
