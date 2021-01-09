<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <meta charset="utf-8">
    <title>Account</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <c:import url="nav.jsp"/>

    <div id="content-account">

        <div class="dark-text">${user.firstName} ${user.lastName}</div>
        <br>
        <div class="dark-text-small">Email: ${user.email}</div>

<%--        <fmt:parseDate value="${user.birthday}" pattern="yyyy-MM-dd HH:mm:ss.SSS" var="parsedDateTime" type="both" />--%>
<%--        <div class="dark-text-small">Birthday: <fmt:formatDate pattern="dd-MM-yyyy" value="${parsedDateTime}" /></div>--%>

        <div class="dark-text-small">Birthday: ${user.birthday}</div>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="dark-text-small">Role: Admin</div>
        </sec:authorize>

        <br>
        <div class="dark-text-small">Tickets:</div>

        <table id="table-account">
            <tr>
                <th>Ticket</th>
                <th>Route</th>
                <th>Route Id</th>
                <th>Train</th>
                <th>Seat</th>
                <th>Start station</th>
                <th>Finish station</th>
                <th>Start time</th>
                <th>Finish time</th>
                <th>Price</th>

            </tr>

            <c:forEach var="ticket" items="${tickets}">
                <tr>
                    <td>${ticket.ticketId}</td>
                    <td>${ticket.ticketRoute.routeName}</td>
                    <td>${ticket.ticketRoute.routeId}</td>
                    <td>${ticket.ticketRoute.train.trainName}</td>
                    <td>${ticket.seatNumber}</td>
                    <td>${ticket.startStation.stationName}</td>
                    <td>${ticket.finishStation.stationName}</td>

                    <fmt:parseDate value="${ticket.startTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                    <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}" /></td>

                    <fmt:parseDate value="${ticket.finishTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                    <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}" /></td>

                    <td>${ticket.price}</td>

                </tr>
            </c:forEach>

        </table>


    </div>
</div>

<%--<c:import url="footer.jsp"/>--%>

</body>
</html>
