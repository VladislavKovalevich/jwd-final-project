<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 13-Apr-22
  Time: 03:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>

<fmt:message key="title.change_account_password" var="title"/>
<fmt:message key="label.user_email" var="email"/>
<fmt:message key="label.user_login" var="login"/>
<fmt:message key="label.user_new_password" var="new_password"/>
<fmt:message key="label.user_old_password" var="old_password"/>
<fmt:message key="label.user_repeated_new_password" var="repeated_new_password"/>
<fmt:message key="message.invalid_password" var="invalid_password"/>
<fmt:message key="message.incorrect_data_format" var="incorrect_data_format"/>
<fmt:message key="message.mismatch_password" var="mismatch_password"/>
<fmt:message key="button.create_new_account" var="create_new_account_btn"/>
<fmt:message key="button.change_password" var="change_pass_btn"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>${title}</title>
    <style>
        .red-color {
            color: red;
        }
    </style>
</head>
<body style="background-color: cadetblue">
<div class="row mb-5 py-lg-5"></div>
<div class="row py-lg-5">
    <div class="col mb-3"></div>


    <div class="col-7 mb-3 py-3 px-3" style="background-color: aliceblue">
        <h3 class="py-md-2">${title}</h3>
        <form class="row g-3 needs-validation" novalidate>
            <input type="hidden" name="command" value="change_account_password">

            <div class="col-md-10">
                <div>
                    ${email}: ${user_email}
                </div>
            </div>

            <div class="col-md-10">
                <div>
                    ${login}: ${user_login}
                </div>
            </div>

            <div class="col-md-4">
                <label for="pass" class="form-label">${old_password}</label>
                <input type="password" class="form-control" name="pass"
                       value="${user_form_data['pass_form']}" id="pass" required>
                <div class="red-color">
                    <c:if test="${not empty user_form_data['wrong_pass_form']}">
                        ${incorrect_data_format}
                    </c:if>
                    <c:if test="${not empty user_form_data['wrong_pass_value']}">
                        ${invalid_password}
                    </c:if>
                </div>
            </div>

            <div class="col-md-8"></div>

            <div class="col-md-4">
                <label for="new_pass" class="form-label">${new_password}</label>
                <input type="password" class="form-control" name="new_pass"
                       value="${user_form_data['new_pass_form']}" id="new_pass" required>
                <div class="red-color">
                    <c:if test="${not empty user_form_data['wrong_new_pass_form']}">
                        ${incorrect_data_format}
                    </c:if>
                </div>
            </div>

            <div class="col-md-8"></div>

            <div class="col-md-4">
                <label for="new_repeat_pass" class="form-label">${repeated_new_password}</label>
                <input type="password" class="form-control" name="new_repeat_pass"
                       value="${user_form_data['new_repeat_pass_form']}" id="new_repeat_pass" required>
                <div class="red-color">
                    <c:if test="${not empty user_form_data['wrong_new_repeat_pass_form']}">
                        ${mismatch_password}
                    </c:if>
                </div>
            </div>

            <div class="col-md-8"></div>

            <div class="col-12">
                <button class="btn btn-primary" type="submit">${change_pass_btn}</button>
            </div>
        </form>
    </div>


    <div class="col mb-3"></div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
