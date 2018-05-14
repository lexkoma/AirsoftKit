<%--
  Created by IntelliJ IDEA.
  User: Alehan
  Date: 04.04.2018
  Time: 23:51
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

    <title>User's list of equipment</title>
</head>
<body class="mystyle">


<div class="container">
    <div style="padding: 0 0 0 85% ">
        <br>
        <a href="${base}/edit" title="Edit your profile"
           class="waves-effect waves-light btn  red accent-2"> Edit profile</a>
        <br>

    </div>
    <br>
    <div style="padding: 0 0 0 85% ">

        <a href="${base}/logout" title="Log out"
           class="waves-effect waves-light btn  red accent-2">Log Out</a>

    </div>

    <div class="row center">
        <h5 style="color: #5d4037;">List of equipment:</h5>
    </div>

    <div class="row left">
        <i class="material-icons center">
            <a href="${base}/itemcreate?login=${personalEquipmentDto.userLogin}"
               id="download-button"
               class="btn-large col s9 waves-effect waves-light brown darken-2">
                Add Equipment
            </a>
        </i>
    </div>

    <div class="row center">
        <div class="col s9">
            <c:choose>
                <c:when test="${!empty personalEquipmentDto.itemsList}">
                    <table class="striped white" width=100% >
                        <tr>
                            <th width=5%>Position</th>
                            <th width=25%>Title</th>
                            <th width=50%>Description</th>
                            <%--<th width=10%>Cost</th>--%>
                            <%--<th width=10%>Quantity</th>--%>
                            <th width=10%></th>
                            <th width=10%></th>
                        </tr>
                        <c:forEach items="${personalEquipmentDto.itemsList}" var="item"
                                   varStatus="count">
                            <tr>
                                <td>${count.count}</td>
                                <td>${item.title}</td>
                                <td>${item.description}</td>
                                <%--<td>${item.price}</td>--%>
                                <%--<td>${item.quantity}</td>--%>
                                <td>
                                    <i class="material-icons center">
                                        <a href="${base}/itemedit?login=${personalEquipmentDto.userLogin}&id=${item.idItem}"
                                           id="download-button"
                                           class="btn-small col s12 waves-effect waves-light brown darken-2">
                                            Edit
                                        </a>
                                    </i>
                                </td>
                                <td>
                                    <i class="material-icons center">
                                        <a href="${base}/itemdelete?id=${item.idItem}"
                                           id="download-button"
                                           class="btn-small col s12 waves-effect waves-light brown darken-2">
                                            Delete
                                        </a>
                                    </i>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <h4 class="emptyList">Equipment's list is empty!. <br> You must add some equipment!</h4>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<br><br>


</body>
</html>


