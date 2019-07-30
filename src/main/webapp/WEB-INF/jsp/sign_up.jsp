<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
    <form action="/users/new" method="post">
        <input name="firstName" type="text" placeholder="first name"><label style="color: red; margin-left: 10px">${nameError}</label><br>
        <input name="lastName" type="text" placeholder="last name"><label style="color: red; margin-left: 10px">${surnameError}</label><br>
        <input name="email" type="text" placeholder="email"><label style="color: red; margin-left: 10px">${emailError}</label><br>
        <input type="submit">
    </form>
</body>
</html>
