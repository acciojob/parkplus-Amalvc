package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
      try{
          if(!userRepository3.findById(userId).isPresent()||!parkingLotRepository3.findById(parkingLotId).isPresent()){
              throw new Exception("Cannot make reservation");
          }
          User user=userRepository3.findById(userId).get();
          ParkingLot parkingLot=parkingLotRepository3.findById(parkingLotId).get();
          List<Spot>list=parkingLot.getSpotList();
          boolean flag=false;
          for(Spot s:list){
              if(!s.getOccupied()){
                  flag=true;
                  break;
              }
          }
          if(flag==false){
              throw new Exception("Cannot make reservation");
          }
          SpotType requested;
          if(numberOfWheels>4){
              requested=SpotType.OTHERS;

          }
          else if(numberOfWheels>2){
              requested=SpotType.FOUR_WHEELER;
          }
          else
          {
              requested=SpotType.TWO_WHEELER;
          }
          boolean flag2=false;
          int price=Integer.MAX_VALUE;
          Spot chosen=null;

          for(Spot s:list){
              if(requested.equals(SpotType.OTHERS) && s.equals(SpotType.OTHERS)){
                  if(s.getPricePerHour()*timeInHours<price && !s.getOccupied()){
                      price=s.getPricePerHour()*timeInHours;
                      chosen=s;
                      flag2=true;
                  }
                  else if(requested.equals(SpotType.TWO_WHEELER) && s.equals(SpotType.OTHERS)||s.equals(SpotType.TWO_WHEELER)||s.equals(SpotType.FOUR_WHEELER)){
                      if(s.getPricePerHour()*timeInHours<price && !s.getOccupied()){
                          price=s.getPricePerHour()*timeInHours;
                          chosen=s;
                          flag2=true;
                      }
                  }
                  else if(requested.equals(SpotType.FOUR_WHEELER)&&s.equals(SpotType.OTHERS)||s.equals(SpotType.FOUR_WHEELER)){
                      if(s.getPricePerHour()*timeInHours<price && !s.getOccupied()){
                          price=s.getPricePerHour()*timeInHours;
                          chosen=s;
                          flag2=true;
                      }
                  }
              }
          }
          if(flag2==false){
              throw new Exception("Cannot make reservation");
          }
          chosen.setOccupied(true);
          Reservation reservation=new Reservation();
          reservation.setUser(user);
          reservation.setNumberOfHours(timeInHours);
          reservation.setSpot(chosen);
          chosen.getReservationList().add(reservation);
          user.getReservationList().add(reservation);
          userRepository3.save(user);
          spotRepository3.save(chosen);
          return reservation;
      }
      catch(Exception e){
          return null;
      }


    }
}
