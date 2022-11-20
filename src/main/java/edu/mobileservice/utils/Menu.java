package edu.mobileservice.utils;

import static edu.mobileservice.utils.AnswerUtils.clearConsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import edu.mobileservice.model.FeedbackEntity;
import edu.mobileservice.model.MobileDeviceEntity;
import edu.mobileservice.model.MobileNumberEntity;
import edu.mobileservice.model.OutgoingCallEntity;
import edu.mobileservice.model.PricePlanEntity;
import edu.mobileservice.model.SMSEntity;
import edu.mobileservice.model.UserEntity;
import edu.mobileservice.model.UsersHaveMobileDevicesEntity;
import edu.mobileservice.services.FeedbackManager;
import edu.mobileservice.services.MobileDeviceManager;
import edu.mobileservice.services.MobileNumberManager;
import edu.mobileservice.services.OutgoingCallManager;
import edu.mobileservice.services.PricePlanManager;
import edu.mobileservice.services.SMSManager;
import edu.mobileservice.services.UserManager;
import edu.mobileservice.services.UsersHaveMobileDevicesManager;
import edu.mobileservice.services.implementations.FeedbackManagerImpl;
import edu.mobileservice.services.implementations.MobileDeviceManagerImpl;
import edu.mobileservice.services.implementations.MobileNumberManagerImpl;
import edu.mobileservice.services.implementations.OutgoingCallManagerImpl;
import edu.mobileservice.services.implementations.PricePlanManagerImpl;
import edu.mobileservice.services.implementations.SMSManagerImpl;
import edu.mobileservice.services.implementations.UserManagerImpl;
import edu.mobileservice.services.implementations.UsersHaveMobileDevicesManagerImpl;


public class Menu {

    private static final String GOING_BACK_TO_MENU_MESSAGE = "Going back to menu\n\n";
    private static final String FIND_ALL_MESSAGE = "Find all - press 1";
    private static final String ENTER_ID_MESSAGE = "Enter id for search";
    private static Logger logger = LogManager.getLogger("Menu");
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private UserManager userManager = new UserManagerImpl();
    private MobileNumberManager mobileNumberManager = new MobileNumberManagerImpl();
    private OutgoingCallManager outgoingCallManager = new OutgoingCallManagerImpl();
    private SMSManager smsManager = new SMSManagerImpl();
    private PricePlanManager pricePlanManager = new PricePlanManagerImpl();
    private UsersHaveMobileDevicesManager usersHaveMobileDevicesManager = new UsersHaveMobileDevicesManagerImpl();
    private MobileDeviceManager mobileDeviceManager = new MobileDeviceManagerImpl();
    private FeedbackManager feedbackManager = new FeedbackManagerImpl();

    public void mainMenu() {
        logger.info("Please choose the working mode:");
        logger.info("1. Administration mode ");
        logger.info("2. User mode");
        logger.info("Your choice:");
        try {
            int choice = Integer.parseInt(bufferedReader.readLine());
            clearConsole();
            if (choice == 1) {
                logger.info("Choose your action");
                logger.info("1. Direct DB modification");
                logger.info("2. Users' feedback");
                int adminChoice = Integer.parseInt(bufferedReader.readLine());
                clearConsole();
                if (adminChoice == 1) {
                    showAdminMenuModification();
                }
                else if (adminChoice == 2) {
                    showUsersNegativeFeedback();
                }

            }
            else if (choice == 2) {
                feedbackManager.processUserFeedback();
            }
        }
        catch (IOException e) {
            logger.error("Menu show error: " + e.getMessage());
        }
    }

    private void showUsersNegativeFeedback() {
        final List<FeedbackEntity> allNegativeFeedbacks = feedbackManager.findAllNegativeFeedback();
        if (CollectionUtils.isEmpty(allNegativeFeedbacks)) {
            logger.warn("There are no negative users feedback");
        }
        else {
            logger.warn("There are " + allNegativeFeedbacks.size() + "negative feedback from users");
            allNegativeFeedbacks.forEach(feedback -> logger.warn(feedback.toString() + "\n"));
            logger.warn("You should reach them and resolve an appropriate issue");
        }
    }


