package com.infosys.timd.bioskopapi.Controller;

import com.infosys.timd.bioskopapi.DTO.*;
import com.infosys.timd.bioskopapi.Model.*;
import com.infosys.timd.bioskopapi.Exception.*;
import com.infosys.timd.bioskopapi.Response.*;
import com.infosys.timd.bioskopapi.Service.*;

import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teamC/v1/DTO")
@AllArgsConstructor
public class BookingController {

    private static final Logger logger = LogManager.getLogger(BookingController.class);
    private static final String Line = "====================";
    private BookingService bookingService;


    //get ALL //logger aman
    @GetMapping("/booking")
    public ResponseEntity<Object> findAll(){
        try {
            List<Booking> bookings = bookingService.getAll();
            List<BookingResponseDTO> results = new ArrayList<>();
            for (Booking dataresults:bookings){
                logger.info(Line + " Logger Start Find All Booking " + Line);
                logger.info("Booking id : "+dataresults.getBookingId()+
                        ", Title Film : "+dataresults.getSchedule().getFilms().getName()+
                        ", Status Playing : "+dataresults.getSchedule().getFilms().getIsPlaying()+
                        ", Studio : "+dataresults.getSchedule().getSeats().getStudioName()+
                        ", Seat Number : "+dataresults.getSchedule().getSeats().getSeatNumber()+
                        ", Status Seat : "+dataresults.getSchedule().getSeats().getIsAvailable()+
                        ", Price : "+dataresults.getSchedule().getPrice()+
                        ", Date Show : "+dataresults.getSchedule().getDateShow()+
                        ", Show Start : "+dataresults.getSchedule().getShowStart()+
                        ", Show End : "+dataresults.getSchedule().getShowEnd());
                BookingResponseDTO bookDTO = dataresults.convertToResponse();
                results.add(bookDTO);
                logger.info(Line + " Logger End Find All Booking " + Line);
            }
            return ResponseHandler.generateResponse("Success Get All",HttpStatus.OK,results);
        }catch(ResourceNotFoundException e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND,"Table has no value"));
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND,"Table has no value");
        }
    }

    //getById //logger aman
    @GetMapping("/booking/{id}")
    public ResponseEntity<Object> getBookingById(@PathVariable Long id){
        try {
            Optional<Booking> booking = bookingService.getBookingById(id);
            Booking bookingget = booking.get();
            bookingget.convertToResponsePost();
            logger.info(Line + " Logger Start Create Booking " + Line);
            logger.info("Update Booking : " + bookingget);
            logger.info(Line + " Logger End Create Booking " + Line);
            return ResponseHandler.generateResponse("Success Get By Id",HttpStatus.OK,bookingget);
        }catch(ResourceNotFoundException e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND,"Data not found"));
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND,"Data not found");
        }
    }

    //update //logger aman
    @PutMapping("/booking/{id}")
    public ResponseEntity<Object> bookingupdate(@PathVariable Long id, @RequestBody BookingRequestDTO bookingRequestDTO){
        try {
            Booking booking = bookingRequestDTO.covertToEntitiy();
            booking.setBookingId(id);
            Booking bookingUpdate = bookingService.updateBooking(booking);
            bookingUpdate.convertToResponse();
            logger.info(Line + " Logger Start Update Booking " + Line);
            logger.info("Update Booking : " + bookingUpdate);
            logger.info(Line + " Logger End Update Booking " + Line);
            return ResponseHandler.generateResponse("Success Update Booking",HttpStatus.CREATED,bookingUpdate);
        }catch (ResourceNotFoundException e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND,"Data not found"));
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND,"Data not found");
        }
    }
    //create
    @PostMapping("/booking")
    public ResponseEntity<Object> bookingCreate(@RequestBody BookingRequestDTO bookingRequestDTO){
        try{
            Booking booking = bookingRequestDTO.covertToEntitiy();
            bookingService.createBooking(booking);
            BookingResponsePost result = booking.convertToResponsePost();
            logger.info(Line + " Logger Start Update Booking " + Line);
            logger.info("Create Booking : " + result);
            logger.info(Line + " Logger End Update Booking " + Line);
            return ResponseHandler.generateResponse("Success Create Booking",HttpStatus.CREATED,result);
        }catch (Exception e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(ResponseHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST,"Data not found"));
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST,"Failed Create Database");
        }
    }

    //delete //logger aman
    @DeleteMapping("/booking/{id}")
    public ResponseEntity<Object> deleteBooking(@PathVariable Long id){
        try {
            bookingService.deleteSBookingById(id);
            Boolean result = Boolean.TRUE;
//            response.put("Booking deleted by ID", Boolean.TRUE);
            logger.info(Line + " Logger Start Delete Booking " + Line);
            logger.info("Success Delete Booking by ID :"+result);
            logger.info(Line + " Logger End Delete Booking " + Line);
            return ResponseHandler.generateResponse("Success Delete Booking by ID",HttpStatus.NO_CONTENT,result);
        }catch(ResourceNotFoundException e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND,"Data not found"));
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND,"Data not found");
        }
    }

    //custom //logger aman
    @PostMapping("/booking/Filmname")
    public ResponseEntity<Object> findBookingBySchdeuleFilmName(@RequestBody Films films){
        try {
            List<Booking> bookingByScheduleFilmsname = bookingService.getBookingByFilmName(films.getName());
            List<BookingResponseDTO> bookingResponseDTOS = bookingByScheduleFilmsname.stream()
                    .map(Booking::convertToResponse)
                    .collect(Collectors.toList());
            logger.info(Line+" Logger Start Query By Film Name Booking "+Line);
            logger.info("Success Query By Filmname : " +bookingResponseDTOS);
            logger.info(Line+" Logger End Query By Film Name Booking "+Line);
            return ResponseHandler.generateResponse("Succes Query By Filmname",HttpStatus.OK,bookingResponseDTOS);
        }catch (ResourceNotFoundException e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND,"Data not found"));
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND,"Data not found");
        }

    }


}





