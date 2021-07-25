package ua.goit.controller.developers;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.DevelopersRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.DeveloperDAO;
import ua.goit.dto.DeveloperDTO;
import ua.goit.service.developers.DevelopersConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/developers/developer")
public class FindByIdServlet extends HttpServlet {
    private SingleEntityRepository<DeveloperDAO> developersRepository;

    @Override
    public void init() {
        this.developersRepository = new DevelopersRepository(HibernateDatabaseConnector.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        DeveloperDAO developerDAO = developersRepository.findById(Integer.parseInt(id)).orElseThrow();
        if(developerDAO.getDeveloperId() == 0){
            ServletException ex = new ServletException("The developer does not exist");
            req.setAttribute("message", ex.getMessage());
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
        }else {
            DeveloperDTO developerDTO = DevelopersConverter.fromDevelopersDAO(developerDAO);
            req.setAttribute("developer", developerDTO);
            req.getRequestDispatcher("/view/developers/findDeveloperById.jsp").forward(req, resp);
        }
    }
}
