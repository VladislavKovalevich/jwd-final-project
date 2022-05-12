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
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Update book</title>
    <style>
        .red-color {
            color: red;
        }
    </style>
</head>
<body style="background-color: cadetblue">
<div class="row mb-4 py-lg-5"></div>
<div class="row py-lg-5">
    <div class="col mb-2"></div>


    <div class="col-6 mb-3 py-3 px-3" style="background-color: aliceblue">
        <h3 class="py-md-3">Add new book</h3>
        <form class="row g-3 needs-validation" novalidate>
            <input type="hidden" name="command" value="update_book_data">

            <div class="col-md-4">
                <label for="validationCustom01" class="form-label">Title</label>
                <input type="text" class="form-control" name="title" id="validationCustom01"
                       value="${book_form_data['title_form']}" required>
                <div class="red-color">
                    <c:if test="${not empty book_form_data['wrong_title_form']}">
                        ${book_form_data['wrong_title_form']}
                    </c:if>
                </div>
            </div>

            <hr/>

            <div class="col-md-3">
                <p class="form-label">Author:</p>
                <select name="author">
                    <c:forEach var="a" items="${authors}">
                        <c:choose>
                            <c:when test="${a eq book_form_data['author_form']}">
                                <option value="${a.id}|${a.name}|${a.surname}" selected>${a.name} ${a.surname}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${a.id}|${a.name}|${a.surname}">${a.name} ${a.surname}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>

            <div class="col-md-3">
                <p class="form-label">Publisher:</p>
                <select name="publisher">
                    <c:forEach var="p" items="${publishers}">
                        <c:choose>
                            <c:when test="${p eq book_form_data['publisher_form']}">
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
                <p class="form-label">Genres:</p>
                <select name="genre">
                    <c:forEach var="g" items="${genres}">
                        <c:choose>
                            <c:when test="${g eq book_form_data['genre_form']}">
                                <option value="${g.id}|${g.name}" selected>${g.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${g.id}|${g.name}">${g.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>

            <hr/>

            <div class="col-md-2">
                <label for="validationCustom02" class="form-label">Number of copies</label>
                <input type="number" class="form-control" name="copies_number" id="validationCustom02"
                       value="${book_form_data['copies_number_form']}" required>
                <div class="red-color">
                    <c:if test="${not empty book_form_data['wrong_copies_number_form']}">
                        ${book_form_data['wrong_copies_number_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-md-2">
                <label for="validationCustomUsername" class="form-label">Release year</label>
                <input type="number" class="form-control" name="release_year" id="validationCustomUsername"
                       value="${book_form_data['release_year_form']}" aria-describedby="inputGroupPrepend" required>
                <div class="red-color">
                    <c:if test="${not empty book_form_data['wrong_release_year_form']}">
                        ${book_form_data['wrong_release_year_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-md-2">
                <label for="validationCustom03" class="form-label">Number of pages</label>
                <input type="number" class="form-control" name="pages_count"
                       value="${book_form_data['pages_count_form']}" id="validationCustom03" required>
                <div class="red-color">
                    <c:if test="${not empty book_form_data['wrong_pages_count_form']}">
                        ${book_form_data['wrong_pages_count_form']}
                    </c:if>
                </div>
            </div>

            <hr/>

            <div class="col-md-10">
                <label for="validationCustom04" class="form-label">Description</label>
                <textarea type="text" class="form-control" name="description"
                          rows="4" id="validationCustom04"
                          required>${book_form_data['description_form']}</textarea>
                <div class="red-color">
                    <c:if test="${not empty book_form_data['wrong_description_form']}">
                        ${book_form_data['wrong_description_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-12">
                <button class="btn btn-primary" type="submit">Update book</button>
            </div>
        </form>
    </div>

    <div class="col mb-3"></div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</html>
