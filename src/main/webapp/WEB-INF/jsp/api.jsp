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

                <td align="left">
                    Get all station names
                </td>
            </tr>


        </table>

    </div>
</div>

<%--<c:import url="footer.jsp"/>--%>

</body>
</html>
