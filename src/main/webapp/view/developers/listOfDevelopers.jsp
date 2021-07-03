<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Developers</title>
    </head>
    <body>
        <p>The list of developers</p>
        <table border="1" cellpadding="5">
            <thead>
                <tr>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Gender</td>
                    <td>Age</td>
                    <td>Experience</td>
                    <td>Salary</td>
                    <td>Email</td>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="developer" items="${developers}">
                 <tr>
                     <td>${developer.firstName}</td>
                     <td>${developer.lastName}</td>
                     <td>${developer.gender}</td>
                     <td>${developer.age}</td>
                     <td>${developer.experienceInYears}</td>
                     <td>${developer.salary}</td>
                     <td>${developer.developerEmail}</td>
                     <td> <a href="/developers/update?name=${developer.developerEmail}">
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