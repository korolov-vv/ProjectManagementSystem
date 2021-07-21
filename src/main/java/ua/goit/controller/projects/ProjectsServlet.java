package ua.goit.controller.projects;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.DevelopersOnProjectsRepository;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dto.DevelopersOnProjectsDTO;
import ua.goit.dto.ProjectsDTO;
import ua.goit.service.developers.DevelopersOnProjectsService;
import ua.goit.service.projects.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

@WebServlet("/projects")
public class ProjectsServlet extends HttpServlet {
    private ProjectsRepository projectsRepository;
    private DevelopersOnProjectsRepository developersOnProjectsRepository;
    private ProjectService projectService;
    private DevelopersOnProjectsService developersOnProjectsService;

    @Override
    public void init() {
        this.projectsRepository = new ProjectsRepository(HibernateDatabaseConnector.getSessionFactory());
        this.developersOnProjectsRepository = new DevelopersOnProjectsRepository(HibernateDatabaseConnector.getSessionFactory());
        this.projectService = new ProjectService(projectsRepository);
//        this.developersOnProjectsService = new DevelopersOnProjectsService(developersOnProjectsRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/projects.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProjectsDTO projectsDTO = addProject(req);
        addDevelopersOnProject(req, projectsDTO);
        resp.sendRedirect(req.getContextPath() + "/projects");
    }

    private ProjectsDTO addProject(HttpServletRequest req) {
        ProjectsDTO projectsDTO = new ProjectsDTO();
        projectsDTO.setProjectName(req.getParameter("name"));
        projectsDTO.setStage(req.getParameter("stage"));
        projectsDTO.setTimePeriod(Integer.parseInt(req.getParameter("period")));
        projectsDTO.setCoast(Integer.parseInt(req.getParameter("coast")));
        projectsDTO.setNumberOfDevelopers(Integer.parseInt(req.getParameter("number of developers")));
        projectsDTO.setDateOfBeginning(LocalDate.parse(req.getParameter("start date")));
        projectService.create(projectsDTO);
        return projectsDTO;
    }

    private void addDevelopersOnProject(HttpServletRequest req, ProjectsDTO projectsDTO) {
        if(!req.getParameter("developers").equals("")) {
            String[] s = req.getParameter("developers").split(",");
            Arrays.stream(s)
                    .map(Integer::parseInt)
                    .map((d) -> {
                        DevelopersOnProjectsDTO developersOnProjectsDTO = new DevelopersOnProjectsDTO();
                        developersOnProjectsDTO.setProjectId(projectsDTO.getProjectId());
                        developersOnProjectsDTO.setDeveloperId(d);
                        return developersOnProjectsDTO;
                    })
                    .map(d -> developersOnProjectsRepository.findUniqueValue(d.getDeveloperId(), d.getProjectId()))
                    .forEach(d -> {
                        if (d.getDeveloperId() == 0 && d.getProjectId() == 0) {
                            developersOnProjectsRepository.create(d);
                        } else developersOnProjectsRepository.update(d);
                    });
        }
    }
}