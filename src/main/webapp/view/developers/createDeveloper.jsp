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
        <form action="/developers" accept-charset="utf-8" method="post" ></br>
            email:<br><input type="text" name="email" placeholder="enter email"/></br>
            first name:<br><input type="text" name="first name" placeholder="type first name"/></br>
            last name:<br><input type="text" name="last name" placeholder="type last name"/></br>
            gender:<br><input type="text" name="gender" placeholder="type gender"/></br>
            age:<br><input type="number" name="age" placeholder="type age"/></br>
            experience:<br><input type="number" name="experience" placeholder="enter experience"/></br>
            salary:<br><input type="number" name="salary" placeholder="enter salary"/></br>
            projects:<br><input type="text" name="projects" placeholder="enter projects Ids, separate by comma"/></br>
            company:<br><input type="text" name="company" placeholder="enter company Id"/></br>
            stack: <select id="stack" name="stack">
                       <option value="JAVA">Java</option>
                       <option value="CPLUS">C++</option>
                       <option value="CSHARP">C#</option>
                       <option value="JS">JS</option>
                     </select>
            level: <select id="level" name="level">
                       <option value="JUNIOR">Junior</option>
                       <option value="MIDDLE">Middle</option>
                       <option value="SENIOR">Senior</option>
                     </select>
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