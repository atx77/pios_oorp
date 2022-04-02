<%@tag description="Main wrapper" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@attribute name="product" type="hr.tvz.diplomski.pios_oorp.domain.Product" required="true" %>
<%@attribute name="chosenCategory" type="hr.tvz.diplomski.pios_oorp.domain.Category" required="false" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="card shadow-2">
    <a href="/product/${product.id}" class="text-decoration-none">
        <img src="${product.imageUrl}" class="card-img-top">
    </a>

    <div class="card-body">
        <a href="/product/${product.id}" class="text-decoration-none text-dark">
            <h5 class="card-title">${product.name}</h5>
        </a>
        <hr>
        <p class="card-text">
            <c:choose>
                <c:when test="${not empty product.actionPrice}">
                    <span class="text-decoration-line-through">
                        <fmt:formatNumber value="${product.regularPrice}" type="currency" currencySymbol="${currencySymbol}"/>
                    </span>
                    <span class="fw-bold fa-lg">
                        <fmt:formatNumber value="${product.actionPrice}" type="currency" currencySymbol="${currencySymbol}"/>
                    </span>
                </c:when>
                <c:otherwise>
                    <span class="fw-bold fa-lg">
                        <fmt:formatNumber value="${product.regularPrice}" type="currency" currencySymbol="${currencySymbol}"/>
                    </span>
                </c:otherwise>
            </c:choose>
        </p>
        <button type="submit" class="btn btn-primary w-100"><i class="fa-solid fa-cart-plus"></i> Dodaj u košaricu</button>
    </div>
</div>