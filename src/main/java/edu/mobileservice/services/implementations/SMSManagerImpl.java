package edu.mobileservice.services.implementations;

import edu.mobileservice.dao.implementations.SMSDAOImpl;
import edu.mobileservice.model.SMSEntity;
import edu.mobileservice.services.SMSManager;

import java.util.List;
import java.util.Objects;

public class SMSManagerImpl implements SMSManager {

    private SMSDAOImpl smsDAO = new SMSDAOImpl();


    @Override
    public List<SMSEntity> findAllSMS() {
        return smsDAO.findAll();
    }

    @Override
    public SMSEntity findSMSById(Integer id) {
        final SMSEntity sms = (SMSEntity) smsDAO.findByID(id);
        if (Objects.isNull(sms)) {
            throw new IllegalStateException(String.format("SMS with %s id doesn't exist", id));
        }
        return sms;
    }

    @Override
    public boolean create(SMSEntity sms) {
        if (Objects.isNull(sms)) {
            throw new IllegalStateException(String.format("SMS doesn't exist"));
        }
        return smsDAO.create(sms);
    }

    @Override
    public boolean update(SMSEntity sms) {
        if (Objects.isNull(sms)) {
            throw new IllegalStateException(String.format("SMS doesn't exist"));
        }
        return smsDAO.update(sms);
    }

    @Override
    public boolean deleteSMS(SMSEntity sms) {
        if (Objects.isNull(sms)) {
            throw new IllegalStateException(String.format("SMS doesn't exist"));
        }
        return smsDAO.delete(sms.getId());
    }
}
