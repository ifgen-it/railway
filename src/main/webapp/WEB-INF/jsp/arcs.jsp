<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <meta charset="utf-8">
    <title>Arcs</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="long-wrapper">
    <c:import url="nav.jsp"/>

    <div id="content-table-arcs">

        <%--        ADD NEW ARC --%>

        <div class="dark-text">Fill inputs to add new arc:</div>

        <form action="/arcs" method="post">
            <div class="form-element">
                <div class="dark-text-small">Begin station</div>
                <select name="beginStation">
                    <c:forEach var="station" items="${stations}">
                        <option value="${station.stationId}">${station.stationName}</option>
                    </c:forEach>
                </select>
                <label class="error-message">${beginStationError}</label>
            </div>

            <div class="form-element">
                <div class="dark-text-small">End station</div>
                <select name="endStation">
                    <c:forEach var="station" items="${stations}">
                        <option value="${station.stationId}">${station.stationName}</option>
                    </c:forEach>
                </select>
                <label class="error-message">${endStationError}</label>
            </div>

            <div class="form-element">
                <div class="dark-text-small">Length</div>
                <input id="length" name="length" type="number" placeholder="Arc length in km" required>
                <label class="error-message">${lengthError}</label>
            </div>

            <input type="submit" value="Add arc">
        </form>


        <%--            SHOW ARCS --%>
        <table id="table-arcs">
            <tr>
                <th>Id</th>
                <th>Begin station</th>
                <th>End station</th>
                <th>Length</th>

            </tr>

            <c:forEach var="arc" items="${arcs}">
                <tr>
                    <td>${arc.arcId}</td>
                    <td>${arc.beginStation.stationName}</td>
                    <td>${arc.endStation.stationName}</td>
                    <td>${arc.length}</td>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%--<c:import url="footer.jsp"/>--%>

</body>
</html>
