package ua.goit.controller.companies;

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

@WebServlet("/companies")
public class CompaniesServlet extends HttpServlet {
    private SingleEntityRepository<CompanyDAO> companiesRepository;
    private CompaniesService companiesService;

    @Override
    public void init() throws ServletException {
        this.companiesRepository = new CompaniesRepository(HibernateDatabaseConnector.getSessionFactory());
        this.companiesService = new CompaniesService(companiesRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/companies.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompanyDTO companyDTO = addCompany(req);
        resp.sendRedirect(req.getContextPath() + "/companies");
    }

    private CompanyDTO addCompany(HttpServletRequest req) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCompanyName(req.getParameter("company name"));
        companyDTO.setNumberOfDevelopers(Integer.parseInt(req.getParameter("number of developers")));
        companiesService.create(companyDTO);
        return companyDTO;
    }
}
