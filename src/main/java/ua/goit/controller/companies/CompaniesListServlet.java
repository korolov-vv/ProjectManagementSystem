package ua.goit.controller.companies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.CompaniesRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.CompanyDAO;
import ua.goit.dto.CompanyDTO;
import ua.goit.service.companies.CompaniesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/companies/list")
public class CompaniesListServlet extends HttpServlet {
    private final static Logger LOG = LoggerFactory.getLogger(CompaniesListServlet.class);

    private SingleEntityRepository<CompanyDAO> companiesRepository;
    private CompaniesService companiesService;

    @Override
    public void init() throws ServletException {
        this.companiesRepository = new CompaniesRepository(HibernateDatabaseConnector.getSessionFactory());
        this.companiesService = new CompaniesService(companiesRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CompanyDTO> companyDTOList = companiesService.findAll();
        req.setAttribute("companies", companyDTOList);
        req.getRequestDispatcher("/view/companies/listOfCompanies.jsp").forward(req, resp);
    }
}
