<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 29-Mar-22
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <title>500</title>
</head>
<body>
<div class="container-fluid">
    <div class="row mx-lg-5 my-2">
        <div class="col-2"></div>
        <div class="col-8">
            <h4 class="mt-3">Error 500</h4>
            <div class="my-2 border-bottom border-top">
                Request from: ${pageContext.errorData.requestURI} is failed<br/>
                Servlet name: ${pageContext.errorData.servletName}<br/>
                Status code: ${pageContext.errorData.statusCode}<br/>
                <c:choose>
                    <c:when test="${not empty pageContext.exception}">
                        Exception: ${pageContext.exception} <br/>
                        Message from exception: ${pageContext.exception.message} <br/>
                    </c:when>
                    <c:otherwise>
                        Message: ${requestScope['jakarta.servlet.error.message']} <br/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="col-2"></div>
    </div>
</div>
<br/><br/>
</body>
</html>