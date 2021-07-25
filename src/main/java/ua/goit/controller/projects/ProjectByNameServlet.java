package ua.goit.controller.projects;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.ProjectDAO;
import ua.goit.dto.ProjectDTO;
import ua.goit.service.projects.ProjectsConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/projects/project")
public class ProjectByNameServlet extends HttpServlet {

    private SingleEntityRepository<ProjectDAO> projectsRepository;

    @Override
    public void init() {
        this.projectsRepository = new ProjectsRepository(HibernateDatabaseConnector.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        ProjectDAO projectDAO = projectsRepository.findByUniqueParameter("projectName", name).orElseThrow();
        if(projectDAO.getProjectId() == 0){
            ServletException ex = new ServletException("The project does not exist");
            req.setAttribute("message", ex.getMessage());
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
        }else {
            ProjectDTO project = ProjectsConverter.fromProjectsDAO(projectDAO);
            req.setAttribute("project", project);
            req.getRequestDispatcher("/view/projects/findProjectByName.jsp").forward(req, resp);
        }
    }
}
