
<%@ page import="java.util.List" %>
<%@ page import="com.evgen.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String header = "Users:";
    List<User> users = (List<User>)request.getAttribute("users");
%>


<html>
<head>
    <title>${title}</title>
</head>
<body>
    <h1>${title}</h1>
    <p>Today <%=new java.util.Date()%></p>

    <h2><%=header%></h2>

    <div>
        <ul>
            <%
                if (users.size() == 0)
                    out.print("<p>" + "There is no users yet" + "</p>");
                for (User user : users){
                    out.print("<li>" + user.getFirstName() + " " + user.getLastName() + " "
                            + "<i>" + user.getEmail() + "</i>" + "</li>");
                }
            %>
        </ul>
    </div>

</body>
</html>
