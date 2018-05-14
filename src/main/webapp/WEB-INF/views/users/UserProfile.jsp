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
    <title>User profile</title>

    <!-- Google Fonts -->

    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900'
          rel='stylesheet'>


    <!-- Custom Stylesheet -->
    <link type="text/css" rel="stylesheet" href="/css/animate.css"/>
    <link type="text/css" rel="stylesheet" href="/css/style.css"/>

</head>


<body class="mystyle">

<div class="mycontainer">

    <div class="login-box">
        <div class="box-header">
            <c:choose>
                <c:when test="${login == null}">
                    <h2 id="signin">Create your profile</h2>
                </c:when>
                <c:otherwise>
                    <h2 id="signin">Edit profile</h2>
                </c:otherwise>
            </c:choose>
        </div>


        <c:choose>
        <c:when test="${login != null}">
        <form action="${base}/edit" method="post">
            </c:when>
            <c:otherwise>
            <form action="${base}/signup" method="post">
                </c:otherwise>
                </c:choose>

                <input type="hidden" name="id" value="${userDto.id}">

                <label for="login">Login</label>
                <br>
                <input type="text" name="login" id="login"
                       value="${sessionScope.userDto.login}">
                <br>
                <label for="email">Email</label>
                <br>
                <input type="text" name="email" id="email"
                       value="${sessionScope.userDto.email}">
                <br>
                <label for="name">Name</label>
                <br>
                <input type="text" name="name" id="name"
                       value="${sessionScope.userDto.name}">

                <br>
                <label for="password">Password</label>
                <br>
                <input type="password" name="password" id="password"
                       value="${sessionScope.userDto.password}">
                <br>
                <label for="password">Confirm password</label>
                <input type="password" name="confirm_password" id="confirm_password">
                <div id="menu">
                    <button type="submit" name="submit" style="padding-left: 25px;
                    padding-right: 25px;"> OK </button>
                    <div><a href="${base}/cancel"
                            class="btn-small waves-effect waves-light">Cancel</a>
                    </div>
                </div>
                <%--<br>--%>
                <%--<button type="submit" name="submit"> OK</button>--%>
                <%--<div><br><a href="${base}/cancel"--%>
                <%--class="btn-small col s12 waves-effect waves-light red accent-1">Cancel</a>--%>
                <%--</div>--%>

                <br>
                <div class="errorMessage">
                    <p><c:if test="${not empty errorProfile}">
                        <font color="red">${errorProfile}</font> </c:if></p>
                </div>


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