    public void showAdminMenuModification() {
        logger.info("Choose the action");
        logger.info("Select the table to interact - press 1");
        logger.info("Find users of network by receiver number(based on 3-table JOIN) - press 2");
        logger.info("Find devices by owner ID(based on 3-table JOIN) - press 3");
        logger.info("Enter 4 to exit");
        try {
            int choice = Integer.parseInt(bufferedReader.readLine());
            switch (choice) {
                case 1:
                    selectTable();
                    break;
                case 2:
                    logger.info("Enter the receiver number for search");
                    String receiverNumberForSearch = bufferedReader.readLine();
                    logger.info(outgoingCallManager.findUsersByReceiverNumber(receiverNumberForSearch));
                    break;
                case 3:
                    logger.info("Enter the owner id for search");
                    Integer ownerIdForSearch = Integer.parseInt(bufferedReader.readLine());
                    logger.info(userManager.findMobileDevicesByOwner(ownerIdForSearch));
                    break;
                default:
                    break;
            }
        }
        catch (IOException e) {
            logger.error("Menu show error: " + e.getMessage());
        }
    }

    private void selectTable() {
        logger.info("Select the table");
        logger.info("Table user - press 1");
        logger.info("Table mobile_number - press 2");
        logger.info("Table outgoing_call - press 3");
        logger.info("Table sms - press 4");
        logger.info("Table price_plan - press 5");
        logger.info("Table users_have_mobile_devices - press 6");
        logger.info("Table mobile_device - press 7");
        try {
            int choice = Integer.parseInt(bufferedReader.readLine());
            switch (choice) {
                case 1:
                    selectTableUserAction();
                    break;
                case 2:
                    selectTableMobileNumberAction();
                    break;
                case 3:
                    selectTableOutgoingCallAction();
                    break;
                case 4:
                    selectTableSMSAction();
                    break;
                case 5:
                    selectTablePricePlanAction();
                    break;
                case 6:
                    selectTableUsersHaveMobileDevicesAction();
                    break;
                case 7:
                    selectTableMobileDeviceAction();
                    break;
                default:
                    break;
            }
        }
        catch (IOException e) {
            logger.error("Menu select table error: " + e.getMessage());
        }
    }

    private void selectTableUserAction() {
        logger.info("Table user");
        logger.info(FIND_ALL_MESSAGE);
        logger.info("Find user by id - press 2");
        logger.info("Create user - press 3");
        logger.info("Update user - press 4");
        logger.info("Delete user - press 5");
        try {
            int choice = Integer.parseInt(bufferedReader.readLine());
            switch (choice) {
                case 1:
                    logger.info(userManager.findAllUsers());
                    break;
                case 2:
                    logger.info(ENTER_ID_MESSAGE);
                    int searchId = Integer.parseInt(bufferedReader.readLine());
                    logger.info(userManager.findUser(searchId));
                    break;
                case 3:
                    logger.info("Enter the parameters for user you want to create");
                    UserEntity userCreateEntity = getParametersAndCreateUserEntityWithThem();
                    final boolean createResult = userManager.create(userCreateEntity);
                    if (createResult) {
                        logger.info("User added");
                    }
                    break;
                case 4:
                    logger.info("Enter the parameters for user you want to update");
                    UserEntity userUpdateEntity = getParametersAndCreateUserEntityWithThem();
                    final boolean updateResult = userManager.update(userUpdateEntity);
                    if (updateResult) {
                        logger.info("User updated");
                    }
                    break;
                case 5:
                    logger.info("Enter the parameters for user you want to delete");
                    UserEntity userDeleteEntity = getParametersAndCreateUserEntityWithThem();
                    final boolean deleteResult = userManager.deleteUser(userDeleteEntity);
                    if (deleteResult) {
                        logger.info("User deleted");
                    }
                    break;
                default:
                    break;
            }
            logger.info(GOING_BACK_TO_MENU_MESSAGE);
            showAdminMenuModification();
        }
        catch (IOException e) {
            logger.error("Table users error: " + e.getMessage());
        }
    }

