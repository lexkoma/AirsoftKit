<%--
  Created by IntelliJ IDEA.
  User: Alehan
  Date: 25.03.2018
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="base"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; UTF-8">
    <title>Sign In</title>

    <!-- Google Fonts -->

    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900'
          rel='stylesheet'>


    <!-- Custom Stylesheet -->
    <link type="text/css" rel="stylesheet" href="/css/animate.css"/>
    <link type="text/css" rel="stylesheet" href="/css/style.css"/>

</head>


<body class="mystyle">

<div class="mycontainer">
    <div class="top">
        <h1 id="title" class="hidden"><span id="logo">Airsoft <span>kits</span></span></h1>
    </div>
    <div class="login-box animated fadeInUp">
        <div class="box-header">
            <h2 id="signin"> Sig In</h2>
        </div>

        <form action="${base}/login" method="post">
            <label for="login">Username</label>
            <br/>
            <input type="text" name="login" id="login">
            <br/>
            <label for="password">Password</label>
            <br/>
            <input type="password" name="password" id="password">
            <br> <button type="submit" name="submit"> Log in </button>


            <br><a id="myref" href="${base}/signup"><p class="small">Haven't account yet?</p></a>
            <br>
            <c:if test="${not empty errorMessage}">
                <font color="red">${errorMessage}</font>
            </c:if>
        </form>

    </div>
</div>

<%-- 	<br><br> errorMessage: ${errorMessage} --%>
<%--     <br><br> base: ${base} --%>


</body>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>


<script>
    $(document).ready(function () {
        $('#logo').addClass('animated fadeInDown');
        $("input:text:visible:first").focus();
    });
    $('#login').focus(function () {
        $('label[for="login"]').addClass('selected');
    });
    $('#login').blur(function () {
        $('label[for="login"]').removeClass('selected');
    });
    $('#password').focus(function () {
        $('label[for="password"]').addClass('selected');
    });
    $('#password').blur(function () {
        $('label[for="password"]').removeClass('selected');
    });
</script>
</html>
