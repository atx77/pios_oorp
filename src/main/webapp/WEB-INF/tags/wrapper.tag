<%@tag description="Main wrapper" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
    <title>${not empty title ? fn:escapeXml(title) : "BlaBlaShop"}</title>
    <link href="/css/style.css" rel="stylesheet">
<%--    <link href="/webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">--%>
</head>
    <body>
        <div>
            <ul>
                <c:if test="${not empty loggedUser}">
                    <li>
                        <p>Dobrodošli, ${loggedUser.firstName}</p>
                    </li>
                    <c:if test="${isUserAdmin}">
                        <li>
                            <p>Vi ste ADMIN</p>
                        </li>
                    </c:if>
                    <li>
                        <a href="#">Moj Profil</a>
                    </li>
                    <li>
                        <a href="/logout">Odjavite se</a>
                    </li>
                </c:if>
                <c:if test="${empty loggedUser}">
                    <li>
                        <a href="/login">Prijavite se</a>
                    </li>
                    <li>
                        <a href="/register">Registrirajte se</a>
                    </li>
                </c:if>
            </ul>
            <ul>
                <li><a href="/">Početna</a></li>
                <c:forEach items="${parentCategories}" var="parentCategory" varStatus="parentCount">
                    <li class="dropdown">
                        <a href="/category/${parentCategory.id}" class="dropbtn">${fn:escapeXml(parentCategory.name)}</a>
                        <c:if test="${not empty parentCategory.subCategories or isUserAdmin}">
                            <div class="dropdown-content">
                                <c:forEach items="${parentCategory.subCategories}" var="subCategory">
                                    <a href="/category/${subCategory.id}">${fn:escapeXml(subCategory.name)}</a>
                                </c:forEach>
                                <c:if test="${isUserAdmin}">
                                    <div>
                                        <hr>
                                        Dodaj novu pod-kategoriju:
                                        <form:form action="/admin/category/add" method="post" modelAttribute="addCategoryAdminForm" id="addCategoryAdminForm-${parentCount.index}">
                                            <form:hidden path="parentCategoryId" value="${parentCategory.id}" id="parentCategoryId-${parentCount.index}"/>
                                            <form:input path="categoryName" id="categoryName-${parentCount.index}"/>
                                            <input type="submit" value="Dodaj">
                                        </form:form>
                                    </div>
                                </c:if>
                            </div>
                        </c:if>
                    </li>
                </c:forEach>

                <c:if test="${isUserAdmin}">
                    <li>
                        <div>Dodaj novu kategoriju:</div>
                        <form:form action="/admin/category/add" method="post" modelAttribute="addCategoryAdminForm" id="addCategoryAdminForm-parent">
                            <form:input path="categoryName" id="categoryName-parent"/>
                            <input type="submit" value="Dodaj">
                        </form:form>
                    </li>
                </c:if>
            </ul>
        </div>
        <div id="main-body-content">
            <jsp:doBody/>
        </div>
        <div>FOOTER</div>
        <script src="/webjars/jquery/3.6.0/dist/jquery.min.js"/>
<%--        <script src="/webjars/bootstrap/5.1.3/js/bootstrap.min.js"/>--%>
    </body>
</html>

