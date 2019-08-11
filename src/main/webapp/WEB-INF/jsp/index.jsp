<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:import url="nav.jsp"/>

    <section id="content" class="clearfix">
        <section id="page-content">
            <div class="picture">
                <img height=60%" src="/resources/img/train_pic_1.png" alt="Train picture">
            </div>
        </section>
        <aside>
            <a class="a-normal" href="/routes">Routes</a><br>
            <a class="a-small" href="/stations">Stations</a><br>
            <a class="a-big" href="/users">Users</a><br>
            <a class="a-normal" href="/users/new">Add new user</a><br>
            <a class="a-big" href="/timetable">Timetable</a><br>
            <a class="a-small" href="/arcs">Arcs</a><br>
            <a class="a-normal" href="/journey">Journey</a><br>

        </aside>
    </section>
    <%--        <div id="empty-div"></div>--%>
</div>
<c:import url="footer.jsp"/>

</body>
</html>
