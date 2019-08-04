<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Trains and Rails</title>
    <link type="text/css" rel="stylesheet" href="/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <header>Trains and Rails</header>
    <nav class="menu">
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/">Users</a>
                <ul>
                    <li><a href="/users">All users</a></li>
                    <li><a href="/users/new">Add new user</a></li>
                </ul>
            </li>

            <li><a href="/">Trains</a>
                <ul>
                    <li><a href="">Stations</a></li>
                    <li><a href="">Routes</a></li>
                    <li><a href="">Timeline</a></li>
                </ul>
            </li>
            <li><a href="/">Contact</a></li>
        </ul>
    </nav>
    <section id="content" class="clearfix">
        <section id="page-content">
            <div class="picture">
                <img height=60%" src="/resources/img/train_pic_1.png" alt="Train picture">
            </div>
        </section>
        <aside>

            <a class="a-small" href="">Stations</a><br>
            <a class="a-big" href="/users">Users</a><br>
            <a class="a-normal" href="/users/new">Add new user</a><br>

        </aside>
    </section>
    <%--        <div id="empty-div"></div>--%>
</div>
<footer>
    T&R 2019
</footer>

</body>
</html>
