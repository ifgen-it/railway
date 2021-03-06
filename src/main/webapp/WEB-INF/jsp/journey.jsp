<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <meta charset="utf-8">
    <title>Journey</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <c:import url="nav.jsp"/>

    <div id="content-table-journey">

        <%--        SEARCH FORM  --%>

        <div class="dark-text">Select stations for journey and departure date range:</div>

        <form action="/journey" method="post">
            <div class="form-element">
                <div class="dark-text-small">Begin station</div>
                <select name="beginStation">
                    <option value="0" selected>Select station</option>
                    <c:forEach var="station" items="${stations}">
                        <option value="${station.stationId}">${station.stationName}</option>
                    </c:forEach>
                </select>
                <label class="error-message">${beginStationError}</label>
            </div>

            <div class="form-element">
                <div class="dark-text-small">End station</div>
                <select name="endStation">
                    <option value="0" selected>Select station</option>
                    <c:forEach var="station" items="${stations}">
                        <option value="${station.stationId}">${station.stationName}</option>
                    </c:forEach>
                </select>
                <label class="error-message">${endStationError}</label>
            </div>

            <div class="form-element">
                <div class="dark-text-small">Date From</div>
                <input id="dateFrom" name="dateFrom" type="date" required>
                <label class="error-message">${dateFromError}</label>
            </div>

            <div class="form-element">
                <div class="dark-text-small">Date To</div>
                <input id="dateTo" name="dateTo" type="date" required>
                <label class="error-message">${dateToError}</label>
            </div>

            <input type="submit" value="Find routes">
        </form>

        <%--            FOUND ROUTES --%>
        <c:if test="${futureRoutes != null}">
            <div class="dark-text">${beginStationName} - ${endStationName}</div>
            <div class="dark-text-small">Departure from ${dateFrom} to ${dateTo}</div>

            <c:if test="${futureRoutesNum == 0}">
                <br>
                <div class="text-bad-news">
                    There is no trains to ride
                </div>
            </c:if>
            <c:if test="${futureRoutesNum > 0}">
                <br>
                <div class="text-good-news">Trains to ride</div>
                <table id="table-journey-future">
                    <tr>
                        <th>Route</th>
                        <th>Train</th>
                        <th>Begin station</th>
                        <th>Departure time</th>
                        <th>End station</th>
                        <th>Arrival time</th>
                        <th>Length</th>
                        <th>Price</th>
                        <th>Ticket</th>
                    </tr>

                    <c:forEach var="route" items="${futureRoutes}">
                        <tr>

                            <td>${route.routeDTO.routeName}</td>
                            <td>${route.routeDTO.train.trainName}</td>
                            <td>${route.routeBeginStation.stationName}</td>

                            <fmt:parseDate value="${route.routeDepartureTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                            <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}" /></td>

                            <td>${route.routeEndStation.stationName}</td>

                            <fmt:parseDate value="${route.routeArrivalTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                            <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}" /></td>

                            <td align="center">${route.routeLength}</td>
                            <td align="center">${route.routePrice}</td>
                            <td align="center"><a
                                    href="/ticket/details?routeId=${route.routeDTO.routeId}&startStationId=${route.routeBeginStation.stationId}&finishStationId=${route.routeEndStation.stationId}">Buy</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

            </c:if>
            <c:if test="${pastRoutesNum > 0}">
                <br>
                <div class="text-good-news">Departed trains</div>

                <table id="table-journey-past">
                    <tr>
                        <th>Route</th>
                        <th>Train</th>
                        <th>Begin station</th>
                        <th>Departure time</th>
                        <th>End station</th>
                        <th>Arrival time</th>
                        <th>Length</th>
                        <th>Price</th>
                    </tr>

                    <c:forEach var="route" items="${pastRoutes}">
                        <tr>

                            <td>${route.routeDTO.routeName}</td>
                            <td>${route.routeDTO.train.trainName}</td>
                            <td>${route.routeBeginStation.stationName}</td>

                            <fmt:parseDate value="${route.routeDepartureTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                            <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}" /></td>

                            <td>${route.routeEndStation.stationName}</td>

                            <fmt:parseDate value="${route.routeArrivalTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                            <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}" /></td>

                            <td align="center">${route.routeLength}</td>
                            <td align="center">${route.routePrice}</td>

                        </tr>
                    </c:forEach>
                </table>

            </c:if>

        </c:if>


    </div>
</div>

<%--<c:import url="footer.jsp"/>--%>

</body>
</html>
