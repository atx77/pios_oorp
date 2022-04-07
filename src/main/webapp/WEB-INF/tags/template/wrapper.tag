<%@tag description="Main wrapper" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>${not empty title ? fn:escapeXml(title) : "BlaBlaShop"}</title>
    <link href="/css/style.css" rel="stylesheet">
    <link href="/webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="/webjars/font-awesome/6.1.0/css/all.min.css" rel="stylesheet">
</head>
    <body class="d-flex flex-column min-vh-100">
        <header class="p-3 bg-dark text-white">
            <c:if test="${isUserAdmin}">
                <div class="alert alert-danger" role="alert">
                    <i class="fa-solid fa-circle-exclamation me-2"></i>Prijavljeni ste kao Administrator!
                </div>
            </c:if>
            <div class="container-fluid">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                        <i class="fa-solid fa-location"></i>
                    </a>
                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a href="/" class="nav-link px-2 text-light">BlaBlaShop</a></li>
                    </ul>
                    <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" method="get" action="/search">
                        <div class="input-group">
                            <input type="text" name="text" class="form-control form-control-dark" placeholder="Pretraži..." aria-label="Search" aria-describedby="search-icon">
                            <button type="submit" class="input-group-text" id="search-icon">
                                <i class="fa-solid fa-magnifying-glass"></i>
                            </button>
                        </div>
                    </form>
                    <div class="text-end">
                        <%--Cart--%>
                        <c:if test="${not isUserAdmin}">
                            <a href="/cart" class="btn btn-outline-light me-2">
                                <i class="fa-solid fa-cart-shopping"></i>&nbsp;
                                <c:choose>
                                    <c:when test="${not empty loggedUser.cart.totalPrice}">
                                        <fmt:formatNumber value="${loggedUser.cart.totalPrice}" type="currency" currencySymbol="${currencySymbol}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:formatNumber value="0" type="currency" currencySymbol="${currencySymbol}"/>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </c:if>

                        <c:if test="${empty loggedUser}">
                            <a href="/login" class="btn btn-outline-light me-2"><i class="fa-solid fa-right-to-bracket me-2"></i>&nbsp;Prijava</a>
                            <a href="/register" class="btn btn-light"><i class="fa-solid fa-user-plus me-2"></i>&nbsp;Registracija</a>
                        </c:if>

                        <c:if test="${not empty loggedUser}">
                            <c:if test="${isUserCustomer}">
                                <a href="/my-account" class="btn btn-outline-light"><i class="fa-solid fa-circle-user me-2"></i>&nbsp;Moj profil</a>
                            </c:if>
                            <c:if test="${isUserAdmin}">
                                <a href="/admin/home" class="btn btn-light"><i class="fa-solid fa-circle-user me-2"></i>&nbsp;Pregled narudžbi</a>
                            </c:if>
                            <a href="/logout" class="btn btn-outline-light"><i class="fa-solid fa-right-from-bracket me-2"></i>Odjavi se</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </header>

        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="container-fluid">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#category-navbar-toggle" aria-controls="category-navbar-toggle" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="category-navbar-toggle">
                    <ul class="navbar-nav me-auto mb-2 mb-md-0 flex-wrap">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/">Početna</a>
                        </li>
                        <c:forEach items="${parentCategories}" var="parentCategory" varStatus="parentCount">
                            <c:if test="${parentCategory.active or isUserAdmin}">
                                <li class="d-inline-flex">
                                    <div class="nav-item dropdown">
                                    <c:if test="${parentCategory.active}">
                                        <a href="/category/${parentCategory.id}" class="nav-link active">${fn:escapeXml(parentCategory.name)}</a>
                                    </c:if>
                                    <c:if test="${not parentCategory.active and isUserAdmin}">
                                        <a href="/category/${parentCategory.id}" class="nav-link disabled"><del>${fn:escapeXml(parentCategory.name)}</del></a>
                                    </c:if>
                                    <c:if test="${not empty parentCategory.subCategories or isUserAdmin}">
                                        <ul class="dropdown-menu">
                                            <c:forEach items="${parentCategory.subCategories}" var="subCategory" varStatus="subCount">
                                                <li>
                                                <div class="d-inline-flex">
                                                    <c:if test="${subCategory.active}">
                                                        <a href="/category/${subCategory.id}" class="dropdown-item">${fn:escapeXml(subCategory.name)}</a>
                                                    </c:if>
                                                    <c:if test="${not subCategory.active and isUserAdmin}">
                                                        <a href="/category/${subCategory.id}" class="dropdown-item disabled"><del>${fn:escapeXml(subCategory.name)}</del></a>
                                                    </c:if>

                                                        <%--Visibility change button--%>
                                                    <c:if test="${isUserAdmin}">
                                                        <form:form action="/admin/category/change-visibility" method="post" modelAttribute="categoryVisibilityAdminForm" id="categoryVisibilityAdminForm-${parentCount.index}-${subCount.index}">
                                                            <form:hidden path="categoryId" value="${subCategory.id}" id="categoryId-${parentCount.index}-${subCount.index}"/>
                                                            <button type="submit" class="btn btn-outline-dark">
                                                                <c:choose>
                                                                    <c:when test="${subCategory.active}">
                                                                        <i class="fa-solid fa-eye-slash"></i>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <i class="fa-solid fa-eye"></i>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </button>
                                                        </form:form>
                                                    </c:if>
                                                </div>
                                                </li>
                                            </c:forEach>
                                            <c:if test="${isUserAdmin}">
                                            <li class="p-3">
                                                <div style="min-width: 200px">
                                                    <hr>
                                                    <p>Dodaj novu pod-kategoriju:</p>
                                                    <form:form action="/admin/category/add" method="post" modelAttribute="addCategoryAdminForm" id="addCategoryAdminForm-${parentCount.index}">
                                                        <form:hidden path="parentCategoryId" value="${parentCategory.id}" id="parentCategoryId-${parentCount.index}"/>
                                                        <form:input path="categoryName" id="categoryName-${parentCount.index}" placeholder="Naziv" cssClass="form-control mb-2"/>
                                                        <button type="submit" class="btn btn-block btn-outline-dark">Dodaj</button>
                                                    </form:form>
                                                </div>
                                            </li>
                                            </c:if>
                                        </ul>
                                    </c:if>
                                    </div>
                                        <%--Visibility change button--%>
                                    <c:if test="${isUserAdmin}">
                                        <div>
                                        <form:form action="/admin/category/change-visibility" method="post" modelAttribute="categoryVisibilityAdminForm" id="categoryVisibilityAdminForm-${parentCount.index}">
                                            <form:hidden path="categoryId" value="${parentCategory.id}" id="categoryId-${parentCount.index}"/>
                                            <button type="submit" class="btn btn-outline-light me-3">
                                                <c:choose>
                                                    <c:when test="${subCategory.active}">
                                                        <i class="fa-solid fa-eye-slash"></i>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <i class="fa-solid fa-eye"></i>
                                                    </c:otherwise>
                                                </c:choose>
                                            </button>
                                        </form:form>
                                        </div>
                                    </c:if>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <c:if test="${isUserAdmin}">
                        <div class="text-end">
                                <div class="text-light m-2 text-center">Dodaj novu kategoriju:</div>
                                <form:form action="/admin/category/add" method="post" modelAttribute="addCategoryAdminForm" id="addCategoryAdminForm-parent" cssClass="d-inline-flex">
                                    <form:input path="categoryName" id="categoryName-parent" placeholder="Naziv" cssClass="form-control mx-1"/>
                                    <button type="submit" class="btn btn-block btn-outline-light">Dodaj</button>
                                </form:form>
                        <div class="text-end">
                    </c:if>
                </div>
            </div>
        </nav>

        <%--Shows alert message--%>
        <c:if test="${not empty alertMessage}">
            <div id="alert-message-wrapper" class="px-4 my-2">
                <c:choose>
                    <c:when test="${alertMessage.alertType eq 'SUCCESS'}">
                        <div class="alert alert-dismissible alert-success" role="alert">
                            <i class="fa-solid fa-circle-check me-2"></i>&nbsp;${alertMessage.message}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:when>
                    <c:when test="${alertMessage.alertType eq 'DANGER'}">
                        <div class="alert alert-dismissible alert-danger" role="alert">
                            <i class="fa-solid fa-circle-exclamation me-2"></i>&nbsp;${alertMessage.message}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-dismissible alert-info" role="alert">
                            <i class="fa-solid fa-circle-info me-2"></i>&nbsp;${alertMessage.message}
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>

        <div id="main-body-content">
            <jsp:doBody/>
        </div>

        <footer class="bg-dark text-center text-lg-start text-light mt-auto">
            <div class="container p-4">
                <div class="row">
                    <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                        <h5 class="text-uppercase">Links</h5>

                        <ul class="list-unstyled mb-0">
                            <li>
                                <a href="#" class="text-light">Link 1</a>
                            </li>
                            <li>
                                <a href="#" class="text-light">Link 2</a>
                            </li>
                        </ul>
                    </div>

                    <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                        <h5 class="text-uppercase">Links</h5>

                        <ul class="list-unstyled mb-0">
                            <li>
                                <a href="#" class="text-light">Link 1</a>
                            </li>
                            <li>
                                <a href="#" class="text-light">Link 2</a>
                            </li>
                        </ul>
                    </div>

                    <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                        <h5 class="text-uppercase">Links</h5>

                        <ul class="list-unstyled mb-0">
                            <li>
                                <a href="#" class="text-light">Link 1</a>
                            </li>
                            <li>
                                <a href="#" class="text-light">Link 2</a>
                            </li>
                        </ul>
                    </div>

                    <div class="col-lg-3 col-md-6 mb-4 mb-md-0">
                        <h5 class="text-uppercase">Links</h5>

                        <ul class="list-unstyled mb-0">
                            <li>
                                <a href="#" class="text-light">Link 1</a>
                            </li>
                            <li>
                                <a href="#" class="text-light">Link 2</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
                <i class="fa-regular fa-copyright"></i> 2022 Copyright: <a class="text-light" href="/">BlaBlaShop</a>
            </div>
        </footer>

        <script src="/webjars/jquery/3.6.0/dist/jquery.min.js"></script>
        <script src="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"/></script>
        <script src="/js/custom.js"></script>
    </body>
</html>

