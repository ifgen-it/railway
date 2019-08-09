<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.evgen.dto.user.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    List<UserDTO> users = (List<UserDTO>) request.getAttribute("users");
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
    <c:import url="nav.jsp"/>

    <div id="content-table-users">
        <form action="/users" method="post">
            <table id="table-users" border="2">
                <tr>
                    <th>Id</th>
                    <th>Role</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Birthday</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Delete user</th>

                </tr>

                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.userId}</td>
                        <td>${user.role.roleName}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.birthday}</td>
                        <td>${user.email}</td>
                        <td>${user.password}</td>
                        <td align="center">
                            <input type="checkbox" name="delete-user" value=${user.userId}>
                        </td>

                    </tr>
                </c:forEach>

                <%--            <%--%>

                <%--                if (users.size() == 0)--%>
                <%--                    out.print("<p>" + "There is no users yet" + "</p>");--%>
                <%--                for (UserDTO user : users) {--%>
                <%--                    out.print("<tr>"--%>
                <%--                            + "<td>" + user.getUserId() + "</td>"--%>
                <%--                            + "<td>" + user.getRole().getRoleName() + "</td>"--%>
                <%--                            + "<td>" + user.getFirstName() + "</td>"--%>
                <%--                            + "<td>" + user.getLastName() + "</td>"--%>
                <%--                            + "<td>" + new SimpleDateFormat("dd-MM-yyyy").format(user.getBirthday()) + "</td>"--%>
                <%--                            + "<td>" + user.getEmail() + "</td>"--%>
                <%--                            + "<td>" + user.getPassword() + "</td>"--%>
                <%--                            --%>
                <%--                            + "</tr>");--%>
                <%--                }--%>
                <%--            %>--%>
            </table>
            <input type="submit" value="Delete users">
        </form>
    </div>
</div>

<c:import url="footer.jsp"/>

</body>
</html>
