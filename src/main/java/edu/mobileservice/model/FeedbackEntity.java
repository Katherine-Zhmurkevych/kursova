package edu.mobileservice.model;

import edu.mobileservice.model.annotations.Column;
import edu.mobileservice.model.annotations.PrimaryKey;
import edu.mobileservice.model.annotations.Table;

@Table(name = "feedback")
public class FeedbackEntity {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;
    @Column(name = "message", length = 1000)
    private String message;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "message_type", length = 8)
    private String messageType;

    public FeedbackEntity() {
    }

    public FeedbackEntity(Integer id, String message, Integer userId, String messageType) {
        this.id = id;
        this.message = message;
        this.userId = userId;
        this.messageType = messageType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return String.format("\nID: %-5s %-8s feedback: %-10s", id, messageType, message);
    }
}
