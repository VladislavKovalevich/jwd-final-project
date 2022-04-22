<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Login</title>
</head>
<body>
<div class="row py-lg-5">
    <div class="col mb-3"></div>
    <div class="col mb-3">
        <form action="controller">
            <h3 class="mb-5">Sign in</h3>
            <input type="hidden" name="command" value="login"/>

            <div class="form-outline mb-4">
                <label class="form-label" for="typeEmailX-2">Email: </label>
                <input type="text" name="email" id="typeEmailX-2" class="form-control" value="${user_form_data['email_form']}"/>
            </div>

            <div class="form-outline mb-4">
                <label class="form-label" for="typePasswordX-2">Password: </label>
                <input type="password" name="pass" id="typePasswordX-2" class="form-control" value="${user_form_data['pass_form']}"/>
            </div>

            <input class="btn btn-primary btn-lg btn-block mb-3" type="submit" value="Login" name="sub">

            <div class="mb-3">${user_form_data['wrong_email_or_pass']} ${user_form_data['not_found_user']}</div>
        </form>
    </div>
    <div class="col mb-3"></div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
