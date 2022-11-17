package edu.mobileservice.services;

import edu.mobileservice.model.MobileNumberEntity;

import java.util.List;


public interface MobileNumberManager {

    List<MobileNumberEntity> findAllMobileNumbers();

    MobileNumberEntity findMobileNumber(final Integer id);

    boolean create(MobileNumberEntity number);

    boolean update(MobileNumberEntity number);

    boolean deleteMobileNumber(MobileNumberEntity number);
}
