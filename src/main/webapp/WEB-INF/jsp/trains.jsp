
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <meta charset="utf-8">
    <title>Trains</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <c:import url="nav.jsp"/>

    <div id="content-table-trains">
<%--        ADD NEW TRAIN --%>
    <div class="dark-text">Fill inputs to add new train:</div>
    <form action="/trains" method="post">
        <div class="form-element">
            <input id="trainName" name="trainName" type="text" placeholder="Train name" required>
            <label class="error-message">${trainNameError}</label>
        </div>

        <div class="form-element">
            <input id="seatsAmount" name="seatsAmount" type="number" placeholder="Seats amount" required>
            <label class="error-message">${seatsAmountError}</label>
        </div>

        <input type="submit" value="Add train">
    </form>

<%--            SHOW TRAINS --%>
        <table id="table-trains" border="2">
            <tr>
                <th>Id</th>
                <th>Train name</th>
                <th>Seats amount</th>

            </tr>

            <c:forEach var="train" items="${trains}">
                <tr>
                    <td>${train.trainId}</td>
                    <td>${train.trainName}</td>
                    <td>${train.seatsAmount}</td>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%--<c:import url="footer.jsp"/>--%>

</body>
</html>
