<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Sign up</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <c:import url="nav.jsp"/>

    <div id="content-add-user">
        <p>Fill inputs to add new user:</p>
        <form action="/users/new" method="post">
            <div class="form-element">
                <input id="firstName" name="firstName" type="text" placeholder="First name" required>
                <label class="error-message">${firstNameError}</label>
            </div>

            <div class="form-element">
                <input id="lastName" name="lastName" type="text" placeholder="Last name" required>
                <label class="error-message">${lastNameError}</label>
            </div>

            <div class="form-element">
                <select name="userRole">
                    <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                    <option value="ROLE_USER" selected>ROLE_USER</option>
                </select>
            </div>

            <div class="form-element">
                <input id="birthday" name="birthday" type="date" placeholder="Birthday" required>
                <label class="error-message">${birthdayError}</label>
            </div>

            <div class="form-element">
                <input id="email" name="email" type="email" placeholder="Email" required>
                <label class="error-message">${emailError}</label>
            </div>

            <div class="form-element">
                <input id="password" name="password" type="text" placeholder="Password" required>
                <label class="error-message">${passwordError}</label>
            </div>

            <input type="submit" value="Submit">
        </form>
    </div>
</div>

<c:import url="footer.jsp"/>

</body>
</html>
