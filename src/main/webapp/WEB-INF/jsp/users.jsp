<%@ page import="java.util.List" %>
<%@ page import="com.evgen.model.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<User> users = (List<User>) request.getAttribute("users");
%>


<html>
<head>
    <meta charset="utf-8">
    <title>Users</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="long-wrapper">
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
    <div id="content-table-users">
        <table id="table-users" border="2">
            <tr>
                <th>Id</th>
                <th>Role Id</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Birthday</th>
                <th>Email</th>
                <th>Password</th>

            </tr>

            <%

                if (users.size() == 0)
                    out.print("<p>" + "There is no users yet" + "</p>");
                for (User user : users) {
                    out.print("<tr>"
                            + "<td>" + user.getUserId() + "</td>"
                            + "<td>" + user.getRoleId() + "</td>"
                            + "<td>" + user.getFirstName() + "</td>"
                            + "<td>" + user.getLastName() + "</td>"
                            + "<td>" + new SimpleDateFormat("dd-MM-yyyy").format(user.getBirthday()) + "</td>"
                            + "<td>" + user.getEmail() + "</td>"
                            + "<td>" + user.getPassword() + "</td>"
                            + "</tr>");
                }
            %>
        </table>
    </div>
</div>

<footer>
    T&R 2019
</footer>

</body>
</html>
