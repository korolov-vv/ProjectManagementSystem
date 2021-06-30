<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>YOUR SELECTED PROJECT IS</title>
    </head>
    <body>
        <c:set var="message" value="${message}" />
        <h2 style="colour:red">${message}</h2>
        <table>
             <thead>
                 <tr>
                     <td><button type="button"><a href="/">BACK TO THE MAIN</a></button></td>
                 </tr>
             </thead>
        </table>
    </body>
</html>
