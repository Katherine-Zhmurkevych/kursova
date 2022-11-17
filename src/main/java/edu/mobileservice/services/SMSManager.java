package edu.mobileservice.services;

import edu.mobileservice.model.SMSEntity;

import java.util.List;


public interface SMSManager {

    List<SMSEntity> findAllSMS();

    SMSEntity findSMSById(final Integer id);

    boolean create(SMSEntity sms);

    boolean update(SMSEntity sms);

    boolean deleteSMS(SMSEntity sms);
}
