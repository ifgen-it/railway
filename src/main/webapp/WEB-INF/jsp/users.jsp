<%@ page import="java.util.List" %>
<%@ page import="com.evgen.dto.user.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


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
<div class="wrapper">
    <c:import url="nav.jsp"/>

    <div id="content-table-users">
        <form action="/users" method="post">
            <table id="table-users">
                <tr>
                    <th>Id</th>
                    <th>Role</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Birthday</th>
                    <th>Email</th>
<%--                    <th>Password</th>--%>

                    <th>Admin</th>
                    <th>User</th>
                    <th>Delete</th>

                </tr>

                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.userId}</td>

                        <c:if test="${user.role.roleName == 'ROLE_ADMIN'}">
                            <td>Admin</td>
                        </c:if>

                        <c:if test="${user.role.roleName == 'ROLE_USER'}">
                            <td>User</td>
                        </c:if>

                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>

                        <fmt:parseDate value="${user.birthday}" pattern="yyyy-MM-dd HH:mm:ss.SSS" var="parsedDateTime" type="both" />
                        <td><fmt:formatDate pattern="dd-MM-yyyy" value="${parsedDateTime}" /></td>

                        <td>${user.email}</td>
<%--                        <td>${user.password}</td>--%>

                        <td align="center">
                            <input type="checkbox" name="make-admin" value=${user.userId}>
                        </td>

                        <td align="center">
                            <input type="checkbox" name="make-user" value=${user.userId}>
                        </td>

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
            <input type="submit" value="Apply">
        </form>
    </div>
</div>

<%--<c:import url="footer.jsp"/>--%>

</body>
</html>
