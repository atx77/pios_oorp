<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<template:wrapper>
    <div class="container-fluid mt-5 mb-5">
        <div class="row g-2">
            <div class="col-md-3">
                <h5 class="text-uppercase">Sortiranje</h5>
                <hr>
                <h5 class="text-uppercase">Filteri</h5>
                <hr>
                <div class="p-2">
                    <div class="heading d-flex justify-content-between align-items-center">
                        <h6 class="text-uppercase">BRAND</h6>
                    </div>
                    <div class="d-flex justify-content-between mt-2">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="filter-1">
                            <label class="form-check-label" for="filter-1">TODO</label>
                        </div>
                    </div>
                    <div class="d-flex justify-content-between mt-2">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="filter-2">
                            <label class="form-check-label" for="filter-2">TODO</label>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-md-9">
                <div class="row g-2">
                    <%--Display products from selected category--%>
                    <c:forEach items="${category.products}" var="product" varStatus="productCount">
                        <c:if test="${product.active}">
                            <div class="col-md-4 mt-3 basic-example-item">
                                <tags:productGridItem product="${product}" chosenCategory="${category}" categoryIndex="1" productIndex="${productCount.index}"/>
                            </div>
                        </c:if>
                    </c:forEach>

                    <%--Display products from subcategories--%>
                    <c:forEach items="${category.subCategories}" var="subCategory" varStatus="subCategoryCount">
                        <c:forEach items="${subCategory.products}" var="product" varStatus="productCount">
                            <c:if test="${product.active}">
                                <div class="col-md-4 mt-3 basic-example-item">
                                    <tags:productGridItem product="${product}" chosenCategory="${category}" categoryIndex="1" productIndex="${productCount.index}"/>
                                </div>
                            </c:if>
                        </c:forEach>
                    </c:forEach>

                    <%--Admin add new product--%>
                    <c:if test="${isUserAdmin}">
                        <div class="col-md-4 mt-3 basic-example-item" data-bs-toggle="modal" data-bs-target="#addNewProductModal">
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
