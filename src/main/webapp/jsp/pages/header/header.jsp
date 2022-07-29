<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:set var="role" value="${user_role}" scope="page"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>

<fmt:message key="label.library" var="library"/>
<fmt:message key="dropdown_menu_title.guest" var="guest_title"/>
<fmt:message key="reference.sign_in" var="sign_in"/>
<fmt:message key="reference.sign_out" var="sign_out"/>
<fmt:message key="reference.change_account_password" var="change_password"/>
<fmt:message key="reference.update_account_data" var="update_account_data"/>
<fmt:message key="reference.get_books_list" var="get_books"/>
<fmt:message key="reference.add_new_book_to_library" var="add_new_book"/>
<fmt:message key="reference.add_book_components" var="add_book_components"/>
<fmt:message key="reference.update_book_components" var="update_book_components"/>
<fmt:message key="dropdown_menu_title.locale" var="change_local"/>
<fmt:message key="dropdown_menu_title.book_menu" var="book_menu"/>
<fmt:message key="reference.orders_list" var="orders_list_ref"/>
<fmt:message key="reference.users_list" var="users_list_ref"/>

<fmt:message key="local.en" var="en"/>
<fmt:message key="local.ru" var="ru"/>

<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mr-0 mr-md-2">${library}</span>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <c:choose>
                <c:when test="${role eq 'CLIENT'}">
                    <ul class="navbar-nav me-auto mb-1 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="${path}/controller?command=show_books_list">${get_books}</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link" href="${path}/controller?command=get_orders_by_user_id&user_id=${user_id}">
                                ${orders_list_ref}
                            </a>
                        </li>
                    </ul>
                    <ul class="navbar-nav my-2 my-lg-0 justify-content-end">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                    ${change_local}
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item"
                                       href="${path}/controller?command=change_local&language=RU">${ru}</a></li>
                                <li><a class="dropdown-item"
                                       href="${path}/controller?command=change_local&language=EN">${en}</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                    ${user_login}
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="${path}/controller?command=logout">${sign_out}</a>
                                </li>
                                <li><a class="dropdown-item"
                                       href="${path}/controller?command=go_to_change_password_page">${change_password}</a>
                                </li>
                                <li><a class="dropdown-item"
                                       href="${path}/controller?command=go_to_update_account_data_page">${update_account_data}</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </c:when>

                <c:when test="${role eq 'ADMIN'}">
                    <ul class="navbar-nav me-auto mb-1 mb-lg-0">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                ${book_menu}
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li class="nav-item">
                                    <a class="nav-link"
                                       href="${path}/controller?command=show_books_list">${get_books}</a>
                                    <a class="nav-link"
                                       href="${path}/controller?command=go_to_add_new_book_page">${add_new_book}</a>
                                    <a class="nav-link"
                                       href="${path}/controller?command=go_to_add_book_components_page">${add_book_components}</a>
                                    <a class="nav-link"
                                       href="${path}/controller?command=go_to_update_book_components_page">${update_book_components}</a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${path}/controller?command=get_orders_list">${orders_list_ref}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${path}/controller?command=show_users_list">${users_list_ref}</a>
                        </li>
                    </ul>
                    <ul class="navbar-nav my-2 my-lg-0 justify-content-end">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                    ${change_local}
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item"
                                       href="${path}/controller?command=change_local&language=RU">${ru}</a></li>
                                <li><a class="dropdown-item"
                                       href="${path}/controller?command=change_local&language=EN">${en}</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                    ${user_login}
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="${path}/controller?command=logout">${sign_out}</a>
                                </li>
                                <li><a class="dropdown-item"
                                       href="${path}/controller?command=go_to_change_password_page">${change_password}</a>
                                </li>
                                <li><a class="dropdown-item"
                                       href="${path}/controller?command=go_to_update_account_data_page">${update_account_data}</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </c:when>

                <c:otherwise>
                    <ul class="navbar-nav me-auto mb-1 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="${path}/controller?command=show_books_list">${get_books}</a>
                        </li>
                    </ul>
                    <ul class="navbar-nav my-2 my-lg-0 justify-content-end">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                    ${change_local}
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item"
                                       href="${path}/controller?command=change_local&language=RU">${ru}</a></li>
                                <li><a class="dropdown-item"
                                       href="${path}/controller?command=change_local&language=EN">${en}</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                                <a class="nav-link"
                                       href="${path}/controller?command=go_to_login_page">${sign_in}</a>
                        </li>
                    </ul>
                </c:otherwise>

            </c:choose>
        </div>
    </div>
</nav>
</body>
</html>
