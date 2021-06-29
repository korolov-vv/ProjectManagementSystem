package ua.goit.service.developers;

import ua.goit.dao.model.DevelopersOnProjectsDAO;
import ua.goit.dto.DevelopersOnProjectsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DevelopersOnProjectsConverter {
    public static DevelopersOnProjectsDAO toDevelopersOnProjects(DevelopersOnProjectsDTO developersOnProjectsDTO) {
        return new DevelopersOnProjectsDAO(
                developersOnProjectsDTO.getDeveloperId(),
                developersOnProjectsDTO.getProjectId()
        );
    }

    public static DevelopersOnProjectsDTO fromDevelopersOnProjects(DevelopersOnProjectsDAO developersOnProjectsDAO) {
        return new DevelopersOnProjectsDTO(
                developersOnProjectsDAO.getDeveloperId(),
                developersOnProjectsDAO.getProjectId()
        );
    }

    public static DevelopersOnProjectsDAO toDeveloperOnProject(ResultSet resultSet) throws SQLException {
        DevelopersOnProjectsDAO developersOnProjectsDAO = new DevelopersOnProjectsDAO();
        while (resultSet.next()) {
            developersOnProjectsDAO.setDeveloperId(resultSet.getLong("developer_id"));
            developersOnProjectsDAO.setProjectId(resultSet.getLong("project_id"));
        }
        return developersOnProjectsDAO;
    }
}
