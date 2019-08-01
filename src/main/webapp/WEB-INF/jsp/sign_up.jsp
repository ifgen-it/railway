<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Sign up</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="wrapper">
    <form action="/users/new" method="post">
        <input name="firstName" type="text" placeholder="first name">
        <label style="color: red; margin-left: 10px">${firstNameError}</label><br>

        <input name="lastName" type="text" placeholder="last name">
        <label style="color: red; margin-left: 10px">${lastNameError}</label><br>

        <input name="birthday" type="text" placeholder="birthday">
        <label style="color: red; margin-left: 10px">${birthdayError}</label><br>

        <input name="email" type="text" placeholder="email">
        <label style="color: red; margin-left: 10px">${emailError}</label><br>

        <input name="password" type="text" placeholder="password">
        <label style="color: red; margin-left: 10px">${passwordError}</label><br>
        <input type="submit">
    </form>
</div>

<footer>
    T&R 2019
</footer>

</body>
</html>
