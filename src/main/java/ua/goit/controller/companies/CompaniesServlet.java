package ua.goit.controller.companies;

import org.hibernate.SessionFactory;
import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.CompaniesRepository;
import ua.goit.dao.CustomersRepository;
import ua.goit.dao.ProjectsRepository;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.CompanyDAO;
import ua.goit.dao.model.CustomerDAO;
import ua.goit.dao.model.ProjectDAO;
import ua.goit.dto.CompanyDTO;
import ua.goit.dto.CustomerDTO;
import ua.goit.dto.ProjectDTO;
import ua.goit.service.companies.CompaniesService;
import ua.goit.service.customers.CustomersConverter;
import ua.goit.service.projects.ProjectsConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/companies")
public class CompaniesServlet extends HttpServlet {
    private final SessionFactory sessionFactory = HibernateDatabaseConnector.getSessionFactory();
    private SingleEntityRepository<CompanyDAO> companiesRepository;
    private SingleEntityRepository<ProjectDAO> projectsRepository;
    private SingleEntityRepository<CustomerDAO> customersRepository;
    private CompaniesService companiesService;

    @Override
    public void init() throws ServletException {
        this.companiesRepository = new CompaniesRepository(sessionFactory);
        this.projectsRepository = new ProjectsRepository(sessionFactory);
        this.customersRepository = new CustomersRepository(sessionFactory);
        this.companiesService = new CompaniesService(companiesRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/companies.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CompanyDTO companyDTO = setCompany(req);
        companyDTO.setProjects(setProjects(req));
        companyDTO.setCustomers(setCustomers(req));
        companiesService.create(companyDTO);
        resp.sendRedirect(req.getContextPath() + "/companies");
    }

    private CompanyDTO setCompany(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        CompanyDTO companyDTO;
        if(companiesRepository.findById(id).isPresent()) {
            companyDTO = companiesService.findById(id);
        }else companyDTO = new CompanyDTO();
        companyDTO.setCompanyName(req.getParameter("company name"));
        companyDTO.setNumberOfDevelopers(Integer.parseInt(req.getParameter("number of developers")));
        return companyDTO;
    }

    private Set<ProjectDTO> setProjects(HttpServletRequest req) {
        if(!req.getParameter("projects").equals("")) {
            String[] s = req.getParameter("projects").split(",");
            return Arrays.stream(s)
                    .map(Integer::parseInt)
                    .map((p) -> {
                        return projectsRepository.findById(p).orElseThrow();
                    })
                    .map(ProjectsConverter::fromProjectsDAO)
                    .collect(Collectors.toSet());
        }else return new HashSet<>();
    }

    private Set<CustomerDTO> setCustomers(HttpServletRequest req) {
        if(!req.getParameter("customers").equals("")) {
            String[] s = req.getParameter("customers").split(",");
            return Arrays.stream(s)
                    .map(Integer::parseInt)
                    .map((p) -> {
                        return customersRepository.findById(p).orElseThrow();
                    })
                    .map(CustomersConverter::fromCustomerDAO)
                    .collect(Collectors.toSet());
        }else return new HashSet<>();
    }
}
