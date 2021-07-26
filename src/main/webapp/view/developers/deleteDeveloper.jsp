<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        <%@include file="/view/css/style.css" %>
    </style>
    <title>THE SELECTED DEVELOPER IS DELETED</title>
</head>
<body>
<div class="mainDiv">
    <div>
        <c:import url="/view/header.jsp"/>
    </div>
    <div class="textDiv">
        <c:set var="developerEmail" value="${developerEmail}"/>
        <a> The developer ${developerEmail} is deleted</a>
    </div>
</div>
</body>
</html>