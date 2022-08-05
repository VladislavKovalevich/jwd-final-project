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
<fmt:message key="button.update_book" var="update_book"/>
<fmt:message key="message.available" var="available_msg"/>
<fmt:message key="message.not_available" var="not_available_msg"/>
<fmt:message key="message.copies" var="copies_msg"/>
<fmt:message key="button.create_order" var="create_order"/>
<fmt:message key="button.add_book_to_order" var="add_book_to_order"/>
<fmt:message key="label.create_new_order" var="create_new_order"/>
<fmt:message key="label.order_type" var="order_type_label"/>
<fmt:message key="label.choose_order" var="choose_order"/>
<fmt:message key="label.choose_exists_order" var="choose_exists_order"/>
<fmt:message key="label.book_was_added_existed_order" var="book_was_added_existed_order"/>
<fmt:message key="label.book_was_added_new_order" var="book_was_added_new_order"/>
<fmt:message key="message.book_is_already_in_order" var="book_is_already_in_order"/>
<fmt:message key="message.book_limit_in_order" var="book_limit_in_order_msg"/>
<fmt:message key="message.orders_limit" var="orders_limit_msg"/>

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
    <script src="${path}/js/script.js"></script>

    <style>
        figure {
            width: 100%; /* Ширина области */
            height: 100%; /* Высота области */
            margin: 0; /* Обнуляем отступы */
        }

        figure img {
            width: 100%; /* Ширина изображений */
            height: 100%; /* Высота изображении */
            object-fit: cover; /* Вписываем фотографию в область */
        }
    </style>
</head>
<header>
    <jsp:include page="../header/header.jsp"/>
</header>
<body class="background-theme">
<section class="container-fluid">
    <div class="container px-4 my-lg-5 white-background">
        <div class="row mb-2">
            <div class="col-4 white-background">
                <div class="">
                    <figure>
                        <c:choose>
                            <c:when test="${not empty book.image.encodeImage}">
                                <img src="${book.image.encodeImage}">
                            </c:when>
                            <c:otherwise>
                                <img src="${path}/img/not_found_image.jpg">
                            </c:otherwise>
                        </c:choose>
                    </figure>
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
                                    ${available_msg} ${book.copiesNumber} ${copies_msg}
                                </c:when>
                                <c:otherwise>
                                    ${not_available_msg}
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
                        <div class="">${book_description}: ${book.description}</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <c:choose>
                <c:when test="${user_role eq 'ADMIN'}">
                    <div class="col-4 my-2">
                        <a class="btn btn-primary"
                           href="${path}/controller?command=go_to_update_book_data_page&book_id=${book.id}">
                                ${update_book}
                        </a>
                    </div>
                </c:when>
                <c:when test="${user_role eq 'CLIENT'}">
                    <c:if test="${not empty operation_feedback_map_ses['order_operation_feedback']}">
                        <div class="green-color">
                                ${book_was_added_new_order}
                        </div>
                    </c:if>
                    <c:if test="${not empty operation_feedback_map_ses['book_operation_feedback']}">
                        <div class="green-color">
                                ${book_was_added_existed_order}
                        </div>
                    </c:if>
                    <c:if test="${not empty operation_feedback_map_ses['book_is_already_exists']}">
                        <div class="red-color col-12 mt-2">
                                ${book_is_already_in_order}
                        </div>
                    </c:if>
                    <c:if test="${not empty operation_feedback_map_ses['book_limit_in_order']}">
                        <div class="red-color col-12 mt-2">
                                ${book_limit_in_order_msg}
                        </div>
                    </c:if>
                    <c:if test="${not empty operation_feedback_map_ses['orders_limit']}">
                        <div class="red-color col-12 mt-2">
                                ${orders_limit_msg}
                        </div>
                    </c:if>
                    <c:if test="${book.copiesNumber > 0}">
                        <div class="col-4 my-2">
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                    data-bs-target="#staticBackdrop">
                                    ${add_book_to_order}
                            </button>
                        </div>
                    </c:if>
                    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false"
                         tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="staticBackdropLabel">${choose_order}</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="card">
                                        <p class="card-header">${create_new_order}:</p>
                                        <div class="col-12 mt-2 card-text ms-2">
                                            <form action="${path}/controller" method="post">
                                                <input type="hidden" name="command" value="create_order">
                                                <input type="hidden" name="book_id" value="${book.id}">
                                                <p>
                                                    <label for="order_type">${order_type_label}:</label>
                                                </p>
                                                <p>
                                                    <select id="order_type" name="order_type">
                                                        <c:forEach var="order_type" items="${order_types}">
                                                            <option value="${order_type}">${order_type}</option>
                                                        </c:forEach>
                                                    </select>
                                                </p>
                                                <input type="submit" class="btn btn-primary" value=${create_order}
                                                        name="sub">
                                            </form>
                                        </div>
                                    </div>

                                    <c:if test="${not empty user_orders}">
                                        <hr/>
                                        <p>or</p>
                                        <hr/>
                                        <div class="card">
                                            <label class="card-header" for="orders">${choose_exists_order}:</label>
                                            <div class="col-12 mt-2 card-text ms-3">
                                                <form action="${path}/controller" method="post">
                                                    <input type="hidden" name="book_id" value="${book.id}">
                                                    <input type="hidden" name="command" value="add_book_to_order">
                                                    <p><select id="orders" name="order_id">
                                                        <c:forEach var="order" items="${user_orders}">
                                                            <c:if test="${order.status eq 'CREATED'}">
                                                                <option value="${order.id}">${order.id} ${order.createdDate} ${order.type}</option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </select>
                                                    </p>
                                                    <button type="submit"
                                                            class="btn btn-primary">${add_book_to_order}</button>
                                                </form>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">${back_btn}
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
