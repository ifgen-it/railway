<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>API for developers</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <c:import url="nav.jsp"/>


    <div id="content-api">
        <div class="dark-text">API for developers</div>

        <table id="table-api" border="2">
            <tr>
                <th>URL</th>
                <th>Description</th>
            </tr>

            <tr>
                <td><a href="/api/stations">/api/stations</a></td>

                <td>
                    <div class="dark-text-small">Get the list of STATIONS:</div>

                    <table id="table-class-stations">
                        <tr>
                            <td><i>int</i></td><td>stationId</td>
                        </tr>
                        <tr>
                            <td><i>String</i></td><td>stationName</td>
                        </tr>
                    </table>
                </td>
            </tr>

            <tr>
                <td>
                    <a href="/api/arrivals/1">/api/arrivals/stationId</a>
                    <br><br>
                    stationId = 1 (Санкт-Петербург)
                </td>

                <td>
                    <div class="dark-text-small">Get the list of STATION ARRIVALS</div>

                    <table id="table-class-arrivals">
                        <tr>
                            <td><i>int</i></td><td>routePathId</td>
                        </tr>
                        <tr>
                            <td><i>LocalDateTime</i></td><td>departureTime</td>
                        </tr>
                        <tr>
                            <td><i>LocalDateTime</i></td><td>arrivalTime</td>
                        </tr>
                        <tr>
                            <td><i>int</i></td><td>routeId</td>
                        </tr>
                        <tr>
                            <td><i>String</i></td><td>routeName</td>
                        </tr>
                        <tr>
                            <td><i>int</i></td><td>trainId</td>
                        </tr>
                        <tr>
                            <td><i>String</i></td><td>trainName</td>
                        </tr>
                    </table>

                </td>
            </tr>

            <tr>
                <td>
                    <a href="/api/departures/1">/api/departures/stationId</a>
                    <br><br>
                    stationId = 1 (Санкт-Петербург)
                </td>

                <td>
                    <div class="dark-text-small">Get the list of STATION DEPARTURES</div>

                    <table id="table-class-departures">
                        <tr>
                            <td><i>int</i></td><td>routePathId</td>
                        </tr>
                        <tr>
                            <td><i>LocalDateTime</i></td><td>departureTime</td>
                        </tr>
                        <tr>
                            <td><i>LocalDateTime</i></td><td>arrivalTime</td>
                        </tr>
                        <tr>
                            <td><i>int</i></td><td>routeId</td>
                        </tr>
                        <tr>
                            <td><i>String</i></td><td>routeName</td>
                        </tr>
                        <tr>
                            <td><i>int</i></td><td>trainId</td>
                        </tr>
                        <tr>
                            <td><i>String</i></td><td>trainName</td>
                        </tr>
                    </table>

                </td>
            </tr>


        </table>

    </div>
</div>

<%--<c:import url="footer.jsp"/>--%>

</body>
</html>
