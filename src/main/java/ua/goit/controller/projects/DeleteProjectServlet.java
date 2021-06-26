package ua.goit.controller.projects;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.ProjectsRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/projects/deleteProject")
public class DeleteProjectServlet extends HttpServlet {
    private ProjectsRepository repository;

    @Override
    public void init() {
        this.repository = new ProjectsRepository(DatabaseConnectionManager.getDataSource());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        repository.delete(name);
        req.setAttribute("name", name);
        req.getRequestDispatcher("projects/deleteProject.jsp").forward(req, resp);
    }

}
