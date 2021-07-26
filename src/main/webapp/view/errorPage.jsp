<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        <%@include file="/view/css/style.css" %>
    </style>
</head>
<body>
<div class="mainDiv">
    <div>
        <c:import url="/view/header.jsp"/>
    </div>
    <div class="textDiv">
        <c:set var="message" value="${message}"/>
        <a>${message}</a>
    </div>
</div>
</body>
</html>
