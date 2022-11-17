package edu.mobileservice.services;

import edu.mobileservice.dao.implementations.UserDAOImpl;
import edu.mobileservice.model.MobileDeviceEntity;
import edu.mobileservice.model.UserEntity;
import edu.mobileservice.services.implementations.UserManagerImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static edu.mobileservice.common.Constants.ZERO;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserManagerImplTest {

    private static Integer EXISTING_USER_ID = 500;
    private static Integer NOT_EXISTING_USER_ID = 600;

    @Mock
    private UserDAOImpl userDAO;
    @InjectMocks
    private UserManagerImpl testedUnit;

    @Test
    public void findAllUsersTest() {
        final UserEntity user = new UserEntity();
        List<UserEntity> expectedResult = Arrays.asList(user);
        when(userDAO.findAll()).thenReturn(expectedResult);

        final List<UserEntity> actualResult = testedUnit.findAllUsers();

        Assert.assertEquals(expectedResult.size(), actualResult.size());
        Assert.assertEquals(expectedResult.get(ZERO), actualResult.get(ZERO));
    }

    @Test
    public void findUserTestWhenExistingId() {
        final UserEntity expectedResult = new UserEntity();
        when(userDAO.findByID(EXISTING_USER_ID)).thenReturn(expectedResult);

        final UserEntity actualResult = testedUnit.findUser(EXISTING_USER_ID);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(expected = IllegalStateException.class)
    public void findUserTestWhenNotExistingId() {
        when(userDAO.findByID(NOT_EXISTING_USER_ID)).thenReturn(null);

        testedUnit.findUser(EXISTING_USER_ID);
    }

    @Test
    public void findMobileDevicesByOwnerTest() {
        final MobileDeviceEntity mobileDevice = new MobileDeviceEntity();
        List<MobileDeviceEntity> expectedResult = Arrays.asList(mobileDevice);
        when(userDAO.findMobileDevicesByOwnerId(EXISTING_USER_ID)).thenReturn(expectedResult);
        when(userDAO.findByID(EXISTING_USER_ID)).thenReturn(new UserEntity());

        final List<MobileDeviceEntity> actualResult = testedUnit.findMobileDevicesByOwner(EXISTING_USER_ID);

        Assert.assertEquals(expectedResult.size(), actualResult.size());
        Assert.assertEquals(expectedResult.get(ZERO), actualResult.get(ZERO));
    }

    @Test(expected = IllegalStateException.class)
    public void findMobileDevicesByOwnerTestWhenNotExistingId() {
        when(userDAO.findByID(NOT_EXISTING_USER_ID)).thenReturn(null);

        testedUnit.findMobileDevicesByOwner(NOT_EXISTING_USER_ID);
    }
}
