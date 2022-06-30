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

import java.util.*;

@RestController
@RequestMapping("/teamC/v1")
@AllArgsConstructor
public class ScheduleController {
    private static final Logger logger = LogManager.getLogger(ScheduleController.class);
    private static final String Line = "============================================================";

    private ScheduleService scheduleService;
    private FilmsService filmsService;
    private SeatsService seatsService;

    /**
     *Get all of data from schedules table
     */
   @GetMapping("/schedule")
    public ResponseEntity<Object> ScheduleList(){
       try {
           List<Schedule> result = scheduleService.getAll();
           List<Map<String,Object>> maps = new ArrayList<>();
           logger.info(Line + "Logger Start Find All Schedule" + Line);
           for (Schedule dataResult:result){
//               Logger.info("Data get All Schedule : "+dataResult.getResult() + dataResult.getName()+dataResult.getIsPlaying
               Map<String,Object> schedule = new HashMap<>();
               logger.info("================================");
               schedule.put("Code : ", dataResult.getFilms());
               logger.info("code :"+dataResult.getFilms());
               schedule.put("Code : ", dataResult.getSeats());
               logger.info("code :"+dataResult.getSeats());
               schedule.put("Code : ", dataResult.getScheduleId());
               logger.info("code :"+dataResult.getScheduleId());
               logger.info("================================");
               maps.add(schedule);
           }

           logger.info(Line + "Logger End Find All Schedule" + Line);
           return ResponseHandler.generateResponse("Successfully  getAll data!", HttpStatus.OK, result);
       }
       catch (ResourceNotFoundException e) {
           logger.error("------------------------------------");
           logger.error(e.getMessage());
           logger.error("------------------------------------");
//           logger.info(ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND, "Tabel has no Value"));
           return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND, "Tabel has no Value");
       }

   }

    /**
     *  create new schedule into schedules table
     *  throws ResourceNotFoundException if bad request happened
     */
    @PostMapping("/schedule")
    public ResponseEntity<Object>createSchedule(@RequestBody Schedule schedule){
            try{
                Films films = filmsService.getReferenceById(schedule.getFilms().getFilmId());
                Seats seats = seatsService.getReferenceById(schedule.getSeats().getSeatId());
                schedule.setFilms(films);
                schedule.setSeats(seats);
                Schedule result = scheduleService.createSchedule(schedule);
                logger.info(Line + "Logger Start Create New Schedule" + Line);
                logger.info(schedule);
                logger.info(Line + " Logger Stop Create New Schedule" + Line);
                return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.CREATED,result);
        }
            catch (ResourceNotFoundException e){
                logger.error("------------------------------------");
                logger.error(e.getMessage());
                logger.error("------------------------------------");
//                logger.info(ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND,"Data Not Found"));
                return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND, null);
       }
    }

    /**
     *Get Schedule by Schedule id
     * throws ResourceNotFoundException if data is not found
     */
    @GetMapping("/schedule/{id}")
    public ResponseEntity<Object> getscheduleById(@PathVariable Integer id){
       try {
           Optional<Schedule> schedule = scheduleService.getScheduleById(id);
           Schedule scheduleget = schedule.get();
           logger.info(Line + " Logger Start Find Schedule ById " + Line);
           logger.info("GetById");
           logger.info(ResponseEntity.ok(schedule));
           logger.info(Line + " Logger End Find Schedule By Id" + Line);
           return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, scheduleget);
       } catch (ResourceNotFoundException e) {
           logger.error("------------------------------------");
           logger.error(e.getMessage());
           logger.error("------------------------------------");
//           logger.info(ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Data Not Found!"));
           return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Data Not Found!");
       }
    }

    /**
     * update schedule
     * throws ResourceNotFoundException if data not found
     */
    @PutMapping("/schedule")
        public ResponseEntity<Object> updateSchedule(@RequestBody Schedule scheduleDetails){
       try {
        Schedule updateSchedule = scheduleService.getReferenceById(scheduleDetails.getScheduleId());
        Films films = filmsService.getReferenceById(scheduleDetails.getFilms().getFilmId());
        Seats seats = seatsService.getReferenceById(scheduleDetails.getSeats().getSeatId());
        updateSchedule.setSeats(seats);
        updateSchedule.setFilms(films);
        updateSchedule.setScheduleId(scheduleDetails.getScheduleId());
        updateSchedule.setPrice(scheduleDetails.getPrice());
        Schedule result = scheduleService.updateSchedule(updateSchedule);
        logger.info(Line + " Logger Start Updated Data" + Line);
        logger.info("Update");
        logger.info(result);
        logger.info(Line + "Logger Finish Updated Data" + Line);
        return ResponseHandler.generateResponse("Data Updated!", HttpStatus.CREATED, result);
    }
       catch (ResourceNotFoundException e){
           logger.error("------------------------------------");
           logger.error(e.getMessage());
           logger.error("------------------------------------");
//           logger.info(ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Data not Found!"));
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Data not Found!");
    }
       }


    /**
     * delete schedule by id
     * throws ResourceNotFoundException if data is not found
     */
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<Object> delete (@PathVariable Integer id){
       try {
           scheduleService.deleteScheduleById(id);
           Map<String, Boolean> response = new HashMap<>();
           response.put("deleted", Boolean.TRUE);
           logger.info(Line + " Logger Deleted Somedata" + Line);
           logger.info(response);
           logger.info(Line + " Logger Deleted Done!" + Line);
           return ResponseHandler.generateResponse("Deleted! ", HttpStatus.NO_CONTENT, response);
    }
       catch (ResourceNotFoundException e){
           logger.error("------------------------------------");
           logger.error(e.getMessage());
           logger.error("------------------------------------");
//           logger.info(ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Data Not Found"));
           return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Data Not Found");
       }
       }

    /**
     *  custom Challenge 4 slide 8 nomor 2.1
     *  create schedule from one of film name at schedules table
     *  throws ResourceNotFoundException if film name is not found
     */
    @PostMapping("/schedule/byfilmname")
    public ResponseEntity<Object> findScheduleByFilmName(@RequestBody Films film){
        try {
            List<Schedule> scheduleByFilmName = scheduleService.getScheduleByFilmName(film.getName());
            logger.info(Line + " Logger Start Create Schedule" + Line);
            logger.info(ResponseEntity.status(HttpStatus.OK).body(scheduleByFilmName));
            logger.info(Line + " Logger Finish Create Schedule" + Line);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.CREATED, scheduleByFilmName);
        }
        catch (ResourceNotFoundException e){
            logger.error("------------------------------------");
            logger.error(e.getMessage());
            logger.error("------------------------------------");
//            logger.info(ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Data Not Found"));
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Data Not Found");
        }
        }


    /**
     * custom Challange 4 slide 8 nomor 2.2
     * create schedule from one of specific film name at schedules table
     *throws ResourceNotFoundException if film name is not found
     */
    @PostMapping("/schedule/byfilmnameLike")
    public ResponseEntity<Object> findScheduleByFilmNameLike(@RequestBody Films film){
       try {
           List<Schedule> scheduleByFilmName = scheduleService.getScheduleByFilmNameLike(film.getName());
        logger.info(Line + " Logger Start Create Schedule By Film Name" + Line);
        logger.info(scheduleByFilmName);
        logger.info(Line + " Logger Finish Create Schedule By Film Name" + Line);
        return ResponseHandler.generateResponse("Successfully added data ! ", HttpStatus.OK, scheduleByFilmName);
    }
       catch (ResourceNotFoundException e){
           logger.error("------------------------------------");
           logger.error(e.getMessage());
           logger.error("------------------------------------");
//           logger.info(ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Data Not Found"));
           return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Data Not Found");
       }
       }


}
