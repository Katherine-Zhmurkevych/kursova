package edu.mobileservice.services.implementations;

import edu.mobileservice.dao.implementations.PricePlanDAOImpl;
import edu.mobileservice.model.PricePlanEntity;
import edu.mobileservice.services.PricePlanManager;

import java.util.List;
import java.util.Objects;


public class PricePlanManagerImpl implements PricePlanManager {

    private PricePlanDAOImpl pricePlanDAO = new PricePlanDAOImpl();

    @Override
    public List<PricePlanEntity> findAllPricePlans() {
        return pricePlanDAO.findAll();
    }

    @Override
    public PricePlanEntity findPricePlanById(Integer id) {
        final PricePlanEntity pricePlan = (PricePlanEntity) pricePlanDAO.findByID(id);
        if (Objects.isNull(pricePlan)) {
            throw new IllegalStateException(String.format("Price plan with %s id doesn't exist", id));
        }
        return pricePlan;
    }

    @Override
    public boolean create(PricePlanEntity pricePlan) {
        if (Objects.isNull(pricePlan)) {
            throw new IllegalStateException(String.format("Price plan doesn't exist"));
        }
        return pricePlanDAO.create(pricePlan);
    }

    @Override
    public boolean update(PricePlanEntity pricePlan) {
        if (Objects.isNull(pricePlan)) {
            throw new IllegalStateException(String.format("Price plan doesn't exist"));
        }
        return pricePlanDAO.update(pricePlan);
    }

    @Override
    public boolean deletePricePlan(PricePlanEntity pricePlan) {
        if (Objects.isNull(pricePlan)) {
            throw new IllegalStateException(String.format("Price plan doesn't exist"));
        }
        return pricePlanDAO.delete(pricePlan.getId());
    }
}
