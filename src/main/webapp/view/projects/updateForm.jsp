<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>PROJECTS</title>
    </head>
    <body>

    <h2 align="center" class="formCapture">
            Update project
        </h2>
            <form action="/projects/update" accept-charset="utf-8" method="post" ></br>
            <input type="hidden" name="id" value='${project.projectId}' />
                name:<br><input type="text" name="name" value="<c:out value='${project.projectName}' />"/></br>
                stage:<br><input type="text" name="stage" value="<c:out value='${project.stage}' />"/></br>
                period:<br><input type="number" name="period" value="<c:out value='${project.timePeriod}' />"/></br>
                coast:<br><input type="number" name="coast" value="<c:out value='${project.coast}' />"/></br>
                number of developers:<br><input type="number" name="number of developers" value="<c:out value='${project.numberOfDevelopers}' />"/></br>
                start date:<br><input type="date" name="start date" value="<c:out value='${project.dateOfBeginning}' />"/></br>
                developers:<br><input type="text" name="developers" value="<c:out value ='${developersOnProjectsDTO.developerId}' />" /></br>
                companies:<br><input type="text" name="companies" value="<c:out value ='${customersAndCompanies.companyId}' />"/></br>
                customers:<br><input type="text" name="customers" value="<c:out value ='${customersAndCompanies.customerId}' />"/></br>
                <br><input type="submit" value="Update"/></br>
            </form>
        <table>
             <thead>
                 <tr>
                     <td><button type="button"><a href="/">BACK TO THE MAIN</a></button></td>
                 </tr>
             </thead>
        </table>