<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


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

            <table id="table-buy-ticket" border="2">
                <tr>

                    <th>Name</th>
                    <th>Value</th>
                </tr>
                <tr>
                    <td>Route name</td>
                    <td>${sessionScope.ticketDetails.routeDTO.routeName}</td>
                </tr>
                <tr>
                    <td>Train name</td>
                    <td>${sessionScope.ticketDetails.routeDTO.train.trainName}</td>
                </tr>
                <tr>
                    <td>From</td>
                    <td>${sessionScope.ticketDetails.routeBeginStation.stationName}</td>
                </tr>
                <tr>
                    <td>To</td>
                    <td>${sessionScope.ticketDetails.routeEndStation.stationName}</td>
                </tr>
                <tr>
                    <td>Departure</td>
                    <td>${sessionScope.ticketDetails.routeDepartureTime}</td>
                </tr>
                <tr>
                    <td>Arrival</td>
                    <td>${sessionScope.ticketDetails.routeArrivalTime}</td>
                </tr>
                <tr>
                    <td>Price, rub</td>
                    <td>${sessionScope.ticketDetails.routePrice}</td>
                </tr>
            </table>

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