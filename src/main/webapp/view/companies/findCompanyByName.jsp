<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>YOUR SELECTED COMPANY IS</title>
    </head>
    <body>
        <c:set var="company" value="${company}" />
        <table border="1" cellpadding="5">
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Company Name</td>
                    <td>Number of developers</td>
                </tr>
            </thead>
            <tbody>
                 <tr>
                     <td>${company.companyId}</td>
                     <td>${company.companyName}</td>
                     <td>${company.numberOfDevelopers}</td>
                 </tr>
            </tbody>
        </table>
        <table>
             <thead>
                 <tr>
                     <td><button type="button"><a href="/">BACK TO THE MAIN</a></button></td>
                 </tr>
             </thead>
        </table>
    </body>
</html>
