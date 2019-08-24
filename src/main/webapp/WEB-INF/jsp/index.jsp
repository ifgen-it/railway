<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            <div class="dark-text">Hello, ${user}!</div>

            <a class="a-normal" href="/routes">Routes</a><br>
            <a class="a-big" href="/timetable">Timetable</a><br>
            <a class="a-normal" href="/journey">Journey</a><br>
            <a class="a-small" href="/ticket/details">Ticket</a><br>

            <sec:authorize access="hasRole('ROLE_USER')">
                <a class="a-big" href="/account">Account</a><br>
            </sec:authorize>

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a class="a-big" href="/users">Users</a><br>
                <a class="a-small" href="/stations">Stations</a><br>
                <a class="a-normal" href="/routes/new/arcs">Create route</a><br>
                <a class="a-small" href="/arcs">Arcs</a><br>
            </sec:authorize>

        </aside>
    </section>
    <%--        <div id="empty-div"></div>--%>
</div>
<c:import url="footer.jsp"/>

</body>
</html>
