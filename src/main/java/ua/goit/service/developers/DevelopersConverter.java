package ua.goit.service.developers;

import ua.goit.dao.model.DeveloperDAO;
import ua.goit.dao.model.ProjectDAO;
import ua.goit.dao.model.SkillDAO;
import ua.goit.dto.DeveloperDTO;
import ua.goit.dto.ProjectDTO;
import ua.goit.dto.SkillDTO;
import ua.goit.service.projects.ProjectsConverter;
import ua.goit.service.skills.SkillsConverter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DevelopersConverter {
    public static DeveloperDAO toDevelopersDAO(DeveloperDTO developerDTO) {
        Set<SkillDAO> skills = toSkillsDAO(developerDTO.getSkills());
        Set<ProjectDAO> projects = toProjectsDAO(developerDTO.getProjects());
        return new DeveloperDAO(developerDTO.getDeveloperId(), developerDTO.getFirstName(),
                developerDTO.getLastName(), developerDTO.getGender(), developerDTO.getAge(),
                developerDTO.getExperienceInYears(), developerDTO.getCompanyId(), developerDTO.getSalary(),
                developerDTO.getDeveloperEmail(), skills, projects);
    }

    public static DeveloperDTO fromDevelopersDAO(DeveloperDAO developerDAO) {
        Set<SkillDTO> skills = fromSkillsDAO(developerDAO.getSkills());
        Set<ProjectDTO> projects = fromProjectsDAO(developerDAO.getProjects());
        return new DeveloperDTO(developerDAO.getDeveloperId(), developerDAO.getFirstName(),
                developerDAO.getLastName(), developerDAO.getGender(), developerDAO.getAge(),
                developerDAO.getExperienceInYears(), developerDAO.getCompanyId(), developerDAO.getSalary(),
                developerDAO.getDeveloperEmail(), skills, projects);
    }

    public static List<DeveloperDTO> fromDevelopersDAOList(List<DeveloperDAO> developerDAOList) {
        List<DeveloperDTO> developerDTOList = new ArrayList<>();
        developerDAOList.stream()
                .forEach((d) -> {
                    developerDTOList.add(DevelopersConverter.fromDevelopersDAO(d));
                });
        return developerDTOList;
    }

    private static Set<SkillDAO> toSkillsDAO(Set<SkillDTO> skillDTOSet) {
        if(skillDTOSet == null) {
            return new HashSet<>();
        }else return skillDTOSet.stream()
                .map(SkillsConverter::toSkillsDAO)
                .collect(Collectors.toSet());
    }

    private static Set<ProjectDAO> toProjectsDAO(Set<ProjectDTO> projectDTOSet) {
        if(projectDTOSet == null) {
            return new HashSet<>();
        }else return projectDTOSet.stream()
                .map(ProjectsConverter::toProjectsDAO)
                .collect(Collectors.toSet());
    }

    private static Set<SkillDTO> fromSkillsDAO(Set<SkillDAO> skillDAOSet) {
        if(skillDAOSet == null) {
            return new HashSet<>();
        }else return skillDAOSet.stream()
                .map(SkillsConverter::fromSkillsDAO)
                .collect(Collectors.toSet());
    }

    private static Set<ProjectDTO> fromProjectsDAO(Set<ProjectDAO> projectDAOSet) {
        if(projectDAOSet == null) {
            return new HashSet<>();
        }else return projectDAOSet.stream()
                .map(ProjectsConverter::fromProjectsDAO)
                .collect(Collectors.toSet());
    }
}
