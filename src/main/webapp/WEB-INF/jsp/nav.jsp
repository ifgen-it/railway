<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<nav class="menu">
    <ul>
        <li><a href="/">Home</a></li>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="/users">Users</a></li>
        </sec:authorize>


        <li><a href="/journey">Journey</a>
            <ul>
                <li><a href="/journey">Find route</a></li>
                <li><a href="/tickets/buy">Buy ticket</a></li>
            </ul>
        </li>

        <li><a href="/trains">Trains</a>
            <ul>
                <li><a href="/stations">Stations</a></li>
                <li><a href="/trains">Trains</a></li>
                <li><a href="/routes">Routes</a></li>
                <li><a href="/timetable">Timetable</a></li>
                <li><a href="/arcs">Arcs</a></li>
                <li><a href="/passengers">Passengers</a></li>
                <li><a href="/routes/new/arcs">Create route</a></li>

            </ul>
        </li>
        <li><a href="/info">Info</a></li>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="/accounts">Accounts</a></li>
        </sec:authorize>


        <li><a href="/sign-up">Sign up</a></li>

        <sec:authorize access="!isAuthenticated()">
            <li><a href="/login">Login</a></li>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
            <li><a href="/logout">Logout</a></li>
        </sec:authorize>


    </ul>
</nav>