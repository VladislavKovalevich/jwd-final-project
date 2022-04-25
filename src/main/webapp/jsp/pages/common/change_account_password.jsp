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

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Change account password</title>
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
        <h3 class="py-md-2">Change account password</h3>
        <form class="row g-3 needs-validation" novalidate>
            <input type="hidden" name="command" value="change_account_password">

            <div class="col-md-10">
                <div>
                    Email: ${user_email}
                </div>
            </div>

            <div class="col-md-10">
                <div>
                    Login: ${user_login}
                </div>
            </div>

            <div class="col-md-4">
                <label for="validationCustom03" class="form-label">Old password</label>
                <input type="password" class="form-control" name="pass"
                       value="${user_form_data['pass_form']}" id="validationCustom03" required>
                <div class="red-color">
                    <c:if test="${not empty user_form_data['wrong_pass_form']}">
                        ${user_form_data['wrong_pass_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-md-8"></div>

            <div class="col-md-4">
                <label for="validationCustom04" class="form-label">New Password</label>
                <input type="password" class="form-control" name="new_pass"
                       value="${user_form_data['new_pass_form']}" id="validationCustom04" required>
                <div class="red-color">
                    <c:if test="${not empty user_form_data['wrong_new_pass_form']}">
                        ${user_form_data['wrong_new_pass_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-md-8"></div>

            <div class="col-md-4">
                <label for="validationCustom05" class="form-label">Repeat new password</label>
                <input type="password" class="form-control" name="new_repeat_pass"
                       value="${user_form_data['new_repeat_pass_form']}" id="validationCustom05" required>
                <div class="red-color">
                    <c:if test="${not empty user_form_data['wrong_new_repeat_pass_form']}">
                        ${user_form_data['wrong_new_repeat_pass_form']}
                    </c:if>
                </div>
            </div>

            <div class="col-md-8"></div>

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
