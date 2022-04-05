<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<template:wrapper>
    <div class="w-100 p-4 d-flex justify-content-center">
        <h1><i class="fa-solid fa-circle-user"></i>&nbsp;${loggedUser.email}</h1>
    </div>
    <hr>
    <div class="w-100 p-4 d-flex justify-content-center">
        <form:form action="/my-account/update" method="post" modelAttribute="updateProfileForm" cssStyle="width: 25rem;">
            <h5>Izmjena osobnih podataka</h5>
            <div class="form-outline mb-4">
                <label class="form-label" for="password-input">Ime</label>
                <form:input path="firstName" id="first-name-input" cssClass="form-control" value="${loggedUser.firstName}" required="true"/>
            </div>

            <div class="form-outline mb-4">
                <label class="form-label" for="last-name-input">Prezime</label>
                <form:input path="lastName" id="last-name-input" cssClass="form-control" value="${loggedUser.lastName}" required="true"/>
            </div>

            <div class="form-outline mb-4">
                <label class="form-label" for="password-input">Lozinka (unesite novu lozinku)</label>
                <form:password path="password" id="password-input" cssClass="form-control"/>
            </div>

            <button type="submit" class="btn btn-lg btn-primary mb-4 w-100">Spremi promjene</button>
        </form:form>
    </div>
    <hr>
    <div class="d-flex justify-content-between">
        <h5>Povijest narudžbi</h5>
        <button type="button" class="btn btn-outline-primary" data-bs-toggle="collapse" data-bs-target="#order-list" aria-expanded="false" aria-controls="order-list">
            <i class="fa-solid fa-angle-down"></i>&nbsp;Prikaži povijest narudžbi
        </button>
    </div>
    <hr>
    <c:choose>
        <c:when test="${loggedUser.orders.size() < 1}">
            <h4>Nemate narudžbi</h4>
        </c:when>
        <c:otherwise>
            <div class="collapse mb-5" id="order-list">
                <c:forEach items="${loggedUser.orders}" var="order" varStatus="status">
                    <div class="list-group">
                        <div class="list-group-item list-group-item-action d-flex w-100 justify-content-between">
                            <div class="col-md-5">
                                <div class="row">
                                    <div class="col-md-6">Broj narudžbe:</div>
                                    <div class="col-md-6"><h5>${order.code}</h5></div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">Datum narudžbe:</div>
                                    <div class="col-md-6"><fmt:formatDate value="${order.creationDate}" pattern="d.M.yyyy. hh:mm:ss"/></div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">Ukupna cijena:</div>
                                    <div class="col-md-6">
                                        <h6><fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="${currencySymbol}"/></h6>
                                    </div>
                                </div>
                            </div>
                            <div class="d-flex justify-content-center align-items-center">
                                <a type="button" class="btn btn-outline-success" href="/order/details/${order.code}">
                                    <i class="fa-solid fa-circle-info"></i>&nbsp;Prikaži detalje
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>

</template:wrapper>

