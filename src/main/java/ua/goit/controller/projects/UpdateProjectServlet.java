package ua.goit.controller.projects;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.DevelopersOnProjectsRepository;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dto.ProjectsDTO;
import ua.goit.service.projects.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/projects/update")
public class UpdateProjectServlet extends HttpServlet {
    private ProjectsRepository projectsRepository;
    private DevelopersOnProjectsRepository developersOnProjectsRepository;
    private ProjectService projectService;
//    private DevelopersOnProjectsService developersOnProjectsService;

    @Override
    public void init() throws ServletException {
        this.projectsRepository = new ProjectsRepository(HibernateDatabaseConnector.getSessionFactory());
        this.developersOnProjectsRepository = new DevelopersOnProjectsRepository(HibernateDatabaseConnector.getSessionFactory());
        this.projectService = new ProjectService(projectsRepository);
//        this.developersOnProjectsService = new DevelopersOnProjectsService(developersOnProjectsRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        ProjectsDTO projectsDTO = projectService.findByUniqueValue(name);
/*
        List<DevelopersOnProjectsDTO> developersOnProjectsDTOList = developersOnProjectsService.findByProjectId(projectsDTO.getProjectId());
        String developers = getDevelopers(developersOnProjectsDTOList);
*/
        req.setAttribute("project", projectsDTO);
//        req.setAttribute("developersList", developers);
        req.getRequestDispatcher("/view/projects/updateForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

/*    private String getDevelopers(List<DevelopersOnProjectsDTO> developersOnProjectsDTOList) {
        String s = "";
        String developers = "";
        if(developersOnProjectsDTOList.size() != 0) {
            developers = developersOnProjectsDTOList.stream()
                    .map(DevelopersOnProjectsDTO::getDeveloperId)
                    .map(d -> s.concat(String.valueOf(d)).concat(","))
                    .collect(Collectors.joining());
        }
        return developers;
    }*/
}