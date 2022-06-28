<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 07-Jun-22
  Time: 19:20
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

    <title>Title</title><!--  -->
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
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-10 m-2">
                        <h2>Order #${order.id}</h2><!--  -->
                    </div>
                    <div class="col-1"></div>
                </div>

                <c:if test="${user_role eq 'ADMIN'}">
                    <hr/>

                    <div class="row">
                        <div class="col-1"></div>
                        <div class="col-10 m-2">
                            <h4>User login: ${order.user.login}</h4><!--  -->
                            <h4>User name: ${order.user.name} ${order.user.surname}</h4><!--  -->
                        </div>
                        <div class="col-1"></div>
                    </div>

                </c:if>

                <hr/>

                <div class="row">
                    <div class="col-1"></div>
                    <div class="col-10 m-2">
                        <h3>Books list:</h3><!--  -->
                    </div>
                    <div class="col-1"></div>
                </div>

                <c:choose>
                    <c:when test="${not empty order_books}">
                        <c:forEach var="book" items="${order_books}">
                            <div class="row">
                                <div class="col-1"></div>
                                <div class="col-10 m-2" style="background-color: aliceblue; border-color: #333333">
                                    <div class="row">
                                        <div class="col-2 white-background">
                                            <div class="">
                                                <c:choose>
                                                    <c:when test="${not empty book.image.encodeImage}">
                                                        <img src="${book.image.encodeImage}" class="img-thumbnail">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="${path}/img/not_found_image.jpg" class="img-thumbnail">
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                        <div class="col-9">
                                            <h4 class="py-1">Title: ${book.title}</h4><!--  -->
                                            <p class="">Author: ${book.author.name} ${book.author.surname}</p><!--  -->
                                            <p class="">Publisher: ${book.publisher.name}</p><!--  -->
                                            <p class="">Genre: ${book.genre.name}</p><!--  -->
                                        </div>
                                        <div class="col-3">
                                            <p>
                                                <c:if test="${order.status eq 'CREATED' && user_role eq 'CLIENT'}">
                                                    <a href="${path}/controller?command=remove_book_from_order&order_id=${order.id}&book_id=${book.id}"
                                                       class="btn btn-primary my-2">Remove book</a><!--  -->
                                                </c:if>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-1"></div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="col-1"></div>
                        <div class="col-10 text-center"><h3>order is empty</h3></div><!--  -->
                        <div class="col-1"></div>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${user_role eq 'CLIENT'}">
                        <c:if test="${order.status eq 'CREATED'}">
                            <hr/>
                            <div class="col-2 my-2">
                                <a class="btn btn-primary"
                                   href="${path}/controller?command=reserve_order&order_id=${order.id}">Reserve
                                    order</a><!--  -->
                            </div>
                        </c:if>
                        <c:if test="${order.status eq 'ORDERED'}">
                            <hr/>
                            <div class="col-2 my-2">
                                <a class="btn btn-primary"
                                   href="${path}/controller?command=return_order&order_id=${order.id}">Return
                                    order</a><!--  -->
                            </div>
                        </c:if>
                        <c:if test="${order.status eq 'RESERVED'}">
                            <hr/>
                            <div class="col-2 my-2">
                                Ожидание подтверждения заказа администратором
                            </div>
                        </c:if>
                        <c:if test="${order.status eq 'REJECTED'}">
                            <hr/>
                            <div class="col-2 my-2">
                                Заказ был отклонен администратором
                            </div>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${order.status eq 'RESERVED'}">
                            <hr/>
                            <div class="col-2 my-2 mx-3">
                                <a class="btn btn-primary"
                                   href="${path}/controller?command=accept_order&order_id=${order.id}">Accept order</a><!--  -->
                            </div>
                            <div class="col-2 my-2 mx-1">
                                <a class="btn btn-danger"
                                   href="${path}/controller?command=reject_order&order_id=${order.id}">Reject order</a><!--  -->
                            </div>
                        </c:if>
                    </c:otherwise>
                </c:choose>

                <hr/>

                <form method="get" action="${path}/controller">
                    <input type="hidden" name="command" value="go_to_order_list_page">
                    <div class="col-12">
                        <button class="btn btn-primary" type="submit">Back</button>
                    </div>
                </form>
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