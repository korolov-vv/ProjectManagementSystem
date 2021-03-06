package ua.goit.controller.companies;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.CompaniesRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.CompanyDAO;
import ua.goit.dto.CompanyDTO;
import ua.goit.service.companies.CompaniesConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/companies/company")
public class FindByNameServlet extends HttpServlet {
    private SingleEntityRepository<CompanyDAO> companiesRepository;

    @Override
    public void init() throws ServletException {
        this.companiesRepository = new CompaniesRepository(HibernateDatabaseConnector.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if (companiesRepository.findByUniqueParameter("companyName", name).isPresent()) {
            CompanyDAO companyDAO = companiesRepository.findByUniqueParameter("companyName", name).orElseThrow();
            CompanyDTO companyDTO = CompaniesConverter.fromCompaniesDAO(companyDAO);
            req.setAttribute("company", companyDTO);
            req.getRequestDispatcher("/view/companies/findCompanyByName.jsp").forward(req, resp);
        } else {
            ServletException ex = new ServletException(String.format("The company with name %s does not exist", name));
            req.setAttribute("message", ex.getMessage());
            req.getRequestDispatcher("/view/errorPage.jsp").forward(req, resp);
        }
    }
}
