package edu.mobileservice.dao;

import edu.mobileservice.model.UserEntity;

import java.util.List;


public interface OutgoingCallDAO {

    List<UserEntity> findUsersByReceiverNumber(String receiverNumber);
}
