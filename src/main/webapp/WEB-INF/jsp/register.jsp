<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="wrapper" tagdir="/WEB-INF/tags" %>

<wrapper:wrapper>
    <div class="w-100 p-4 d-flex justify-content-center">
        <form:form action="/register" method="post" modelAttribute="registerForm" cssStyle="width: 25rem;">
            <div class="form-outline mb-4">
                <label class="form-label" for="email-input">E-mail addresa</label>
                <form:input path="email" id="email-input" cssClass="form-control"/>
            </div>

            <div class="form-outline mb-4">
                <label class="form-label" for="password-input">Ime</label>
                <form:input path="firstName" id="first-name-input" cssClass="form-control"/>
            </div>

            <div class="form-outline mb-4">
                <label class="form-label" for="email-input">Prezime</label>
                <form:input path="lastName" id="last-name-input" cssClass="form-control"/>
            </div>

            <div class="form-outline mb-4">
                <label class="form-label" for="email-input">Lozinka</label>
                <form:password path="password" id="password-input" cssClass="form-control"/>
            </div>

            <div class="row mb-4">
                <div class="col d-flex justify-content-center">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="accept-rules" checked />
                        <label class="form-check-label" for="accept-rules">Prihvaćam pravila i uvjete</label>
                    </div>
                </div>

                <div class="col">
                    <a href="/login">Već imate račun?<br>Prijavite se</a>
                </div>
            </div>

            <button type="submit" class="btn btn-lg btn-primary mb-4 w-100">Registrirajte se</button>
        </form:form>
    </div>
</wrapper:wrapper>

