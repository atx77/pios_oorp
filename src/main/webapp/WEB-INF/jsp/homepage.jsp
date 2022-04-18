<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<c:set var="productsPerCarouselPage" value="5"/>
<c:set var="maxNumberOfCategoryCarousels" value="3"/>
<c:if test="${maxNumberOfCategoryCarousels > parentCategories.size()}">
    <c:set var="maxNumberOfCategoryCarousels" value="${parentCategories.size() - 1}"/>
</c:if>
<template:wrapper>
    <div class="row mb-5">
        <div id="bannerCarouselIndicators" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
                 <c:forEach items="${banners}" var="banner" varStatus="status">
                     <button type="button" data-bs-target="#bannerCarouselIndicators" data-bs-slide-to="${status.index}" ${status.index eq 0 ? 'class="active" aria-current="true"' : ''} aria-label="Slide ${status.index}"></button>
                 </c:forEach>
            </div>
            <div class="carousel-inner">
                <c:forEach items="${banners}" var="banner" varStatus="status">
                <div class="carousel-item ${status.index eq 0 ? 'active' : ''}" style="max-height: 40vw">
                    <img src="${banner.url}" class="d-block w-100">
                </div>
                </c:forEach>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#bannerCarouselIndicators" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#bannerCarouselIndicators" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>

    <c:set var="productPageCount" value="${(newestProducts.size() + productsPerCarouselPage -1) / productsPerCarouselPage}"/>
    <div class="d-flex justify-content-center text-center mb-2 mt-5">
        <h3>Najnoviji proizvodi</h3>
    </div>
    <div class="row">
        <div id="newestProductsCarouselControls" class="carousel slide carousel-dark" data-bs-ride="carousel">
            <div class="carousel-inner">
                <c:forEach var="currentPage" begin="1" end="${productPageCount}">
                    <div class="carousel-item ${currentPage eq 1 ? 'active' : ''}">
                        <div class="d-flex justify-content-center">
                        <c:forEach items="${newestProducts}" var="product" varStatus="productCount" begin="${(currentPage-1) * productsPerCarouselPage}" end="${(currentPage * productsPerCarouselPage)-1}">
                                <tags:productGridItem product="${product}" productIndex="${productCount.index}"/>
                        </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <button class="carousel-control-prev text-dark" type="button" data-bs-target="#newestProductsCarouselControls" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#newestProductsCarouselControls" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>

<%--&lt;%&ndash;TODO filter inactive, get categories from controller&ndash;%&gt;--%>
<%--    <c:forEach items="${parentCategories}" var="currentCategory" begin="0" end="${maxNumberOfCategoryCarousels}" varStatus="status">--%>
<%--        <c:set var="productPageCount" value="${(currentCategory.products.size() + productsPerCarouselPage -1) / productsPerCarouselPage}"/>--%>
<%--        <div class="d-flex justify-content-center text-center mb-2 mt-5">--%>
<%--            <h3>${currentCategory.name}</h3>--%>
<%--        </div>--%>
<%--        <div class="row">--%>
<%--            <div id="newestProductsCarouselControls-${status.index}" class="carousel slide carousel-dark" data-bs-ride="carousel">--%>
<%--                <div class="carousel-inner">--%>
<%--                    <c:forEach var="currentPage" begin="1" end="${productPageCount}">--%>
<%--                        <div class="carousel-item ${currentPage eq 1 ? 'active' : ''}">--%>
<%--                            <div class="d-flex justify-content-center">--%>
<%--                                <c:forEach items="${currentCategory.products}" var="product" varStatus="productCount" begin="${(currentPage-1) * productsPerCarouselPage}" end="${(currentPage * productsPerCarouselPage)-1}">--%>
<%--                                    <tags:productGridItem product="${product}" productIndex="${productCount.index}"/>--%>
<%--                                </c:forEach>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </c:forEach>--%>
<%--                </div>--%>
<%--                <button class="carousel-control-prev text-dark" type="button" data-bs-target="#newestProductsCarouselControls-${status.index}" data-bs-slide="prev">--%>
<%--                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>--%>
<%--                    <span class="visually-hidden">Previous</span>--%>
<%--                </button>--%>
<%--                <button class="carousel-control-next" type="button" data-bs-target="#newestProductsCarouselControls-${status.index}" data-bs-slide="next">--%>
<%--                    <span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
<%--                    <span class="visually-hidden">Next</span>--%>
<%--                </button>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </c:forEach>--%>

</template:wrapper>
