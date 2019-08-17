<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <meta charset="utf-8">
    <title>Ticket purchase</title>
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

            <%--  TICKET WAS BOUGHT --%>
            <c:if test="${ticketBought == true}">

                <div class="dark-text">Thank you for purchase!</div>
                <div class="ticket-paper">


                    <table id="table-ticket-paper">
                        <caption><span class="ticket-paper-small">TRAINS AND RAILS</span></caption>

                        <tr><td colspan="2"><span class="ticket-paper-small">------------------------------</span></td></tr>
                        <tr>
                            <td><span class="ticket-paper-small">TICKET:</span></td>
                            <td><span class="ticket-paper-big">${ticket.ticketId}</span></td>
                        </tr>
                        <tr>
                            <td><span class="ticket-paper-small">PASSENGER:</span></td>
                            <td><span class="ticket-paper-big">${ticket.user.firstName} ${ticket.user.lastName}</span></td>
                        </tr>

                        <tr>
                            <td><span class="ticket-paper-small">FROM:</span></td>
                            <td><span class="ticket-paper-big">${ticket.startStation.stationName}</span></td>
                        </tr>
                        <tr>
                            <td><span class="ticket-paper-small">TO:</span></td>
                            <td><span class="ticket-paper-big">${ticket.finishStation.stationName}</span></td>
                        </tr>

                        <tr>
                            <td><span class="ticket-paper-small">DEPARTURE:</span></td>
                            <td><span class="ticket-paper-big">${ticket.startTime}</span></td>
                        </tr>
                        <tr>
                            <td><span class="ticket-paper-small">ARRIVAL:</span></td>
                            <td><span class="ticket-paper-big">${ticket.finishTime}</span></td>
                        </tr>

                        <tr>
                            <td><span class="ticket-paper-small">ROUTE:</span></td>
                            <td><span class="ticket-paper-big">${ticket.ticketRoute.routeName}</span></td>
                        </tr>

                        <tr>
                            <td><span class="ticket-paper-small">TRAIN:</span></td>
                            <td><span class="ticket-paper-big">${ticket.ticketRoute.train.trainName}</span></td>
                        </tr>
                        <tr>
                            <td><span class="ticket-paper-small">SEAT:</span></td>
                            <td><span class="ticket-paper-big">${ticket.seatNumber}</span></td>
                        </tr>

                        <tr>
                            <td><span class="ticket-paper-small">PRICE:</span></td>
                            <td><span class="ticket-paper-big">${ticket.price}</span></td>
                        </tr>

                    </table>

                </div>

                <div class="dark-text">Discover another <a href="/journey">journey</a> or see your purchase in
                    your <a
                            href="/account?id=${userId}">account page</a></div>
            </c:if>

            <%--            PURCHASE FAIL  --%>
            <c:if test="${ticketBought == false}">
                <div class="dark-text">Purchasing was not successful!</div>
                <div class="text-bad-news">
                    Because: ${ticketBuyError}
                </div>
            </c:if>

            <%--            TICKET WAS NOT BOUGHT YET  -- GET CHOOSEN ROUTE FROM SESSION --%>
            <c:if test="${ticketBought == null}">
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

                    <form action="/tickets/buy" method="post">

                        <div class="form-element">
                            <div class="dark-text-small">Select seat number</div>
                            <select name="seatNumber" required>
                                <c:forEach var="seat" items="${sessionScope.freeSeats}">
                                    <option value="${seat}">${seat}</option>
                                </c:forEach>
                            </select>
                            <label class="error-message">${seatNumberError}</label>
                        </div>

                        <div class="form-element">
                            <div class="dark-text-small">Select user</div>
                            <select name="userId" required>
                                <c:forEach var="user" items="${sessionScope.allUsers}">
                                    <option value="${user.userId}">${user.email} ${user.firstName} ${user.lastName} </option>
                                </c:forEach>
                            </select>
                            <label class="error-message">${seatNumberError}</label>
                        </div>

                        <input type="submit" value="Buy ticket">
                    </form>

                </c:if>


            </c:if>

        </c:if>

    </div>
</div>

<%--<c:import url="footer.jsp"/>--%>

</body>
</html>
