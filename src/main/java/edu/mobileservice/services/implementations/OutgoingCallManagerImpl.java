package edu.mobileservice.services.implementations;

import edu.mobileservice.dao.implementations.OutgoingCallDAOImpl;
import edu.mobileservice.model.OutgoingCallEntity;
import edu.mobileservice.model.UserEntity;
import edu.mobileservice.services.OutgoingCallManager;

import java.util.List;
import java.util.Objects;


public class OutgoingCallManagerImpl implements OutgoingCallManager {

    private OutgoingCallDAOImpl callDAO = new OutgoingCallDAOImpl();

    @Override
    public List<OutgoingCallEntity> findAllCalls() {
        return callDAO.findAll();
    }

    @Override
    public List<UserEntity> findUsersByReceiverNumber(final String receiverNumber) {
        return callDAO.findUsersByReceiverNumber(receiverNumber);
    }

    @Override
    public OutgoingCallEntity findCallById(final Integer id) {
        final OutgoingCallEntity outgoingCallEntity = callDAO.findByID(id);
        if (Objects.isNull(outgoingCallEntity)) {
            throw new IllegalStateException(String.format("Call with %s id not exists", id));
        }
        return outgoingCallEntity;
    }

    @Override
    public boolean create(OutgoingCallEntity call) {
        if (Objects.isNull(call)) {
            throw new IllegalStateException(String.format("Call doesn't exist"));
        }
        return callDAO.create(call);
    }

    @Override
    public boolean update(OutgoingCallEntity call) {
        if (Objects.isNull(call)) {
            throw new IllegalStateException(String.format("Call doesn't exist"));
        }
        return callDAO.update(call);
    }

    @Override
    public boolean deleteCall(OutgoingCallEntity call) {
        if (Objects.isNull(call)) {
            throw new IllegalStateException(String.format("Call doesn't exist"));
        }
        return callDAO.delete(call.getId());
    }
}
