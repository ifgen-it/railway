
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


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
        <table id="table-routes" >
            <tr>
                <th>Id</th>
                <th>Route</th>
                <th>Train</th>
                <th>Seats</th>
                <th>Begin station</th>
                <th>Departure</th>
                <th>End station</th>
                <th>Arrival</th>

            </tr>

            <c:forEach var="route" items="${routes}">
                <tr>
                    <td>${route.routeDTO.routeId}</td>
                    <td>${route.routeDTO.routeName}</td>
                    <td>${route.routeDTO.train.trainName}</td>
                    <td>${route.routeDTO.train.seatsAmount}</td>
                    <td>${route.routeBeginStation.stationName}</td>

                    <fmt:parseDate value="${route.routeDepartureTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                    <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}" /></td>

                    <td>${route.routeEndStation.stationName}</td>

                    <fmt:parseDate value="${route.routeArrivalTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                    <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}" /></td>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%--<c:import url="footer.jsp"/>--%>

</body>
</html>
