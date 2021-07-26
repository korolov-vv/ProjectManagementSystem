package ua.goit.controller.companies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.CompaniesRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.CompanyDAO;
import ua.goit.dto.CompanyDTO;
import ua.goit.dto.CustomerDTO;
import ua.goit.dto.ProjectDTO;
import ua.goit.service.companies.CompaniesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/companies/update")
public class UpdateCompanyServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateCompanyServlet.class);
    private SingleEntityRepository<CompanyDAO> companiesRepository;
    private CompaniesService companiesService;

    @Override
    public void init() throws ServletException {
        this.companiesRepository = new CompaniesRepository(HibernateDatabaseConnector.getSessionFactory());
        this.companiesService = new CompaniesService(companiesRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        CompanyDTO companyDTO = companiesService.findByName(name);
        req.setAttribute("company", companyDTO);
        req.setAttribute("projects", getProjectIds(companyDTO));
        req.setAttribute("customers", getCustomerIds(companyDTO));
        req.getRequestDispatcher("/view/companies/updateCompanyForm.jsp").forward(req, resp);
    }

    private String getProjectIds(CompanyDTO companyDTO) {
        String projects = "";
        if (companyDTO.getProjects().size() != 0) {
            String s = "";
            projects = companyDTO.getProjects().stream()
                    .map(ProjectDTO::getProjectId)
                    .map(String::valueOf)
                    .map((id) -> s.concat(id + ","))
                    .collect(Collectors.joining());
            LOG.debug("-----------------------------------------------------The list of projects is set: " + projects);
            return projects;
        }else return projects;
    }

    private String getCustomerIds(CompanyDTO companyDTO) {
        String customers = "";
        if(companyDTO.getCustomers().size() != 0) {
            String s = "";
            customers = companyDTO.getCustomers().stream()
                    .map(CustomerDTO::getCustomerId)
                    .map(String::valueOf)
                    .map((id) -> s.concat(id + ","))
                    .collect(Collectors.joining());
            LOG.debug("-----------------------------------------------------The list of customers is set: " + customers);
            return customers;
        }else return customers;
    }
}
