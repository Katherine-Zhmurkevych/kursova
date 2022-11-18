package edu.mobileservice.services;

import edu.mobileservice.model.FeedbackEntity;
import edu.mobileservice.model.MobileDeviceEntity;

import java.util.List;

public interface FeedbackManager {

    List<FeedbackEntity> findAllNegativeFeedback();

    boolean create(FeedbackEntity feedback);

    boolean update(FeedbackEntity feedback);

    boolean delete(FeedbackEntity feedback);
}
