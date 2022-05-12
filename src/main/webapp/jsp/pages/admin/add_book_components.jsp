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
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Add book</title>
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
        <h3 class="py-md-3">Add book components</h3>
        <hr/>
        <form action="controller" class="row g-3 needs-validation" novalidate>
            <input type="hidden" name="command" value="add_new_author">
            <div class="col-md-12">
                <p class="form-label">Author:</p>
            </div>

            <div class="col-md-3">
                <label for="validationCustom03" class="form-label">Author name</label>
                <input type="text" class="form-control" name="author_name" id="validationCustom03"
                       value="${book_components_form_data['author_name_form']}" required>
                <div class="red-color">
                    <c:if test="${not empty book_components_form_data['wrong_author_name_form']}">
                        ${book_components_form_data['wrong_author_name_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-md-9"></div>

            <div class="col-md-3">
                <label for="validationCustom04" class="form-label">Author surname</label>
                <input type="text" class="form-control" name="author_surname" id="validationCustom04"
                       value="${book_components_form_data['author_surname_form']}" required>
                <div class="red-color">
                    <c:if test="${not empty book_components_form_data['wrong_author_surname_form']}">
                        ${book_components_form_data['wrong_author_surname_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-md-9"></div>

            <div class="col-12">
                <button class="btn btn-primary" type="submit">Add author</button>
            </div>
        </form>

        <hr/>

        <form action="controller" class="row g-3 needs-validation" novalidate>
            <input type="hidden" name="command" value="add_new_publisher">

            <div class="col-md-12">
                <p class="form-label">Publisher:</p>
            </div>

            <div class="col-md-3">
                <label for="validationCustom01" class="form-label">Publisher name</label>
                <input type="text" class="form-control" name="publisher_name" id="validationCustom01"
                       value="${book_components_form_data['publisher_name_form']}" required>
                <div class="red-color">
                    <c:if test="${not empty book_components_form_data['wrong_publisher_name_form']}">
                        ${book_components_form_data['wrong_publisher_name_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-md-9"></div>

            <div class="col-12">
                <button class="btn btn-primary" type="submit">Add publisher</button>
            </div>
        </form>

        <hr/>

        <form action="controller" class="row g-3 needs-validation" novalidate>
            <input type="hidden" name="command" value="add_new_genre">
            <div class="col-md-12">
                <p class="form-label">Genre:</p>
            </div>

            <div class="col-md-3">
                <label for="validationCustom02" class="form-label">Genre name</label>
                <input type="text" class="form-control" name="genre_name" id="validationCustom02"
                       value="${book_components_form_data['genre_name_form']}" required>
                <div class="red-color">
                    <c:if test="${not empty book_components_form_data['wrong_genre_name_form']}">
                        ${book_components_form_data['wrong_genre_name_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-md-9"></div>

            <div class="col-12">
                <button class="btn btn-primary" type="submit">Add genre</button>
            </div>
        </form>

        <hr/>

        <form action="controller">
            <input type="hidden" name="command" value="go_to_main_page">
            <div class="col-12">
                <button class="btn btn-primary" type="submit">Back</button>
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
