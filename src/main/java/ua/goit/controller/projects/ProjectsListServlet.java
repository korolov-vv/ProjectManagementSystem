package ua.goit.controller.projects;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.ProjectsDAO;
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

    private SingleEntityRepository<ProjectsDAO> projectsRepository;

    @Override
    public void init() {
        this.projectsRepository = new ProjectsRepository(HibernateDatabaseConnector.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProjectsDTO> projectsDTOList = projectsRepository.findAll().stream()
                .map(ProjectsConverter::fromProjectsDAO)
                .collect(Collectors.toList());
        req.setAttribute("projects", projectsDTOList);
        req.getRequestDispatcher("/view/projects/list.jsp").forward(req, resp);
    }
}
