<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="wrapper" tagdir="/WEB-INF/tags" %>

<wrapper:wrapper>
        <form:form action="/register" method="post" modelAttribute="registerForm">
            Email:<form:input path="email"/>
            Ime:<form:input path="firstName" />
            Prezime:<form:input path="lastName" />
            Lozinka:<form:password path="password" />
            <input type="submit" value="submit">
        </form:form>
</wrapper:wrapper>

