package ua.goit.controller.projects;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.DevelopersOnProjectsRepository;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dao.model.ProjectsDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/projects/deleteProject")
public class DeleteProjectServlet extends HttpServlet {
    private ProjectsRepository projectsRepository;
    private DevelopersOnProjectsRepository developersOnProjectsRepository;

    @Override
    public void init() {
        this.projectsRepository = new ProjectsRepository(HibernateDatabaseConnector.getSessionFactory());
        this.developersOnProjectsRepository = new DevelopersOnProjectsRepository(HibernateDatabaseConnector.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        ProjectsDAO projectsDAOForDelete = projectsRepository.findByUniqueValue(name);
        int projectId = projectsDAOForDelete.getProjectId();
/*        List<DevelopersOnProjectsDAO> toDeleteDevelopersOnProject = (developersOnProjectsRepository.findByProject(projectId)).stream()
                .filter((p) -> p.getProjectId() == projectId)
                .collect(Collectors.toList());
        if (toDeleteDevelopersOnProject.size() > 0) {
            developersOnProjectsRepository.deleteForProjects(projectId);
        }*/
        projectsRepository.delete(name);
        req.setAttribute("name", projectsDAOForDelete.getProjectName());
        req.getRequestDispatcher("/view/projects/deleteProject.jsp").forward(req, resp);
    }

}
