<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Sign in</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <c:import url="nav.jsp"/>

    <div id="content-add-user">
        <div class="dark-text">Login page</div>

        <form action="/login/process" method="post">

            <div class="form-element">
                <input id="email" name="email" type="email" placeholder="Email" required>
                <label class="error-message">${emailError}</label>
            </div>

            <div class="form-element">
                <input id="password" name="password" type="password" placeholder="Password" required>
                <label class="error-message">${passwordError}</label>
            </div>

            <%--            <div class="form-element">--%>
            <%--                <label class="dark-text-small">Remember password:</label>--%>
            <%--                <input id="remember-me" name="remember-me" type="checkbox" value="true">--%>
            <%--            </div>--%>

            <input type="submit" value="Login">
        </form>
    </div>
</div>

<%--<c:import url="footer.jsp"/>--%>

</body>
</html>
