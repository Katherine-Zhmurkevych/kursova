package edu.mobileservice.model;

import edu.mobileservice.model.annotations.Column;
import edu.mobileservice.model.annotations.PrimaryKey;
import edu.mobileservice.model.annotations.Table;

@Table(name = "outgoing_call")
public class OutgoingCallEntity {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;
    @Column(name = "caller_number_id")
    private Integer callerNumberId;
    @Column(name = "receiver_number", length = 45)
    private String receiverNumber;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "type", length = 45)
    private String type;

    public OutgoingCallEntity() {
    }

    public OutgoingCallEntity(Integer id, Integer callerNumberId, String receiverNumber, Integer duration, String type) {
        this.id = id;
        this.callerNumberId = callerNumberId;
        this.receiverNumber = receiverNumber;
        this.duration = duration;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCallerNumberId() {
        return callerNumberId;
    }

    public void setCallerNumberId(Integer callerNumberId) {
        this.callerNumberId = callerNumberId;
    }

    public String getReceiverNumber() {
        return receiverNumber;
    }

    public void setReceiverNumber(String receiverNumber) {
        this.receiverNumber = receiverNumber;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("\nID: %-5s Caller number ID: %-5s Receiver number: %-15s Duration: %-5s Type: %-7s",
                id, callerNumberId, receiverNumber, duration, type);
    }
}
