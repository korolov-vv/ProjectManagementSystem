package ua.goit.controller.developers;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.DevelopersRepository;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.DeveloperDAO;
import ua.goit.dao.model.ProjectDAO;
import ua.goit.dto.DeveloperDTO;
import ua.goit.service.developers.DevelopersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
}
