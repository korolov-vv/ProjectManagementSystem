<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Developer</title>
    </head>
    <body>

    <h2 align="center" class="formCapture">
            Update developer
        </h2>
            <form action="/companies/update" accept-charset="utf-8" method="post" ></br>
            <input type="hidden" name="id" value='${company.companyId}' />
                company name:<br><input type="text" name="company name" value="<c:out value='${company.companyName}' />"/></br>
                number of developers:<br><input type="number" name="number of developers" value="<c:out value='${company.numberOfDevelopers}' />"/></br>
                projects:<br><input type="text" name="projects" /></br>
                customers:<br><input type="text" name="customers" /></br>
                <br><input type="submit" value="Update"/></br>
            </form>
        <table>
             <thead>
                 <tr>
                     <td><button type="button"><a href="/">BACK TO THE MAIN</a></button></td>
                 </tr>
             </thead>
        </table>