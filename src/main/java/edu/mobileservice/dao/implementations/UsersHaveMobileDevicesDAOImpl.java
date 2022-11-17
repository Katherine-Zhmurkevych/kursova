package edu.mobileservice.dao.implementations;

import edu.mobileservice.dao.UsersHaveMobileDevicesDAO;
import edu.mobileservice.model.UsersHaveMobileDevicesEntity;
import edu.mobileservice.utils.ConnectionUtil;
import edu.mobileservice.utils.Transformer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static edu.mobileservice.common.Constants.ZERO;


public class UsersHaveMobileDevicesDAOImpl extends AbstractDAOImpl implements UsersHaveMobileDevicesDAO {

    private static Logger logger = LogManager.getLogger("UsersHaveMobileDevicesDAOImpl");

    public UsersHaveMobileDevicesDAOImpl() {
        super(UsersHaveMobileDevicesEntity.class);
    }

    public List<UsersHaveMobileDevicesEntity> findUsersHaveMobileDevicesByUserId(Integer userId) {
        Connection connection = ConnectionUtil.getConnection();
        List<UsersHaveMobileDevicesEntity> usersHaveMobileDevicesList = new ArrayList<>();
        String findByIdQuery = "SELECT * FROM users_have_mobile_devices WHERE user_id = " + String.valueOf(userId);
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(findByIdQuery);
            while (rs.next()) {
                usersHaveMobileDevicesList.add((UsersHaveMobileDevicesEntity) new Transformer(UsersHaveMobileDevicesEntity.class)
                        .fromResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            logger.error("SQL exception: " + e.getMessage());
        }
        return usersHaveMobileDevicesList;
    }

    public String prepareDeleteQuery(Integer userId, Integer mobileId) {
        String deleteEntityQuery = "DELETE FROM users_have_mobile_devices WHERE user_id = " + String.valueOf(userId)
                + " AND mobile_device_id = " + mobileId;
        return deleteEntityQuery;
    }

    public boolean deleteUsersHaveMobileDevicesOnUserIdAndMobileId(Integer userId, Integer mobileId) {
        int numberOfUpdatedRows = ZERO;
        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement()) {
            numberOfUpdatedRows = statement.executeUpdate(prepareDeleteQuery(userId, mobileId));
        } catch (SQLException e) {
            logger.error("SQL exception: " + e.getMessage());
        }
        return numberOfUpdatedRows > ZERO;
    }
}
