<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Enter project's name:'</title>
    </head>
    <body>
        <form action="project" accept-charset="utf-8" method="get" >
            name:<input type="text" name="name" placeholder="type name"/>
            <input type="submit" value="ok"/>
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