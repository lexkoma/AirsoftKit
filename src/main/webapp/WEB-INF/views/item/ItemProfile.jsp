<%--
  Created by IntelliJ IDEA.
  User: Alehan
  Date: 09.04.2018
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css"/>
    <link type="text/css" rel="stylesheet" href="/css/animate.css"/>
    <link type="text/css" rel="stylesheet" href="/css/style.css"/>
    <title>Equipment profile</title>
</head>
<body>

<main>
    <div class="container maxwidth600">
        <div class="row center">
            <c:choose>
                <c:when test="${itemDto == null}">
                    <h5>Add equipment, please</h5>
                </c:when>
                <c:otherwise>
                    <h5>Edit equipment:</h5>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="row center">
            <c:choose>
            <c:when test="${sessionScope.itemDto != null}">
            <form action="${base}/itemedit" method="post">
                </c:when>
                <c:otherwise>
                <form action="${base}/itemcreate" method="post">
                    </c:otherwise>
                    </c:choose>

                        <input type="text" name="title" placeholder="Title: " value="${sessionScope.itemDto.title}">

        </div>
        <div class="row center">
            <input type="text" name="description"	 placeholder="Description:"
                   value="${sessionScope.itemDto.description}">
        </div>

        <div class="row center">
            <input type="text" name="price"	 placeholder="Price:"
                   value="${sessionScope.itemDto.price}">
        </div>

        <div class="row center">
            <input type="text" name="quantity"	 placeholder="Quantity:"
                   value="${sessionScope.itemDto.quantity}">
        </div>

        <div class="row center">
            <button
                    class="btn col s12 waves-effect waves-light brown darken-2"
                    type="submit" name="ok" value="Submit">
                <i class="material-icons center">Submit</i>
            </button>
        </div>
        <div class="row center">
            <i class="material-icons center">
                <a href="${base}/cancel"
                   id="download-button"
                   class="btn-large col s12 waves-effect waves-light brown darken-2">
                    Cancel
                </a>
            </i>
        </div>

        <c:if test="${not empty errorMessage}">
            <font color="red">${errorMessage}</font>
        </c:if>

        </form>
    </div>
</main>



</body>
</html>