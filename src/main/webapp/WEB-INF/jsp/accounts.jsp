<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.evgen.dto.user.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
    <meta charset="utf-8">
    <title>My account</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <c:import url="nav.jsp"/>

    <div id="content-accounts">

        <div class="dark-text">Select user to display account info:</div>

        <form action="/account" method="get">
            <div class="form-element">
                <select name="id" class="form-element" required>

                    <c:forEach var="user" items="${users}">
                        <option value="${user.userId}">${user.userId}_${user.lastName} ${user.firstName}_${user.email}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" value="Show account info">
        </form>

    </div>
</div>

<c:import url="footer.jsp"/>

</body>
</html>
