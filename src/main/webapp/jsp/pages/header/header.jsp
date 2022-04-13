<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:set var="role" value="${user_role}" scope="page"/>

<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <span class="navbar-brand">Library</span>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <c:choose>
                    <c:when test="${role eq 'CLIENT'}">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">Home (Client)</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${path}/controller?command=show_books_list">Books list</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Orders list</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                ${user_name} ${user_surname}
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="${path}/controller?command=logout">Sign out</a></li>
                            </ul>
                        </li>
                    </ul>
                    </c:when>

                    <c:when test="${role eq 'ADMIN'}">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="#">Home (Admin)</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${path}/controller?command=show_books_list">Books list</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Orders list</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Users list</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    ${user_name} ${user_surname}
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="${path}/controller?command=logout">Sign out</a></li>
                                </ul>
                            </li>
                        </ul>
                    </c:when>

                    <c:otherwise>
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="#">Home (Guest)</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${path}/controller?command=show_books_list">Books list</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Guest
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="${path}/controller?command=go_to_create_new_account_page">Create new account</a></li>
                                    <li><a class="dropdown-item" href="${path}/controller?command=go_to_login_page">Sign in</a></li>
                                </ul>
                            </li>
                        </ul>
                    </c:otherwise>

                </c:choose>
            </div>
        </div>
    </nav>
</body>
</html>
