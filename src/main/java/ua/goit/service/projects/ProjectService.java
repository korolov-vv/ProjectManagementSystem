package ua.goit.service.projects;

import ua.goit.dao.Repository;
import ua.goit.dao.model.ProjectsDAO;
import ua.goit.dto.ProjectsDTO;

public class ProjectService {
    private Repository<ProjectsDAO> repository;

    public ProjectService(Repository<ProjectsDAO> repository) {
        this.repository = repository;
    }

    public ProjectsDTO create(ProjectsDTO projectsDTO) {
        ProjectsDAO projectsDAO = ProjectsConverter.toProject(projectsDTO);
        repository.create(projectsDAO);
        ProjectsDAO savedProjectsDAO = repository.findById(projectsDAO.getProjectId());
        return ProjectsConverter.fromProject(savedProjectsDAO);
    }

    public ProjectsDTO update(ProjectsDTO projectsDTO) {
        ProjectsDAO projectsDAO = ProjectsConverter.toProject(projectsDTO);
        repository.update(projectsDAO);
        ProjectsDAO updatedProjectsDAO = repository.findById(projectsDAO.getProjectId());
        return ProjectsConverter.fromProject(updatedProjectsDAO);
    }

    public void delete(String name) {
        repository.delete(name);
    }
}
