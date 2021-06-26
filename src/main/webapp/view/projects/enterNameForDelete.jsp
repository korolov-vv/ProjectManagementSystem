<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Enter data</title>
    </head>
    <body>
        <form action="deleteProject" accept-charset="utf-8" method="delete" >
            name:<input type="text" name="name" placeholder="type name"/>
            <input type="submit" value="Delete"/>
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