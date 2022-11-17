package edu.mobileservice.services;

import edu.mobileservice.dao.implementations.OutgoingCallDAOImpl;
import edu.mobileservice.model.OutgoingCallEntity;
import edu.mobileservice.model.UserEntity;
import edu.mobileservice.services.implementations.OutgoingCallManagerImpl;
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
public class OutgoingCallManagerImplTest {

    private static String RECEIVER_NUMBER_STUB = "Number STUB";
    private static Integer EXISTING_CALL_ID = 100;
    private static Integer NOT_EXISTING_CALL_ID = 200;

    @Mock
    private OutgoingCallDAOImpl callDAO;
    @InjectMocks
    private OutgoingCallManagerImpl testedUnit;

    @Test
    public void findAllCallsTest() {
        final OutgoingCallEntity outgoingCallEntity = new OutgoingCallEntity();
        List<OutgoingCallEntity> expectedResult = Arrays.asList(outgoingCallEntity);
        when(callDAO.findAll()).thenReturn(expectedResult);

        final List<OutgoingCallEntity> actualResult = testedUnit.findAllCalls();

        Assert.assertEquals(expectedResult.size(), actualResult.size());
        Assert.assertEquals(expectedResult.get(ZERO), actualResult.get(ZERO));
    }

    @Test
    public void findUsersByReceiverNumberTest() {
        final UserEntity userEntity = new UserEntity();
        List<UserEntity> expectedResult = Arrays.asList(userEntity);
        when(callDAO.findUsersByReceiverNumber(RECEIVER_NUMBER_STUB)).thenReturn(expectedResult);

        final List<UserEntity> actualResult = testedUnit.findUsersByReceiverNumber(RECEIVER_NUMBER_STUB);

        Assert.assertEquals(expectedResult.size(), actualResult.size());
        Assert.assertEquals(expectedResult.get(ZERO), actualResult.get(ZERO));
    }

    @Test
    public void findCallByIdTestWhenExistingId() {
        final OutgoingCallEntity expectedResult = new OutgoingCallEntity();
        when(callDAO.findByID(EXISTING_CALL_ID)).thenReturn(expectedResult);

        final OutgoingCallEntity actualResult = testedUnit.findCallById(EXISTING_CALL_ID);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(expected = IllegalStateException.class)
    public void findCallByIdTestWhenNotExistingId() {
        when(callDAO.findByID(NOT_EXISTING_CALL_ID)).thenReturn(null);

        testedUnit.findCallById(NOT_EXISTING_CALL_ID);
    }
}
