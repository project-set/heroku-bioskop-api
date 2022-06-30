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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/teamC/v1/DTO")
@AllArgsConstructor
public class FilmsControllerDTO {

    private static final Logger logger = LogManager.getLogger(FilmsControllerDTO.class);
    private static final String Line = "======================";
    private FilmsService filmsService;


    //GET ALL // LOGGER AMAN
    @GetMapping("/films")
    public ResponseEntity<Object> findAllFilms() {
        try {
            List<Films> result = filmsService.findAllFilms();
            logger.info(Line + "Logger Start Find All Films" + Line);
            logger.info(filmsService.findAllFilms());
            logger.info(Line + "Logger End Find All Films" + Line);
            return ResponseHandler.generateResponse("Succes All", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //GET BY ID // LOGGER AMAN
    @GetMapping("/films/{filmId}")
    public ResponseEntity<Object> getfilmById(@PathVariable Long filmId) {
        try {
            Optional<Films> films = filmsService.findbyId(filmId);
            Films filmget = films.get();
            logger.info(Line + "Logger Start Find Films by Id" + Line);
            logger.info(filmget.convertToResponse());
            logger.info(Line + "Logger End Find Films by Id" + Line);
            return ResponseHandler.generateResponse("Succes Get", HttpStatus.OK, filmget);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }


    //UPDATE // LOGGER AMAN//
    @PutMapping("/films/{filmId}")
    public ResponseEntity<Object> filmsupdate(@PathVariable Long filmId, @RequestBody FilmsRequestDTO filmsRequestDTO) {
        try {
            Films films = filmsRequestDTO.convertToEntity();
            films.setFilmId(filmId);
            Films filmsUpdate = filmsService.updateFilm(films, filmId);
            logger.info(Line + "Logger Start Update Films by Id" + Line);
            logger.info(filmsUpdate.convertToResponse());
            logger.info(Line + "Logger End Update Films by Id" + Line);
            return ResponseHandler.generateResponse("Succes Update", HttpStatus.OK, filmsUpdate);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //DELETE // LOGGER AMAN
    @DeleteMapping("/films/{filmId}")
    public ResponseEntity<Object> deleteBooking(@PathVariable Long filmId) {
        try {
            filmsService.deleteFilmById(filmId);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            logger.info(Line + "Logger Start Delete Films by Id" + Line);
            logger.info(response);
            logger.info(Line + "Logger End Delete Films by Id" + Line);
            return ResponseHandler.generateResponse("Succes Delete", HttpStatus.OK, response);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //CREATE // LOGGER AMAN
    @PostMapping("/films")
    public ResponseEntity<Object> createFilm(@RequestBody FilmsRequestDTO filmRequestDTO) {
        try {
            Films filmsCreate = filmRequestDTO.convertToEntity();
            filmsService.createFilm(filmsCreate);
            logger.info(Line + "Logger Start Create Films" + Line);
            logger.info(filmsCreate.convertToResponse());
            logger.info(Line + "Logger End Create Films" + Line);
            return ResponseHandler.generateResponse("Succes Create", HttpStatus.OK, filmsCreate);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping("/fimsDTO/{isPlaying}")
    public ResponseEntity<Object> findFilmIsPlaying(@PathVariable Integer isPlaying){
    try{
       List <Films> filmIsPlaying = filmsService.getByIsPlaying(isPlaying);
        logger.info(Line + "Logger Start Find Films is Playing" + Line);
        logger.info(filmsService.getByIsPlaying(isPlaying));
        logger.info(Line + "Logger End Find Films" + Line);
        return ResponseHandler.generateResponse("Find Film Succes", HttpStatus.OK, filmIsPlaying);
    }catch (Exception e){
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
    }
    }
}