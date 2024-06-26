<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<template:wrapper>
    <div class="d-flex justify-content-between">
        <div><h3>Pregled košarice</h3></div>
        <div class="text-end">
            <c:choose>
                <c:when test="${loggedUser.cart.items.size() < 1}">
                    <a href="/cart/checkout" class="btn btn-primary btn-lg disabled">Nastavi na plaćanje&nbsp;<i class="fa-solid fa-caret-right"></i></a>
                </c:when>
                <c:otherwise>
                    <a href="/cart/checkout" class="btn btn-primary btn-lg">Nastavi na plaćanje&nbsp;<i class="fa-solid fa-caret-right"></i></a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <hr>
    <c:choose>
        <c:when test="${loggedUser.cart.items.size() < 1}">
            <h4>Nemate proizvoda u košarici</h4>
        </c:when>
        <c:otherwise>
            <c:forEach items="${loggedUser.cart.items}" var="cartItem" varStatus="cartItemStatus">
                <div class="list-group">
                    <div class="list-group-item list-group-item-action d-flex w-100 justify-content-between">
                        <div class="d-flex justify-content-between">
                            <div class="d-flex justify-content-center align-items-center">
                                <div class="img-wrapper-product-list">
                                    <a class="text-decoration-none text-dark" href="/product/${cartItem.product.id}">
                                        <img class="img-thumbnail img-product-list" src="${cartItem.product.imageUrl}">
                                    </a>
                                </div>
                            </div>
                            <div class="justify-content-center ms-3">
                                <a class="text-decoration-none text-dark" href="/product/${cartItem.product.id}">
                                    <h5 class="mb-2">${cartItem.product.name}</h5>
                                </a>
                                <div class="mb-2">
                                    <c:choose>
                                        <c:when test="${not empty cartItem.product.actionPrice}">
                                            <span class="text-decoration-line-through me-1">
                                                <fmt:formatNumber value="${cartItem.product.regularPrice}" type="currency" currencySymbol="${currencySymbol}"/>
                                            </span>
                                            <c:if test="${not empty cartItem.product.discountPercentage}">
                                                <span class="me-2 text-success">(-${cartItem.product.discountPercentage}%)</span>
                                            </c:if>
                                            <span class="fw-bold">
                                                <fmt:formatNumber value="${cartItem.product.actionPrice}" type="currency" currencySymbol="${currencySymbol}"/>
                                            </span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="fw-bold">
                                                <fmt:formatNumber value="${cartItem.product.regularPrice}" type="currency" currencySymbol="${currencySymbol}"/>
                                            </span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div>
                                    <c:choose>
                                        <c:when test="${not empty cartItem.product.actionPrice}">
                                            <p>Ukupno: <span class="fw-bold"><fmt:formatNumber value="${cartItem.product.actionPrice * cartItem.quantity}" type="currency" currencySymbol="${currencySymbol}"/></span></p>
                                        </c:when>
                                        <c:otherwise>
                                            <p>Ukupno: <span class="fw-bold"><fmt:formatNumber value="${cartItem.product.regularPrice * cartItem.quantity}" type="currency" currencySymbol="${currencySymbol}"/></span></p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex justify-content-center align-items-center">
                            <form:form action="/cart/change-quantity" method="post" modelAttribute="changeProductQuantityInCartForm" id="change-quantity-form-${cartItemStatus.index}" cssClass="m-0">
                                <div class="mx-5 d-inline-flex align-items-center">
                                    <form:hidden path="productId" value="${cartItem.product.id}" id="change-quantity-form-productId-${cartItemStatus.index}"/>
                                    <form:input path="quantity" type="number" cssClass="form-control me-1" style="width: 75px;" min="0" step="1" value="${cartItem.quantity}" id="change-quantity-form-quantity-${cartItemStatus.index}"/>
                                    <button type="submit" class="btn btn-outline-success text-wrap">Promijeni količinu</button>
                                </div>
                            </form:form>

                            <form:form action="/cart/remove-product" method="post" modelAttribute="removeProductFromCartForm" id="remove-product-from-cart-form-${cartItemStatus.index}" cssClass="m-0">
                                <form:hidden path="productId" value="${cartItem.product.id}" id="remove-product-from-cart-form-productId-${cartItemStatus.index}"/>
                                <button type="submit" class="btn btn-danger">
                                    <i class="fa-solid fa-xmark"></i>&nbsp;Obriši
                                </button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <div class="d-flex justify-content-between my-3">
                <h3>
                    Ukupno:&nbsp;<fmt:formatNumber value="${loggedUser.cart.totalPrice}" type="currency" currencySymbol="${currencySymbol}"/>
                </h3>
                <div class="text-end">
                    <a href="/cart/checkout" class="btn btn-primary btn-lg">Nastavi na plaćanje&nbsp;<i class="fa-solid fa-caret-right"></i></a>
                </div>
            </div>
        </c:otherwise>
    </c:choose>

</template:wrapper>

