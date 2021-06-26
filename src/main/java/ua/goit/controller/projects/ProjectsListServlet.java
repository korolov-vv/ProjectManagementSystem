package ua.goit.controller.projects;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dto.ProjectsDTO;
import ua.goit.service.projects.ProjectsConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/projects/list")
public class ProjectsListServlet extends HttpServlet {

    private ProjectsRepository repository;

    @Override
    public void init() {
        this.repository = new ProjectsRepository(DatabaseConnectionManager.getDataSource());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProjectsDTO> projectsDTOList = repository.findAllProjects().stream()
                .map(ProjectsConverter::fromProject)
                .collect(Collectors.toList());
        req.setAttribute("projects", projectsDTOList);
        req.getRequestDispatcher("/view/projects/list.jsp").forward(req, resp);
    }
}
