<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>

<fmt:message key="title.show_books_list" var="title"/>
<fmt:message key="label.book_title" var="book_title"/>
<fmt:message key="label.book_author" var="book_author"/>
<fmt:message key="label.book_copies_number" var="book_copies_number"/>
<fmt:message key="label.book_description" var="book_description"/>
<fmt:message key="label.book_genre" var="book_genre"/>
<fmt:message key="label.book_pages_count" var="book_pages_count"/>
<fmt:message key="label.book_publish_year" var="book_publish_year"/>
<fmt:message key="label.book_publisher" var="book_publisher"/>
<fmt:message key="reference.next_page" var="next"/>
<fmt:message key="reference.prev_page" var="prev"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>${title}</title>
    <style>
        figure {
            width: 100%; /* Ширина области */
            height: 400px; /* Высота области */
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
<body>
<div class="row">
    <div class="col-2">
        <div>
            Панель фильтров
        </div>
    </div>
    <div class="col-10">
        <c:choose>
            <c:when test="${empty books_list}">
                ${not_found_msg}
            </c:when>
            <c:otherwise>
                <div class="card mb-3 px-lg-1 py-lg-1">
                    <c:forEach var="book" items="${books_list}">
                        <div class="row g-0 py-lg-3">
                            <figure class="col-md-3">
                                <img src="${path}/img/not_found_image.jpg"/>
                            </figure>
                            <div class="col-md-6">
                                <div class="card-body">
                                    <a class=" card-title link-secondary text-decoration-none"
                                       href="${path}/controller?command=show_book_info&book_id=${book.id}">
                                            ${book.id}. ${book.title}
                                    </a>
                                    <p class="card-text">${book_author}: ${book.author.name} ${book.author.surname}</p>
                                    <p class="card-text">${book_genre}: ${book.genre.name}</p>
                                    <p class="card-text">${book_publisher}: ${book.publisher.name}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>

        <div class="row">
            <div class="col-md-3">
                <c:if test="${pagination_data['current_page_num'] > 1}">
                    <a href="${path}/controller?command=show_books_list&page_direction=prev">${prev}</a>
                </c:if>
            </div>
            <div class="col-md-2">
                ${pagination_data['current_page_num']}
            </div>
            <div class="col-md-3">
                <c:if test="${pagination_data['current_page_num'] < pagination_data['pages_number']}">
                    <a href="${path}/controller?command=show_books_list&page_direction=next">${next}</a>
                </c:if>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
<footer>
    <jsp:include page="../footer/footer.jsp"/>
</footer>
</html>
