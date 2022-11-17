package edu.mobileservice.dao.implementations;

import edu.mobileservice.dao.MobileNumberDAO;
import edu.mobileservice.model.MobileNumberEntity;


public class MobileNumberDAOImpl extends AbstractDAOImpl implements MobileNumberDAO {

    public MobileNumberDAOImpl() {
        super(MobileNumberEntity.class);
    }
}
