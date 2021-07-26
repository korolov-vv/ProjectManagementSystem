<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <style><%@include file="/view/css/style.css"%></style>
        <title>Enter data</title>
    </head>
    <body>
    <div class="mainDiv">
        <div>
            <c:import url="/view/header.jsp" />
        </div>
        <div class="shortForm">
            <form action="developer" accept-charset="utf-8" method="get" >
                <div class="title">Enter email</div>
                <div class="subtitle">Enter the email of the searched developer</div>
                <div class="oneInput-container ic1">
                    <input id="developerEmail" class="oneInput" type="text" name="developerEmail" placeholder=" " />
                    <div class="oneCut"></div>
                    <label for="developerEmail" class="onePlaceholder">type email</label>
                </div>
                <button type="submit" class="submit">SUBMIT</button>
            </form>
        </div>
    </div>
    </body>
</html>