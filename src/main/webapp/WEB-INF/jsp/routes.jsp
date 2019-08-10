
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <meta charset="utf-8">
    <title>Routes</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <c:import url="nav.jsp"/>

    <div id="content-table-routes">
        <table id="table-routes" border="2">
            <tr>
                <th>Id</th>
                <th>Route name</th>
                <th>Train name</th>
                <th>Seats amount</th>

            </tr>

            <c:forEach var="route" items="${routes}">
                <tr>
                    <td>${route.routeId}</td>
                    <td>${route.routeName}</td>
                    <td>${route.train.trainName}</td>
                    <td>${route.train.seatsAmount}</td>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<c:import url="footer.jsp"/>

</body>
</html>
