<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 7/29/2022
  Time: 10:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>

<fmt:message key="reference.sign_out" var="sign_out_ref"/>
<fmt:message key="reference.change_account_password" var="change_password_ref"/>
<fmt:message key="reference.update_account_data" var="update_account_data_ref"/>
<fmt:message key="title.user_info" var="user_info_title"/>
<fmt:message key="title.user_options" var="user_options_title"/>
<fmt:message key="title.account" var="account_title"/>
<fmt:message key="label.user_email" var="email_label"/>
<fmt:message key="label.user_login" var="login_label"/>
<fmt:message key="label.user_mobile_phone" var="mobile_phone_label"/>
<fmt:message key="label.user_name" var="name_label"/>
<fmt:message key="label.user_surname" var="surname_label"/>
<fmt:message key="label.user_passport_serial_number" var="passport_serial_number_label"/>
<fmt:message key="message.not_found" var="not_found_msg"/>
<fmt:message key="label.user_role" var="user_role_label"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <title>${account_title}</title>

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
            <div class="card mt-2">
                <h5 class="card-header">${user_info_title}</h5>
                <div class="card-body">
                    <p class="card-text">${name_label}: ${user_info.name}</p>
                    <p class="card-text">${surname_label}: ${user_info.surname}</p>
                    <p class="card-text">${login_label}: ${user_info.login}</p>
                    <p class="card-text">${email_label}: ${user_info.email}</p>
                    <p class="card-text">${passport_serial_number_label}: ${user_info.passportSerialNumber}</p>
                    <p class="card-text">${mobile_phone_label}:
                        <c:choose>
                            <c:when test="${empty user_info.mobilePhone}">
                                ${not_found_msg}
                            </c:when>
                            <c:otherwise>
                                ${user_info.mobilePhone}
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <p class="card-text">${user_role_label}: ${user_info.role}</p>
                </div>
            </div>
            <div class="card my-2">
                <h5 class="card-header">${user_options_title}</h5>
                <div class="card-body">
                    <p>
                        <a class="card-text" href="${path}/controller?command=go_to_update_account_data_page">${update_account_data_ref}</a>
                    </p>
                    <p>
                        <a class="card-text" href="${path}/controller?command=go_to_change_password_page">${change_password_ref}</a>
                    </p>
                    <p>
                        <a class="card-text" href="${path}/controller?command=logout">${sign_out_ref}</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
<footer>
    <jsp:include page="../footer/footer.jsp"/>
</footer>
</html>
