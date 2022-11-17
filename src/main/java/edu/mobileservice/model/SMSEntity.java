package edu.mobileservice.model;

import edu.mobileservice.model.annotations.Column;
import edu.mobileservice.model.annotations.PrimaryKey;
import edu.mobileservice.model.annotations.Table;

@Table(name = "sms")
public class SMSEntity {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;
    @Column(name = "sender_number_id")
    private Integer senderNumberId;
    @Column(name = "receiver_number", length = 45)
    private String receiverNumber;
    @Column(name = "text", length = 45)
    private String text;

    public SMSEntity() {
    }

    public SMSEntity(Integer id, Integer senderNumberId, String receiverNumber, String text) {
        this.id = id;
        this.senderNumberId = senderNumberId;
        this.receiverNumber = receiverNumber;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderNumberId() {
        return senderNumberId;
    }

    public void setSenderNumberId(Integer senderNumberId) {
        this.senderNumberId = senderNumberId;
    }

    public String getReceiverNumber() {
        return receiverNumber;
    }

    public void setReceiverNumber(String receiverNumber) {
        this.receiverNumber = receiverNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("\nID: %-5s Sender number ID: %-5s Receiver number: %-13s Text: %-1s",
                id, senderNumberId, receiverNumber, text);
    }
}
