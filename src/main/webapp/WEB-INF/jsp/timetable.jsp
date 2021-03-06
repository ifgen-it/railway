<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


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
                <select name="stationId">
                    <c:forEach var="station" items="${stations}">
                        <c:if test="${stationName != null}">
                            <c:if test="${stationName != station.stationName}">
                                <option value="${station.stationId}">${station.stationName}</option>
                            </c:if>
                            <c:if test="${stationName == station.stationName}">
                                <option selected value="${station.stationId}">${station.stationName}</option>
                            </c:if>

                        </c:if>
                        <c:if test="${stationName == null}">
                            <option value="${station.stationId}">${station.stationName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>

<%--            <div class="form-element">--%>
<%--                <label class="dark-text-small">Only today</label>--%>
<%--                <input id="timetable-today" name="timetable-today" type="checkbox">--%>
<%--            </div>--%>

            <input type="submit" value="Show timetable">
        </form>

        <c:if test="${stationName != null}">
            <div class="dark-text">Timetable::${stationName}</div>
            <table id="table-timetable-arr-dep">

                <tr>
                    <th>ARRIVAL</th>
                    <th>DEPARTURE</th>
                </tr>
                <tr>
                        <%--                    Arrivals --%>
                    <td>
                        <table id="table-timetable-arrival">
                            <tr>
                                <th>Arrival</th>
                                <th>Route</th>
                                <th>Train</th>

                            </tr>

                            <c:forEach var="arrival" items="${arrivals}">
                                <tr>

                                    <fmt:parseDate value="${arrival.arrivalTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                    <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}" /></td>

                                    <td>${arrival.route.routeName}</td>
                                    <td>${arrival.route.train.trainName}</td>

                                </tr>
                            </c:forEach>

                        </table>
                    </td>
                        <%--    Departures --%>
                    <td>
                        <table id="table-timetable-departure">
                            <tr>
                                <th>Departure</th>
                                <th>Route</th>
                                <th>Train</th>

                            </tr>

                            <c:forEach var="departure" items="${departures}">
                                <tr>
                                    <fmt:parseDate value="${departure.departureTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                    <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}" /></td>

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
