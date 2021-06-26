package ua.goit.controller.projects;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dao.model.ProjectsDAO;
import ua.goit.dto.ProjectsDTO;
import ua.goit.service.projects.ProjectsConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/projects/project")
public class ProjectByNameServlet extends HttpServlet {

    private ProjectsRepository repository;

    @Override
    public void init() {
        this.repository = new ProjectsRepository(DatabaseConnectionManager.getDataSource());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        ProjectsDAO projectsDAO = repository.findByUniqueValue(name);
        ProjectsDTO project = ProjectsConverter.fromProject(projectsDAO);
        req.setAttribute("project", project);
        req.getRequestDispatcher("/view/projects/findProjectByName.jsp").forward(req, resp);
    }
}
