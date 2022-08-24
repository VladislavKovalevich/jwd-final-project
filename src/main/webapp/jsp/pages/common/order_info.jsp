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
<%@ page import="by.vlad.library.entity.OrderStatus" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>

<fmt:message key="title.order_info" var="title"/>
<fmt:message key="label.order" var="order_label"/>
<fmt:message key="label.user_name" var="name"/>
<fmt:message key="label.order_type" var="type"/>
<fmt:message key="label.order_status" var="status"/>
<fmt:message key="button.more_button" var="more_btn"/>
<fmt:message key="label.created_date" var="create_date"/>
<fmt:message key="label.reserved_date" var="reserved_date"/>
<fmt:message key="label.ordered_date" var="ordered_date"/>
<fmt:message key="label.rejected_date" var="rejected_date"/>
<fmt:message key="label.returned_date" var="returned_date"/>
<fmt:message key="label.estimated_return_date" var="estimated_return_date"/>
<fmt:message key="message.none" var="none"/>
<fmt:message key="message.user_login" var="user_login"/>
<fmt:message key="message.user_name" var="user_name"/>
<fmt:message key="message.books_list" var="book_list"/>
<fmt:message key="button.back_to_main" var="back_btn"/>
<fmt:message key="button.reserve_order" var="reserve_order"/>
<fmt:message key="button.reject_order" var="reject_order"/>
<fmt:message key="button.accept_order" var="accept_order"/>
<fmt:message key="button.return_order" var="return_order"/>
<fmt:message key="button.delete_order" var="delete_order"/>
<fmt:message key="button.remove_book" var="remove_book"/>
<fmt:message key="message.empty_order" var="empty_order"/>
<fmt:message key="label.book_title" var="book_title"/>
<fmt:message key="label.book_author" var="book_author"/>
<fmt:message key="label.book_publisher" var="book_publisher"/>
<fmt:message key="label.book_genre" var="book_genre"/>
<fmt:message key="message.wait_order" var="wait_order"/>
<fmt:message key="message.reject_order_by_admin" var="reject_order_by_admin"/>
<fmt:message key="message.wrong_book_copies_number" var="wrong_book_copies_message"/>
<fmt:message key="message.banned_modal_title" var="banned_modal_title"/>
<fmt:message key="message.banned_modal_text" var="banned_modal_message"/>
<fmt:message key="reference.exit" var="exit_ref"/>


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

    <title>${title}</title>
</head>
<header>
    <jsp:include page="../header/header.jsp"/>
