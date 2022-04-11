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

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Books list</title>
</head>
<header>
    <jsp:include page="../header/header.jsp"/>
</header>
<body>
<div class="container px-4 py-lg-5">
    <div class="row">
        <div class="col-4">
            <div class="">
                <img src="${path}/img/not_found_image.jpg"/>
            </div>
        </div>
        <div class="col-7">
            <div class="px-lg-1 py-lg-1">
                <br/>
                <h3>${book.name}</h3>
                Авторы:
                <h6>
                    <c:forEach var="author" items="${authors}">
                        ${author.name} ${author.surname};
                    </c:forEach>
                </h6>
                <div class="">
                    <p class="">Колличество копий: ${book.copiesNumber}</p>
                    <p class="">Колличество страниц: ${book.numberOfPages}</p>
                    <p class="">Цена: ${book.price}</p>
                    <p class="">Жанр: ${book.genre}</p>
                    <p class="">Год выпуска: ${book.releaseYear}</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<footer>
    <jsp:include page="../footer/footer.jsp"/>
</footer>
</html>
