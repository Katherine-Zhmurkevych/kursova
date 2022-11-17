package edu.mobileservice.services;

import edu.mobileservice.model.PricePlanEntity;

import java.util.List;


public interface PricePlanManager {

    List<PricePlanEntity> findAllPricePlans();

    PricePlanEntity findPricePlanById(final Integer id);

    boolean create(PricePlanEntity pricePlan);

    boolean update(PricePlanEntity pricePlan);

    boolean deletePricePlan(PricePlanEntity pricePlan);
}