</header>
<body class="background-theme">
<section class="container-fluid">
    <c:if test="${return_book_banned_flag == true}">
        <div class="modal__custom modal__active">
            <div class="modal__content">
                <h3 class="modal__title">${banned_modal_title}</h3>
                <p class="modal__description">${banned_modal_message}</p>
                <a href="${path}/controller?command=logout">${exit_ref}</a>
            </div>
        </div>
    </c:if>
    <div class="row m-lg-5 m-5">
        <div class="col-2"></div>
        <div class="col-8">
            <div class="row" style="background-color: aliceblue">
                <div class="col-1"></div>
                <div class="col-10">
                    <div class="card mt-2">
                        <h2 class="card-header">${order_label} #${order.id}</h2><!--  -->
                        <div class="my-2 card-text px-2">
                            <c:if test="${user_role eq 'CLIENT'}">
                                <p>${ordered_date}:
                                    <c:choose>
                                        <c:when test="${not empty order.acceptedDate}">
                                            ${order.acceptedDate}
                                        </c:when>
                                        <c:otherwise>
                                            ${none}
                                        </c:otherwise>
                                    </c:choose>
                                </p>

                                <p>${rejected_date}:
                                    <c:choose>
                                        <c:when test="${not empty order.rejectedDate}">
                                            ${order.rejectedDate}
                                        </c:when>
                                        <c:otherwise>
                                            ${none}
                                        </c:otherwise>
                                    </c:choose>
                                </p>

                                <p>${estimated_return_date}:
                                    <c:choose>
                                        <c:when test="${not empty order.estimatedReturnDate}">
                                            ${order.estimatedReturnDate}
                                        </c:when>
                                        <c:otherwise>
                                            ${none}
                                        </c:otherwise>
                                    </c:choose>
                                </p>

                                <p>${returned_date}:
                                    <c:choose>
                                        <c:when test="${not empty order.returnedDate}">
                                            ${order.returnedDate}
                                        </c:when>
                                        <c:otherwise>
                                            ${none}
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </c:if>

                            <c:if test="${user_role eq 'ADMIN'}">
                                <p>${create_date}: ${order.createdDate}</p>

                                <p>${reserved_date}:
                                    <c:choose>
                                        <c:when test="${not empty order.reservedDate}">
                                            ${order.reservedDate}
                                        </c:when>
                                        <c:otherwise>
                                            ${none}
                                        </c:otherwise>
                                    </c:choose>
                                </p>

                                <p>${ordered_date}:
                                    <c:choose>
                                        <c:when test="${not empty order.acceptedDate}">
                                            ${order.acceptedDate}
                                        </c:when>
                                        <c:otherwise>
                                            ${none}
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                                <p>${rejected_date}:
                                    <c:choose>
                                        <c:when test="${not empty order.rejectedDate}">
                                            ${order.rejectedDate}
                                        </c:when>
                                        <c:otherwise>
                                            ${none}
                                        </c:otherwise>
                                    </c:choose>
                                </p>

                                <p>${estimated_return_date}:
                                    <c:choose>
                                        <c:when test="${not empty order.estimatedReturnDate}">
                                            ${order.estimatedReturnDate}
                                        </c:when>
                                        <c:otherwise>
                                            ${none}
                                        </c:otherwise>
                                    </c:choose>
                                </p>

                                <p>${returned_date}:
                                    <c:choose>
                                        <c:when test="${not empty order.returnedDate}">
                                            ${order.returnedDate}
                                        </c:when>
                                        <c:otherwise>
                                            ${none}
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </c:if>
                        </div>
                    </div>

                    <c:if test="${user_role eq 'ADMIN'}">
                        <div class="card my-2">
                            <h2 class="card-header">User info</h2>
                            <div class="card-text mx-1">
                                <p>${user_login}: ${order.user.login}</p><!--  -->
                                <p>${user_name}: ${order.user.name} ${order.user.surname}</p><!--  -->
                            </div>
                        </div>
                    </c:if>

                    <hr/>

                    <div class="card my-2">
                        <h3 class="card-header">${book_list}:</h3><!--  -->

                        <div class="card-text">
                            <c:choose>
                                <c:when test="${not empty order_books}">
                                    <c:forEach var="book" items="${order_books}">
                                        <div class="row ms-1 my-1">
                                            <div class="col-2">
                                                <div>
                                                    <c:choose>
                                                        <c:when test="${not empty book.image.encodeImage}">
                                                            <img src="${book.image.encodeImage}" class="img-thumbnail">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img src="${path}/img/not_found_image.jpg"
                                                                 class="img-thumbnail">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>
                                            <div class="col-9">
                                                <h4 class="py-1">${book_title}: ${book.title}</h4><!--  -->
                                                <p> ${book_author}: ${book.author.name} ${book.author.surname}</p>
                                                <!--  -->
                                                <p>${book_publisher}: ${book.publisher.name}</p><!--  -->
                                                <p>${book_genre}: ${book.genre.name}</p><!--  -->
                                            </div>
                                            <div class="col-3">
                                                <p class="ps-2">
                                                    <c:if test="${order.status eq 'CREATED' && user_role eq 'CLIENT'}">
                                                        <a href="${path}/controller?command=remove_book_from_order&book_id=${book.id}"
                                                           class="btn btn-primary my-2">${remove_book}</a><!-- -->
                                                    </c:if>
                                                </p>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="card-text text-center"><h3>${empty_order}</h3></div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <!-- buttons-->
                    <div class="row">
                        <c:choose>
                            <c:when test="${user_role eq 'CLIENT'}">
                                <c:if test="${order.status eq 'CREATED' && not empty order_books}">
                                    <hr/>
                                    <div class="col-2 my-1">
                                        <a class="btn btn-primary"
                                           href="${path}/controller?command=change_order_status&order_status=${OrderStatus.RESERVED}">${reserve_order}</a>
                                        <!--  -->
                                    </div>
                                </c:if>
                                <c:if test="${order.status eq 'ACCEPTED'}">
                                    <hr/>
                                    <div class="col-2 my-2">
                                        <a class="btn btn-primary"
                                           href="${path}/controller?command=return_order">${return_order}</a>
                                        <!--  -->
                                    </div>
                                </c:if>
                                <c:if test="${order.status eq 'RESERVED'}">
                                    <hr/>
                                    <div class="col-2 my-2">
                                            ${wait_order}
                                    </div>
                                </c:if>
                                <c:if test="${order.status eq 'REJECTED'}">
                                    <hr/>
                                    <div class="col-2 my-2">
                                            ${reject_order_by_admin}
                                    </div>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${order.status eq 'RESERVED'}">
                                    <hr/>
                                    <c:if test="${not empty wrong_book_copies_count}">
                                        <div class="col-12">
                                                ${wrong_book_copies_message}
                                        </div>
                                    </c:if>
                                    <div class="col-2 my-2 mx-3">
                                        <a class="btn btn-primary"
                                           href="${path}/controller?command=accept_order">${accept_order}</a>
                                    </div>
                                    <div class="col-2 my-2 mx-1">
                                        <a class="btn btn-danger"
                                           href="${path}/controller?command=change_order_status&order_status=${OrderStatus.REJECTED}">${reject_order}</a>
                                    </div>
                                </c:if>
                            </c:otherwise>
                        </c:choose>

                        <hr/>

                        <div class="col-auto mr-auto">
                            <form method="get" action="${path}/controller">
                                <input type="hidden" name="command" value="go_to_order_list_page">
                                <button class="btn btn-primary" type="submit">${back_btn}</button>
                            </form>
                        </div>
                        <div class="col-auto">
                            <c:if test="${order.status eq 'CREATED' && user_role eq 'CLIENT'}">
                                <a href="${path}/controller?command=delete_order&order_id=${order.id}"
                                   class="btn btn-danger">${delete_order}</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-1"></div>
        </div>
        <div class="col-2"></div>
    </div>
</section>
</body>
<footer>
    <jsp:include page="../footer/footer.jsp"/>
</footer>
</html>