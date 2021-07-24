package ua.goit.controller.developers;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.DevelopersRepository;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.DevelopersDAO;
import ua.goit.dao.model.Levels;
import ua.goit.dao.model.ProjectsDAO;
import ua.goit.dao.model.Stack;
import ua.goit.dto.DevelopersDTO;
import ua.goit.dto.ProjectsDTO;
import ua.goit.dto.SkillsDTO;
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

@WebServlet("/developers")
public class DevelopersServlet extends HttpServlet {
    private SingleEntityRepository<DevelopersDAO> developersRepository;
    private SingleEntityRepository<ProjectsDAO> projectsRepository;
    private DevelopersService developersService;

    @Override
    public void init() throws ServletException {
        this.developersRepository = new DevelopersRepository(HibernateDatabaseConnector.getSessionFactory());
        this.projectsRepository = new ProjectsRepository(HibernateDatabaseConnector.getSessionFactory());
        this.developersService = new DevelopersService(developersRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/developers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DevelopersDTO developersDTO = setDeveloper(req);
        developersDTO.setSkills(setSkills(req, developersDTO));
        developersDTO.setProjects(setProjects(req));
        developersService.create(developersDTO);
        resp.sendRedirect(req.getContextPath() + "/developers");
    }

    private DevelopersDTO setDeveloper(HttpServletRequest req) {
        DevelopersDTO developersDTO = new DevelopersDTO();
        developersDTO.setFirstName(req.getParameter("first name"));
        developersDTO.setLastName(req.getParameter("last name"));
        developersDTO.setGender(req.getParameter("gender"));
        developersDTO.setAge(Integer.parseInt(req.getParameter("age")));
        developersDTO.setExperienceInYears(Integer.parseInt(req.getParameter("experience")));
        developersDTO.setCompanyId(Integer.parseInt(req.getParameter("company")));
        developersDTO.setSalary(Integer.parseInt(req.getParameter("salary")));
        developersDTO.setDeveloperEmail(req.getParameter("email"));
        return developersDTO;
    }

    private Set<ProjectsDTO> setProjects(HttpServletRequest req) {
        if(!req.getParameter("projects").equals("")) {
            String[] s = req.getParameter("projects").split(",");
            List<Integer> projectIds = Arrays.stream(s)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            return projectIds.stream()
                    .map((p) -> {
                        return projectsRepository.findById(p);
                    })
                    .map(ProjectsConverter::fromProjectsDAO)
                    .collect(Collectors.toSet());
        }else return new HashSet<>();
    }

    private Set<SkillsDTO> setSkills(HttpServletRequest req, DevelopersDTO developersDTO) {
        SkillsDTO skillsDTO = new SkillsDTO();
        Set<SkillsDTO> skillsDTOSet = new HashSet<>();
        skillsDTO.setDeveloperEmail(developersDTO.getDeveloperEmail());
        skillsDTO.setStack(Stack.valueOf(req.getParameter("stack")));
        skillsDTO.setLevel(Levels.valueOf(req.getParameter("level")));
        skillsDTOSet.add(skillsDTO);
        return skillsDTOSet;
    }
}
