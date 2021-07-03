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
            <form action="/developers/update" accept-charset="utf-8" method="post" ></br>
            <input type="hidden" name="id" value='${developer.developerId}' />
                first name:<br><input type="text" name="first name" value="<c:out value='${developer.firstName}' />"/></br>
                last name:<br><input type="text" name="last name" value="<c:out value='${developer.lastName}' />"/></br>
                gender:<br><input type="text" name="gender" value="<c:out value='${developer.gender}' />"/></br>
                age:<br><input type="number" name="age" value="<c:out value='${developer.age}' />"/></br>
                experience:<br><input type="number" name="experience" value="<c:out value='${developer.experienceInYears}' />"/></br>
                company:<br><input type="number" name="company" value="<c:out value='${developer.companyId}' />"/></br>
                salary:<br><input type="number" name="salary" value="<c:out value='${developer.salary}' />"/></br>
                email:<br><input type="text" name="email" value="<c:out value='${developer.developerEmail}' />"/></br>
<%--                projects:<br><input type="text" name="projects" value="<c:out value ='${developersOnProjectsDTO.projectIdId}' />" /></br>  --%>

                stack: <select id="stack" name="stack">
<%--                                       <option value = "<c:out value ='${skillsDTO.stack}' />">${skillsDTO.stack}</option>   --%>
                                       <option value="JAVA">Java</option>
                                       <option value="CPLUS">C++</option>
                                       <option value="CSHARP">C#</option>
                                       <option value="JS">JS</option>
                                     </select>
                            level: <select id="level" name="level">
<%--                                       <option value = "<c:out value ='${skillsDTO.level}' />">${skillsDTO.level}</option>   --%>
                                       <option value="JUNIOR">Junior</option>
                                       <option value="MIDDLE">Middle</option>
                                       <option value="SENIOR">Senior</option>
                                     </select>

                <br><input type="submit" value="Update"/></br>
            </form>
        <table>
             <thead>
                 <tr>
                     <td><button type="button"><a href="/">BACK TO THE MAIN</a></button></td>
                 </tr>
             </thead>
        </table>