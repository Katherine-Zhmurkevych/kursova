package edu.mobileservice.services;

import edu.mobileservice.model.OutgoingCallEntity;
import edu.mobileservice.model.UserEntity;

import java.util.List;


public interface OutgoingCallManager {

    List<OutgoingCallEntity> findAllCalls();

    List<UserEntity> findUsersByReceiverNumber(final String receiverNumber);

    OutgoingCallEntity findCallById(final Integer id);

    boolean create(OutgoingCallEntity call);

    boolean update(OutgoingCallEntity call);

    boolean deleteCall(OutgoingCallEntity call);
}
