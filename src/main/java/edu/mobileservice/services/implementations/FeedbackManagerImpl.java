package edu.mobileservice.services.implementations;

import edu.mobileservice.dao.implementations.FeedbackDAOImpl;
import edu.mobileservice.model.FeedbackEntity;
import edu.mobileservice.services.FeedbackManager;

import java.util.List;
import java.util.Objects;

public class FeedbackManagerImpl implements FeedbackManager {

    private FeedbackDAOImpl feedbackDAO = new FeedbackDAOImpl();

    @Override
    public List<FeedbackEntity> findAllNegativeFeedback() {
        return feedbackDAO.findAllNegativeFeedback();
    }

    @Override
    public boolean create(FeedbackEntity feedback) {
        if (Objects.isNull(feedback)) {
            throw new IllegalStateException(String.format("Feedback doesn't exist"));
        }
        return feedbackDAO.create(feedback);
    }

    @Override
    public boolean update(FeedbackEntity feedback) {
        if (Objects.isNull(feedback)) {
            throw new IllegalStateException(String.format("Feedback doesn't exist"));
        }
        return feedbackDAO.update(feedback);
    }

    @Override
    public boolean delete(FeedbackEntity feedback) {
        if (Objects.isNull(feedback)) {
            throw new IllegalStateException(String.format("Feedback doesn't exist"));
        }
        return feedbackDAO.delete(feedback.getId());
    }
}
