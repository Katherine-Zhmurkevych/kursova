package edu.mobileservice.services.implementations;

import static edu.mobileservice.utils.AnswerUtils.NEGATIVE;
import static edu.mobileservice.utils.AnswerUtils.POSITIVE;
import static edu.mobileservice.utils.AnswerUtils.YES_ANSWERS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import edu.mobileservice.dao.implementations.FeedbackDAOImpl;
import edu.mobileservice.model.FeedbackEntity;
import edu.mobileservice.model.UserEntity;
import edu.mobileservice.services.FeedbackManager;
import edu.mobileservice.services.UserManager;

public class FeedbackManagerImpl implements FeedbackManager {
    private static final Logger logger = LogManager.getLogger("FeedbackManagerImpl");

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final FeedbackDAOImpl feedbackDAO = new FeedbackDAOImpl();
    private final UserManager userManager = new UserManagerImpl();


    @Override
    public List<FeedbackEntity> findAllNegativeFeedback() {
        return feedbackDAO.findAllNegativeFeedback();
    }

    @Override
    public void processUserFeedback() throws IOException {
        logger.info("Please enter your user ID:");
        final int userId = Integer.parseInt(bufferedReader.readLine());
        final UserEntity user = userManager.findUser(userId);
        if (Objects.nonNull(user)) {
            // Case when user exists in DB
            logger.info("Your name is " + user.getName());
            logger.info("Your surname is " + user.getSurname());
            logger.info("Your phone is " + user.getPhone());
        }
        else {
            // Case when user doesn't exists in DB
            logger.warn("#####New User registration#####");
            logger.warn("Please enter your name:");
            final String name = String.valueOf(bufferedReader.readLine());
            logger.warn("Please enter your surname:");
            final String surname = String.valueOf(bufferedReader.readLine());
            logger.warn("Please enter your phone:");
            final String phone = String.valueOf(bufferedReader.readLine());
            final UserEntity newUser = new UserEntity(userId, name, surname, phone);
            userManager.create(newUser);
            logger.warn("#####New User registration is complete#####");
            logger.warn(newUser.toString() + " was stored into DB");
        }

        logger.info("Are you satisfied with our service? Yes/no");
        final String satisfactionFlag = String.valueOf(bufferedReader.readLine());
        logger.info("Please leave a comment:");
        final String message = String.valueOf(bufferedReader.readLine());
        final String messageType = YES_ANSWERS.contains(satisfactionFlag) ? POSITIVE : NEGATIVE;
        final String feedbackId = UUID.randomUUID().toString();

        final FeedbackEntity newFeedback = new FeedbackEntity(feedbackId, message, userId, messageType);
        logger.info("Save data? Yes/no");
        final String saveDataFlag = String.valueOf(bufferedReader.readLine());
        if (YES_ANSWERS.contains(saveDataFlag)) {
            create(newFeedback);
        }
    }

    @Override
    public boolean create(FeedbackEntity feedback) {
        if (Objects.isNull(feedback)) {
            throw new IllegalStateException("Feedback doesn't exist");
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
