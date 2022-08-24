<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 06-Apr-22
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="config.pagecontent"/>

<fmt:message key="message.footer" var="footer"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container position-relative p-3 mb-2 bg-secondary bg-opacity-75 text-white text-center" id="foot_position">
    <div>${footer}</div>
</div>
<script type="text/javascript">
    if (document.body.scrollHeight > window.innerHeight) {
        document.getElementById("foot_position").className = "container position-relative p-3 mb-2 bg-secondary bg-opacity-75 text-white text-center";
    } else {
        document.getElementById("foot_position").className = "container position-absolute fixed-bottom p-3 mb-2 bg-secondary bg-opacity-75 text-white text-center";
    }
</script>
</body>
</html>
