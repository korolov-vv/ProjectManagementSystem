package ua.goit.controller.projects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.controller.companies.UpdateCompanyServlet;
import ua.goit.dao.DevelopersRepository;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.DeveloperDAO;
import ua.goit.dao.model.ProjectDAO;
import ua.goit.dto.CompanyDTO;
import ua.goit.dto.DeveloperDTO;
import ua.goit.dto.ProjectDTO;
import ua.goit.service.projects.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/projects/update")
public class UpdateProjectServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateCompanyServlet.class);
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
        req.setAttribute("developersList", getDeveloperIds(projectDTO));
        req.setAttribute("companies", getCompanyIds(projectDTO));
        req.getRequestDispatcher("/view/projects/updateForm.jsp").forward(req, resp);
    }

    private String getDeveloperIds(ProjectDTO projectDTO) {
        String developersList = "";
        if(projectDTO.getDevelopers().size() != 0) {
            String s = "";
            developersList = projectDTO.getDevelopers().stream()
                    .map(DeveloperDTO::getDeveloperId)
                    .map(String::valueOf)
                    .map((id) -> s.concat(id + ","))
                    .collect(Collectors.joining());
            LOG.debug("-----------------------------------------------------The list of projects is set: " + developersList);
            return developersList;
        }else return developersList;
    }

    private String getCompanyIds(ProjectDTO projectDTO) {
        String companies = "";
        if(projectDTO.getCompanies().size() != 0) {
            String s = "";
            companies = projectDTO.getCompanies().stream()
                    .map(CompanyDTO::getCompanyId)
                    .map(String::valueOf)
                    .map((id) -> s.concat(id + ","))
                    .collect(Collectors.joining());
            LOG.debug("-----------------------------------------------------The list of projects is set: " + companies);
            return companies;
        }else return companies;
    }
}