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
            <input type="hidden" name="command" value="add_author">
            <div class="col-md-12">
                <p class="form-label">Author:</p>
                <select name="author" id="author"
                        onchange="updateAuthorFields('author', 'author_name', 'author_surname')">
                    <c:forEach var="a" items="${authors}">
                        <c:choose>
                            <c:when test="${a.id eq component_form_data['author_form']}">
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
                <label for="author_name" class="form-label">Author name</label>
                <input type="text" class="form-control" name="author_name" id="author_name"
                       value="${author_form_data['author_name_form']}" required>
                <div class="red-color">
                    <c:if test="${not empty author_form_data['wrong_author_name_form']}">
                        ${author_form_data['wrong_author_name_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-md-9"></div>

            <div class="col-md-3">
                <label for="author_surname" class="form-label">Author surname</label>
                <input type="text" class="form-control" name="author_surname" id="author_surname"
                       value="${author_form_data['author_surname_form']}" required>
                <div class="red-color">
                    <c:if test="${not empty author_form_data['wrong_author_surname_form']}">
                        ${author_form_data['wrong_author_surname_form']}
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
            <input type="hidden" name="command" value="add_publisher">
            <div class="col-md-12">
                <p class="form-label">Publisher:</p>
                <select name="publisher" id="publisher"
                        onchange="updateGenreOrPublisher('publisher', 'publisher_name')">
                    <c:forEach var="p" items="${publishers}">
                        <c:choose>
                            <c:when test="${p.id eq book_form_data['publisher_form']}">
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
                <label for="publisher_name" class="form-label">Publisher name</label>
                <input type="text" class="form-control" name="publisher_name" id="publisher_name"
                       value="${publisher_form_data['publisher_name_form']}" required>
                <div class="red-color">
                    <c:if test="${not empty publisher_form_data['wrong_publisher_name_form']}">
                        ${publisher_form_data['wrong_publisher_name_form']}
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
            <input type="hidden" name="command" value="add_genre">
            <div class="col-md-12">
                <p class="form-label">Genre:</p>
                <select name="genre" id="genre" onchange="updateGenreOrPublisher('genre', 'genre_name')">
                    <c:forEach var="g" items="${genres}">
                        <c:choose>
                            <c:when test="${g.id eq book_form_data['genre_form']}">
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
                <label for="genre_name" class="form-label">Genre name</label>
                <input type="text" class="form-control" name="genre_name" id="genre_name"
                       value="${genre_form_data['genre_name_form']}" required>
                <div class="red-color">
                    <c:if test="${not empty genre_form_data['wrong_genre_name_form']}">
                        ${genre_form_data['wrong_genre_name_form']}
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
