<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 29-Mar-22
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500</title>
</head>
<body>
    Request from: ${pageContext.errorData.requestURI} is failed<br/>
    Servlet name: ${pageContext.errorData.servletName}<br/>
    Status code:  ${pageContext.errorData.statusCode}<br/>
    Exception: ${pageContext.exception}<br/>
    Message: ${error_msg}
<br/><br/>
</body>
</html>