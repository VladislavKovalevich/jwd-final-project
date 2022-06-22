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

<fmt:message key="title.add_book_components" var="title"/>
<fmt:message key="label.author_name" var="author_name"/>
<fmt:message key="label.author_surname" var="author_surname"/>
<fmt:message key="label.publisher_name" var="publisher_name"/>
<fmt:message key="label.genre_name" var="genre_name"/>
<fmt:message key="label.book_author" var="author"/>
<fmt:message key="label.book_publisher" var="publisher"/>
<fmt:message key="label.book_genre" var="genre"/>
<fmt:message key="message.incorrect_data_format" var="incorrect_data_format"/>
<fmt:message key="message.incorrect_author" var="incorrect_author"/>
<fmt:message key="message.incorrect_publisher" var="incorrect_publisher"/>
<fmt:message key="message.incorrect_genre" var="incorrect_genre"/>
<fmt:message key="message.author_has_been_added" var="author_has_been_added"/>
<fmt:message key="message.publisher_has_been_added" var="publisher_has_been_added"/>
<fmt:message key="message.genre_has_been_added" var="genre_has_been_added"/>
<fmt:message key="button.add_author" var="add_author_btn"/>
<fmt:message key="button.add_publisher" var="add_publisher_btn"/>
<fmt:message key="button.add_genre" var="add_genre_btn"/>
<fmt:message key="button.back_to_main" var="back_btn"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <title>${title}</title>

    <link rel="stylesheet" href="${path}/css/styles.css">

</head>
<body class="background-theme">
<section class="container-fluid">
    <div class="row mb-4 py-lg-5"></div>
    <div class="row py-lg-5">
        <div class="col mb-2"></div>

        <div class="col-6 mb-3 py-3 px-3" style="background-color: aliceblue">
            <h3 class="py-md-3">${title}</h3>
            <hr/>
            <form method="post" action="${path}/controller" class="row g-3 needs-validation" novalidate>
                <input type="hidden" name="command" value="add_new_author">
                <div class="col-md-12">
                    <p class="form-label">${author}:</p>
                </div>

                <div class="col-md-3">
                    <label for="author_name" class="form-label">${author_name}</label>
                    <input type="text" class="form-control" maxlength="20" name="author_name" id="author_name"
                           value="${book_components_form_data['author_name_form']}"
                           pattern="[А-ЯЁ][а-яё]*"
                           required oninvalid="this.setCustomValidity('surname rules')">
                    <div class="red-color">
                        <c:if test="${not empty book_components_form_data['wrong_author_name_form']}">
                            ${incorrect_data_format}
                        </c:if>
                    </div>
                </div>

                <div class="col-md-9"></div>

                <div class="col-md-3">
                    <label for="author_surname" class="form-label">${author_surname}</label>
                    <input type="text" class="form-control" maxlength="40" name="author_surname" id="author_surname"
                           value="${book_components_form_data['author_surname_form']}"
                           pattern="([А-ЯЁ][а-яё\s]*)?([А-ЯЁ][а-яё]*)"
                           required oninvalid="this.setCustomValidity('surname rules')">
                    <div class="red-color">
                        <c:if test="${not empty book_components_form_data['wrong_author_surname_form']}">
                            ${incorrect_data_format}
                        </c:if>
                    </div>
                </div>

                <div class="col-md-9"></div>

                <div class="col-12">
                    <button class="btn btn-primary" type="submit">${add_author_btn}</button>
                </div>
                <div class="col-12 red-color">
                    <c:if test="${not empty book_components_form_data['wrong_author_exists_form']}">
                        ${incorrect_author}
                    </c:if>
                </div>
                <div class="col-12 green-color">
                    <c:if test="${not empty book_components_form_data['author_operation_feedback']}">
                        ${author_has_been_added}
                    </c:if>
                </div>
            </form>

            <hr/>

            <form method="post" action="${path}/controller" class="row g-3 needs-validation" novalidate>
                <input type="hidden" name="command" value="add_new_publisher">

                <div class="col-md-12">
                    <p class="form-label">${publisher}:</p>
                </div>

                <div class="col-md-3">
                    <label for="publisher_name" class="form-label">${publisher_name}</label>
                    <input type="text" class="form-control" maxlength="40" name="publisher_name" id="publisher_name"
                           value="${book_components_form_data['publisher_name_form']}"
                           pattern="([А-ЯЁ][а-яё\s]*)?([А-ЯЁ][а-яё]*)"
                           required oninvalid="this.setCustomValidity('surname rules')">
                    <div class="red-color">
                        <c:if test="${not empty book_components_form_data['wrong_publisher_name_form']}">
                            ${incorrect_data_format}
                        </c:if>
                    </div>
                </div>

                <div class="col-md-9"></div>

                <div class="col-12">
                    <button class="btn btn-primary" type="submit">${add_publisher_btn}</button>
                </div>
                <div class="col-12 red-color">
                    <c:if test="${not empty book_components_form_data['wrong_publisher_exists_form']}">
                        ${incorrect_publisher}
                    </c:if>
                </div>
                <div class="col-12 green-color">
                    <c:if test="${not empty book_components_form_data['publisher_operation_feedback']}">
                        ${publisher_has_been_added}
                    </c:if>
                </div>
            </form>

            <hr/>

            <form method="post" action="${path}/controller" class="row g-3 needs-validation" novalidate>
                <input type="hidden" name="command" value="add_new_genre">
                <div class="col-md-12">
                    <p class="form-label">${genre}:</p>
                </div>

                <div class="col-md-3">
                    <label for="genre_name" class="form-label">${genre_name}</label>
                    <input type="text" class="form-control" maxlength="40" name="genre_name" id="genre_name"
                           value="${book_components_form_data['genre_name_form']}"
                           pattern="([А-ЯЁ][а-яё\s]*)?([А-ЯЁ][а-яё]*)"
                           required oninvalid="this.setCustomValidity('surname rules')">
                    <div class="red-color">
                        <c:if test="${not empty book_components_form_data['wrong_genre_name_form']}">
                            ${incorrect_data_format}
                        </c:if>
                    </div>
                </div>

                <div class="col-md-9"></div>

                <div class="col-12">
                    <button class="btn btn-primary" type="submit">${add_genre_btn}</button>
                </div>
                <div class="col-12 red-color">
                    <c:if test="${not empty book_components_form_data['wrong_genre_exists_form']}">
                        ${incorrect_genre}
                    </c:if>
                </div>
                <div class="col-12 green-color">
                    <c:if test="${not empty book_components_form_data['genre_operation_feedback']}">
                        ${genre_has_been_added}
                    </c:if>
                </div>
            </form>

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
</html>
