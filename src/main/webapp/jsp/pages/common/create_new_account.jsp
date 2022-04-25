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

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Title</title>
    <style>
        .red-color{
            color: red;
        }
    </style>
</head>
<body style="background-color: cadetblue">
<div class="row mb-5 py-lg-5"></div>
<div class="row py-lg-5">
    <div class="col mb-3"></div>


    <div class="col-7 mb-3 py-3 px-3" style="background-color: aliceblue">
        <h3 class="py-md-5">Create new account</h3>
        <form class="row g-3 needs-validation" novalidate>
            <input type="hidden" name="command" value="create_new_account">

            <div class="col-md-4">
                <label for="validationCustom01" class="form-label">Name</label>
                <input type="text" class="form-control" name="name" id="validationCustom01"
                       value="${user_form_data['name_form']}" required>
                <div class="red-color">
                    <c:if test="${not empty user_form_data['wrong_name_form']}">
                        ${user_form_data['wrong_name_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-md-4">
                <label for="validationCustom02" class="form-label">Surname</label>
                <input type="text" class="form-control" name="surname" id="validationCustom02"
                       value="${user_form_data['surname_form']}" required>
                <div class="red-color">
                    <c:if test="${not empty user_form_data['wrong_surname_form']}">
                        ${user_form_data['wrong_surname_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-md-4">
                <label for="validationCustomUsername" class="form-label">Login</label>
                <input type="text" class="form-control" name="login" id="validationCustomUsername"
                       value="${user_form_data['login_form']}" aria-describedby="inputGroupPrepend" required>
                <div class="red-color">
                    <c:if test="${not empty user_form_data['wrong_login_form']}">
                        ${user_form_data['wrong_login_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-md-4">
                <label for="validationCustom03" class="form-label">Email</label>
                <input type="text" class="form-control" name="email"
                       value="${user_form_data['email_form']}" id="validationCustom03" required>
                <div class="red-color">
                    <c:if test="${not empty user_form_data['wrong_email_form']}">
                        ${user_form_data['wrong_email_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-md-4">
                <label for="validationCustom04" class="form-label">Phone number</label>
                <input type="text" class="form-control" name="phone_number"
                       value="${user_form_data['phone_number_form']}" id="validationCustom04" required>
                <div class="red-color">
                    <c:if test="${not empty user_form_data['wrong_phone_number_form']}">
                        ${user_form_data['wrong_phone_number_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-md-4">
                <label for="validationCustom05" class="form-label">Passport serial number</label>
                <input type="text" class="form-control" name="serial_number"
                       value="${user_form_data['serial_number_form']}" id="validationCustom05" required>
                <div class="red-color">
                    <c:if test="${not empty user_form_data['wrong_serial_number_form']}">
                        ${user_form_data['wrong_serial_number_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-md-4">
                <label for="validationCustom06" class="form-label">Password</label>
                <input type="password" class="form-control" name="pass"
                       value="${user_form_data['pass_form']}" id="validationCustom06" required>
                <div class="red-color">
                    <c:if test="${not empty user_form_data['wrong_pass_form']}">
                        ${user_form_data['wrong_pass_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-md-4">
                <label for="validationCustom07" class="form-label">Repeated password</label>
                <input type="password" class="form-control" name="repeated_pass"
                       value="${user_form_data['repeat_pass_form']}" id="validationCustom07" required>
                <div class="red-color">
                    <c:if test="${not empty user_form_data['wrong_repeat_pass_form']}">
                        ${user_form_data['wrong_repeat_pass_form']}
                    </c:if>
                </div>
            </div>


            <div class="col-12">
                <button class="btn btn-primary" type="submit">Submit form</button>
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
