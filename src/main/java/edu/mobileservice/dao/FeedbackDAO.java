package edu.mobileservice.dao;

import edu.mobileservice.model.FeedbackEntity;

import java.util.List;

public interface FeedbackDAO {

    List<FeedbackEntity> findAllNegativeFeedback();
}
