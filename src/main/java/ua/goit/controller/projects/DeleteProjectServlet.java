package ua.goit.controller.projects;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.ProjectDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/projects/deleteProject")
public class DeleteProjectServlet extends HttpServlet {
    private SingleEntityRepository<ProjectDAO> projectsRepository;

    @Override
    public void init() {
        this.projectsRepository = new ProjectsRepository(HibernateDatabaseConnector.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        ProjectDAO projectDAOForDelete = projectsRepository.findByUniqueParameter("projectName", name).orElseThrow();
        projectsRepository.deleteByParameter("projectName", name);
        req.setAttribute("name", projectDAOForDelete.getProjectName());
        req.getRequestDispatcher("/view/projects/deleteProject.jsp").forward(req, resp);
    }

}
