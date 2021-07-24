package ua.goit.controller.companies;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.CompaniesRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.CompaniesDAO;
import ua.goit.dto.CompaniesDTO;
import ua.goit.service.companies.CompaniesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/companies/update")
public class UpdateCompanyServlet extends HttpServlet {
    private SingleEntityRepository<CompaniesDAO> companiesRepository;
    private CompaniesService companiesService;

    @Override
    public void init() throws ServletException {
        this.companiesRepository = new CompaniesRepository(HibernateDatabaseConnector.getSessionFactory());
        this.companiesService = new CompaniesService(companiesRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        CompaniesDTO companiesDTO = companiesService.findByName(name);
        req.setAttribute("company", companiesDTO);
        req.getRequestDispatcher("/view/companies/updateCompanyForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CompaniesDTO companiesDTO = updateCompany(req);
        resp.sendRedirect(req.getContextPath() + "/companies");
    }

    private CompaniesDTO updateCompany(HttpServletRequest req) {
        CompaniesDTO companiesDTO = new CompaniesDTO();
        companiesDTO.setCompanyName(req.getParameter("company name"));
        companiesDTO.setNumberOfDevelopers(Integer.parseInt(req.getParameter("number of developers")));
        companiesService.update(companiesDTO);
        return companiesDTO;
    }
}
