<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<template:wrapper>
    <h3>Pregled svih narudžbi</h3>
    <c:choose>
        <c:when test="${orders.size() < 1}">
            <h4>Nije pronađena niti jedna narudžba</h4>
        </c:when>
        <c:otherwise>
            <h6>Pronađeno ${orders.size()} narudžbi</h6>
            <tag:orderList orders="${orders}"/>
        </c:otherwise>
    </c:choose>
</template:wrapper>

