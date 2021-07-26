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
public class FindByEmailServlet extends HttpServlet {
    private SingleEntityRepository<DeveloperDAO> developersRepository;

    @Override
    public void init() {
        this.developersRepository = new DevelopersRepository(HibernateDatabaseConnector.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String developerEmail = req.getParameter("developerEmail");
        if(developersRepository.findByUniqueParameter("developerEmail", developerEmail).isPresent()) {
            DeveloperDAO developerDAO = developersRepository.findByUniqueParameter(
                    "developerEmail", developerEmail
            ).get();
            DeveloperDTO developerDTO = DevelopersConverter.fromDevelopersDAO(developerDAO);
            req.setAttribute("developer", developerDTO);
            req.getRequestDispatcher("/view/developers/findDeveloperByEmail.jsp").forward(req, resp);
        }else {
            ServletException ex = new ServletException(String.format("The developer with email: %s does not exist", developerEmail));
            req.setAttribute("message", ex.getMessage());
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
        }
    }
}
