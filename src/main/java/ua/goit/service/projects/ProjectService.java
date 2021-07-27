package ua.goit.service.projects;

import ua.goit.dao.SingleEntityRepository;
import ua.goit.dao.model.ProjectDAO;
import ua.goit.dto.ProjectDTO;

public class ProjectService {
    private SingleEntityRepository<ProjectDAO> repository;

    public ProjectService(SingleEntityRepository<ProjectDAO> repository) {
        this.repository = repository;
    }

    public ProjectDTO create(ProjectDTO projectDTO) {
        ProjectDAO projectDAO = ProjectsConverter.toProjectsDAO(projectDTO);
        repository.create(projectDAO);
        ProjectDAO savedProjectDAO = repository.findByUniqueParameter("projectName",
                projectDAO.getProjectName()).orElseThrow();
        return ProjectsConverter.fromProjectsDAO(savedProjectDAO);
    }

    public ProjectDTO update(ProjectDTO projectDTO) {
        ProjectDAO projectDAO = ProjectsConverter.toProjectsDAO(projectDTO);
        repository.update(projectDAO);
        ProjectDAO updatedProjectDAO = repository.findById(projectDAO.getProjectId()).orElseThrow();
        return ProjectsConverter.fromProjectsDAO(updatedProjectDAO);
    }

    public void delete(String name) {
        repository.deleteByParameter("projectName", name);
    }

    public ProjectDTO findById(int id) {
        return ProjectsConverter.fromProjectsDAO(repository.findById(id).orElseThrow());
    }

    public ProjectDTO findByUniqueValue(String value) {
        return ProjectsConverter.fromProjectsDAO(repository.findByUniqueParameter("projectName", value).orElseThrow());
    }
}
