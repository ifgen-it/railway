<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Passengers</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <c:import url="nav.jsp"/>

    <div id="content-passengers">
        <div class="dark-text">Select route to display passengers:</div>

        <form action="/passengers" method="post">
            <div class="form-element">
                <select name="routeId" class="form-element">
                    <option value="0">All routes</option>

                    <c:forEach var="route" items="${routesExt}">
                        <option value="${route.routeDTO.routeId}">${route.routeDTO.routeId}_${route.routeDTO.routeName}_${route.routeDepartureTime}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" value="Show passengers">
        </form>

        <c:if test="${tickets != null}">
            <c:if test="${allRoutes == true}">
                <div class="dark-text">Passengers on all routes :</div>
            </c:if>

            <c:if test="${allRoutes == false}">
                <div class="dark-text">Passengers on the route : ${searchRoute.routeDTO.routeName} ${searchRoute.routeDepartureTime}</div>
            </c:if>

            <table id="table-passengers" border="2">
                <tr>
                    <th>Route Id</th>
                    <th>Route name</th>
                    <th>Train name</th>
                    <th>Seat number</th>
                    <th>Start station</th>
                    <th>Finish station</th>
                    <th>Start time</th>
                    <th>Finish time</th>
                    <th>Price</th>
                    <th>Ticket Id</th>
                    <th>Passenger</th>



                </tr>

                <c:forEach var="ticket" items="${tickets}">
                    <tr>
                        <td>${ticket.ticketRoute.routeId}</td>
                        <td>${ticket.ticketRoute.routeName}</td>
                        <td>${ticket.ticketRoute.train.trainName}</td>
                        <td>${ticket.seatNumber}</td>
                        <td>${ticket.startStation.stationName}</td>
                        <td>${ticket.finishStation.stationName}</td>
                        <td>${ticket.startTime}</td>
                        <td>${ticket.finishTime}</td>
                        <td>${ticket.price}</td>
                        <td>${ticket.ticketId}</td>
                        <td><a href="/accounts?id=${ticket.user.userId}">${ticket.user.lastName} ${ticket.user.firstName} ${ticket.user.email}</a></td>

                    </tr>
                </c:forEach>

            </table>


        </c:if>

    </div>
</div>

<%--<c:import url="footer.jsp"/>--%>

</body>
</html>
