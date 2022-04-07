<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<template:wrapper>
    <div class="row">
        <div class="col-md-6 border-end">
            <img src="${product.imageUrl}"/>
        </div>
        <div class="col-md-6">
            <div class="my-3">
                <h2>${product.name}</h2>
            </div>
            <div class="my-3">
                <p>${product.summary}</p>
            </div>
            <div class="my-3">
                <c:choose>
                    <c:when test="${not empty product.actionPrice}">
                        <div class="d-flex">
                            <div>
                                <h2 class="text-decoration-line-through fw-light">
                                    <fmt:formatNumber value="${product.regularPrice}" type="currency" currencySymbol="${currencySymbol}"/>
                                </h2>
                            </div>
                            <div class="ms-2">
                                <h2 class="text-success">
                                (-${product.discountPercentage}%)
                                </h2>
                            </div>
                        </div>
                    <h2 class="fw-bold">
                        <fmt:formatNumber value="${product.actionPrice}" type="currency" currencySymbol="${currencySymbol}"/>
                    </h2>
                    </c:when>
                    <c:otherwise>
                    <h2>
                        <fmt:formatNumber value="${product.regularPrice}" type="currency" currencySymbol="${currencySymbol}"/>
                    </h2>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="my-3">
                <p>${product.recensions.size()} recenzija</p>
            </div>
            <div class="my-3">
                <c:if test="${not isUserAdmin}">
                    <form:form action="/cart/add" method="post" modelAttribute="addToCartForm" id="add-to-cart-form}">
                        <form:hidden path="productId" value="${product.id}" id="add-to-cart-form-productid}"/>
                        <form:input type="number" path="quantity" value="1" cssClass="form-control" min="1" max="10" id="add-to-cart-form-${productIndex}"/>
                        <button type="submit" class="btn btn-primary w-100 my-1"><i class="fa-solid fa-cart-plus"></i>&nbsp;Dodaj u košaricu</button>
                    </form:form>
                </c:if>
            </div>
        </div>
    </div>

    <div class="row d-flex justify-content-center text-center mt-3">
        <h4>Opis proizvoda</h4>
    </div>
    <hr>
    <div class="d-flex justify-content-center">
        <div class="col-md-9">
            ${product.description}
        </div>
    </div>

    <div class="row d-flex justify-content-center text-center mt-5">
        <h4>Recenzije</h4>
    </div>
    <hr>
    <div class="d-flex justify-content-center">
        <div class="col-md-9">
            <c:if test="${userHasBoughtProduct}">
                <form:form action="/product/recension/add" method="post" modelAttribute="addRecensionForm" id="add-recension-form">
                    <form:hidden path="productId" value="${product.id}" id="add-recension-form-productid}"/>
                    <form:textarea path="text" cssClass="form-control" id="add-recension-form-text" required="required"/>
                    <button type="submit" class="btn btn-primary w-100 my-1"><i class="fa-solid fa-comment-plus"></i>&nbsp;Ostavi recenziju</button>
                </form:form>
            </c:if>
            <c:choose>
                <c:when test="${empty product.recensions}">
                    <h6>Nema recenzija</h6>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${product.recensions}" var="recension">
                        <div class="list-group mb-2">
                            <div class="list-group-item list-group-item-action">
                                <div class="d-flex w-100 justify-content-between border-bottom">
                                    <h5 class="mb-1">${recension.user.firstName}</h5>
                                    <small class="text-muted">${recension.creationDate}</small>
                                </div>
                                <p class="mb-1">${recension.text}</p>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</template:wrapper>

