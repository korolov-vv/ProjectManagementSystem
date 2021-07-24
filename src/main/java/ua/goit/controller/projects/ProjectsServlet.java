package ua.goit.controller.projects;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.DevelopersRepository;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.DevelopersDAO;
import ua.goit.dao.model.ProjectsDAO;
import ua.goit.dto.DevelopersDTO;
import ua.goit.dto.ProjectsDTO;
import ua.goit.service.developers.DevelopersConverter;
import ua.goit.service.projects.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/projects")
public class ProjectsServlet extends HttpServlet {
    private SingleEntityRepository<ProjectsDAO> projectsRepository;
    private SingleEntityRepository<DevelopersDAO> developersRepository;
    private ProjectService projectService;

    @Override
    public void init() {
        this.projectsRepository = new ProjectsRepository(HibernateDatabaseConnector.getSessionFactory());
        this.developersRepository = new DevelopersRepository(HibernateDatabaseConnector.getSessionFactory());
        this.projectService = new ProjectService(projectsRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/projects.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProjectsDTO projectsDTO = setProject(req);
        projectsDTO.setDevelopers(setDevelopers(req, projectsDTO));
        projectService.create(projectsDTO);
        resp.sendRedirect(req.getContextPath() + "/projects");
    }

    private ProjectsDTO setProject(HttpServletRequest req) {
        ProjectsDTO projectsDTO = new ProjectsDTO();
        projectsDTO.setProjectName(req.getParameter("name"));
        projectsDTO.setStage(req.getParameter("stage"));
        projectsDTO.setTimePeriod(Integer.parseInt(req.getParameter("period")));
        projectsDTO.setCoast(Integer.parseInt(req.getParameter("coast")));
        projectsDTO.setNumberOfDevelopers(Integer.parseInt(req.getParameter("number of developers")));
        projectsDTO.setDateOfBeginning(LocalDate.parse(req.getParameter("start date")));
        return projectsDTO;
    }

    private Set<DevelopersDTO> setDevelopers(HttpServletRequest req, ProjectsDTO projectsDTO) {
        Set<DevelopersDTO> developersDTOSet = new HashSet<>();
        if (!req.getParameter("developers").equals("")) {
            String[] s = req.getParameter("developers").split(",");
            Arrays.stream(s)
                    .map(Integer::parseInt)
                    .forEach((d) -> {
                        DevelopersDTO developersDTO = DevelopersConverter.fromDevelopersDAO(developersRepository.findById(d));
                        developersDTOSet.add(developersDTO);
                    });
        }
        return developersDTOSet;
    }
}