<%@tag description="Main wrapper" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="orders" type="java.util.Collection" required="true" %>

<c:forEach items="${orders}" var="order" varStatus="status">
    <div class="list-group">
        <div class="list-group-item list-group-item-action d-flex w-100 justify-content-between">
            <div class="col-md-5">
                <div class="row">
                    <div class="col-md-6">Broj narudžbe:</div>
                    <div class="col-md-6"><h5>${order.code}</h5></div>
                </div>
                <div class="row">
                    <div class="col-md-6">Datum narudžbe:</div>
                    <div class="col-md-6"><fmt:formatDate value="${order.creationDate}" pattern="d.M.yyyy. hh:mm:ss"/></div>
                </div>
                <div class="row">
                    <div class="col-md-6">Ukupna cijena:</div>
                    <div class="col-md-6">
                        <h6><fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="${currencySymbol}"/></h6>
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-center align-items-center">
                <a type="button" class="btn btn-outline-success" href="/my-account/order/details/${order.code}">
                    <i class="fa-solid fa-circle-info"></i>&nbsp;Prikaži detalje
                </a>
            </div>
        </div>
    </div>
</c:forEach>
