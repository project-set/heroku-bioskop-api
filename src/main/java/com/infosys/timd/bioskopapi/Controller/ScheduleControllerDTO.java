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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teamC/v1/DTO")
@AllArgsConstructor
public class ScheduleControllerDTO {

    private static final Logger logger = LogManager.getLogger(ScheduleControllerDTO.class);
    private static final String Line = "============================================================";

    private ScheduleService scheduleService;

    /**
     *create new schedule into schedules table
     * throws ResourceNotFoundException if bad request happened
     */
    @PostMapping("/schedule")
    public ResponseEntity<Object> createScheduleDTO(@RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        try {
            Schedule scheduleCreate = scheduleRequestDTO.convertToEntity();
            scheduleService.createSchedule(scheduleCreate);
            ScheduleResponseFilmSeatDTO result = scheduleCreate.convertToResponseFilmsSeat();
            logger.info(Line + " Logger Start Created New Schedule" + Line);
            logger.info(result);
            logger.info(Line + " Logger Stop Create New Schedule" + Line);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.CREATED, result);
        } catch (ResourceNotFoundException e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Data Notfound!!");
        }
    }

    /**
     * update schedule
     * throws ResourceNotFoundException if data not found
     */
    @PutMapping("/schedule/{id}")
    public ResponseEntity<Object> updateScheduleDTO(@PathVariable Integer id, @RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        try {
            Schedule schedule = scheduleRequestDTO.convertToEntity();
            schedule.setScheduleId(id);
            Schedule scheduleUpdate = scheduleService.updateSchedule(schedule);
            logger.info(Line + " Logger Start Updated Data" + Line);
            logger.info("Update : " + scheduleUpdate);
            logger.info(Line + " Logger Finish Updated Data" + Line);
            return ResponseHandler.generateResponse("Data Updated!", HttpStatus.CREATED, scheduleUpdate);
        } catch (ResourceNotFoundException e) {
            logger.error("------------------------------------");
            logger.error(e.getMessage());
            logger.error("------------------------------------");
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Data not Found!!");
        }
    }
    /**
     * delete schedule by id
     * throws ResourceNotFoundException if data is not found
     */
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<Object> deleteBooking(@PathVariable Integer id) {
        try {
            scheduleService.deleteScheduleById(id);
            Boolean result = Boolean.TRUE;
            logger.info(Line + " Logger Start Delete Schedule " + Line);
            logger.info("Success Delete Schedule by ID :" + result);
            logger.info(Line + " Logger End Delete Schedule " + Line);
            return ResponseHandler.generateResponse("Success Delete Booking by ID", HttpStatus.NO_CONTENT, result);
        } catch (ResourceNotFoundException e) {
            logger.error("------------------------------------");
            logger.error(e.getMessage());
            logger.error("------------------------------------");
//            logger.info(ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "DAta Not Found!"));
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "DAta Not Found!");
        }
    }
//    public ResponseEntity<Object>deleteScheduleHardway(@PathVariable Integer id){
//       try {
//           scheduleService.deleteScheduleById(id);
//           logger.info(Line);
//           return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, id);
//       }
//       catch (Exception e){
//           return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, id);
//       }
//       }

    /**
     *Get Schedule by Schedule id
     * throws ResourceNotFoundException if data is not found
     */
    @GetMapping("/schedule/{id}")
    public ResponseEntity<Object> getscheduleById(@PathVariable Integer id) {
        try {
            Optional<Schedule> schedule = scheduleService.getScheduleById(id);
            Schedule scheduleget = schedule.get();
            scheduleget.convertToResponse();
            logger.info(Line + " Logger Start Find Schedule ById " + Line);
            logger.info("GetById");
            logger.info(scheduleget);
            logger.info(Line + " Logger End Find Schedule By Id");
            return ResponseHandler.generateResponse("Successfully retrivied data!", HttpStatus.OK, scheduleget);
        } catch (ResourceNotFoundException e) {
            logger.error("------------------------------------");
            logger.error(e.getMessage());
            logger.error("------------------------------------");
//            logger.info( ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Data Not Found!"));
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Data Not Found!"); }
    }

    /**
     * custom Challange 4 slide 8 nomor 2.2
     * create schedule from one of specific film name at schedules table
     *throws ResourceNotFoundException if film name is not found
     */
    @PostMapping("/schedule/byfilmnameLike")
    public ResponseEntity<Object> findScheduleByFilmName(@RequestBody Films film) {
        try {
            List<Schedule> scheduleByFilmName = scheduleService.getScheduleByFilmNameLike(film.getName());
            List<ScheduleResponseNameLikeDTO> scheduleResponseNameLikeDTOS = scheduleByFilmName.stream()
                    .map(Schedule::convertToResponseNameLike)
                    .collect(Collectors.toList());
//       logger.info("Get Name :"+scheduleByFilmName);
            logger.info(Line + "Logger Start Create New Schedule" + Line);
            logger.info("Get Response :" + scheduleResponseNameLikeDTOS);
            logger.info(Line + "Logger Finish Create New Schedule" + Line);
            return ResponseHandler.generateResponse("Success" ,HttpStatus.CREATED,scheduleResponseNameLikeDTOS);
        } catch (ResourceNotFoundException e) {
            logger.error("------------------------------------");
            logger.error(e.getMessage());
            logger.error("------------------------------------");
//            logger.info(ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND, "Data Not Found!!"));
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND, "Data Not Found!!");

        }
    }
}
