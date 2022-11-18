package edu.mobileservice.dao.implementations;

import edu.mobileservice.dao.FeedbackDAO;
import edu.mobileservice.model.FeedbackEntity;
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

import static edu.mobileservice.common.Queries.GET_ALL_NEGATIVE_FEEDBACK;

public class FeedbackDAOImpl extends AbstractDAOImpl implements FeedbackDAO {

    private static Logger logger = LogManager.getLogger("FeedbackDAOImpl");

    public FeedbackDAOImpl() {
        super(FeedbackEntity.class);
    }

    @Override
    public List<FeedbackEntity> findAllNegativeFeedback() {
        Connection connection = ConnectionUtil.getConnection();
        List<FeedbackEntity> feedbackList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_NEGATIVE_FEEDBACK)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                feedbackList.add((FeedbackEntity) new Transformer(FeedbackEntity.class).fromResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            logger.error("SQL exception: " + e.getMessage());
        }
        return feedbackList;
    }
}
