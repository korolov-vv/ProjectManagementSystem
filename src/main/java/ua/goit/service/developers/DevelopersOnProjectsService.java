package ua.goit.service.developers;

public class DevelopersOnProjectsService {
    /*private DevelopersOnProjectsRepository repository;

    public DevelopersOnProjectsService(DevelopersOnProjectsRepository repository) {
        this.repository = repository;
    }

    public DevelopersOnProjectsDTO create(DevelopersOnProjectsDTO developersOnProjectsDTO) {
        DevelopersOnProjectsDAO developersOnProjectsDAO = DevelopersOnProjectsConverter.toDevelopersOnProjects(developersOnProjectsDTO);
        repository.create(developersOnProjectsDAO);
        DevelopersOnProjectsDAO savedDevelopersOnProjectsDAO = repository.findUniqueValue(
                developersOnProjectsDTO.getDeveloperId(),
                developersOnProjectsDTO.getProjectId()
        );

        return DevelopersOnProjectsConverter.fromDevelopersOnProjects(savedDevelopersOnProjectsDAO);
    }

    public DevelopersOnProjectsDTO update(DevelopersOnProjectsDTO developersOnProjectsDTO) {
        DevelopersOnProjectsDAO developersOnProjectsDAO = DevelopersOnProjectsConverter.toDevelopersOnProjects(developersOnProjectsDTO);
        repository.update(developersOnProjectsDAO);
        DevelopersOnProjectsDAO updatedDevelopersOnProjectsDAO = repository.findUniqueValue(
                developersOnProjectsDTO.getDeveloperId(),
                developersOnProjectsDTO.getProjectId()
        );
        return DevelopersOnProjectsConverter.fromDevelopersOnProjects(updatedDevelopersOnProjectsDAO);
    }

    public void delete(int developerId, int projectId) {
        repository.deleteUniqueRecord(developerId, projectId);
    }

    public List<DevelopersOnProjectsDTO> findByProjectId(int projectId) {
        List<DevelopersOnProjectsDAO> DevelopersOnProjectsDAOList = repository.findByProject(projectId);
        return DevelopersOnProjectsDAOList.stream()
                .map(DevelopersOnProjectsConverter::fromDevelopersOnProjects)
                .collect(Collectors.toList());
    }*/
}
