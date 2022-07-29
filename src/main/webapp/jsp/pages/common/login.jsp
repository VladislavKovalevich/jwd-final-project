<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>

<fmt:message key="title.login" var="login"/>
<fmt:message key="label.user_email" var="email"/>
<fmt:message key="label.user_password" var="password"/>
<fmt:message key="message.user_not_found" var="not_found"/>
<fmt:message key="message.wrong_email_or_password" var="wrong_data"/>
<fmt:message key="button.sign_in" var="sign_in_btn"/>
<fmt:message key="reference.create_new_account" var="create_new_account"/>
<fmt:message key="label.sign_in" var="sign_in_label"/>
<fmt:message key="message.new_account" var="new_account_message"/>
<fmt:message key="message.user_is_banned" var="user_is_banned_message"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <title>${login}</title>

    <link rel="stylesheet" href="${path}/css/styles.css">
</head>
<body class="background-theme">
<section class="container-fluid">
    <div class="row my-lg-5">
        <div class="col-4"></div>
        <div class="col-4 mb-3 white-background">
            <form method="post" action="${path}/controller" class="row g-3 needs-validation" novalidate>
                <h3 class="mb-5">${sign_in_label}</h3>
                <input type="hidden" name="command" value="login"/>

                <div class="form-outline mb-4">
                    <label class="form-label" for="email">${email}: </label>
                    <input type="text" name="email" id="email" class="form-control"
                           pattern="[\da-z]([\da-z_\-\.]*)([\da-z_\-]*)@[\da-z_\-]{2,}\.[a-z]{2,6}"
                           required oninvalid="this.setCustomValidity('email rules')"
                           value="${user_form_data['email_form']}"/>
                </div>

                <div class="form-outline mb-4">
                    <label class="form-label" for="password">${password}: </label>
                    <input type="password" name="pass" id="password" class="form-control"
                           pattern="[\da-zA-Z\-!«»#\$%&'\(\)\*\+,\./:;=\?@_`\{\|\}~]{8,16}"
                           required oninvalid="this.setCustomValidity('password rules')"
                           value="${user_form_data['pass_form']}"/>
                </div>

                <div class="mb-3 red-color">
                    <c:if test="${not empty user_form_data['wrong_email_or_pass']}">
                        ${wrong_data}
                    </c:if>
                    <c:if test="${not empty user_form_data['not_found_user']}">
                        ${not_found}
                    </c:if>
                    <c:if test="${not empty user_form_data['user_is_banned']}">
                        ${user_is_banned_message}
                    </c:if>
                </div>

                <input class="btn btn-primary btn-lg btn-block mb-3" type="submit" value="${sign_in_btn}" name="sub">

                <div class="green-color">
                    <c:if test="${not empty create_account_feedback}">
                        ${new_account_message}
                    </c:if>
                </div>

                <div class="mx-3 my-4">
                    <a href="${path}/controller?command=go_to_create_new_account_page">${create_new_account}</a>
                </div>
            </form>
        </div>
        <div class="col-4"></div>
    </div>
</section>
</body>
</html>
