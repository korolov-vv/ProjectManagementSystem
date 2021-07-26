package ua.goit.service.companies;

import ua.goit.dao.model.CompanyDAO;
import ua.goit.dto.CompanyDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CompaniesConverter {
    public static CompanyDAO toCompaniesDAO(CompanyDTO companyDTO) {
        return new CompanyDAO(companyDTO.getCompanyId(), companyDTO.getCompanyName(),
                companyDTO.getNumberOfDevelopers());
    }

    public static CompanyDTO fromCompaniesDAO(CompanyDAO companyDAO) {
        return new CompanyDTO(companyDAO.getCompanyId(), companyDAO.getCompanyName(),
                companyDAO.getNumberOfDevelopers());
    }

    public static List<CompanyDTO> fromCompaniesDAOList(List<CompanyDAO> companyDAOList) {
        List<CompanyDTO> companyDTOList = new ArrayList<>();
        companyDAOList
                .forEach((company) -> {
                    companyDTOList.add(CompaniesConverter.fromCompaniesDAO(company));
                });
        return companyDTOList;
    }

    public static Set<CompanyDAO> toCompaniesDAOSet(Set<CompanyDTO> companyDTOSet) {
        if(companyDTOSet == null) {
            return new HashSet<>();
        }else return companyDTOSet.stream()
                .map(CompaniesConverter::toCompaniesDAO)
                .collect(Collectors.toSet());
    }

    public static Set<CompanyDTO> fromCompaniesDAOSet(Set<CompanyDAO> companyDAOSet) {
        if(companyDAOSet == null) {
            return new HashSet<>();
        }else return companyDAOSet.stream()
                .map(CompaniesConverter::fromCompaniesDAO)
                .collect(Collectors.toSet());
    }
}
