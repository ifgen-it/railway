<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<nav class="menu">
    <ul>

        <%--        ANONYMOUS --%>
        <li><a href="/">HOME</a></li>

        <li><a href="/timetable">TIMETABLE</a>
            <ul>
                <li><a href="/timetable">TIMETABLE</a></li>
                <li><a href="/routes">ROUTES</a></li>
            </ul>
        </li>

        <li><a href="/journey">JOURNEY</a>
            <ul>
                <li><a href="/journey">FIND ROUTE</a></li>
                <li><a href="/ticket/details">TICKET</a></li>
            </ul>
        </li>

        <li><a href="/info">INFO</a></li>
        <li><a href="/api">API</a></li>

        <%--            USER --%>
        <sec:authorize access="isAuthenticated()">

            <li><a href="/account">ACCOUNT</a></li>

        </sec:authorize>

        <%--            ADMIN --%>
        <sec:authorize access="hasRole('ROLE_ADMIN')">

            <li><a href="/users">HUMANS</a>
                <ul>
                    <li><a href="/users">USERS</a></li>
                    <li><a href="/accounts">ACCOUNTS</a></li>
                    <li><a href="/passengers">PASSENGERS</a></li>
                </ul>
            </li>

            <li><a href="/trains">TRAINS</a>
                <ul>
                    <li><a href="/trains">TRAINS</a></li>
                    <li><a href="/routes/new/arcs">NEW ROUTE</a></li>
                </ul>
            </li>

            <li><a href="/arcs">RAILS</a>
                <ul>
                    <li><a href="/arcs">ARCS</a></li>
                    <li><a href="/stations">STAITONS</a></li>
                </ul>
            </li>

<%--            <li><a href="/send-message">Message</a>--%>
<%--            </li>--%>

        </sec:authorize>

        <%-- AUTHORIZE --%>
        <sec:authorize access="!isAuthenticated()">
            <li><a href="/sign-up">SIGN UP</a></li>
            <li><a href="/login">LOGIN</a></li>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
            <li><a href="/logout">LOGOUT</a></li>
        </sec:authorize>


        <%--            USER NAME --%>
        <sec:authorize access="isAuthenticated()">
            <span class="user-logged-text">[<sec:authentication property="principal.firstName"/>]</span>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <span class="user-logged-text">[Guest]</span>
        </sec:authorize>

    </ul>
</nav>