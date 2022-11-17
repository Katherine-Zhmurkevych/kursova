package edu.mobileservice.dao.implementations;

import edu.mobileservice.dao.OutgoingCallDAO;
import edu.mobileservice.model.OutgoingCallEntity;
import edu.mobileservice.model.UserEntity;
import edu.mobileservice.utils.ConnectionUtil;
import edu.mobileservice.utils.Transformer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static edu.mobileservice.common.Constants.ONE;
import static edu.mobileservice.common.Queries.GET_USERS_BY_RECEIVER_NUMBER;


public class OutgoingCallDAOImpl extends AbstractDAOImpl<OutgoingCallEntity, Integer> implements OutgoingCallDAO {

    private static Logger logger = LogManager.getLogger("CallDAOImpl");

    public OutgoingCallDAOImpl() {
        super(OutgoingCallEntity.class);
    }

    @Override
    public List<UserEntity> findUsersByReceiverNumber(String receiverNumber) {
        Connection connection = ConnectionUtil.getConnection();
        List<UserEntity> userList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_USERS_BY_RECEIVER_NUMBER)) {
            statement.setString(ONE, receiverNumber);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                userList.add((UserEntity) new Transformer(UserEntity.class).fromResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            logger.error("SQL exception: " + e.getMessage());
        }
        return userList;
    }
}
