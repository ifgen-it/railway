<%@ page import="java.util.List" %>
<%@ page import="com.evgen.model.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String header = "Users:";
    List<User> users = (List<User>) request.getAttribute("users");
%>


<html>
<head>
    <title>${title}</title>
</head>
<body>
<h1>${title}</h1>
<p>Today <%=new java.util.Date()%>
</p>

<h2><%=header%>
</h2>

<div>
    <table border="1" bgcolor="#7fffd4">
        <tr>
            <th>Id</th>
            <th>Role Id</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Birthday</th>
            <th>Email</th>
            <th>Password</th>
            <th>Created</th>

        </tr>

    <%
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

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
                    + "<td>" + formatter.format(user.getCreated()) + "</td>"
                    + "</tr>");
        }
    %>
    </table>
</div>

</body>
</html>
