<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<nav class="menu">
    <ul>

        <%--        ANONYMOUS --%>
        <li><a href="/">Home</a></li>

        <li><a href="/timetable">Timetable</a>
            <ul>
                <li><a href="/timetable">Timetable</a></li>
                <li><a href="/routes">Routes</a></li>
            </ul>
        </li>

        <li><a href="/journey">Journey</a>
            <ul>
                <li><a href="/journey">Find route</a></li>
                <li><a href="/ticket/details">Ticket</a></li>
            </ul>
        </li>

        <li><a href="/info">Info</a></li>
        <li><a href="/api">API</a></li>

        <%--            USER --%>
        <sec:authorize access="isAuthenticated()">

            <li><a href="/account">Account</a></li>

        </sec:authorize>

        <%--            ADMIN --%>
        <sec:authorize access="hasRole('ROLE_ADMIN')">

            <li><a href="/users">Humans</a>
                <ul>
                    <li><a href="/users">Users</a></li>
                    <li><a href="/accounts">Accounts</a></li>
                    <li><a href="/passengers">Passengers</a></li>
                </ul>
            </li>

            <li><a href="/trains">Trains</a>
                <ul>
                    <li><a href="/trains">Trains</a></li>
                    <li><a href="/routes/new/arcs">New route</a></li>
                </ul>
            </li>

            <li><a href="/arcs">Rails</a>
                <ul>
                    <li><a href="/arcs">Arcs</a></li>
                    <li><a href="/stations">Stations</a></li>
                </ul>
            </li>

<%--            <li><a href="/send-message">Message</a>--%>
<%--            </li>--%>

        </sec:authorize>

        <%-- AUTHORIZE --%>
        <sec:authorize access="!isAuthenticated()">
            <li><a href="/sign-up">Sign up</a></li>
            <li><a href="/login">Login</a></li>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
            <li><a href="/logout">Logout</a></li>
        </sec:authorize>


        <%--            USER NAME --%>
        <sec:authorize access="isAuthenticated()">
            <li><sec:authentication property="principal.firstName"/></li>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <li>Guest</li>
        </sec:authorize>

    </ul>
</nav>