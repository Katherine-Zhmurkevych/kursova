package edu.mobileservice.common;

public class Queries {

    public static String GET_USERS_BY_RECEIVER_NUMBER = "SELECT u.id, u.name, u.surname"
            + " FROM outgoing_call JOIN mobile_number ON caller_number_id = mobile_number.id"
            + " JOIN user AS u ON mobile_number.user_id = u.id"
            + " WHERE receiver_number = ?";
    public static String GET_DEVICES_BY_OWNER_ID = "SELECT md.id, md.brand, md.model FROM user as u"
            + " JOIN users_have_mobile_devices AS ud ON u.id = ud.user_id"
            + " JOIN mobile_device AS md ON ud.mobile_device_id = md.id"
            + " WHERE u.id = ?";
    public static String GET_ALL_NEGATIVE_FEEDBACK = "SELECT f.id, f.message, f.user_id, f.message_type " +
            "FROM feedback AS f WHERE f.message_type = 'negative'";
}
