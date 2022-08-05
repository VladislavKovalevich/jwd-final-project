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
<fmt:message key="label.book_filter_panel" var="book_filter_panel"/>
<fmt:message key="label.publishers" var="publishers_label"/>
<fmt:message key="label.genres" var="genres_label"/>
<fmt:message key="label.authors" var="authors_label"/>
<fmt:message key="label.are_available" var="are_available_label"/>
<fmt:message key="button.book_filter" var="filter_btn"/>
<fmt:message key="message.empty_list" var="empty_list_header"/>
<fmt:message key="message.banned_modal_title" var="banned_modal_title"/>
<fmt:message key="message.banned_modal_text" var="banned_modal_message"/>
<fmt:message key="reference.exit" var="exit_ref"/>

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

    <style>
        figure {
            width: 100%;
            height: 400px;
            margin: 0;
        }

        figure img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .truncate {
            padding: 0;
        }

        .truncate p {
            margin: 0;
            -webkit-line-clamp: 3;
            display: -webkit-box;
            -webkit-box-orient: vertical;
            overflow: hidden;
        }
    </style>
</head>
<header>
    <jsp:include page="../header/header.jsp"/>
</header>
<body class="background-theme">
<section class="container-fluid">
    <div class="row m-lg-5 m-5">
        <div class="col-2 white-background">
            <div class="m-3">
                <form method="get" action="${path}/controller">
                    <input type="hidden" name="command" value="show_books_list"/>
                    <input type="hidden" name="book_direction" value="null"/>
                    <h4 class="text-center">${book_filter_panel}</h4>
                    <div class="mt-3">
                        <h5>${authors_label}:</h5>
                        <div class="custom-scroll-container">
                            <c:forEach var="author" items="${authors}">
                                <label for="author${author.id}"></label>
                                <c:choose>
                                    <c:when test="${empty filter_data['author']}">
                                        <input type="checkbox" id="author${author.id}" name="author${author.id}"/>
                                        ${author.name} ${author.surname}<br/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="flag_author" value="0" scope="page"/>
                                        <c:forEach var="authorId" items="${filter_data['author']}">
                                            <c:if test="${(authorId eq author.id) and (flag_author eq 0)}">
                                                <input type="checkbox" id="author${author.id}" name="author${author.id}"
                                                       checked/>
                                                ${author.name} ${author.surname}<br/>
                                                <c:set var="flag_author" value="1" scope="page"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${flag_author eq 0}">
                                            <input type="checkbox" id="author${author.id}" name="author${author.id}"/>
                                            ${author.name} ${author.surname}<br/>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="mt-3">
                        <h5>${genres_label}:</h5>
                        <div class="custom-scroll-container">
                            <c:forEach var="genre" items="${genres}">
                                <label for="genre${genre.id}"></label>
                                <c:choose>
                                    <c:when test="${empty filter_data['genre']}">
                                        <input type="checkbox" id="genre${genre.id}" name="genre${genre.id}"/>
                                        ${genre.name} <br/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="flag_genre" value="0" scope="page"/>
                                        <c:forEach var="genreId" items="${filter_data['genre']}">
                                            <c:if test="${(genreId eq genre.id) and (flag_genre eq 0)}">
                                                <input type="checkbox" id="genre${genre.id}" name="genre${genre.id}"
                                                       checked/>
                                                ${genre.name} <br/>
                                                <c:set var="flag_genre" value="1" scope="page"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${flag_genre eq 0}">
                                            <input type="checkbox" id="genre${genre.id}" name="genre${genre.id}"/>
                                            ${genre.name} <br/>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="mt-3">
                        <h5>${publishers_label}:</h5>
                        <div class="custom-scroll-container">
                            <c:forEach var="publisher" items="${publishers}">
                                <label for="publisher${publisher.id}"></label>
                                <c:choose>
                                    <c:when test="${empty filter_data['publisher']}">
                                        <input type="checkbox" id="publisher${publisher.id}"
                                               name="publisher${publisher.id}"/>
                                        ${publisher.name} <br/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="flag_publisher" value="0" scope="page"/>
                                        <c:forEach var="publisherId" items="${filter_data['publisher']}">
                                            <c:if test="${(publisherId eq publisher.id) and (flag_publisher eq 0)}">
                                                <input type="checkbox" id="publisher${publisher.id}"
                                                       name="publisher${publisher.id}"
                                                       checked/>
                                                ${publisher.name} <br/>
                                                <c:set var="flag_publisher" value="1" scope="page"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${flag_publisher eq 0}">
                                            <input type="checkbox" id="publisher${publisher.id}"
                                                   name="publisher${publisher.id}"/>
                                            ${publisher.name} <br/>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="mt-3">
                        <label for="is_exists">${are_available_label}</label>
                        <c:choose>
                            <c:when test="${empty filter_data['is_exists']}">
                                <input type="checkbox" name="is_exists" id="is_exists"/>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="is_exists" id="is_exists" checked/>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="mt-4">
                        <button class="btn btn-primary" type="submit">${filter_btn}</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-9 mx-4 white-background">
            <c:choose>
                <c:when test="${empty books_list}">
                    <h5 class="text-center mt-2">${empty_list_header}</h5>
                </c:when>
                <c:otherwise>
                    <c:forEach var="book" items="${books_list}">
                        <a class="link-secondary text-decoration-none"
                           href="${path}/controller?command=show_book_info&book_id=${book.id}" data-toggle="tooltip">
                            <div class="row white-background link my-1">
                                <figure class="col-3" style="width: 200px; height: 300px" ;>
                                    <c:choose>
                                        <c:when test="${book.image == null}">
                                            <img src="${path}/img/not_found_image.jpg">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${book.image.encodeImage}">
                                        </c:otherwise>
                                    </c:choose>
                                </figure>
                                <div class="col-6 ">
                                    <div class="card-body">

                                        <h4>${book.title}</h4>
                                        <p class="card-text">${book_author}: ${book.author.name} ${book.author.surname}</p>
                                        <p class="card-text">${book_genre}: ${book.genre.name}</p>
                                        <p class="card-text">${book_publisher}: ${book.publisher.name}</p>
                                        <div class="truncate"><p>${book_description}: ${book.description}</p></div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                    <div class="row">
                        <div class="col-md-3 text-center me-auto">
                            <c:if test="${pagination_data['book_current_page_num'] > 1}">
                                <a href="${path}/controller?command=show_books_list&book_page_direction=prev">${prev}</a>
                            </c:if>
                        </div>
                        <div class="col-md-2 text-center me-auto">
                                ${pagination_data['book_current_page_num']}
                        </div>
                        <div class="col-md-3 text-center me-auto">
                            <c:if test="${(pagination_data['book_current_page_num'] < pagination_data['book_pages_number'])}">
                                <a href="${path}/controller?command=show_books_list&book_page_direction=next">${next}</a>
                            </c:if>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</section>
</body>
<footer>
    <jsp:include page="../footer/footer.jsp"/>
</footer>
<script>
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>
</html>
