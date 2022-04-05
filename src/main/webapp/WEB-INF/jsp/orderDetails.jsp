<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<template:wrapper>
    <div><h3>Pregled narudžbe br. ${order.code}</h3></div>
    <hr>
        <div class="d-flex justify-content-between">
            <div style="width:400px;">
                <div class="row">
                    <div class="col-md-6 fw-bolder">Broj narudžbe:</div>
                    <div class="col-md-6">${order.code}</div>
                </div>
                <div class="row">
                    <div class="col-md-6 fw-bolder">Datum:</div>
                    <div class="col-md-6"><fmt:formatDate value="${order.creationDate}" pattern="d.M.yyyy. hh:mm:ss"/></div>
                </div>
                <div class="row">
                    <div class="col-md-6 fw-bolder">Način plaćanja:</div>
                    <div class="col-md-6">${order.paymentMethod.description}</div>
                </div>
                <div class="row">
                    <div class="col-md-6 fw-bolder">Ukupna cijena:</div>
                    <div class="col-md-6"><h5><fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="${currencySymbol}"/></h5></div>
                </div>
            </div>
            <div style="width:300px;">
                <div class="row">
                    <div class="col-md-12 fw-bold">Adresa za dostavu:</div>
                </div>
                <div class="row">
                    <div class="col-md-12">${order.shippingAddress.user.firstName}&nbsp;${order.shippingAddress.user.lastName}</div>
                </div>
                <div class="row">
                    <div class="col-md-12">${order.shippingAddress.street}</div>
                </div>
                <div class="row">
                    <div class="col-md-12">${order.shippingAddress.postcode}&nbsp;${order.shippingAddress.city}</div>
                </div>
                <div class="row">
                    <div class="col-md-12">${order.shippingAddress.country.description}</div>
                </div>
            </div>
        </div>
    <hr>
    <h4 class="my-4">Stavke narudžbe</h4>
    <c:forEach items="${order.items}" var="orderItem" varStatus="status">
        <div class="list-group">
            <div class="list-group-item list-group-item-action d-flex w-100 justify-content-between">
                <div class="d-flex justify-content-between">
                    <div class="d-flex justify-content-center align-items-center">
                        <div class="img-wrapper-product-list">
                            <a class="text-decoration-none text-dark" href="/product/${orderItem.product.id}">
                                <img class="img-thumbnail img-product-list" src="${orderItem.product.imageUrl}">
                            </a>
                        </div>
                    </div>
                    <div class="justify-content-center ms-3">
                        <div class="row">
                            <div class="col-md-12">
                                <a class="text-decoration-none text-dark" href="/product/${orderItem.product.id}"><h5>${orderItem.product.name}</h5></a>
                            </div>
                        </div>
                        <div class="row border-bottom" style="width: 400px;">
                            <div class="col-md-5">Cijena:</div>
                            <div class="col-md-7">
                                <c:choose>
                                    <c:when test="${not (orderItem.sellingPrice eq orderItem.regularPrice)}">
                                            <span class="text-decoration-line-through me-1">
                                                <fmt:formatNumber value="${orderItem.sellingPrice}" type="currency" currencySymbol="${currencySymbol}"/>
                                            </span>
                                        <c:if test="${not empty orderItem.discountPercentage}">
                                            <span class="me-2 text-success">(-${orderItem.discountPercentage}%)</span>
                                        </c:if>
                                        <h6>
                                            <fmt:formatNumber value="${orderItem.sellingPrice}" type="currency" currencySymbol="${currencySymbol}"/>
                                        </h6>
                                    </c:when>
                                    <c:otherwise>
                                        <h6>
                                            <fmt:formatNumber value="${orderItem.sellingPrice}" type="currency" currencySymbol="${currencySymbol}"/>
                                        </h6>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="row border-bottom">
                            <div class="col-md-5">Količina:</div>
                            <div class="col-md-7">${orderItem.quantity}</div>
                        </div>
                        <div class="row">
                            <div class="col-md-5">Ukupno:</div>
                            <div class="col-md-7">
                                <span class="fw-bold"><fmt:formatNumber value="${orderItem.totalPrice}" type="currency" currencySymbol="${currencySymbol}"/></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="d-flex justify-content-center align-items-center">
                </div>
            </div>
        </div>
    </c:forEach>
    <hr>
    <h3 class="my-3">
        Ukupno:&nbsp;<fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="${currencySymbol}"/>
    </h3>


</template:wrapper>

