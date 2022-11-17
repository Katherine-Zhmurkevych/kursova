package edu.mobileservice.dao.implementations;

import edu.mobileservice.dao.UserDAO;
import edu.mobileservice.model.MobileDeviceEntity;
import edu.mobileservice.model.UserEntity;
import edu.mobileservice.utils.ConnectionUtil;
import edu.mobileservice.utils.Transformer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static edu.mobileservice.common.Constants.ONE;
import static edu.mobileservice.common.Queries.GET_DEVICES_BY_OWNER_ID;


public class UserDAOImpl extends AbstractDAOImpl implements UserDAO {

    private static Logger logger = LogManager.getLogger("UsersDAOImpl");

    public UserDAOImpl() {
        super(UserEntity.class);
    }

    @Override
    public List<MobileDeviceEntity> findMobileDevicesByOwnerId(Integer ownerId) {
        Connection connection = ConnectionUtil.getConnection();
        List<MobileDeviceEntity> mobileDeviceList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_DEVICES_BY_OWNER_ID)) {
            statement.setInt(ONE, ownerId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                mobileDeviceList.add((MobileDeviceEntity) new Transformer(MobileDeviceEntity.class).fromResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            logger.error("SQL exception: " + e.getMessage());
        }
        return mobileDeviceList;
    }
}
