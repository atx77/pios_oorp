<%@tag description="Main wrapper" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>${test}</title>
</head>
    <body>
        <div>
            HEADER
            <c:if test="${not empty loggedUser}"><div>Successfully logged in with email ${loggedUser.email} role ${loggedUser.type}</div></c:if>
        </div>
        <jsp:doBody/>
        <div>FOOTER</div>
    </body>
</html>

