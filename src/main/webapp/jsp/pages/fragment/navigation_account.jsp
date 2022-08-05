<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 7/31/2022
  Time: 5:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>

<fmt:message key="reference.home_account" var="home_account_ref"/>
<fmt:message key="reference.orders_list" var="orders_list_ref"/>
<fmt:message key="reference.users_list" var="users_list_ref"/>

<html>
<body>
<div class="nav flex-column white-background" role="tablist" aria-orientation="vertical">
    <a class="nav-link active" data-toggle="pill"
       href="${path}/controller?command=go_to_account_page" role="tab"
       aria-selected="false">${home_account_ref}</a>

    <c:choose>
        <c:when test="${user_role eq 'CLIENT'}">
            <a class="nav-link" data-toggle="pill"
               href="${path}/controller?command=get_orders_by_user_id" role="tab"
               aria-selected="false">${orders_list_ref}</a>
        </c:when>
        <c:when test="${user_role eq 'ADMIN'}">
            <a class="nav-link" data-toggle="pill"
               href="${path}/controller?command=get_orders_list" role="tab"
               aria-selected="false">${orders_list_ref}</a>
            <a class="nav-link" data-toggle="pill"
               href="${path}/controller?command=show_users_list" role="tab"
               aria-selected="false">${users_list_ref}</a>
        </c:when>
    </c:choose>
</div>
</body>
</html>
