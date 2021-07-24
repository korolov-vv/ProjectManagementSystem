package ua.goit.service.projects;

import ua.goit.dao.model.ProjectsDAO;
import ua.goit.dao.SingleEntityRepository;
import ua.goit.dto.ProjectsDTO;

public class ProjectService {
    private SingleEntityRepository<ProjectsDAO> repository;

    public ProjectService(SingleEntityRepository<ProjectsDAO> repository) {
        this.repository = repository;
    }

    public ProjectsDTO create(ProjectsDTO projectsDTO) {
        ProjectsDAO projectsDAO = ProjectsConverter.toProjectsDAO(projectsDTO);
        repository.create(projectsDAO);
        ProjectsDAO savedProjectsDAO = repository.findByUniqueValue(projectsDAO.getProjectName());
        return ProjectsConverter.fromProjectsDAO(savedProjectsDAO);
    }

    public ProjectsDTO update(ProjectsDTO projectsDTO) {
        ProjectsDAO projectsDAO = ProjectsConverter.toProjectsDAO(projectsDTO);
        repository.update(projectsDAO);
        ProjectsDAO updatedProjectsDAO = repository.findById(projectsDAO.getProjectId());
        return ProjectsConverter.fromProjectsDAO(updatedProjectsDAO);
    }

    public void delete(String name) {
        repository.delete(name);
    }

    public ProjectsDTO findById(int id) {
        return ProjectsConverter.fromProjectsDAO(repository.findById(id));
    }

    public ProjectsDTO findByUniqueValue(String value) {
        return ProjectsConverter.fromProjectsDAO(repository.findByUniqueValue(value));
    }
}
