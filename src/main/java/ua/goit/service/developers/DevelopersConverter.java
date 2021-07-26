package ua.goit.service.developers;

import ua.goit.dao.model.DeveloperDAO;
import ua.goit.dto.DeveloperDTO;

import java.util.ArrayList;
import java.util.List;

public class DevelopersConverter {
    public static DeveloperDAO toDevelopersDAO(DeveloperDTO developerDTO) {
        return new DeveloperDAO(developerDTO.getDeveloperId(), developerDTO.getFirstName(),
                developerDTO.getLastName(), developerDTO.getGender(), developerDTO.getAge(),
                developerDTO.getExperienceInYears(), developerDTO.getCompanyId(), developerDTO.getSalary(),
                developerDTO.getDeveloperEmail());
    }

    public static DeveloperDTO fromDevelopersDAO(DeveloperDAO developerDAO) {
        return new DeveloperDTO(developerDAO.getDeveloperId(), developerDAO.getFirstName(),
                developerDAO.getLastName(), developerDAO.getGender(), developerDAO.getAge(),
                developerDAO.getExperienceInYears(), developerDAO.getCompanyId(), developerDAO.getSalary(),
                developerDAO.getDeveloperEmail());
    }

    public static List<DeveloperDTO> fromDevelopersDAOList(List<DeveloperDAO> developerDAOList) {
        List<DeveloperDTO> developerDTOList = new ArrayList<>();
        developerDAOList.stream()
                .forEach((d) -> {
                    developerDTOList.add(DevelopersConverter.fromDevelopersDAO(d));
                });
        return developerDTOList;
    }
}
