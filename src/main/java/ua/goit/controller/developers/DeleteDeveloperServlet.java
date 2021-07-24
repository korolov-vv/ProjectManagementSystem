package ua.goit.controller.developers;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.DevelopersRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.DevelopersDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/developers/deleteDeveloper")
public class DeleteDeveloperServlet extends HttpServlet {
    private SingleEntityRepository<DevelopersDAO> developersRepository;

    @Override
    public void init() throws ServletException {
        this.developersRepository = new DevelopersRepository(HibernateDatabaseConnector.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        DevelopersDAO developersDAOForDelete = developersRepository.findById(Integer.parseInt(id));
        developersRepository.delete(Integer.parseInt(id));
        req.setAttribute("id", developersDAOForDelete.getDeveloperId());
        req.getRequestDispatcher("/view/developers/deleteDeveloper.jsp").forward(req, resp);
    }
}
