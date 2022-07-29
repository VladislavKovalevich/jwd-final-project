<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 27-Apr-22
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>

<fmt:message key="title.add_book" var="title"/>
<fmt:message key="label.book_title" var="book_title"/>
<fmt:message key="label.book_author" var="book_author"/>
<fmt:message key="label.book_copies_number" var="book_copies_number"/>
<fmt:message key="label.book_description" var="book_description"/>
<fmt:message key="label.book_genre" var="book_genre"/>
<fmt:message key="label.book_pages_count" var="book_pages_count"/>
<fmt:message key="label.book_publish_year" var="book_publish_year"/>
<fmt:message key="label.book_publisher" var="book_publisher"/>
<fmt:message key="button.add_book" var="add_book_btn"/>
<fmt:message key="message.incorrect_data_format" var="incorrect_data_format"/>
<fmt:message key="message.book_has_been_added" var="book_has_been_added"/>
<fmt:message key="message.book_with_title_is already_exists" var="book_is_already_exists"/>
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
    <script src="${path}/js/script.js"></script>
</head>
<body class="background-theme">
<section class="container-fluid">
    <div class="row mb-4 py-lg-5"></div>
    <div class="row py-lg-5">
        <div class="col mb-2"></div>


        <div class="col-6 mb-3 py-3 px-3" style="background-color: aliceblue">
            <h3 class="py-md-3">${title}</h3>
            <form method="post" action="${path}/controller" enctype="multipart/form-data" class="row g-3 needs-validation" novalidate>
                <input type="hidden" name="command" value="add_new_book">

                <div class="col-md-4">
                    <label for="title" class="form-label">${book_title}</label>
                    <input type="text" class="form-control" name="title" id="title"
                           value="${book_form_data['title_form']}"
                           pattern="[А-ЯЁ][а-яё\d\s]+[А-ЯЁа-яё\d\s]*(:\s[А-ЯЁа-яё\d\s]+)*"
                           required oninvalid="this.setCustomValidity('name rules')">
                    <div class="red-color">
                        <c:if test="${not empty book_form_data['wrong_title_form']}">
                            ${incorrect_data_format}
                        </c:if>
                        <c:if test="${not empty book_form_data['wrong_title_exists_form']}">
                            ${book_is_already_exists}
                        </c:if>
                    </div>
                </div>

                <hr/>

                <div class="col-md-3">
                    <p class="form-label">${book_author}:</p>
                    <select name="author">
                        <c:forEach var="a" items="${authors}">
                            <c:choose>
                                <c:when test="${a.id eq book_form_data['author_form']}">
                                    <option value="${a.id}" selected>${a.name} ${a.surname}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${a.id}">${a.name} ${a.surname}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-3">
                    <p class="form-label">${book_publisher}:</p>
                    <select name="publisher">
                        <c:forEach var="p" items="${publishers}">
                            <c:choose>
                                <c:when test="${p.id eq book_form_data['publisher_form']}">
                                    <option value="${p.id}" selected>${p.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${p.id}">${p.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-3">
                    <p class="form-label">${book_genre}:</p>
                    <select name="genre">
                        <c:forEach var="g" items="${genres}">
                            <c:choose>
                                <c:when test="${g.id eq book_form_data['genre_form']}">
                                    <option value="${g.id}" selected>${g.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${g.id}">${g.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>

                <hr/>

                <div class="col-md-2">
                    <label for="copies_number" class="form-label">${book_copies_number}</label>
                    <input type="number" class="form-control" name="copies_number" id="copies_number"
                           value="${book_form_data['copies_number_form']}"
                           pattern="[1-9][\d]*"
                           required oninvalid="this.setCustomValidity('name rules')">
                    <div class="red-color">
                        <c:if test="${not empty book_form_data['wrong_copies_number_form']}">
                            ${incorrect_data_format}
                        </c:if>
                    </div>
                </div>

                <div class="col-md-2">
                    <label for="release_year" class="form-label">${book_publish_year}</label>
                    <input type="number" class="form-control" name="release_year" id="release_year"
                           value="${book_form_data['release_year_form']}" aria-describedby="inputGroupPrepend"
                           pattern="([12][\d]{3})|([1-9]{1,3})"
                           required oninvalid="this.setCustomValidity('name rules')">
                    <div class="red-color">
                        <c:if test="${not empty book_form_data['wrong_release_year_form']}">
                            ${incorrect_data_format}
                        </c:if>
                    </div>
                </div>

                <div class="col-md-2">
                    <label for="pages_count" class="form-label">${book_pages_count}</label>
                    <input type="number" class="form-control" name="pages_count"
                           value="${book_form_data['pages_count_form']}" id="pages_count"
                           pattern="[1-9][\d]{1,4}"
                           required oninvalid="this.setCustomValidity('')">
                    <div class="red-color">
                        <c:if test="${not empty book_form_data['wrong_pages_count_form']}">
                            ${incorrect_data_format}
                        </c:if>
                    </div>
                </div>

                <hr/>

                <div class="col-md-10">
                    <label for="description" class="form-label">${book_description}</label>
                    <textarea type="text" class="form-control" name="description"
                              rows="4" id="description" required>${book_form_data['description_form']}</textarea>
                    <div class="red-color">
                        <c:if test="${not empty book_form_data['wrong_description_form']}">
                            ${incorrect_data_format}
                        </c:if>
                    </div>
                </div>

                <div class="col-12">
                    <label for="image">Choose image</label>
                    <input type="file" id="image" name="image" class="form-control" accept=".jpg, .png, .jpeg, .gif, .bmp, .tif, .tiff">
                </div>

                <div class="col-12">
                    <button class="btn btn-primary" type="submit">${add_book_btn}</button>
                </div>

                <div class="col-12 green-color">
                    <c:if test="${not empty book_form_data['add_book_msg']}">
                        ${book_has_been_added}
                    </c:if>
                </div>
            </form>

            <hr>

            <div class="col-12">
                <form method="get" action="${path}/controller">
                    <input type="hidden" name="command" value="go_to_main_page">
                    <div class="col-12">
                        <button class="btn btn-primary" type="submit">${back_btn}</button>
                    </div>
                </form>
            </div>

        </div>

        <div class="col mb-3"></div>
    </div>
</section>
</body>
</html>