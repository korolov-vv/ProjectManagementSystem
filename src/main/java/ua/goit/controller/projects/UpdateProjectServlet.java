package ua.goit.controller.projects;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.DevelopersRepository;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.DeveloperDAO;
import ua.goit.dao.model.ProjectDAO;
import ua.goit.dto.DeveloperDTO;
import ua.goit.dto.ProjectDTO;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProjectDTO projectDTO = setProject(req);
        projectDTO.setDevelopers(setDevelopers(req, projectDTO));
        projectService.update(projectDTO);
        resp.sendRedirect(req.getContextPath() + "/projects");
    }

    private ProjectDTO setProject(HttpServletRequest req) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectName(req.getParameter("name"));
        projectDTO.setStage(req.getParameter("stage"));
        projectDTO.setTimePeriod(Integer.parseInt(req.getParameter("period")));
        projectDTO.setCoast(Integer.parseInt(req.getParameter("coast")));
        projectDTO.setNumberOfDevelopers(Integer.parseInt(req.getParameter("number of developers")));
        projectDTO.setDateOfBeginning(LocalDate.parse(req.getParameter("start date")));
        return projectDTO;
    }

    private Set<DeveloperDTO> setDevelopers(HttpServletRequest req, ProjectDTO projectDTO) {
        Set<DeveloperDTO> developerDTOSet = new HashSet<>();
        if (!req.getParameter("developers").equals("")) {
            String[] s = req.getParameter("developers").split(",");
            Arrays.stream(s)
                    .map(Integer::parseInt)
                    .forEach((d) -> {
                        DeveloperDTO developerDTO = DevelopersConverter.fromDevelopersDAO(developersRepository.findById(d).orElseThrow());
                        developerDTOSet.add(developerDTO);
                    });
        }
        return developerDTOSet;
    }
}