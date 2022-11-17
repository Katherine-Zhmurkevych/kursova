package edu.mobileservice.dao.implementations;

import edu.mobileservice.dao.PricePlanDAO;
import edu.mobileservice.model.PricePlanEntity;


public class PricePlanDAOImpl extends AbstractDAOImpl implements PricePlanDAO {

    public PricePlanDAOImpl() {
        super(PricePlanEntity.class);
    }
}
