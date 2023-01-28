package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private SpotType spotType;
    private int PricePerHour;
    private boolean occupied;


    @OneToMany(mappedBy = "spot",cascade = CascadeType.ALL)
    private List<Reservation>reservationList=new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private ParkingLot parkingLot;

    public int getId() {
        return id;
    }

    public int getPricePerHour() {
        return PricePerHour;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void setPricePerHour(int pricePerHour) {
        PricePerHour = pricePerHour;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }
    public boolean getOccupied(){
        return occupied;
    }

    public Spot(){

    }
    public Spot(SpotType spotType,int PricePerHour,boolean occupied){
        this.spotType=spotType;
        this.PricePerHour=PricePerHour;
        this.occupied=occupied;

    }


}

