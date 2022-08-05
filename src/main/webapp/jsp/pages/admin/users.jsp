<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 25-Apr-22
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>
<fmt:message key="title.users_list" var="title"/>
<fmt:message key="label.user_login" var="login"/>
<fmt:message key="label.user_email" var="email"/>
<fmt:message key="label.user_mobile_phone" var="phone"/>
<fmt:message key="button.block_user" var="block_btn"/>
<fmt:message key="button.unblock_user" var="unblock_btn"/>
<fmt:message key="message.not_found" var="not_found_msg"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <title>${title}</title>

    <link rel="stylesheet" href="${path}/css/styles.css">
    <script src="${path}/js/script.js"></script>
</head>
<header>
    <jsp:include page="../header/header.jsp"/>
</header>
<body class="background-theme">
<section class="container-fluid">
    <div class="row mx-lg-5 my-lg-5">
        <div class="col-3 mx-2">
            <jsp:include page="../fragment/navigation_account.jsp"/>
        </div>
        <div class="col-8 mx-2 white-background">
            <div class="row my-4">
                <c:forEach var="user" items="${users_list}">
                    <div class="col-1"></div>
                    <div class="col-10 my-2" style="background-color: #c7b39b">
                        <h5>${user.name} ${user.surname}</h5>
                        <p>${login}: ${user.login}</p>
                        <p>${email}: ${user.email}</p>
                        <p>${phone}:
                            <c:choose>
                                <c:when test="${not empty user.mobilePhone or user.mobilePhone eq ''}">
                                    ${user.mobilePhone}
                                </c:when>
                                <c:otherwise>
                                    ${not_found_msg}
                                </c:otherwise>
                            </c:choose>
                        </p>

                        <hr/>

                        <form type="post" action="${path}/controller">
                            <input type="hidden" name="command" value="change_user_status">
                            <input type="hidden" name="user_id" value="${user.email}">
                            <p class="py-1">
                                <c:choose>
                                    <c:when test="${user.banned}">
                                        <input type="hidden" name="user_status" value="false">
                                        <input type="submit" class="btn btn-primary" value="${unblock_btn}">
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="user_status" value="true">
                                        <input type="submit" class="btn btn-danger" value="${block_btn}">
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </form>
                    </div>
                    <div class="col-1"></div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>
</body>
<footer>
    <jsp:include page="../footer/footer.jsp"/>
</footer>
</html>
