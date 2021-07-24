package ua.goit.service.developers;

import ua.goit.dao.model.DevelopersDAO;
import ua.goit.dao.model.ProjectsDAO;
import ua.goit.dao.model.SkillsDAO;
import ua.goit.dto.DevelopersDTO;
import ua.goit.dto.ProjectsDTO;
import ua.goit.dto.SkillsDTO;
import ua.goit.service.projects.ProjectsConverter;
import ua.goit.service.skills.SkillsConverter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DevelopersConverter {
    public static DevelopersDAO toDevelopersDAO(DevelopersDTO developersDTO) {
        Set<SkillsDAO> skills = toSkillsDAO(developersDTO.getSkills());
        Set<ProjectsDAO> projects = toProjectsDAO(developersDTO.getProjects());
        return new DevelopersDAO(developersDTO.getDeveloperId(), developersDTO.getFirstName(),
                developersDTO.getLastName(), developersDTO.getGender(), developersDTO.getAge(),
                developersDTO.getExperienceInYears(), developersDTO.getCompanyId(), developersDTO.getSalary(),
                developersDTO.getDeveloperEmail(), skills, projects);
    }

    public static DevelopersDTO fromDevelopersDAO(DevelopersDAO developersDAO) {
        Set<SkillsDTO> skills = fromSkillsDAO(developersDAO.getSkills());
        Set<ProjectsDTO> projects = fromProjectsDAO(developersDAO.getProjects());
        return new DevelopersDTO(developersDAO.getDeveloperId(), developersDAO.getFirstName(),
                developersDAO.getLastName(), developersDAO.getGender(), developersDAO.getAge(),
                developersDAO.getExperienceInYears(), developersDAO.getCompanyId(), developersDAO.getSalary(),
                developersDAO.getDeveloperEmail(), skills, projects);
    }

    public static List<DevelopersDTO> fromDevelopersDAOList(List<DevelopersDAO> developersDAOList) {
        List<DevelopersDTO> developersDTOList = new ArrayList<>();
        developersDAOList.stream()
                .forEach((d) -> {
                    developersDTOList.add(DevelopersConverter.fromDevelopersDAO(d));
                });
        return developersDTOList;
    }

    private static Set<SkillsDAO> toSkillsDAO(Set<SkillsDTO> skillsDTOSet) {
        if(skillsDTOSet == null) {
            return new HashSet<>();
        }else return skillsDTOSet.stream()
                .map(SkillsConverter::toSkillsDAO)
                .collect(Collectors.toSet());
    }

    private static Set<ProjectsDAO> toProjectsDAO(Set<ProjectsDTO> projectsDTOSet) {
        if(projectsDTOSet == null) {
            return new HashSet<>();
        }else return projectsDTOSet.stream()
                .map(ProjectsConverter::toProjectsDAO)
                .collect(Collectors.toSet());
    }

    private static Set<SkillsDTO> fromSkillsDAO(Set<SkillsDAO> skillsDAOSet) {
        if(skillsDAOSet == null) {
            return new HashSet<>();
        }else return skillsDAOSet.stream()
                .map(SkillsConverter::fromSkillsDAO)
                .collect(Collectors.toSet());
    }

    private static Set<ProjectsDTO> fromProjectsDAO(Set<ProjectsDAO> projectsDAOSet) {
        if(projectsDAOSet == null) {
            return new HashSet<>();
        }else return projectsDAOSet.stream()
                .map(ProjectsConverter::fromProjectsDAO)
                .collect(Collectors.toSet());
    }
}
