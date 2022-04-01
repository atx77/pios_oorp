<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<template:wrapper>
    <div class="w-100 p-4 d-flex justify-content-center">
        <div class="text-center">
            <h1>Oops!</h1>
            <h2>404 Not Found</h2>
            <div>Došlo je do greške! Stranica koju tražite ne postoji.</div>
            <div><a href="/" class="btn btn-lg btn-primary my-4 w-100">Povratak na početnu stranicu</a></div>
        </div>
    </div>
</template:wrapper>

