<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="wrapper" tagdir="/WEB-INF/tags" %>

<wrapper:wrapper>
    <c:if test="${param['error']}"><div>Login error</div></c:if>
    <c:if test="${sucessfullyRegistered}"><div>Uspješno ste registrirani. Prijavite se.</div></c:if>

    <form:form action="/login" method="post" modelAttribute="loginForm">
        Email:<form:input path="username" />
        Password:<form:password path="password" />
        <input type="submit" value="submit">
    </form:form>

</wrapper:wrapper>

