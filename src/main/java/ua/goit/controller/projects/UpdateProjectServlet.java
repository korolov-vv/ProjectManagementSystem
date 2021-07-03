package ua.goit.controller.projects;

import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.CustomersAndCompaniesRepository;
import ua.goit.dao.DevelopersOnProjectsRepository;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dto.CustomersAndCompaniesDTO;
import ua.goit.dto.DevelopersOnProjectsDTO;
import ua.goit.dto.ProjectsDTO;
import ua.goit.service.customers.CustomersAndCompaniesService;
import ua.goit.service.developers.DevelopersOnProjectsService;
import ua.goit.service.projects.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/projects/update")
public class UpdateProjectServlet extends HttpServlet {
    private ProjectsRepository projectsRepository;
    private DevelopersOnProjectsRepository developersOnProjectsRepository;
    private CustomersAndCompaniesRepository customersAndCompaniesRepository;
    private ProjectService projectService;
    private DevelopersOnProjectsService developersOnProjectsService;
    private CustomersAndCompaniesService customersAndCompaniesService;

    @Override
    public void init() throws ServletException {
        this.projectsRepository = new ProjectsRepository(DatabaseConnectionManager.getDataSource());
        this.developersOnProjectsRepository = new DevelopersOnProjectsRepository(DatabaseConnectionManager.getDataSource());
        this.customersAndCompaniesRepository = new CustomersAndCompaniesRepository(DatabaseConnectionManager.getDataSource());
        this.projectService = new ProjectService(projectsRepository);
        this.developersOnProjectsService = new DevelopersOnProjectsService(developersOnProjectsRepository);
        this.customersAndCompaniesService = new CustomersAndCompaniesService(customersAndCompaniesRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        ProjectsDTO projectsDTO = projectService.findByUniqueValue(name);
        List<DevelopersOnProjectsDTO> developersOnProjectsDTOList = developersOnProjectsService.findByProjectId(projectsDTO.getProjectId());
        List<CustomersAndCompaniesDTO> customersAndCompaniesDTOList = customersAndCompaniesService.findByProjectId(projectsDTO.getProjectId());
        req.setAttribute("project", projectsDTO);
        req.setAttribute("developersList", developersOnProjectsDTOList);
        req.setAttribute("customersAndCompaniesList", customersAndCompaniesDTOList);
        req.getRequestDispatcher("/view/projects/updateForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectsDTO projectsDTO = new ProjectsDTO();
        projectsDTO.setProjectName(req.getParameter("name"));
        projectsDTO.setStage(req.getParameter("stage"));
        projectsDTO.setTimePeriod(Integer.parseInt(req.getParameter("period")));
        projectsDTO.setCoast(Integer.parseInt(req.getParameter("coast")));
        projectsDTO.setNumberOfDevelopers(Integer.parseInt(req.getParameter("number of developers")));
        projectsDTO.setDateOfBeginning(LocalDate.parse(req.getParameter("start date")));
        projectService.update(projectsDTO);
        resp.sendRedirect(req.getContextPath() + "/projects");
    }
}