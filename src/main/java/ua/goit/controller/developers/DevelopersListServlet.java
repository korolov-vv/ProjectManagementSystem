package ua.goit.controller.developers;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.DevelopersRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.DeveloperDAO;
import ua.goit.dto.DeveloperDTO;
import ua.goit.service.developers.DevelopersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/developers/list")
public class DevelopersListServlet extends HttpServlet {
    private SingleEntityRepository<DeveloperDAO> developersRepository;
    private DevelopersService developersService;

    @Override
    public void init() throws ServletException {
        this.developersRepository = new DevelopersRepository(HibernateDatabaseConnector.getSessionFactory());
        this.developersService = new DevelopersService(developersRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DeveloperDTO> developerDTOList = developersService.findAll();
        req.setAttribute("developers", developerDTOList);
        req.getRequestDispatcher("/view/developers/listOfDevelopers.jsp").forward(req, resp);
    }
}
