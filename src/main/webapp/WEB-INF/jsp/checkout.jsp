<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<template:wrapper>
    <div class="d-flex justify-content-between">
        <div><h3>Naplata</h3></div>
        <div class="text-end">
            <a href="/cart" class="btn btn-outline-primary btn-lg"><i class="fa-solid fa-cart-shopping"></i>&nbsp;Povratak u košaricu</a>
        </div>
    </div>
    <hr>
    <c:choose>
        <c:when test="${loggedUser.cart.items.size() < 1}">
            <h4>Nemate proizvoda u košarici</h4>
        </c:when>
        <c:otherwise>
            <div class="row g-5">
                <div class="col-md-5 col-lg-4 order-md-last">
                    <h4 class="d-flex justify-content-between align-items-center mb-3">
                        <span class="text-dark">Pregled košarice</span>
                    </h4>
                    <ul class="list-group mb-3">
                        <li class="list-group-item d-flex justify-content-between lh-sm bg-light text-dark">
                            <h6 class="my-0">Artikl (količina)</h6>
                            <h6 class="my-0">Ukupna cijena</h6>
                        </li>
                        <c:forEach items="${loggedUser.cart.items}" var="cartItem" varStatus="cartItemStatus">
                            <li class="list-group-item d-flex justify-content-between lh-sm">
                                <div class="justify-content-center">
                                    <h6 class="my-0">${cartItem.product.name} (${cartItem.quantity})</h6>
                                </div>
                                <span class="text-muted">
                                    <c:choose>
                                        <c:when test="${not empty cartItem.product.actionPrice}">
                                            <fmt:formatNumber value="${cartItem.product.actionPrice * cartItem.quantity}" type="currency" currencySymbol="${currencySymbol}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:formatNumber value="${cartItem.product.regularPrice * cartItem.quantity}" type="currency" currencySymbol="${currencySymbol}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </span>
                            </li>
                        </c:forEach>
                        <li class="list-group-item d-flex justify-content-between lh-sm bg-dark text-light">
                            <h6 class="my-0">Ukupno</h6>
                            <h6 class="my-0">
                                <fmt:formatNumber value="${loggedUser.cart.totalPrice}" type="currency" currencySymbol="${currencySymbol}"/>
                            </h6>
                        </li>
                    </ul>
                </div>

                <div class="col-md-7 col-lg-8">
                    <h4 class="mb-3">Adresa za dostavu</h4>
                    <form:form action="/cart/checkout" method="post" modelAttribute="checkoutForm">
                        <div class="row g-3">
                            <div class="col-sm-6">
                                <label for="firstName" class="form-label">Ime</label>
                                <form:input path="firstName" cssClass="form-control" required="true"/>
                            </div>
                            <div class="col-sm-6">
                                <label for="lastName" class="form-label">Prezime</label>
                                <form:input path="lastName" cssClass="form-control" required="true"/>
                            </div>
                            <div class="col-12">
                                <label for="street" class="form-label">Ulica i kućni broj</label>
                                <form:input path="street" cssClass="form-control" required="true"/>
                            </div>
                            <div class="col-md-5">
                                <label for="country" class="form-label">Država</label>
                                <select class="form-select" id="country">
                                    <option>Hrvatska</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label for="city" class="form-label">Grad</label>
                                <form:input path="city" cssClass="form-control" required="true"/>
                            </div>
                            <div class="col-md-3">
                                <label for="postcode" class="form-label">Poštanski broj</label>
                                <form:input path="postcode" cssClass="form-control" required="true"/>
                            </div>
                        </div>
                        <hr>
                        <h4 class="mb-3">Način dostave</h4>
                        <div class="my-3">
                            <c:forEach items="${deliveryModes}" var="deliveryMode" varStatus="status">
                                <div class="form-check">
                                    <label class="form-check-label" for="deliveryMode-${status.index}">
                                        ${deliveryMode.description}
                                    </label>
                                    <form:radiobutton path="deliveryMode" name="deliveryMode" value="${deliveryMode}" id="deliveryMode-${status.index}" class="form-check-input" required="true"/>
                                </div>
                            </c:forEach>
                        </div>
                        <hr>
                        <h4 class="mb-3">Način plaćanja</h4>
                        <div class="my-3">
                            <c:forEach items="${paymentMethods}" var="paymentMethod" varStatus="status">
                                <div class="form-check">
                                    <label class="form-check-label" for="paymentMethod-${status.index}">
                                            ${paymentMethod.description}
                                    </label>
                                    <form:radiobutton path="paymentMethod" name="paymentMethod" value="${paymentMethod}" id="paymentMethod-${status.index}" class="form-check-input" required="true"/>
                                </div>
                            </c:forEach>
                        </div>
                        <hr>
                        <button class="w-100 btn btn-success btn-lg" type="submit">Završi narudžbu</button>
                    </form:form>
                </div>
            </div>
        </c:otherwise>
    </c:choose>

</template:wrapper>

