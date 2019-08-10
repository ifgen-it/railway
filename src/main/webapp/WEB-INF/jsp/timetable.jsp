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
        <form action="/timetable" method="post">
            <p class="light-text">Choose station:</p>
            <div class="form-element">
                <select name="stationId" class="form-element">
                    <c:forEach var="station" items="${stations}">
                        <option value="${station.stationId}">${station.stationName}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" value="Show timetable">
        </form>

        <p class="light-text">Arrival:</p>
        <table id="table-timetable-arrival" border="2">
            <tr>
                <th>Arrival time</th>
                <th>Route</th>
                <th>Train</th>
                <th>Station</th>

            </tr>

            <c:forEach var="arrival" items="${arrivals}">
                <tr>
                    <td>${arrival.arrivalTime}</td>
                    <td>${arrival.route.routeName}</td>
                    <td>${arrival.route.train.trainName}</td>
                    <td>${arrival.arc.endStation.stationName}</td>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<c:import url="footer.jsp"/>

</body>
</html>
