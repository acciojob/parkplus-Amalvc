package com.driver.model;

import javax.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private boolean paymentCompleted;
    private PaymentMode paymentMode;
    @OneToOne
    @JoinColumn
    private Reservation reservation;

    public int getId() {
        return id;
    }
    public boolean isPaymentCompleted(){
        return paymentCompleted;
    }
    public Payment(boolean paymentCompleted,PaymentMode paymentMode){
        this.paymentCompleted=paymentCompleted;
        this.paymentMode=paymentMode;
    }


    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPaymentCompleted(boolean paymentCompleted) {
        this.paymentCompleted = paymentCompleted;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    public Payment(){
        this.paymentCompleted=false;
    }
}
