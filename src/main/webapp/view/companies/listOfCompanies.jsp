<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Companies</title>
    </head>
    <body>
        <p>The list of companies</p>
        <table border="1" cellpadding="5">
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Company Name</td>
                    <td>Number of developers</td>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="company" items="${companies}">
                 <tr>
                     <td>${company.companyId}</td>
                     <td>${company.companyName}</td>
                     <td>${company.numberOfDevelopers}</td>
                     <td> <a href="/companies/update?name=${developer.companyName}">
                             <button>Update</button>
                          </a>
                     </td>
                 </tr>
            </c:forEach>
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