    private UserEntity getParametersAndCreateUserEntityWithThem() throws IOException {
        logger.info("Id:");
        Integer createId = Integer.parseInt(bufferedReader.readLine());
        logger.info("First name:");
        String createFirstName = bufferedReader.readLine();
        logger.info("Surname:");
        String createSurname = bufferedReader.readLine();
        logger.info("Phone number:");
        String phone = bufferedReader.readLine();
        return new UserEntity(createId, createFirstName, createSurname, phone);
    }

    private void selectTableMobileNumberAction() {
        logger.info("Table mobile_number");
        logger.info(FIND_ALL_MESSAGE);
        logger.info("Find mobile number by id - press 2");
        logger.info("Create mobile number - press 3");
        logger.info("Update mobile number - press 4");
        logger.info("Delete mobile number - press 5");
        try {
            int choice = Integer.parseInt(bufferedReader.readLine());
            switch (choice) {
                case 1:
                    logger.info(mobileNumberManager.findAllMobileNumbers());
                    break;
                case 2:
                    logger.info(ENTER_ID_MESSAGE);
                    int searchId = Integer.parseInt(bufferedReader.readLine());
                    logger.info(mobileNumberManager.findMobileNumber(searchId));
                    break;
                case 3:
                    logger.info("Enter the parameters for user you want to create");
                    MobileNumberEntity mobileNumberCreateEntity = getParametersAndCreateMobileNumberEntityWithThem();
                    final boolean createResult = mobileNumberManager.create(mobileNumberCreateEntity);
                    if (createResult) {
                        logger.info("Mobile number added");
                    }
                    break;
                case 4:
                    logger.info("Enter the parameters for user you want to update");
                    MobileNumberEntity mobileNumberUpdateEntity = getParametersAndCreateMobileNumberEntityWithThem();
                    final boolean updateResult = mobileNumberManager.update(mobileNumberUpdateEntity);
                    if (updateResult) {
                        logger.info("Mobile number updated");
                    }
                    break;
                case 5:
                    logger.info("Enter the parameters for user you want to delete");
                    MobileNumberEntity mobileNumberDeleteEntity = getParametersAndCreateMobileNumberEntityWithThem();
                    final boolean deleteResult = mobileNumberManager.deleteMobileNumber(mobileNumberDeleteEntity);
                    if (deleteResult) {
                        logger.info("Mobile number deleted");
                    }
                    break;
            }
            logger.info(GOING_BACK_TO_MENU_MESSAGE);
            showAdminMenuModification();
        }
        catch (IOException e) {
            logger.error("Table mobile_number error: " + e.getMessage());
        }
    }

    private MobileNumberEntity getParametersAndCreateMobileNumberEntityWithThem() throws IOException {
        logger.info("Id:");
        Integer createId = Integer.parseInt(bufferedReader.readLine());
        logger.info("Number:");
        String createNumber = bufferedReader.readLine();
        logger.info("Price plan Id:");
        Integer createPricePlanId = Integer.parseInt(bufferedReader.readLine());
        logger.info("User Id:");
        Integer createUserId = Integer.parseInt(bufferedReader.readLine());
        return new MobileNumberEntity(createId, createNumber, createPricePlanId, createUserId);
    }

    private void selectTableOutgoingCallAction() {
        logger.info("Table outgoing_call");
        logger.info(FIND_ALL_MESSAGE);
        logger.info("Find outgoing call by id - press 2");
        logger.info("Create outgoing call - press 3");
        logger.info("Update outgoing call - press 4");
        logger.info("Delete outgoing call - press 5");
        try {
            int choice = Integer.parseInt(bufferedReader.readLine());
            switch (choice) {
                case 1:
                    logger.info(outgoingCallManager.findAllCalls());
                    break;
                case 2:
                    logger.info(ENTER_ID_MESSAGE);
                    int searchId = Integer.parseInt(bufferedReader.readLine());
                    logger.info(outgoingCallManager.findCallById(searchId));
                    break;
                case 3:
                    logger.info("Enter the parameters for outgoing call you want to create");
                    OutgoingCallEntity outgoingCallCreateEntity = getParametersAndCreateOutgoingCallEntityWithThem();
                    final boolean createResult = outgoingCallManager.create(outgoingCallCreateEntity);
                    if (createResult) {
                        logger.info("Outgoing call added");
                    }
                    break;
                case 4:
                    logger.info("Enter the parameters for outgoing call you want to update");
                    OutgoingCallEntity outgoingCallUpdateEntity = getParametersAndCreateOutgoingCallEntityWithThem();
                    final boolean updateResult = outgoingCallManager.update(outgoingCallUpdateEntity);
                    if (updateResult) {
                        logger.info("Outgoing call updated");
                    }
                    break;
                case 5:
                    logger.info("Enter the parameters for outgoing call you want to delete");
                    OutgoingCallEntity outgoingCallDeleteEntity = getParametersAndCreateOutgoingCallEntityWithThem();
                    final boolean deleteResult = outgoingCallManager.deleteCall(outgoingCallDeleteEntity);
                    if (deleteResult) {
                        logger.info("Outgoing call deleted");
                    }
                    break;
            }
            logger.info(GOING_BACK_TO_MENU_MESSAGE);
            showAdminMenuModification();
        }
        catch (IOException e) {
            logger.error("Table outgoing_call error: " + e.getMessage());
        }
    }

