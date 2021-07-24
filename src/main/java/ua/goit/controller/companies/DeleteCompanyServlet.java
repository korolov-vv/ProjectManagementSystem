package ua.goit.controller.companies;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.CompaniesRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.CompaniesDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/companies/deleteCompany")
public class DeleteCompanyServlet extends HttpServlet {
    private SingleEntityRepository<CompaniesDAO> companiesRepository;

    @Override
    public void init() throws ServletException {
        this.companiesRepository = new CompaniesRepository(HibernateDatabaseConnector.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        CompaniesDAO companiesDAOForDelete = companiesRepository.findById(Integer.parseInt(id));
        companiesRepository.delete(String.valueOf(companiesDAOForDelete.getCompanyId()));
        req.setAttribute("id", companiesDAOForDelete.getCompanyId());
        req.getRequestDispatcher("/view/companies/deleteCompany.jsp").forward(req, resp);
    }
}
