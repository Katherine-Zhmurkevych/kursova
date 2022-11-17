package edu.mobileservice.dao.implementations;

import edu.mobileservice.dao.MobileDeviceDAO;
import edu.mobileservice.model.MobileDeviceEntity;


public class MobileDeviceDAOImpl extends AbstractDAOImpl implements MobileDeviceDAO {

    public MobileDeviceDAOImpl() {
        super(MobileDeviceEntity.class);
    }
}
