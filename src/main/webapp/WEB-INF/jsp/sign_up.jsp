
<html>
<head>
    <title>Sign up</title>
</head>
<body>
    <form action="/passengers/new" method="post">
        <input name="name" type="text" placeholder="name"><label style="color: red; margin-left: 10px">${nameError}</label><br>
        <input name="surname" type="text" placeholder="surname"><label style="color: red; margin-left: 10px">${surnameError}</label><br>
        <input name="email" type="text" placeholder="email"><label style="color: red; margin-left: 10px">${emailError}</label><br>
        <input type="submit">
    </form>
</body>
</html>
