<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

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
                        <div class="col-md-4 mt-3 basic-example-item">
                            <tags:productListItem product="${product}" chosenCategory="${category}" categoryIndex="1" productIndex="${productCount.index}"/>
                        </div>
                    </c:forEach>

                    <%--Display products from subcategories--%>
                    <c:forEach items="${category.subCategories}" var="subCategory" varStatus="subCategoryCount">
                        <c:forEach items="${subCategory.products}" var="product" varStatus="productCount">
                            <div class="col-md-4 mt-3 basic-example-item">
                                <tags:productListItem product="${product}" chosenCategory="${category}" categoryIndex="1" productIndex="${productCount.index}"/>
                            </div>
                        </c:forEach>
                    </c:forEach>

                    <c:if test="${isUserAdmin}">
                        <div class="col-md-4 mt-3 basic-example-item">
                            <div class="card shadow-2" role="button">
                                <div class="card-img-top justify-content-center text-center p-3 display-1">
                                    <i class="fa-solid fa-plus"></i>
                                </div>

                                <div class="card-body">
                                    <h5 class="card-title"><i class="fa-solid fa-plus"></i> Dodaj novi proizvod</h5>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</template:wrapper>
