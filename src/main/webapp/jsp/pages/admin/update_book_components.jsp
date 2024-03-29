<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 10-May-22
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>

<fmt:message key="title.update_book_components" var="title"/>
<fmt:message key="label.author_name" var="author_name"/>
<fmt:message key="label.author_surname" var="author_surname"/>
<fmt:message key="label.publisher_name" var="publisher_name"/>
<fmt:message key="label.genre_name" var="genre_name"/>
<fmt:message key="label.book_author" var="type"/>
<fmt:message key="label.book_publisher" var="publisher"/>
<fmt:message key="label.book_genre" var="status"/>
<fmt:message key="message.incorrect_data_format" var="incorrect_data_format"/>
<fmt:message key="message.incorrect_author" var="incorrect_author"/>
<fmt:message key="message.incorrect_publisher" var="incorrect_publisher"/>
<fmt:message key="message.incorrect_genre" var="incorrect_genre"/>
<fmt:message key="message.author_has_been_updated" var="author_has_been_update"/>
<fmt:message key="message.publisher_has_been_updated" var="publisher_has_been_update"/>
<fmt:message key="message.genre_has_been_updated" var="genre_has_been_update"/>
<fmt:message key="button.update_author" var="update_author_btn"/>
<fmt:message key="button.update_publisher" var="update_publisher_btn"/>
<fmt:message key="button.update_genre" var="update_genre_btn"/>
<fmt:message key="button.back_to_main" var="back_btn"/>
<fmt:message key="label.select_book_author" var="select_author"/>
<fmt:message key="label.select_book_publisher" var="select_publisher"/>
<fmt:message key="label.select_book_genre" var="select_genre"/>

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
</head>
<body class="background-theme">
<section class="container-fluid">
    <div class="row mb-4 py-lg-5"></div>
    <div class="row py-lg-5">
        <div class="col mb-2"></div>

        <div class="col-6 mb-3 py-3 px-3 white-background">
            <h3 class="py-md-3 card-header">${title}</h3>

            <div class="card mt-2">
                <h5 class="card-header">${type}</h5>
                <div class="card-text my-2 ms-1">
                    <form method="post" action="${path}/controller" class="row g-3 needs-validation" novalidate>

                        <input type="hidden" name="command" value="update_author">

                        <div class="col-md-12">
                            <p class="form-label">${select_author}:</p>
                            <select name="author" id="author"
                                    onchange="updateAuthorFields('author', 'author_name', 'author_surname')">
                                <c:forEach var="a" items="${authors}">
                                    <c:choose>
                                        <c:when test="${a eq book_components_form_data['author_form']}">
                                            <option value="${a.id}|${a.name}|${a.surname}"
                                                    selected>${a.name} ${a.surname}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${a.id}|${a.name}|${a.surname}">${a.name} ${a.surname}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-3">
                            <label for="author_name" class="form-label">${author_name}</label>
                            <input type="text" class="form-control" maxlength="20" name="author_name" id="author_name"
                                   value="${book_components_form_data['author_name_form']}"
                                   pattern="[А-ЯЁ][а-яё]*"
                                   required oninvalid="this.setCustomValidity('surname rules')">
                            <c:if test="${not empty book_components_form_data['wrong_author_name_form']}">
                                <div class="red-color">
                                        ${incorrect_data_format}
                                </div>
                            </c:if>
                        </div>

                        <div class="col-md-9"></div>

                        <div class="col-md-3">
                            <label for="author_surname" class="form-label">${author_surname}</label>
                            <input type="text" class="form-control" maxlength="40" name="author_surname"
                                   id="author_surname"
                                   value="${book_components_form_data['author_surname_form']}"
                                   pattern="([А-ЯЁ][а-яё\s]*)?([А-ЯЁ][а-яё]*)"
                                   required oninvalid="this.setCustomValidity('surname rules')">

                            <c:if test="${not empty book_components_form_data['wrong_author_surname_form']}">
                                <div class="red-color">
                                        ${incorrect_data_format}
                                </div>
                            </c:if>
                        </div>

                        <div class="col-md-9"></div>

                        <div class="col-12">
                            <button class="btn btn-primary" type="submit">${update_author_btn}</button>
                        </div>

                        <c:if test="${not empty book_components_form_data['wrong_author_exists_form']}">
                            <div class="col-12 red-color">
                                    ${incorrect_author}
                            </div>
                        </c:if>
                        <c:if test="${book_components_form_data['author_surname_form'] == null
                                and book_components_form_data['author_name_form'] == null}">
                            <div class="col-12 green-color">
                                    ${author_has_been_update}
                            </div>
                        </c:if>

                    </form>
                </div>
            </div>

            <hr/>

            <div class="card mt-2">
                <h5 class="card-header">${publisher}</h5>
                <div class="card-text my-2 ms-1">
                    <form method="post" action="${path}/controller" class="row g-3 needs-validation" novalidate>
                        <input type="hidden" name="command" value="update_publisher">
                        <div class="col-md-12">
                            <p class="form-label">${select_publisher}:</p>
                            <select name="publisher" id="publisher"
                                    onchange="updateGenreOrPublisher('publisher', 'publisher_name')">
                                <c:forEach var="p" items="${publishers}">
                                    <c:choose>
                                        <c:when test="${p eq book_components_form_data['publisher_form']}">
                                            <option value="${p.id}|${p.name}" selected>${p.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${p.id}|${p.name}">${p.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-3">
                            <label for="publisher_name" class="form-label">${publisher_name}</label>
                            <input type="text" class="form-control" maxlength="40" name="publisher_name"
                                   id="publisher_name"
                                   value="${book_components_form_data['publisher_name_form']}"
                                   pattern="([А-ЯЁ][а-яё\s]*)?([А-ЯЁ][а-яё]*)"
                                   required oninvalid="this.setCustomValidity('surname rules')">

                            <c:if test="${not empty book_components_form_data['wrong_publisher_name_form']}">
                                <div class="red-color">
                                        ${incorrect_data_format}
                                </div>
                            </c:if>
                        </div>

                        <div class="col-md-9"></div>

                        <div class="col-12">
                            <button class="btn btn-primary" type="submit">${update_publisher_btn}</button>
                        </div>

                        <c:if test="${not empty book_components_form_data['wrong_publisher_exists_form']}">
                            <div class="col-12 red-color">
                                    ${incorrect_publisher}
                            </div>
                        </c:if>
                        <c:if test="${book_components_form_data['publisher_name_form'] == null}">
                            <div class="col-12 green-color">
                                    ${publisher_has_been_update}
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
            <hr/>

            <div class="card mt-2">
                <h5 class="card-header">${status}</h5>
                <div class="card-text my-2 ms-1">
                    <form method="post" action="${path}/controller" class="row g-3 needs-validation" novalidate>
                        <input type="hidden" name="command" value="update_genre">
                        <div class="col-md-12">
                            <p class="form-label">${select_genre}:</p>
                            <select name="genre" id="genre" onchange="updateGenreOrPublisher('genre', 'genre_name')">
                                <c:forEach var="g" items="${genres}">
                                    <c:choose>
                                        <c:when test="${g eq book_components_form_data['genre_form']}">
                                            <option value="${g.id}|${g.name}" selected>${g.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${g.id}|${g.name}">${g.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-3">
                            <label for="genre_name" class="form-label">${genre_name}</label>
                            <input type="text" class="form-control" maxlength="40" name="genre_name" id="genre_name"
                                   value="${book_components_form_data['genre_name_form']}"
                                   pattern="([А-ЯЁ][а-яё\s]*)?([А-ЯЁ][а-яё]*)"
                                   required oninvalid="this.setCustomValidity('surname rules')">
                            <c:if test="${not empty book_components_form_data['wrong_genre_name_form']}">
                                <div class="red-color">
                                        ${incorrect_data_format}
                                </div>
                            </c:if>
                        </div>

                        <div class="col-md-9"></div>

                        <div class="col-12">
                            <button class="btn btn-primary" type="submit">${update_genre_btn}</button>
                        </div>

                        <c:if test="${not empty book_components_form_data['wrong_genre_exists_form']}">
                            <div class="col-12 red-color">
                                    ${incorrect_publisher}
                            </div>
                        </c:if>
                        <c:if test="${book_components_form_data['genre_name_form'] == null}">

                            <div class="col-12 green-color">
                                    ${genre_has_been_update}
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
            <hr/>

            <form method="get" action="${path}/controller">
                <input type="hidden" name="command" value="go_to_main_page">
                <div class="col-12">
                    <button class="btn btn-primary" type="submit">${back_btn}</button>
                </div>
            </form>
        </div>

        <div class="col mb-3"></div>
    </div>
</section>
</body>
<script type="text/javascript">
    function updateAuthorFields(select_id, name_id, surname_id) {
        let string = document.getElementById(select_id).value;
        let array = string.split('|');
        document.getElementById(name_id).value = array[1];
        document.getElementById(surname_id).value = array[2];
    }

    function updateGenreOrPublisher(select_id, name_id) {
        let string = document.getElementById(select_id).value;
        let array = string.split('|');
        document.getElementById(name_id).value = array[1];
    }
</script>
</html>
