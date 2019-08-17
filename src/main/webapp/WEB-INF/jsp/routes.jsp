
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
        <div class="dark-text">Routes</div>
        <table id="table-routes" border="2">
            <tr>
                <th>Id</th>
                <th>Route name</th>
                <th>Train name</th>
                <th>Seats amount</th>
                <th>Begin station</th>
                <th>Departure time</th>
                <th>End station</th>
                <th>Arrival time</th>

            </tr>

            <c:forEach var="route" items="${routes}">
                <tr>
                    <td>${route.routeDTO.routeId}</td>
                    <td>${route.routeDTO.routeName}</td>
                    <td>${route.routeDTO.train.trainName}</td>
                    <td>${route.routeDTO.train.seatsAmount}</td>
                    <td>${route.routeBeginStation.stationName}</td>
                    <td>${route.routeDepartureTime}</td>
<%--                    <td><fmt:formatDate type="time" value="${route.routeDepartureTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
                    <td>${route.routeEndStation.stationName}</td>
                    <td>${route.routeArrivalTime}</td>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<c:import url="footer.jsp"/>

</body>
</html>
