<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>YOUR SELECTED PROJECT IS</title>
    </head>
    <body>
        <c:set var="project" value="${project}" />
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
                 <tr>
                     <td>${project.projectName}</td>
                     <td>${project.dateOfBeginning}</td>
                     <td>${project.stage}</td>
                     <td>${project.stage}</td>
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