    private OutgoingCallEntity getParametersAndCreateOutgoingCallEntityWithThem() throws IOException {
        logger.info("Id:");
        Integer createId = Integer.parseInt(bufferedReader.readLine());
        logger.info("Caller number Id:");
        Integer createCallerNumberId = Integer.parseInt(bufferedReader.readLine());
        logger.info("Receiver number:");
        String createReceiverNumber = bufferedReader.readLine();
        logger.info("Duration:");
        Integer createDuration = Integer.parseInt(bufferedReader.readLine());
        logger.info("Type:");
        String createType = bufferedReader.readLine();
        return new OutgoingCallEntity(createId, createCallerNumberId, createReceiverNumber, createDuration, createType);
    }

    private void selectTableSMSAction() {
        logger.info("Table sms");
        logger.info(FIND_ALL_MESSAGE);
        logger.info("Find sms by id - press 2");
        logger.info("Create sms - press 3");
        logger.info("Update sms - press 4");
        logger.info("Delete sms - press 5");
        try {
            int choice = Integer.parseInt(bufferedReader.readLine());
            switch (choice) {
                case 1:
                    logger.info(smsManager.findAllSMS());
                    break;
                case 2:
                    logger.info(ENTER_ID_MESSAGE);
                    int searchId = Integer.parseInt(bufferedReader.readLine());
                    logger.info(smsManager.findSMSById(searchId));
                    break;
                case 3:
                    logger.info("Enter the parameters for sms you want to create");
                    SMSEntity smsCreateEntity = getParametersAndCreateSMSEntityWithThem();
                    final boolean createResult = smsManager.create(smsCreateEntity);
                    if (createResult) {
                        logger.info("SMS added");
                    }
                    break;
                case 4:
                    logger.info("Enter the parameters for sms you want to update");
                    SMSEntity smsUpdateEntity = getParametersAndCreateSMSEntityWithThem();
                    final boolean updateResult = smsManager.update(smsUpdateEntity);
                    if (updateResult) {
                        logger.info("SMS updated");
                    }
                    break;
                case 5:
                    logger.info("Enter the parameters for sms you want to delete");
                    SMSEntity smsDeleteEntity = getParametersAndCreateSMSEntityWithThem();
                    final boolean deleteResult = smsManager.deleteSMS(smsDeleteEntity);
                    if (deleteResult) {
                        logger.info("SMS deleted");
                    }
                    break;
            }
            logger.info(GOING_BACK_TO_MENU_MESSAGE);
            showAdminMenuModification();
        }
        catch (IOException e) {
            logger.error("Table sms error: " + e.getMessage());
        }
    }

    private SMSEntity getParametersAndCreateSMSEntityWithThem() throws IOException {
        logger.info("Id:");
        Integer createId = Integer.parseInt(bufferedReader.readLine());
        logger.info("Sender number Id:");
        Integer createSenderNumberId = Integer.parseInt(bufferedReader.readLine());
        logger.info("Receiver number:");
        String createReceiverNumber = bufferedReader.readLine();
        logger.info("Text:");
        String createText = bufferedReader.readLine();
        return new SMSEntity(createId, createSenderNumberId, createReceiverNumber, createText);
    }

