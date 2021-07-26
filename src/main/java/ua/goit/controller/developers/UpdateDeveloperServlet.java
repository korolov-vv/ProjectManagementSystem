package ua.goit.controller.developers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.controller.companies.UpdateCompanyServlet;
import ua.goit.dao.DevelopersRepository;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.DeveloperDAO;
import ua.goit.dao.model.ProjectDAO;
import ua.goit.dto.DeveloperDTO;
import ua.goit.dto.ProjectDTO;
import ua.goit.dto.SkillDTO;
import ua.goit.service.developers.DevelopersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/developers/update")
public class UpdateDeveloperServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateCompanyServlet.class);
    private SingleEntityRepository<DeveloperDAO> developersRepository;
    private SingleEntityRepository<ProjectDAO> projectsRepository;
    private DevelopersService developersService;

    @Override
    public void init() throws ServletException {
        this.developersRepository = new DevelopersRepository(HibernateDatabaseConnector.getSessionFactory());
        this.projectsRepository = new ProjectsRepository(HibernateDatabaseConnector.getSessionFactory());
        this.developersService = new DevelopersService(developersRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        DeveloperDTO developerDTO = developersService.findByEmail(email);
        req.setAttribute("developer", developerDTO);
        req.setAttribute("skillsDTO", getSkillDTO(developerDTO));
        req.setAttribute("projectsList", getProjectIds(developerDTO));
        req.getRequestDispatcher("/view/developers/updateDeveloperForm.jsp").forward(req, resp);
    }

    private String getProjectIds(DeveloperDTO developerDTO) {
        String projectsList = "";
        if(developerDTO.getProjects().size() != 0) {
            String s = "";
            projectsList = developerDTO.getProjects().stream()
                    .map(ProjectDTO::getProjectId)
                    .map(String::valueOf)
                    .map((id) -> s.concat(id + ","))
                    .collect(Collectors.joining());
            LOG.debug("-----------------------------------------------------The list of projects is set: " + projectsList);
            return projectsList;
        }else return projectsList;
    }

    private SkillDTO getSkillDTO(DeveloperDTO developerDTO) {
        if(developerDTO.getSkills().stream().findFirst().isPresent()) {
            SkillDTO skillDTO = developerDTO.getSkills().stream().findFirst().get();
            LOG.debug("-----------------------------------------------------The skill of developers is set: "
                    + skillDTO.getStack() + skillDTO.getLevel());
            return skillDTO;
        }else return new SkillDTO();
    }
}
