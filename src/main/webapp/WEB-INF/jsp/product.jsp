<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<template:wrapper>
    <%--Admin add new product--%>
    <c:if test="${isUserAdmin}">
        <div class="d-flex justify-content-center">
            <button type="button" class="btn btn-outline-primary btn-lg" data-bs-toggle="modal" data-bs-target="#editProductModal">
                <h5 class="card-title text-center"><i class="fa-solid fa-pen"></i>&nbsp;Uredi ovaj proizvod</h5>
            </button>
        </div>
        <hr>
        <div class="modal fade" id="editProductModal" aria-labelledby="editProductModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editProductModalLabel">Uredi ovaj proizvod</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form:form action="/admin/product/add-edit" method="post" modelAttribute="editProductForm">
                        <div class="modal-body">
                            <form:hidden path="productId" value="${product.id}"/>
                            <div class="mb-3">
                                <label for="name" class="col-form-label">Naziv:</label>
                                <form:input path="name" cssClass="form-control" value="${product.name}" required="true"/>
                            </div>
                            <div class="mb-3">
                                <label for="description" class="col-form-label">Opis:</label>
                                <form:textarea path="description" cssClass="form-control" value="${product.description}" required="true"/>
                            </div>
                            <div class="mb-3">
                                <label for="summary" class="col-form-label">Kratki opis:</label>
                                <form:textarea path="summary" cssClass="form-control" value="${product.summary}"/>
                            </div>
                            <div class="mb-3">
                                <label for="regularPrice" class="col-form-label">Redovna cijena:</label>
                                <form:input path="regularPrice" type="number" cssClass="form-control" min="0.01" step="0.01" value="${product.regularPrice}" required="true"/>
                            </div>
                            <div class="mb-3">
                                <label for="actionPrice" class="col-form-label">Akcijska cijena(opcionalno):</label>
                                <form:input path="actionPrice" type="number" cssClass="form-control" min="0.01" step="0.01" value="${product.actionPrice}"/>
                            </div>
                            <div class="mb-3">
                                <label for="imageUrl" class="col-form-label">URL slike:</label>
                                <form:input path="imageUrl" cssClass="form-control" value="${product.imageUrl}" required="true"/>
                            </div>
                            <div class="mb-3">
                                <label for="brand" class="col-form-label">Brand:</label>
                                <form:input path="brand" cssClass="form-control" value="${product.brand.name}"/>
                            </div>
                            <div class="mb-3">
                                <label class="col-form-label">Aktivan:</label>
                                <div class="form-check">
                                    <label for="active-true" class="form-check-label">DA</label>
                                    <form:radiobutton path="active" name="active" id="active-true" value="true" required="required" cssClass="form-check-input"/>
                                </div>
                                <div class="form-check">
                                    <label for="active-false" class="form-check-label">NE</label>
                                    <form:radiobutton path="active" name="active" id="active-false" value="false" required="required" cssClass="form-check-input"/>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal"><i class="fa-solid fa-xmark"></i>&nbsp;Odustani</button>
                            <button type="submit" class="btn btn-success"><i class="fa-solid fa-plus"></i>&nbsp;Spremi promjene</button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </c:if>


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
                <a href="#recensions-wrapper" class="text-decoration-none text-dark">${product.recensions.size()} recenzija</a>
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
    <div class="d-flex justify-content-center" id="recensions-wrapper">
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
                                    <h6 class="mb-1">${recension.user.firstName}</h6>
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

