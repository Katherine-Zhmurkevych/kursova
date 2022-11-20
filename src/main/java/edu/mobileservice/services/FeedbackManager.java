package edu.mobileservice.services;

import edu.mobileservice.model.FeedbackEntity;
import edu.mobileservice.model.MobileDeviceEntity;

import java.io.IOException;
import java.util.List;

public interface FeedbackManager {

    List<FeedbackEntity> findAllNegativeFeedback();

    void processUserFeedback() throws IOException;

    boolean create(FeedbackEntity feedback);

    boolean update(FeedbackEntity feedback);

    boolean delete(FeedbackEntity feedback);
}
