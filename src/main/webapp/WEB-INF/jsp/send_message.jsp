<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Send message</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <c:import url="nav.jsp"/>


    <div id="content-table-timetable">
        <div class="dark-text">Select station to send message:</div>
        <form action="/send-message" method="post">
            <div class="form-element">
                <select name="stationId" class="form-element">
                    <c:forEach var="station" items="${stations}">
                        <option value="${station.stationId}">${station.stationName}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" value="Send message">
        </form>

        <c:if test="${stationName != null}">
            <div class="dark-text-small">Message for ${stationName} was sent!</div>

        </c:if>

    </div>
</div>

<%--<c:import url="footer.jsp"/>--%>

</body>
</html>
