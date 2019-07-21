
<%@ page import="java.util.List" %>
<%@ page import="com.evgen.model.Passenger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String header = "Passengers:";
    List<Passenger> passengers = (List<Passenger>)request.getAttribute("passengers");
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
                for (Passenger pas : passengers){
                    out.print("<li>" + pas.getName() + " " + pas.getSurname() + " "
                            + "<i>" + pas.getEmail() + "</i>" + "</li>");
                }
            %>
        </ul>
    </div>

</body>
</html>
