package ua.goit.controller.developers;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.DevelopersRepository;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.DeveloperDAO;
import ua.goit.dao.model.Levels;
import ua.goit.dao.model.ProjectDAO;
import ua.goit.dao.model.Stack;
import ua.goit.dto.DeveloperDTO;
import ua.goit.dto.ProjectDTO;
import ua.goit.dto.SkillDTO;
import ua.goit.service.developers.DevelopersService;
import ua.goit.service.projects.ProjectsConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/developers/update")
public class UpdateDeveloperServlet extends HttpServlet {
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
        req.setAttribute("skillsDTO", developerDTO.getSkills());
        req.setAttribute("projectsList", developerDTO.getProjects());
        req.getRequestDispatcher("/view/developers/updateDeveloperForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        updateDeveloper(req);
        resp.sendRedirect(req.getContextPath() + "/developers");
    }

    private DeveloperDTO updateDeveloper(HttpServletRequest req) {
        DeveloperDTO developerDTO = setDeveloper(req);
        developerDTO.setSkills(setSkills(req, developerDTO));
        developerDTO.setProjects(setProjects(req));
        developersService.update(developerDTO);
        return developerDTO;
    }

    private DeveloperDTO setDeveloper(HttpServletRequest req) {
        DeveloperDTO developerDTO = new DeveloperDTO();
        developerDTO.setFirstName(req.getParameter("first name"));
        developerDTO.setLastName(req.getParameter("last name"));
        developerDTO.setGender(req.getParameter("gender"));
        developerDTO.setAge(Integer.parseInt(req.getParameter("age")));
        developerDTO.setExperienceInYears(Integer.parseInt(req.getParameter("experience")));
        developerDTO.setCompanyId(Integer.parseInt(req.getParameter("company")));
        developerDTO.setSalary(Integer.parseInt(req.getParameter("salary")));
        developerDTO.setDeveloperEmail(req.getParameter("email"));
        return developerDTO;
    }

    private Set<ProjectDTO> setProjects(HttpServletRequest req) {
        if(!req.getParameter("projects").equals("")) {
            String[] s = req.getParameter("projects").split(",");
            List<Integer> projectIds = Arrays.stream(s)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            return projectIds.stream()
                    .map((p) -> {
                        return projectsRepository.findById(p).orElseThrow();
                    })
                    .map(ProjectsConverter::fromProjectsDAO)
                    .collect(Collectors.toSet());
        }else return new HashSet<>();
    }

    private Set<SkillDTO> setSkills(HttpServletRequest req, DeveloperDTO developerDTO) {
        SkillDTO skillDTO = new SkillDTO();
        Set<SkillDTO> skillDTOSet = new HashSet<>();
        skillDTO.setDeveloperEmail(developerDTO.getDeveloperEmail());
        skillDTO.setStack(Stack.valueOf(req.getParameter("stack")));
        skillDTO.setLevel(Levels.valueOf(req.getParameter("level")));
        skillDTOSet.add(skillDTO);
        return skillDTOSet;
    }
}
