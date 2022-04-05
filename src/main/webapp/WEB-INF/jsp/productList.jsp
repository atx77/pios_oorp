<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<template:wrapper>
    <div class="container-fluid mt-5 mb-5">
        <div class="row g-2">
            <div class="col-md-3">
                <c:choose>
                    <c:when test="${not empty category}">
                        <c:set var="targetUrl" value="/category/${category.id}" scope="page"/>
                    </c:when>
                    <c:when test="${not empty searchText}">
                        <c:set var="targetUrl" value="/search" scope="page"/>
                    </c:when>
                </c:choose>
                <form action="${targetUrl}" method="get" >
                    <c:if test="${not empty searchText}">
                        <input type="hidden" value="${searchText}" name="text">
                    </c:if>
                    <h5 class="text-uppercase">Sortiranje</h5>
                    <hr>
                    <div class="mb-5">
                        <select name="sort" class="form-select">
                            <c:forEach items="${sortTypes}" var="sortType">
                                <option value="${sortType}" ${chosenSort eq sortType ? 'selected' : ''}>${sortType.description}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <h5 class="text-uppercase">Filteri</h5>
                    <hr>
                    <div class="p-2">
                        <div class="heading d-flex justify-content-between align-items-center">
                            <h6 class="text-uppercase">Brand</h6>
                        </div>
                        <c:forEach items="${productBrands}" var="productBrand">
                            <div class="d-flex justify-content-between mt-2">
                                <div class="form-check">
                                    <input type="checkbox" id="brand-${productBrand.name}" name="brand" value="${productBrand.name}" class="form-check-input" ${fn:containsIgnoreCase(filteredBrands, productBrand.name) ? 'checked="checked"' : ''}>
                                    <label class="form-check-label" for="brand-${productBrand.name}">${productBrand.name}</label>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <hr>
                    <div class="p-2">
                        <div class="heading d-flex justify-content-between align-items-center">
                            <h6 class="text-uppercase">Rasprodaja</h6>
                        </div>
                        <div class="d-flex justify-content-between mt-2">
                            <div class="form-check">
                                <input type="checkbox" id="isOnSale" name="isOnSale" value="true" class="form-check-input" ${filteredIsOnSale eq true ? 'checked="checked"' : ''}>
                                <label class="form-check-label" for="isOnSale">Na rasprodaji</label>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="p-2">
                        <div class="heading d-flex justify-content-between align-items-center">
                            <h6 class="text-uppercase">Cijena</h6>
                        </div>
                        <div class="d-flex justify-content-between mt-2">
                            <div class="w-100">
                                <label class="form-label" for="minPrice">Najniža cijena</label>
                                <input type="number" id="minPrice" name="minPrice" class="form-control" min="0.01" step="0.01" value="${not empty filteredMinPrice ? filteredMinPrice : ''}">
                            </div>
                        </div>
                        <div class="d-flex justify-content-between mt-2">
                            <div class="w-100">
                                <label class="form-label" for="maxPrice">Najviša cijena</label>
                                <input type="number" id="maxPrice" name="maxPrice" class="form-control" min="0.01" step="0.01" value="${not empty filteredMaxPrice ? filteredMaxPrice : ''}">
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="p-2">
                        <button type="submit" class="btn btn-dark w-100"><i class="fa-solid fa-filter"></i>&nbsp;Filtriraj</button>
                    </div>
                </form>
            </div>
            <div class="col-md-9" id="all-products-wrapper">
                <div class="row g-2">
                    <c:choose>
                        <c:when test="${not empty category}">
                            <h4>Rezultati pretrage za kategoriju '${category.name}'</h4>
                        </c:when>
                        <c:when test="${not empty searchText}">
                            <h4>Rezultati pretrage za tekst '${searchText}'</h4>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${empty productSearchResults}">
                            <h6>Nije pronađen niti jedan proizvod</h6>
                        </c:when>
                        <c:otherwise>
                            <div id="products-count-wrapper">
                                <h6>Pronađeno ${productSearchResults.size()} proizvoda</h6>
                            </div>
                        </c:otherwise>
                    </c:choose>

                    <%--Display products--%>
                    <c:forEach items="${productSearchResults}" var="product" varStatus="productCount">
                        <c:if test="${product.active}">
                            <div class="col-md-6 col-lg-6 col-xl-4 col-xxl-3 mt-3 basic-example-item">
                                <tags:productGridItem product="${product}" productIndex="${productCount.index}"/>
                            </div>
                        </c:if>
                    </c:forEach>

                    <%--Admin add new product--%>
                    <c:if test="${isUserAdmin and not empty category}">
                        <div class="col-md-6 col-lg-6 col-xl-4 col-xxl-3 mt-3 basic-example-item" data-bs-toggle="modal" data-bs-target="#addNewProductModal">
                            <div class="card shadow-2" role="button">
                                <div class="card-img-top justify-content-center text-center p-3 display-1">
                                    <i class="fa-solid fa-plus"></i>
                                </div>

                                <div class="card-body">
                                    <h5 class="card-title text-center">Dodaj novi proizvod</h5>
                                </div>
                            </div>
                        </div>


                        <div class="modal fade" id="addNewProductModal" tabindex="-1" aria-labelledby="addNewProductModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="addNewProductModalLabel">Dodaj novi proizvod</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <form:form action="/admin/product/add" method="post" modelAttribute="addNewProductForm">
                                    <div class="modal-body">
                                            <form:hidden path="categoryId" value="${category.id}"/>
                                            <div class="mb-3">
                                                <label for="name" class="col-form-label">Naziv:</label>
                                                <form:input path="name" cssClass="form-control"/>
                                            </div>
                                            <div class="mb-3">
                                                <label for="description" class="col-form-label">Opis:</label>
                                                <form:textarea path="description" cssClass="form-control"/>
                                            </div>
                                            <div class="mb-3">
                                                <label for="summary" class="col-form-label">Kratki opis:</label>
                                                <form:textarea path="summary" cssClass="form-control"/>
                                            </div>
                                            <div class="mb-3">
                                                <label for="regularPrice" class="col-form-label">Redovna cijena:</label>
                                                <form:input path="regularPrice" type="number" cssClass="form-control" min="0.01" step="0.01"/>
                                            </div>
                                            <div class="mb-3">
                                                <label for="actionPrice" class="col-form-label">Akcijska cijena(opcionalno):</label>
                                                <form:input path="actionPrice" type="number" cssClass="form-control" min="0.01" step="0.01"/>
                                            </div>
                                            <div class="mb-3">
                                                <label for="imageUrl" class="col-form-label">URL slike:</label>
                                                <form:input path="imageUrl" cssClass="form-control"/>
                                            </div>
                                            <div class="mb-3">
                                                <label for="brand" class="col-form-label">Brand:</label>
                                                <form:input path="brand" cssClass="form-control"/>
                                            </div>

                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal"><i class="fa-solid fa-xmark"></i>&nbsp;Odustani</button>
                                        <button type="submit" class="btn btn-success"><i class="fa-solid fa-plus"></i>&nbsp;Dodaj</button>
                                    </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</template:wrapper>
