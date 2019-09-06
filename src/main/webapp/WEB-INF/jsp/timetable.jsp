<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Timetable</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <c:import url="nav.jsp"/>


    <div id="content-table-timetable">
        <div class="dark-text">Select station to display timetable:</div>
        <form action="/timetable" method="post">
            <div class="form-element">
                <select name="stationId" class="form-element">
                    <c:forEach var="station" items="${stations}">
                        <option value="${station.stationId}">${station.stationName}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-element">
                <label class="dark-text-small">Only today</label>
                <input id="timetable-today" name="timetable-today" type="checkbox">
            </div>

            <input type="submit" value="Show timetable">
        </form>

        <c:if test="${stationName != null}">
            <div class="dark-text">Timetable :: ${stationName}</div>
            <table id="table-timetable-arr-dep">

                <tr>
                    <th>ARRIVAL</th>
                    <th>DEPARTURE</th>
                </tr>
                <tr>
                        <%--                    Arrivals --%>
                    <td>
                        <table id="table-timetable-arrival" border="2">
                            <tr>
                                <th>Arrival time</th>
                                <th>Route</th>
                                <th>Train</th>

                            </tr>

                            <c:forEach var="arrival" items="${arrivals}">
                                <tr>
                                    <td>${arrival.arrivalTime}</td>
                                    <td>${arrival.route.routeName}</td>
                                    <td>${arrival.route.train.trainName}</td>

                                </tr>
                            </c:forEach>

                        </table>
                    </td>
                        <%--    Departures --%>
                    <td>
                        <table id="table-timetable-departure" border="2">
                            <tr>
                                <th>Departure time</th>
                                <th>Route</th>
                                <th>Train</th>

                            </tr>

                            <c:forEach var="departure" items="${departures}">
                                <tr>
                                    <td>${departure.departureTime}</td>
                                    <td>${departure.route.routeName}</td>
                                    <td>${departure.route.train.trainName}</td>

                                </tr>
                            </c:forEach>

                        </table>
                    </td>
                </tr>
            </table>


        </c:if>

    </div>
</div>

<%--<c:import url="footer.jsp"/>--%>

</body>
</html>
