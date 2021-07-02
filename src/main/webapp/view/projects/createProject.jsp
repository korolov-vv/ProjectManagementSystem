<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Create project</title>
    </head>
    <body>

    <h2 align="center" class="formCapture">
        Create project
    </h2>
        <form action="/projects" accept-charset="utf-8" method="post" ></br>
            name:<br><input type="text" name="name" placeholder="type name"/></br>
            stage:<br><input type="text" name="stage" placeholder="type stage"/></br>
            period:<br><input type="number" name="period" placeholder="type period"/></br>
            coast:<br><input type="number" name="coast" placeholder="enter coast"/></br>
            number of developers:<br><input type="number" name="number of developers" placeholder="enter number of developers"/></br>
            start date:<br><input type="date" name="start date"/></br>
            developers:<br><input type="text" name="developers" placeholder="enter developer's Ids, separate by comma"/></br>
            companies:<br><input type="text" name="companies" placeholder="enter companies Ids, separate by comma"/></br>
            customers:<br><input type="text" name="customers" placeholder="enter customer's Ids, separate by comma"/></br>

            <br><input type="submit" value="Submit"/></br>
        </form>
    <table>
         <thead>
             <tr>
                 <td><button type="button"><a href="/">BACK TO THE MAIN</a></button></td>
             </tr>
         </thead>
    </table>
    </body>
</html>