<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Create new route</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <c:import url="nav.jsp"/>


    <div id="content-new-route">
        <div class="dark-text">Add new arc to the route:</div>
        <form action="/routes/new/arcs" method="post">

            <div class="form-element">
                <div class="dark-text-small">Arc</div>
                <select name="arcId">
                    <c:forEach var="arc" items="${arcs}">
                        <option value="${arc.arcId}">${arc.beginStation.stationName}
                            - ${arc.endStation.stationName}_${arc.length} km
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-element">
                <div class="dark-text-small">Departure time</div>
                <input id="departureTime" name="departureTime" type="datetime-local" required>
                <label class="error-message">${departureTimeError}</label>
            </div>

            <div class="form-element">
                <div class="dark-text-small">Arrival time</div>
                <input id="arrivalTime" name="arrivalTime" type="datetime-local" required>
                <label class="error-message">${arrivalTimeError}</label>
            </div>


            <input type="submit" value="Add arc">
        </form>

        <c:if test="${sessionScope.firstArcAdded != null}">
            <div class="dark-text">Added arcs:</div>

            <table id="table-new-route" border="2">
                <tr>
                    <th>Arc</th>
                    <th>Begin station</th>
                    <th>End station</th>
                    <th>Departure</th>
                    <th>Arrival</th>
                    <th>Length</th>

                </tr>

                <c:forEach var="routePath" items="${sessionScope.tempRoutePaths}" varStatus="routePathsCount">
                    <tr>

                        <td>${routePathsCount.count}</td>
                        <td>${routePath.arc.beginStation.stationName}</td>
                        <td>${routePath.arc.endStation.stationName}</td>
                        <td>${routePath.departureTime}</td>
                        <td>${routePath.arrivalTime}</td>
                        <td>${routePath.arc.length}</td>

                    </tr>
                </c:forEach>

            </table>

            <form action="/routes/new/train" method="get">
                <input type="submit" value="Next: Add train and route name">
            </form>
        </c:if>

    </div>
</div>

<%--<c:import url="footer.jsp"/>--%>

</body>
</html>
