<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Create developer</title>
    </head>
    <body>

    <h2 align="center" class="formCapture">
        Create developer
    </h2>
        <form action="/companies" accept-charset="utf-8" method="post" ></br>

            company name:<br><input type="text" name="company name" placeholder="type company name"/></br>
            number of developers:<br><input type="number" name="number of developers" placeholder="type number of developers"/></br>
            projects:<br><input type="text" name="projects" placeholder="enter projects Ids, separate by comma"/></br>
            customers:<br><input type="text" name="customers" placeholder="enter customers Ids, separate by comma"/></br>
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