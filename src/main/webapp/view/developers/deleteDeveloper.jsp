<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>THE SELECTED DEVELOPER IS DELETED</title>
    </head>
    <body>
        <c:set var="id" value="${id}" />
        <H3 style="colour:red"> The developer ${id} is deleted</H3>
        <table>
             <thead>
                 <tr>
                     <td><button type="button"><a href="/">BACK TO THE MAIN</a></button></td>
                 </tr>
             </thead>
        </table>
    </body>
</html>