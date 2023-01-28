package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
      ParkingLot parkingLot=new ParkingLot();
      parkingLot.setAddress(name);
      parkingLot.setAddress(address);
      parkingLotRepository1.save(parkingLot);
      return parkingLot;
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        Spot spot=new Spot();
        spot.setPricePerHour(pricePerHour);
        if(numberOfWheels==2){
            spot.setSpotType(SpotType.TWO_WHEELER);
        }
        else if(numberOfWheels==4){
            spot.setSpotType(SpotType.FOUR_WHEELER);
        }
        else{
            spot.setSpotType(SpotType.OTHERS);
        }
        spot.setOccupied(true);
        ParkingLot p=parkingLotRepository1.findById(parkingLotId).get();
        spot.setParkingLot(p);
        p.getSpotList().add(spot);
        parkingLotRepository1.save(p);

        return spot;


    }

    @Override
    public void deleteSpot(int spotId) {
        spotRepository1.deleteById(spotId);

    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {


         ParkingLot p=parkingLotRepository1.findById(parkingLotId).get();
         List<Spot>list=p.getSpotList();
         Spot spot=null;
         for(Spot s:list){
             if(s.getId()==spotId){

                spot=s;
                break;
             }
         }
         spot.setPricePerHour(pricePerHour);
         spot.setParkingLot(parkingLotRepository1.findById(parkingLotId).get());
         spotRepository1.save(spot);
         return spot;






    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        parkingLotRepository1.deleteById(parkingLotId);

    }
}