    private void selectTablePricePlanAction() {
        logger.info("Table price_plan");
        logger.info(FIND_ALL_MESSAGE);
        logger.info("Find price plan by id - press 2");
        logger.info("Create price plan - press 3");
        logger.info("Update price plan - press 4");
        logger.info("Delete price plan - press 5");
        try {
            int choice = Integer.parseInt(bufferedReader.readLine());
            switch (choice) {
                case 1:
                    logger.info(pricePlanManager.findAllPricePlans());
                    break;
                case 2:
                    logger.info(ENTER_ID_MESSAGE);
                    int searchId = Integer.parseInt(bufferedReader.readLine());
                    logger.info(pricePlanManager.findPricePlanById(searchId));
                    break;
                case 3:
                    logger.info("Enter the parameters for price plan you want to create");
                    PricePlanEntity pricePlanCreateEntity = getParametersAndCreatePricePlanEntityWithThem();
                    final boolean createResult = pricePlanManager.create(pricePlanCreateEntity);
                    if (createResult) {
                        logger.info("Price plan added");
                    }
                    break;
                case 4:
                    logger.info("Enter the parameters for price plan you want to update");
                    PricePlanEntity pricePlanUpdateEntity = getParametersAndCreatePricePlanEntityWithThem();
                    final boolean updateResult = pricePlanManager.update(pricePlanUpdateEntity);
                    if (updateResult) {
                        logger.info("Price plan updated");
                    }
                    break;
                case 5:
                    logger.info("Enter the parameters for price plan you want to delete");
                    PricePlanEntity pricePlanDeleteEntity = getParametersAndCreatePricePlanEntityWithThem();
                    final boolean deleteResult = pricePlanManager.deletePricePlan(pricePlanDeleteEntity);
                    if (deleteResult) {
                        logger.info("Price plan deleted");
                    }
                    break;
            }
            logger.info(GOING_BACK_TO_MENU_MESSAGE);
            showAdminMenuModification();
        }
        catch (IOException e) {
            logger.error("Table price_plan error: " + e.getMessage());
        }
    }

    private PricePlanEntity getParametersAndCreatePricePlanEntityWithThem() throws IOException {
        logger.info("Id:");
        Integer createId = Integer.parseInt(bufferedReader.readLine());
        logger.info("Name:");
        String createName = bufferedReader.readLine();
        logger.info("Specifications:");
        String createSpecifications = bufferedReader.readLine();
        logger.info("Price:");
        BigDecimal createPrice = new BigDecimal(bufferedReader.readLine());
        logger.info("Mobile number Id:");
        Integer createMobileNumberId = Integer.parseInt(bufferedReader.readLine());
        return new PricePlanEntity(createId, createName, createSpecifications, createPrice, createMobileNumberId);
    }

    private void selectTableUsersHaveMobileDevicesAction() {
        logger.info("Table users_have_mobile_devices");
        logger.info(FIND_ALL_MESSAGE);
        logger.info("Find users with mobile devices by user id - press 2");
        logger.info("Create user with mobile device - press 3");
        //        logger.info("Update user with mobile device - press 4");
        logger.info("Delete user with mobile device - press 5");
        try {
            int choice = Integer.parseInt(bufferedReader.readLine());
            switch (choice) {
                case 1:
                    logger.info(usersHaveMobileDevicesManager.findAllUsersHaveMobileDevices());
                    break;
                case 2:
                    logger.info("Enter user id for search");
                    int searchId = Integer.parseInt(bufferedReader.readLine());
                    logger.info(usersHaveMobileDevicesManager.findUsersHaveMobileDevicesByUserId(searchId));
                    break;
                case 3:
                    logger.info("Enter the parameters for user having mobile device you want to create");
                    UsersHaveMobileDevicesEntity usersHaveMobileDevicesCreateEntity =
                            getParametersAndCreateUsersHaveMobileDevicesEntityWithThem();
                    final boolean createResult = usersHaveMobileDevicesManager
                            .create(usersHaveMobileDevicesCreateEntity);
                    if (createResult) {
                        logger.info("User with mobile device added");
                    }
                    break;
                //                case 4:
                //                    logger.info("Enter the parameters for user having mobile device you want to
                //                    update");
                //                    UsersHaveMobileDevicesEntity usersHaveMobileDevicesUpdateEntity =
                //                            getParametersAndCreateUsersHaveMobileDevicesEntityWithThem();
                //                    final boolean updateResult = usersHaveMobileDevicesManager.update
                //                    (usersHaveMobileDevicesUpdateEntity);
                //                    if (updateResult) {
                //                        logger.info("User with mobile device updated");
                //                    }
                //                    break;
                case 5:
                    logger.info("Enter the parameters for user having mobile device you want to delete");
                    UsersHaveMobileDevicesEntity usersHaveMobileDevicesDeleteEntity =
                            getParametersAndCreateUsersHaveMobileDevicesEntityWithThem();
                    final boolean deleteResult = usersHaveMobileDevicesManager.
                            deleteUsersHaveMobileDevices(usersHaveMobileDevicesDeleteEntity);
                    if (deleteResult) {
                        logger.info("User with mobile device deleted");
                    }
                    break;
            }
            logger.info(GOING_BACK_TO_MENU_MESSAGE);
            showAdminMenuModification();
        }
        catch (IOException e) {
            logger.error("Table users_have_mobile_devices error: " + e.getMessage());
        }
    }

