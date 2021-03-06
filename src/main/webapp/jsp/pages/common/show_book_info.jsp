<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 11-Apr-22
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>

<fmt:message key="title.show_book_info" var="title"/>
<fmt:message key="label.book_title" var="book_title"/>
<fmt:message key="label.book_author" var="book_author"/>
<fmt:message key="label.book_copies_number" var="book_copies_number"/>
<fmt:message key="label.book_description" var="book_description"/>
<fmt:message key="label.book_genre" var="book_genre"/>
<fmt:message key="label.book_pages_count" var="book_pages_count"/>
<fmt:message key="label.book_publish_year" var="book_publish_year"/>
<fmt:message key="label.book_publisher" var="book_publisher"/>
<fmt:message key="button.back_to_main" var="back_btn"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <title>${title}</title>

    <link rel="stylesheet" href="${path}/css/styles.css">
</head>
<header>
    <jsp:include page="../header/header.jsp"/>
</header>
<body class="background-theme">
<section class="container-fluid">
    <div class="container px-4 py-lg-5">
        <div class="row">
            <div class="col-4 white-background">
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
            <div class="col-8 white-background">
                <div class="px-lg-1 py-lg-1">
                    <br/>
                    <h3>${book.title}</h3>
                    <h6>${book_author}: ${book.author.name} ${book.author.surname} </h6>
                    <div class="">
                        <br/>
                        <p class="">${book_copies_number}:
                            <c:choose>
                                <c:when test="${book.copiesNumber > 0}">
                                    ?? ?????????????? ${book.copiesNumber} ??????????(-??)
                                </c:when>
                                <c:otherwise>
                                    ?????? ?? ??????????????
                                </c:otherwise>
                            </c:choose>
                        </p>
                        <p class="">${book_pages_count}: ${book.numberOfPages}</p>
                        <br/>
                        <p class="">${book_genre}: ${book.genre.name}</p>
                        <br/>
                        <p class="">${book_publisher}: ${book.publisher.name}</p>
                        <p class="">${book_publish_year}: ${book.releaseYear}</p>
                        <br/>
                        <p class="">${book_description}: ${book.description}</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="row white-background">
            <c:choose>
                <c:when test="${user_role eq 'ADMIN'}">
                    <div class="col-4 my-2">
                        <a class="btn btn-primary"
                           href="${path}/controller?command=go_to_update_book_data_page&book_id=${book.id}">
                            Update book
                        </a>
                    </div>
                </c:when>
                <c:when test="${user_role eq 'CLIENT'}">
                    <c:if test="${not empty order_operation_feedback}">
                        <div class="green-color">
                            Book was added to new order
                        </div>
                    </c:if>
                    <c:if test="${not empty book_operation_feedback}">
                        <div class="green-color">
                            Book was added existed order
                        </div>
                    </c:if>
                    <c:if test="${book.copiesNumber > 0}">
                        <div class="col-4 my-2">
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                    data-bs-target="#staticBackdrop">
                                Add book to order
                            </button>
                        </div>
                    </c:if>
                    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false"
                         tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="staticBackdropLabel">Choose the order</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <p>Create new order:</p>
                                    <div class="col-12 mt-2">
                                        <form action="${path}/controller" method="post">
                                            <input type="hidden" name="command" value="create_order">
                                            <input type="hidden" name="book_id" value="${book.id}">
                                            <p>
                                                <label for="order_type"></label>
                                                <select id="order_type" name="order_type">
                                                    <c:forEach var="order_type" items="${order_types}">
                                                        <option value="${order_type}">${order_type}</option>
                                                    </c:forEach>
                                                </select>
                                            </p>
                                            <input type="submit" class="btn btn-primary" value="Create order"
                                                   name="sub">
                                        </form>
                                    </div>

                                    <div class="col-12 mt-2">
                                        <c:if test="${not empty user_orders}">
                                            <form action="${path}/controller" method="post">
                                                <input type="hidden" name="book_id" value="${book.id}">
                                                <input type="hidden" name="command" value="add_book_to_order">
                                                <label for="orders">Or choose the exists order:</label>
                                                <p><select id="orders" name="order_id">
                                                    <c:forEach var="order" items="${user_orders}">
                                                        <c:if test="${order.status eq 'CREATED'}">
                                                            <option value="${order.id}">${order.id} ${order.createdDate} ${order.type}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                                </p>
                                                <button type="submit" class="btn btn-primary">Add to order</button>
                                            </form>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
            </c:choose>

            <hr/>

            <form method="get" action="${path}/controller">
                <input type="hidden" name="command" value="go_to_main_page">
                <div class="col-12">
                    <button class="btn btn-primary" type="submit">${back_btn}</button>
                </div>
            </form>

        </div>
    </div>
</section>
</body>
<footer>
    <jsp:include page="../footer/footer.jsp"/>
</footer>
</html>
