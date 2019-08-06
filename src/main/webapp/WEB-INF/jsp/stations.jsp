<%@ page import="java.util.List" %>
<%@ page import="com.evgen.entity.user.UserEntity" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
//    List<UserEntity> users = (List<UserEntity>) request.getAttribute("users");
%>


<html>
<head>
    <meta charset="utf-8">
    <title>Stations</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="long-wrapper">
    <c:import url="nav.jsp"/>

    <div id="content-table-stations">
        <table id="table-stations" border="2">
            <tr>
                <th>Id</th>
                <th>Station name</th>

            </tr>

            <%

//                if (users.size() == 0)
//                    out.print("<p>" + "There is no users yet" + "</p>");
//                for (UserEntity user : users) {
//                    out.print("<tr>"
//                            + "<td>" + user.getUserId() + "</td>"
//                            + "<td>" + user.getRoleId() + "</td>"
//                            + "<td>" + user.getFirstName() + "</td>"
//                            + "<td>" + user.getLastName() + "</td>"
//                            + "<td>" + new SimpleDateFormat("dd-MM-yyyy").format(user.getBirthday()) + "</td>"
//                            + "<td>" + user.getEmail() + "</td>"
//                            + "<td>" + user.getPassword() + "</td>"
//                            + "</tr>");
//                }
            %>
        </table>
    </div>
</div>

<c:import url="footer.jsp"/>

</body>
</html>
