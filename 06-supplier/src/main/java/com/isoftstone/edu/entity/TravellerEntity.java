package com.isoftstone.edu.entity;
import java.lang.Long;
public class TravellerEntity {
    private Long travellerId;
    private Long roomId;
    private String travellerName;
    private String email;
    private String phone;
    private String orderTime;
    private String leaveDay;
    private String limitedDay;

    public TravellerEntity(Long travellerId,Long roomId,String travellerName,String email
     ,String phone,String orderTime,String leaveDay,String limitedDay ) {

        this.travellerId = travellerId;
        this.email = email;
        this.leaveDay = leaveDay;
        this.limitedDay = limitedDay;
        this.orderTime = orderTime;
        this.phone = phone;
        this.roomId = roomId;
        this.travellerName = travellerName;
    }

    public Long getTravellerId() {
        return travellerId;
    }

    public void setTravellerId(Long travellerId) {
        Long convert = null;
               String  next = travellerId.toString().trim();
               convert = Long.valueOf(next);
        this.travellerId = travellerId == null ? null : convert;;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        Long convert = null;
        String  next = roomId.toString().trim();
        convert = Long.valueOf(next);
        this.roomId = roomId == null ? null : convert;
    }

    public String getTravellerName() {
        return travellerName;
    }

    public void setTravellerName(String travellerName) {
        this.travellerName = travellerName == null?null:travellerName.trim();
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime == null ? null : orderTime.trim();
    }

    public String getLeaveDay() {
        return leaveDay;
    }

        public void setLeaveDay(String leaveDay) {
        this.leaveDay = leaveDay == null ? null : leaveDay.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getLimitedDay() {
        return limitedDay;
    }

    public void setLimitedDay(String limitedDay) {
        this.limitedDay = limitedDay == null ? null : limitedDay.trim();
    }
}
