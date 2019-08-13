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

            <c:if test="${ticketBought == true}">
                <div class="dark-text">Thank you for purchase!</div>
                <div class="dark-text">Discover another <a href="/journey">journey</a> or see your purchase in your <a href="/account">account page</a></div>
            </c:if>

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

                <form action="/tickets/buy" method="post">
                    <input type="submit" value="Buy ticket">
                </form>
            </c:if>

        </c:if>


        <%--        <div class="dark-text">Select stations for journey and departure date range:</div>--%>

        <%--        <form action="/journey" method="post">--%>
        <%--            <div class="form-element">--%>
        <%--                <div class="dark-text-small">Begin station</div>--%>
        <%--                <select name="beginStation">--%>
        <%--                    <option value="0" selected>Select station</option>--%>
        <%--                    <c:forEach var="station" items="${stations}">--%>
        <%--                        <option value="${station.stationId}">${station.stationName}</option>--%>
        <%--                    </c:forEach>--%>
        <%--                </select>--%>
        <%--                <label class="error-message">${beginStationError}</label>--%>
        <%--            </div>--%>

        <%--            <div class="form-element">--%>
        <%--                <div class="dark-text-small">End station</div>--%>
        <%--                <select name="endStation">--%>
        <%--                    <option value="0" selected>Select station</option>--%>
        <%--                    <c:forEach var="station" items="${stations}">--%>
        <%--                        <option value="${station.stationId}">${station.stationName}</option>--%>
        <%--                    </c:forEach>--%>
        <%--                </select>--%>
        <%--                <label class="error-message">${endStationError}</label>--%>
        <%--            </div>--%>

        <%--            <div class="form-element">--%>
        <%--                <div class="dark-text-small">Date From</div>--%>
        <%--                <input id="dateFrom" name="dateFrom" type="date" required>--%>
        <%--                <label class="error-message">${dateFromError}</label>--%>
        <%--            </div>--%>

        <%--            <div class="form-element">--%>
        <%--                <div class="dark-text-small">Date To</div>--%>
        <%--                <input id="dateTo" name="dateTo" type="date" required>--%>
        <%--                <label class="error-message">${dateToError}</label>--%>
        <%--            </div>--%>

        <%--            <input type="submit" value="Find routes">--%>
        <%--        </form>--%>


        <%--            FOUND ROUTES --%>


        <%--        <c:if test="${directRoutes != null}">--%>
        <%--            <div class="dark-text">${beginStationName} - ${endStationName}</div>--%>
        <%--            <div class="dark-text-small">Departure from ${dateFrom} to ${dateTo}</div>--%>

        <%--            <c:if test="${directRoutesNum == 0}">--%>
        <%--                <div class="text-bad-news">--%>
        <%--                    There is no routes yet--%>
        <%--                </div>--%>
        <%--            </c:if>--%>
        <%--            <c:if test="${directRoutesNum > 0}">--%>

        <%--                <table id="table-journey" border="2">--%>
        <%--                    <tr>--%>

        <%--                        <th>Route name</th>--%>
        <%--                        <th>Train name</th>--%>

        <%--                        <th>Begin station</th>--%>
        <%--                        <th>Departure time</th>--%>
        <%--                        <th>End station</th>--%>
        <%--                        <th>Arrival time</th>--%>

        <%--                        <th>Length</th>--%>
        <%--                        <th>Price</th>--%>

        <%--                        <th>Ticket</th>--%>
        <%--                    </tr>--%>

        <%--                    <c:forEach var="route" items="${directRoutes}">--%>
        <%--                        <tr>--%>

        <%--                            <td>${route.routeDTO.routeName}</td>--%>
        <%--                            <td>${route.routeDTO.train.trainName}</td>--%>

        <%--                            <td>${route.routeBeginStation.stationName}</td>--%>
        <%--                            <td>${route.routeDepartureTime}</td>--%>
        <%--                            <td>${route.routeEndStation.stationName}</td>--%>
        <%--                            <td>${route.routeArrivalTime}</td>--%>

        <%--                            <td align="center">${route.routeLength}</td>--%>
        <%--                            <td align="center">${route.routePrice}</td>--%>

        <%--                            <td align="center"><a--%>
        <%--                                    href="/tickets/buy?routeId=${route.routeDTO.routeId}&startStationId=${route.routeBeginStation.stationId}&finishStationId=${route.routeEndStation.stationId}">Buy</a>--%>
        <%--                            </td>--%>


        <%--                        </tr>--%>
        <%--                    </c:forEach>--%>
        <%--                </table>--%>
        <%--            </c:if>--%>

        <%--        </c:if>--%>


    </div>
</div>

<c:import url="footer.jsp"/>

</body>
</html>
