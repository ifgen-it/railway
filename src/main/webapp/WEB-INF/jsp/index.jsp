<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Trains and Rails</title>
    <link type="text/css" rel="stylesheet" href="/resources/css/style.css">
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
            <div>
                Page content
            </div>
        </section>
        <aside>
            Side menu
        </aside>
    </section>
    <%--        <div id="empty-div"></div>--%>
</div>
<footer>
    T&R 2019
</footer>

</body>
</html>
