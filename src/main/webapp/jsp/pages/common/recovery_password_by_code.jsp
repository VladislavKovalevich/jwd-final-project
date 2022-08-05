<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 8/2/2022
  Time: 2:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>

<fmt:message key="title.change_password_by_key" var="change_password_title"/>
<fmt:message key="label.user_new_password" var="new_password_label"/>
<fmt:message key="label.user_repeated_new_password" var="repeated_new_password_label"/>
<fmt:message key="label.secret_code" var="secret_code_label"/>
<fmt:message key="message.wrong_code" var="wrong_code_msg"/>
<fmt:message key="message.mismatch_password" var="mismatch_password_msg"/>
<fmt:message key="message.incorrect_data_format" var="incorrect_data_format_msg"/>
<fmt:message key="button.change_password" var="change_pass_btn"/>
<fmt:message key="button.back_to_main" var="back_btn"/>
<fmt:message key="button.send_code" var="send_code_btn"/>
<fmt:message key="title.send_code" var="send_secret_code_header"/>
<fmt:message key="label.user_email" var="email_label"/>
<fmt:message key="title.verify_code" var="verify_code_header"/>
<fmt:message key="button.send_code_again" var="send_code_again_btn"/>
<fmt:message key="button.verify_code" var="verify_code_btn"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <title>${change_password_title}</title>

    <link rel="stylesheet" href="${path}/css/styles.css">
    <script src="${path}/js/script.js"></script>

</head>
<body class="background-theme">
<section class="container-fluid">
    <div class="row my-lg-5">
        <div class="col-4"></div>
        <div class="col-4 mb-3 white-background">
            <c:choose>
                <c:when test="${user_form_data['change_password_operation_status_code'] eq '0'}">
                    <form method="post" action="${path}/controller" class="row g-3 needs-validation" novalidate>
                        <h3 class="mb-3">${send_secret_code_header}:</h3>
                        <input type="hidden" name="command" value="send_password_code"/>

                        <div class="col-md-4">
                            <label class="form-label" for="email">${email_label}: </label>
                            <input type="text" name="email" id="email" class="form-control"
                                   pattern="[\da-z]([\da-z_\-\.]*)([\da-z_\-]*)@[\da-z_\-]{2,}\.[a-z]{2,6}"
                                   required oninvalid="this.setCustomValidity('email rules')"
                                   value="${user_form_data['email_form']}"/>

                            <c:if test="${not empty user_form_data['wrong_email_form']}">
                                <div class="red-color">
                                        ${incorrect_data_format_msg}
                                </div>
                            </c:if>

                            <div class="col-12 mt-2">
                                <button class="btn btn-primary" type="submit">${send_code_btn}</button>
                            </div>
                        </div>
                    </form>
                </c:when>

                <c:when test="${user_form_data['change_password_operation_status_code'] eq '1'}">
                    <form method="post" action="${path}/controller" class="row g-3 needs-validation" novalidate>
                        <h3 class="mb-3">${verify_code_header}:</h3>
                        <input type="hidden" name="command" value="verify_password_code"/>
                        <div class="col-md-4">
                            <label class="form-label" for="secret_code">${secret_code_label}: </label>
                            <input type="password" name="secret_code" id="secret_code" class="form-control"
                                   pattern="[\d]{5}"
                                   required oninvalid="this.setCustomValidity('code rules')"
                                   value="${user_form_data['secret_code_form']}"/>

                            <c:if test="${not empty user_form_data['wrong_secret_code']}">
                                <div class="mb-3 red-color">
                                        ${wrong_code_msg}
                                </div>
                            </c:if>
                        </div>

                        <div class="col-12">
                            <button class="btn btn-primary" type="submit">${verify_code_btn}</button>
                        </div>
                    </form>

                    <div class="col-12">
                        <a href="${path}/controller?command=send_password_code">${send_code_again_btn}</a>
                    </div>

                </c:when>

                <c:when test="${user_form_data['change_password_operation_status_code'] eq '2'}">
                    <form method="post" action="${path}/controller" class="row g-3 needs-validation" novalidate>
                        <h3 class="mb-3">${change_password_title}</h3>
                        <input type="hidden" name="command" value="recovery_password_by_code"/>

                        <div class="col-md-4">
                            <label for="new_pass" class="form-label">${new_password_label}</label>
                            <input type="password" class="form-control" name="new_pass"
                                   value="${user_form_data['new_pass_form']}" id="new_pass"
                                   pattern="[\da-zA-Z\-!«»#\$%&'\(\)\*\+,\./:;=\?@_`\{\|\}~]{8,16}"
                                   required oninvalid="this.setCustomValidity('password rules')">
                            <div class="red-color">
                                <c:if test="${not empty user_form_data['wrong_new_pass_form']}">
                                    ${incorrect_data_format_msg}
                                </c:if>
                            </div>
                        </div>

                        <div class="col-md-8"></div>

                        <div class="col-md-4">
                            <label for="new_repeat_pass" class="form-label">${repeated_new_password_label}</label>
                            <input type="password" class="form-control" name="new_repeat_pass"
                                   value="${user_form_data['new_repeat_pass_form']}" id="new_repeat_pass"
                                   pattern="[\da-zA-Z\-!«»#\$%&'\(\)\*\+,\./:;=\?@_`\{\|\}~]{8,16}"
                                   required oninvalid="this.setCustomValidity('password rules')">
                            <div class="red-color">
                                <c:if test="${not empty user_form_data['wrong_new_repeat_pass_form']}">
                                    ${mismatch_password_msg}
                                </c:if>
                            </div>
                        </div>

                        <div class="col-12">
                            <button class="btn btn-primary" type="submit">${change_pass_btn}</button>
                        </div>

                    </form>
                </c:when>
            </c:choose>
            <hr/>

            <div class="col-12 mb-2">
                <a class="btn btn-primary" href="${path}/controller?command=go_to_login_page">${back_btn}</a>
            </div>
        </div>
        <div class="col-4"></div>
    </div>
</section>
</body>
</html>
