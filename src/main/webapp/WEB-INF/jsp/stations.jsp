
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <meta charset="utf-8">
    <title>Stations</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <c:import url="nav.jsp"/>

    <div id="content-table-stations">

        <%--        ADD NEW STATION --%>
        <div class="dark-text">Fill input to add new station:</div>
        <form action="/stations" method="post">
            <div class="form-element">
                <input id="stationName" name="stationName" type="text" placeholder="Station name" required>
                <label class="error-message">${stationNameError}</label>
            </div>

            <input type="submit" value="Add station">
        </form>


        <%--            SHOW STATIONS --%>
        <table id="table-stations" border="2">
            <tr>
                <th>Id</th>
                <th>Station name</th>

            </tr>

            <c:forEach var="station" items="${stations}">
                <tr>
                    <td>${station.stationId}</td>
                    <td>${station.stationName}</td>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%--<c:import url="footer.jsp"/>--%>

</body>
</html>
