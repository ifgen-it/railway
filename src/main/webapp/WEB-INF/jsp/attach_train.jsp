<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Create new route | train and route name</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <c:import url="nav.jsp"/>

    <div id="content-attach-train">

        <c:if test="${routeCreated == true}">
            <div class="dark-text">Route was successfully created!</div>
            <div class="text-good-news">Route id = ${routeId}</div>
        </c:if>

        <c:if test="${routeCreated == false}">
            <div class="text-bad-news">Route creation was failed!</div>
            <div class="text-bad-news">${routeCreationError}</div>
        </c:if>

        <c:if test="${routeCreated == null}">

            <div class="dark-text">Select train and input route name:</div>
            <form action="/routes/new/train" method="post">

                <div class="form-element">
                    <div class="dark-text-small">Train</div>
                    <select name="trainId">
                        <c:forEach var="train" items="${trains}">
                            <option value="${train.trainId}">${train.trainId}_${train.trainName}</option>
                        </c:forEach>
                    </select>
                    <label class="error-message">${trainError}</label>
                </div>

                <div class="form-element">
                    <div class="dark-text-small">Route name</div>
                    <input id="routeName" name="routeName" type="text" required>
                    <label class="error-message">${routeNameError}</label>
                </div>

                <input type="submit" value="Create route">
            </form>

            <%-- ADDED ARCS --%>

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

                            <fmt:parseDate value="${routePath.departureTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                            <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}" /></td>

                            <fmt:parseDate value="${routePath.arrivalTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                            <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}" /></td>

                            <td>${routePath.arc.length}</td>

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
