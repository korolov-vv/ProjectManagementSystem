package ua.goit.controller.companies;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.CompaniesRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.CompaniesDAO;
import ua.goit.dto.CompaniesDTO;
import ua.goit.service.companies.CompaniesConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/companies/company")
public class FindByNameServlet extends HttpServlet {
    private SingleEntityRepository<CompaniesDAO> companiesRepository;

    @Override
    public void init() throws ServletException {
        this.companiesRepository = new CompaniesRepository(HibernateDatabaseConnector.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        CompaniesDAO companiesDAO = (CompaniesDAO) companiesRepository.findByUniqueValue(name);
        if(companiesDAO.getCompanyId() == 0){
            ServletException ex = new ServletException("The company does not exist");
            req.setAttribute("message", ex.getMessage());
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
        }
        CompaniesDTO companiesDTO = CompaniesConverter.fromCompaniesDAO(companiesDAO);
        req.setAttribute("company", companiesDTO);
        req.getRequestDispatcher("/view/companies/findCompanyByName.jsp").forward(req, resp);
    }
}
