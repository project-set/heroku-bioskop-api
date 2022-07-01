package com.infosys.timd.bioskopapi.Controller;

import com.infosys.timd.bioskopapi.Model.*;
import com.infosys.timd.bioskopapi.Exception.*;
import com.infosys.timd.bioskopapi.Response.*;
import com.infosys.timd.bioskopapi.Service.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping("/teamC/v1")
public class SeatsController {

    private static final Logger logger = LogManager.getLogger(SeatsController.class);
    private static final String Line = "============================================================";
    @Autowired
    private SeatsService seatsService;

    @GetMapping("/seats")
    public ResponseEntity<Object> getSeats(){
        try{
            List<Seats> result = seatsService.findAllseats();
            logger.info(Line);
            logger.info("GetAll");
            logger.info(seatsService.findAllseats());
            logger.info(Line);
            return ResponseHandler.generateResponse("Succesfully Read All Data Seats !", HttpStatus.OK, result);
        }
        catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS.OK, null);
        }

    }

    @PostMapping("/seats")
    public ResponseEntity<Object> createseats(@RequestBody Seats seat){
        try{
            Seats result = seatsService.createseat(seat);
            logger.info(Line);
            logger.info("Create");
            logger.info(seatsService.createseat(seat));
            logger.info(Line);
            return ResponseHandler.generateResponse("Succesfully Add Data Seats !", HttpStatus.OK, result);
        }catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS.OK, null);
        }
    }

    @GetMapping("/seats/{seatId}")
    public ResponseEntity<Object> getseatsById(@PathVariable Long seatId){
        try{
            Optional<Seats> seats = seatsService.findbyid(seatId);
            logger.info(Line);
            logger.info("GetById");
            logger.info(ResponseEntity.ok());
            logger.info(Line);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, seatId);
        }catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
        //Seats seat = seatsService.findbyid(seatId).orElseThrow(() ->
        //new ResourceNotFoundException("seats not exit with Seats_number:" + seatId));
    }

    @PutMapping("/seats/{seatId}")
    public ResponseEntity<Object> updateSeats(@PathVariable Long seatId, @RequestBody Seats seatsDetails){
        try{
            Seats seat = seatsService.findbyid(seatId).orElseThrow(() ->
                    new ResourceNotFoundException("seats not exit with Seats_number:" + seatsDetails));

            //seat.setSeatId(seatsDetails.getSeatId());
            seat.setStudioName(seatsDetails.getStudioName());
            seat.setSeatNumber(seatsDetails.getSeatNumber());
            seat.setIsAvailable(seatsDetails.getIsAvailable());

            Seats updatedseats = seatsService.updateseat(seat);
            logger.info(Line);
            logger.info("Update");
            logger.info(seatsService.updateseat(seat));
            logger.info(Line);
            return ResponseHandler.generateResponse("Success Update Seats",HttpStatus.OK, updatedseats);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }

    }

    @DeleteMapping("/seats/{seatId}")
    public ResponseEntity<Object> deleteseats(@PathVariable Long seatId){
        try{
            Optional<Seats> seats = seatsService.findbyid(seatId);
            Map<String, Boolean> respone = new HashMap<>();
            respone.put("deleted", Boolean.TRUE);
            logger.info(Line);
            logger.info("Delete");
            logger.info(ResponseEntity.ok(respone));
            logger.info(Line);
            return ResponseHandler.generateResponse("Deleted! ", HttpStatus.OK, seatId);
        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

//        Seats seat = seatsService.findbyid(seatId).orElseThrow(() ->
//                new ResourceNotFoundException("seats not exit with seatId:" + seatId));

    }

    //custom challange 4 slide 8 nomor 3
    @PostMapping("/seats/isAvailable")
    public ResponseEntity<Object> findSeats(@RequestBody Seats seats){
        try{
            List<Seats> result = seatsService.getSeatAvailable(seats.getIsAvailable());
            logger.info(Line);
            logger.info("Query Seat");
            logger.info(result);
            logger.info(Line);
            return ResponseHandler.generateResponse("Successfully seats is Available ! ", HttpStatus.OK, result);
        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

}
