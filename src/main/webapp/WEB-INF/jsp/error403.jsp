<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Something went wrong</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <c:import url="nav.jsp"/>

    <div class="content-error">
        <div class="dark-text">Something went wrong!</div>
        <br>

        <div class="text-bad-news">403 Forbidden</div>
        <div class="text-bad-news">The request was valid, but the server is refusing action.
            You might not have the necessary permissions for a resource, or may need an account of some sort.</div>
        <br>

        <div class="error-exception">
            Requested URL: ${url}
            <br>
            Error: ${exception}
        </div>

        <!--
        <c:forEach items="${exception.stackTrace}" var="ste">${ste}
        </c:forEach>
        -->

    </div>
</div>

<%--<c:import url="footer.jsp"/>--%>

</body>
</html>