    private UsersHaveMobileDevicesEntity getParametersAndCreateUsersHaveMobileDevicesEntityWithThem()
            throws IOException {
        logger.info("User Id:");
        Integer createUserId = Integer.parseInt(bufferedReader.readLine());
        logger.info("Mobile device Id:");
        Integer createMobileDeviceId = Integer.parseInt(bufferedReader.readLine());
        return new UsersHaveMobileDevicesEntity(createUserId, createMobileDeviceId);
    }

    private void selectTableMobileDeviceAction() {
        logger.info("Table mobile_device");
        logger.info(FIND_ALL_MESSAGE);
        logger.info("Find mobile device by id - press 2");
        logger.info("Create mobile device - press 3");
        logger.info("Update mobile device - press 4");
        logger.info("Delete mobile device - press 5");
        try {
            int choice = Integer.parseInt(bufferedReader.readLine());
            switch (choice) {
                case 1:
                    logger.info(mobileDeviceManager.findAllMobileDevices());
                    break;
                case 2:
                    logger.info("Enter mobile device id for search");
                    int searchId = Integer.parseInt(bufferedReader.readLine());
                    logger.info(mobileDeviceManager.findMobileDeviceById(searchId));
                    break;
                case 3:
                    logger.info("Enter the parameters for mobile device you want to create");
                    MobileDeviceEntity mobileDeviceCreateEntity = getParametersAndCreateMobileDeviceEntityWithThem();
                    final boolean createResult = mobileDeviceManager.create(mobileDeviceCreateEntity);
                    if (createResult) {
                        logger.info("Mobile device added");
                    }
                    break;
                case 4:
                    logger.info("Enter the parameters for mobile device you want to update");
                    MobileDeviceEntity mobileDeviceUpdateEntity = getParametersAndCreateMobileDeviceEntityWithThem();
                    final boolean updateResult = mobileDeviceManager.update(mobileDeviceUpdateEntity);
                    if (updateResult) {
                        logger.info("Mobile device updated");
                    }
                    break;
                case 5:
                    logger.info("Enter the parameters mobile device you want to delete");
                    MobileDeviceEntity mobileDeviceDeleteEntity = getParametersAndCreateMobileDeviceEntityWithThem();
                    final boolean deleteResult = mobileDeviceManager.delete(mobileDeviceDeleteEntity);
                    if (deleteResult) {
                        logger.info("Mobile device deleted");
                    }
                    break;
            }
            logger.info(GOING_BACK_TO_MENU_MESSAGE);
            showAdminMenuModification();
        }
        catch (IOException e) {
            logger.error("Table mobile_devices error: " + e.getMessage());
        }
    }

    private MobileDeviceEntity getParametersAndCreateMobileDeviceEntityWithThem() throws IOException {
        logger.info("Id:");
        Integer createId = Integer.parseInt(bufferedReader.readLine());
        logger.info("Brand:");
        String createBrand = bufferedReader.readLine();
        logger.info("Model:");
        String createModel = bufferedReader.readLine();
        return new MobileDeviceEntity(createId, createBrand, createModel);
    }
}
