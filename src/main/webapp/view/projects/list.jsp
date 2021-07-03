<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>PROJECTS</title>
    </head>
    <body>
        <p>The list of projects</p>
        <table border="1" cellpadding="5">
            <thead>
                <tr>
                    <td>Project Name</td>
                    <td>Date Of Beginning</td>
                    <td>Stage</td>
                    <td>Coast</td>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="project" items="${projects}">
                 <tr>
                     <td>${project.projectName}</td>
                     <td>${project.dateOfBeginning}</td>
                     <td>${project.stage}</td>
                     <td>${project.stage}</td>
                     <td> <a href="/projects/update?name=${project.projectName}">
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