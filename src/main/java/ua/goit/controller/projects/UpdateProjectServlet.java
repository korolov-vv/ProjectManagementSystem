package ua.goit.controller.projects;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.DevelopersRepository;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.DeveloperDAO;
import ua.goit.dao.model.ProjectDAO;
import ua.goit.dto.ProjectDTO;
import ua.goit.service.projects.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/projects/update")
public class UpdateProjectServlet extends HttpServlet {
    private SingleEntityRepository<ProjectDAO> projectsRepository;
    private SingleEntityRepository<DeveloperDAO> developersRepository;
    private ProjectService projectService;

    @Override
    public void init() throws ServletException {
        this.projectsRepository = new ProjectsRepository(HibernateDatabaseConnector.getSessionFactory());
        this.developersRepository = new DevelopersRepository(HibernateDatabaseConnector.getSessionFactory());
        this.projectService = new ProjectService(projectsRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        ProjectDTO projectDTO = projectService.findByUniqueValue(name);
        req.setAttribute("project", projectDTO);
        req.setAttribute("developersList", projectDTO.getDevelopers());
        req.getRequestDispatcher("/view/projects/updateForm.jsp").forward(req, resp);
    }
}