<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <meta charset="utf-8">
    <title>Ticket details</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <c:import url="nav.jsp"/>

    <div id="content-buy-ticket">

        <%--        ERROR HANDLERS --%>
        <c:if test="${errorParse == true}">
            <div class="dark-text">Something went wrong. Please select again route for your <a
                    href="/journey">journey</a>!
            </div>
        </c:if>

        <%--    WENT FROM LINK HERE --%>

        <c:if test="${errorGotNull == true && sessionScope.ticketDetails == null}">
            <div class="dark-text">Select route for your <a href="/journey">journey</a> first!</div>
        </c:if>

        <%--        GET INFO ABOUT ROUTE TO BUY  --%>

        <c:if test="${ (errorGotNull == false && errorParse == false) || (errorGotNull == true && sessionScope.ticketDetails != null)}">


            <%--           GET CHOOSEN ROUTE FROM SESSION --%>

            <div class="dark-text">Ticket details</div>

            <table id="table-buy-ticket">
                <tr>

                    <th>Category</th>
                    <th>Information</th>
                </tr>
                <tr>
                    <td>ROUTE ........</td>
                    <td>${sessionScope.ticketDetails.routeDTO.routeName}</td>
                </tr>
                <tr>
                    <td>TRAIN ........</td>
                    <td>${sessionScope.ticketDetails.routeDTO.train.trainName}</td>
                </tr>
                <tr>
                    <td>FROM .........</td>
                    <td>${sessionScope.ticketDetails.routeBeginStation.stationName}</td>
                </tr>
                <tr>
                    <td>TO ...........</td>
                    <td>${sessionScope.ticketDetails.routeEndStation.stationName}</td>
                </tr>
                <tr>
                    <td>DEPARTURE ....</td>
                    <fmt:parseDate value="${sessionScope.ticketDetails.routeDepartureTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                    <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}" /></td>

                </tr>
                <tr>
                    <td>ARRIVAL ......</td>
                    <fmt:parseDate value="${sessionScope.ticketDetails.routeArrivalTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                    <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}" /></td>

                </tr>
                <tr>
                    <td>PRICE ........</td>
                    <td>${sessionScope.ticketDetails.routePrice}</td>
                </tr>
            </table>
            <br>

            <%--                IF THERE IS NO AVALIABLE SEATS --%>
            <c:if test="${sessionScope.freeSeatsAmount == 0}">
                <div class="text-bad-news">
                    <br>
                    There is no available seats for purchase on this route!
                </div>
            </c:if>

            <%--                IF THERE ARE AVALIABLE SEATS --%>
            <c:if test="${sessionScope.freeSeatsAmount > 0}">

                <form action="/ticket/buy" method="post">

                    <div class="form-element">
                        <div class="dark-text-small">Select seat number</div>
                        <select name="seatNumber" required>
                            <c:forEach var="seat" items="${sessionScope.freeSeats}">
                                <option value="${seat}">${seat}</option>
                            </c:forEach>
                        </select>
                        <label class="error-message">${seatNumberError}</label>
                    </div>

                    <div class="dark-text-small">
                        <sec:authorize access="isAuthenticated()">
                            Passenger: <sec:authentication property="principal.firstName"/> <sec:authentication
                                property="principal.lastName"/>
                        </sec:authorize>
                        <sec:authorize access="!isAuthenticated()">
                            Passenger: Guest
                        </sec:authorize>
                    </div>

                    <input type="submit" value="Buy ticket">
                </form>

            </c:if>


        </c:if>

    </div>
</div>

<%--<c:import url="footer.jsp"/>--%>

</body>
</html>
