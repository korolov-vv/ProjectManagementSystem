package ua.goit.controller.developers;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.DevelopersRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.DeveloperDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/developers/deleteDeveloper")
public class DeleteDeveloperServlet extends HttpServlet {
    private SingleEntityRepository<DeveloperDAO> developersRepository;

    @Override
    public void init() throws ServletException {
        this.developersRepository = new DevelopersRepository(HibernateDatabaseConnector.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String developerEmail = req.getParameter("developerEmail");
        if(developersRepository.findByUniqueParameter("developerEmail", developerEmail).isPresent()) {
            DeveloperDAO developerDAOForDelete = developersRepository.findByUniqueParameter(
                    "developerEmail", developerEmail
            ).get();
            developersRepository.deleteByParameter("developerEmail", developerEmail);
            req.setAttribute("developerEmail", developerDAOForDelete.getDeveloperEmail());
            req.getRequestDispatcher("/view/developers/deleteDeveloper.jsp").forward(req, resp);
        }else {
            ServletException ex = new ServletException(String.format("The developer with email: %s does not exist", developerEmail));
            req.setAttribute("message", ex.getMessage());
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
        }
    }
}
