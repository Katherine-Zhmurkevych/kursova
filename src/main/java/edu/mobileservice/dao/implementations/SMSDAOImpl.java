package edu.mobileservice.dao.implementations;

import edu.mobileservice.dao.SMSDAO;
import edu.mobileservice.model.SMSEntity;


public class SMSDAOImpl extends AbstractDAOImpl implements SMSDAO {

    public SMSDAOImpl() {
        super(SMSEntity.class);
    }
}
