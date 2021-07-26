package ua.goit.service.developers;

import ua.goit.dao.model.DeveloperDAO;
import ua.goit.dto.DeveloperDTO;
import ua.goit.service.projects.ProjectsConverter;
import ua.goit.service.skills.SkillsConverter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DevelopersConverter {
    public static DeveloperDAO toDevelopersDAO(DeveloperDTO developerDTO) {
        DeveloperDAO developerDAO = getDeveloperDAO(developerDTO);
        developerDAO.setSkills(SkillsConverter.toSkillsDAOSet(developerDTO.getSkills()));
        developerDAO.setProjects(ProjectsConverter.toProjectsDAOSet(developerDTO.getProjects()));
        return developerDAO;
    }

    public static DeveloperDTO fromDevelopersDAO(DeveloperDAO developerDAO) {
        DeveloperDTO developerDTO = getDeveloperDTO(developerDAO);
        developerDTO.setSkills(SkillsConverter.fromSkillsDAOSet(developerDAO.getSkills()));
        developerDTO.setProjects(ProjectsConverter.fromProjectsDAOSet(developerDAO.getProjects()));
        return developerDTO;
    }

    public static List<DeveloperDTO> fromDevelopersDAOList(List<DeveloperDAO> developerDAOList) {
        List<DeveloperDTO> developerDTOList = new ArrayList<>();
        developerDAOList.stream()
                .forEach((d) -> {
                    developerDTOList.add(DevelopersConverter.fromDevelopersDAO(d));
                });
        return developerDTOList;
    }

    public static Set<DeveloperDAO> toDevelopersDAOSet(Set<DeveloperDTO> developerDTOSet) {
        if(developerDTOSet == null) {
            return new HashSet<>();
        }else return developerDTOSet.stream()
                .map(DevelopersConverter::getDeveloperDAO)
                .collect(Collectors.toSet());
    }

    public static Set<DeveloperDTO> fromDevelopersDAOSet(Set<DeveloperDAO> developerDAOSet) {
        if(developerDAOSet == null) {
            return new HashSet<>();
        }else return developerDAOSet.stream()
                .map(DevelopersConverter::getDeveloperDTO)
                .collect(Collectors.toSet());
    }

    private static DeveloperDAO getDeveloperDAO(DeveloperDTO developerDTO) {
        return new DeveloperDAO(developerDTO.getDeveloperId(), developerDTO.getFirstName(),
                developerDTO.getLastName(), developerDTO.getGender(), developerDTO.getAge(),
                developerDTO.getExperienceInYears(), developerDTO.getCompanyId(), developerDTO.getSalary(),
                developerDTO.getDeveloperEmail());
    }

    private static DeveloperDTO getDeveloperDTO(DeveloperDAO developerDAO) {
        return new DeveloperDTO(developerDAO.getDeveloperId(), developerDAO.getFirstName(),
                developerDAO.getLastName(), developerDAO.getGender(), developerDAO.getAge(),
                developerDAO.getExperienceInYears(), developerDAO.getCompanyId(), developerDAO.getSalary(),
                developerDAO.getDeveloperEmail());
    }
}