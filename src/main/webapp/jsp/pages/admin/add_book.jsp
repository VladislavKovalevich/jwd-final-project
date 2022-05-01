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
    <title>Title</title>
    <style>
        .red-color {
            color: red;
        }
    </style>
    <script>
        function createNewAuthor(form) {
            if (form.new_author_check.checked) {
                form.author.disabled = 1;
                form.new_author_name.disabled = 0;
                form.new_author_surname.disabled = 0;
            } else {
                form.author.disabled = 0;
                form.new_author_name.disabled = 1;
                form.new_author_surname.disabled = 1;
            }
        }

        function createNewPublisher(form) {
            if (form.new_publisher_check.checked){
                form.publisher.disabled = 1;
                form.new_publisher_name.disabled = 0;
            }else{
                form.new_publisher_name.disabled = 1;
                form.publisher.disabled = 0;
            }
        }
    </script>
</head>
<body style="background-color: cadetblue">
<div class="row mb-5 py-lg-5"></div>
<div class="row py-lg-5">
    <div class="col mb-3"></div>


    <div class="col-7 mb-3 py-3 px-3" style="background-color: aliceblue">
        <h3 class="py-md-5">Add new book</h3>
        <form class="row g-3 needs-validation" novalidate>
            <input type="hidden" name="command" value="add_new_book">

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

            <div class="col-md-12">
                <div class="col-md-4">
                    <p class="form-label">Choose author:</p>
                    <select name="author">
                        <c:forEach var="a" items="${authors}"><!-- Добавить условие на отображение выбранного автора -->
                            <option value="${a}">${a.name} ${a.surname}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-check py-2">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1" name="new_author_check"
                           onclick="createNewAuthor(this.form)">
                    <label class="form-check-label" for="exampleCheck1">Add new author</label>
                </div>

                <div class="col-md-2">
                    <label for="validationCustom08" class="form-label">Name</label>
                    <input type="text" class="form-control" name="new_author_name"
                           value="${book_form_data['new_author_name_form']}" id="validationCustom08" disabled required>
                    <div class="red-color">
                        <c:if test="${not empty book_form_data['wrong_new_author_name_form']}">
                            ${book_form_data['wrong_new_author_name_form']}
                        </c:if>
                    </div>
                </div>

                <div class="col-md-2">
                    <label for="validationCustom11" class="form-label">Surname</label>
                    <input type="text" class="form-control" name="new_author_surname"
                           value="${book_form_data['new_author_surname_form']}" id="validationCustom11" disabled
                           required>
                    <div class="red-color">
                        <c:if test="${not empty book_form_data['wrong_new_author_surname_form']}">
                            ${book_form_data['wrong_new_author_surname_form']}
                        </c:if>
                    </div>
                </div>
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

            <div class="col-md-12">
                <div class="col-md-4">
                    <p class="form-label">Publisher:</p>
                    <select name="publisher">
                        <c:forEach var="p" items="${publishers}">
                            <option value="${p}">${p.name}</option><!-- Добавить условие на отображение выбранного автора -->
                        </c:forEach>
                    </select>
                </div>

                <div class="form-check py-2">
                    <input type="checkbox" class="form-check-input" id="exampleCheck2" name="new_publisher_check"
                           onclick="createNewPublisher(this.form)">
                    <label class="form-check-label" for="exampleCheck2">Add new publisher</label>
                </div>

                <div class="col-md-2">
                    <label for="validationCustom13" class="form-labnew_publisher_name_formel">Publisher name</label>
                    <input type="text" class="form-control" name="new_publisher_name"
                           value="${book_form_data['']}" id="validationCustom13" disabled required>
                    <div class="red-color">
                        <c:if test="${not empty book_form_data['wrong_new_publisher_name_form']}">
                            ${book_form_data['wrong_new_publisher_name_form']}
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="col-md-10">
                <label for="validationCustom04" class="form-label">Description</label>
                <textarea type="text" class="form-control" name="description"
                          value ="${book_form_data['description_form']}" rows="4" id="validationCustom04"
                          required></textarea>
                <div class="red-color">
                    <c:if test="${not empty book_form_data['wrong_description_form']}">
                        ${book_form_data['wrong_description_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-12">
                <button class="btn btn-primary" type="submit">Submit form</button>
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