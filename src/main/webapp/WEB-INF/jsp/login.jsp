<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<template:wrapper>
    <c:if test="${param['error']}">
        <div>
            <div class="alert alert-danger" role="alert">
                <i class="fa-solid fa-circle-exclamation me-2"></i>Došlo je do greške! Provjerite e-mail i lozinku.
            </div>
        </div>
    </c:if>
    <c:if test="${sucessfullyRegistered}">
        <div class="alert alert-success" role="alert">
            <i class="fa-solid fa-circle-exclamation me-2"></i>Uspješno ste se registrirali. Prijavite se.
        </div>
    </c:if>

    <div class="w-100 p-4 d-flex justify-content-center">
        <form:form action="/login" method="post" modelAttribute="loginForm" cssStyle="width: 25rem;">
            <div class="form-outline mb-4">
                <label class="form-label" for="email-input">E-mail addresa</label>
                <form:input path="username" id="email-input" cssClass="form-control"/>
            </div>

            <div class="form-outline mb-4">
                <label class="form-label" for="password-input">Lozinka</label>
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
                    <a href="/register">Nemate račun?<br>Registrirajte se</a>
                </div>
            </div>

            <button type="submit" class="btn btn-lg btn-primary mb-4 w-100">Prijavite se</button>
        </form:form>
    </div>
</template:wrapper>

