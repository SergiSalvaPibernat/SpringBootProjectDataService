package com.web.DataService.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="REGISTRATIONS")
public class Registration {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long id;

    @Column(name="CUSTOMER_ID")
    long customerId;

    @Column(name="EVENT_ID")
    long eventId;

    @Column(name="REGISTRATION_DATE")
    String registrationDate;

    @Column(name="STATUS")
    String status = "ACTIVE";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", eventId=" + eventId +
                ", registrationDate='" + registrationDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}