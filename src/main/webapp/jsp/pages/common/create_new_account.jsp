<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 12-Apr-22
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>

<fmt:message key="title.create_new_account" var="title"/>
<fmt:message key="label.user_email" var="email"/>
<fmt:message key="label.user_login" var="login"/>
<fmt:message key="label.user_mobile_phone" var="mobile_phone"/>
<fmt:message key="label.user_name" var="name"/>
<fmt:message key="label.user_surname" var="surname"/>
<fmt:message key="label.user_passport_serial_number" var="passport_serial_number"/>
<fmt:message key="label.user_password" var="password"/>
<fmt:message key="label.user_repeated_password" var="repeated_password"/>
<fmt:message key="message.incorrect_email" var="incorrect_email"/>
<fmt:message key="message.incorrect_data_format" var="incorrect_data_format"/>
<fmt:message key="message.mismatch_password" var="mismatch_password"/>
<fmt:message key="button.create_new_account" var="create_new_account_btn"/>
<fmt:message key="button.back_to_main" var="back_btn"/>

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
</head>
<body class="background-theme">
<section class="container-fluid">
    <div class="row mb-5 py-lg-5"></div>
    <div class="row py-lg-5">
        <div class="col mb-3"></div>


        <div class="col-7 mb-3 py-3 px-3">
            <h3 class="py-md-5">${title}</h3>
            <form method="post" action="${path}/controller" class="row g-3 needs-validation" novalidate>
                <input type="hidden" name="command" value="create_new_account">

                <div class="col-md-4">
                    <label for="name" class="form-label">${name}</label>
                    <input type="text" class="form-control" name="name" id="name"
                           value="${user_form_data['name_form']}" required>
                    <div class="red-color">
                        <c:if test="${not empty user_form_data['wrong_name_form']}">
                            ${incorrect_data_format}
                        </c:if>
                    </div>
                </div>

                <div class="col-md-4">
                    <label for="surname" class="form-label">${surname}</label>
                    <input type="text" class="form-control" name="surname" id="surname"
                           value="${user_form_data['surname_form']}" required>
                    <div class="red-color">
                        <c:if test="${not empty user_form_data['wrong_surname_form']}">
                            ${incorrect_data_format}
                        </c:if>
                    </div>
                </div>

                <div class="col-md-4">
                    <label for="login" class="form-label">${login}</label>
                    <input type="text" class="form-control" name="login" id="login"
                           value="${user_form_data['login_form']}" aria-describedby="inputGroupPrepend" required>
                    <div class="red-color">
                        <c:if test="${not empty user_form_data['wrong_login_form']}">
                            ${incorrect_data_format}
                        </c:if>
                    </div>
                </div>

                <div class="col-md-4">
                    <label for="email" class="form-label">${email}</label>
                    <input type="text" class="form-control" name="email"
                           value="${user_form_data['email_form']}" id="email" required>
                    <div class="red-color">
                        <c:if test="${not empty user_form_data['wrong_email_form']}">
                            ${incorrect_data_format}
                        </c:if>
                        <c:if test="${not empty user_form_data['wrong_email_exists']}">
                            ${incorrect_email}
                        </c:if>
                    </div>
                </div>

                <div class="col-md-4">
                    <label for="mobile_phone" class="form-label">${mobile_phone}</label>
                    <input type="text" class="form-control" name="phone_number"
                           value="${user_form_data['phone_number_form']}" id="mobile_phone" required>
                    <div class="red-color">
                        <c:if test="${not empty user_form_data['wrong_phone_number_form']}">
                            ${incorrect_data_format}
                        </c:if>
                    </div>
                </div>

                <div class="col-md-4">
                    <label for="passport_serial_number" class="form-label">${passport_serial_number}</label>
                    <input type="text" class="form-control" name="serial_number"
                           value="${user_form_data['serial_number_form']}" id="passport_serial_number" required>
                    <div class="red-color">
                        <c:if test="${not empty user_form_data['wrong_serial_number_form']}">
                            ${incorrect_data_format}
                        </c:if>
                    </div>
                </div>

                <div class="col-md-4">
                    <label for="password" class="form-label">${password}</label>
                    <input type="password" class="form-control" name="pass"
                           value="${user_form_data['pass_form']}" id="password" required>
                    <div class="red-color">
                        <c:if test="${not empty user_form_data['wrong_pass_form']}">
                            ${incorrect_data_format}
                        </c:if>
                    </div>
                </div>

                <div class="col-md-4">
                    <label for="repeated_password" class="form-label">${repeated_password}</label>
                    <input type="password" class="form-control" name="repeated_pass"
                           value="${user_form_data['repeat_pass_form']}" id="repeated_password" required>
                    <div class="red-color">
                        <c:if test="${not empty user_form_data['wrong_repeat_pass_form']}">
                            ${mismatch_password}
                        </c:if>
                    </div>
                </div>


                <div class="col-12">
                    <button class="btn btn-primary" type="submit">${create_new_account_btn}</button>
                </div>
            </form>

            <hr/>

            <form action="controller">
                <input type="hidden" name="command" value="go_to_main_page">
                <div class="col-12">
                    <button class="btn btn-primary" type="submit">${back_btn}</button>
                </div>
            </form>

        </div>


        <div class="col mb-3"></div>
    </div>
</section>
</body>
</html>
