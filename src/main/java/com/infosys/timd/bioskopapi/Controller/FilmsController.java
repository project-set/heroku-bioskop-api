package com.infosys.timd.bioskopapi.Controller;

import com.infosys.timd.bioskopapi.DTO.*;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/teamC/v1")
public class FilmsController {

    private static final Logger logger = LogManager.getLogger(FilmsController.class);
    private static final String Line = "====================";

    @Autowired
    private final FilmsService filmsService;

    public FilmsController(FilmsService filmsService) {
        this.filmsService = filmsService;
    }


    @GetMapping("/home")
    public String home() {
        return "Team 4 = Bayu Indra, Yohannes, Rendra, Viona, Ade, Billy";
    }

    @GetMapping("/film/{filmId}")
    public ResponseEntity<Object> getUserById(@PathVariable Long filmId) {
        try {
            Optional<Films> result = filmsService.findbyId(filmId);
            logger.info(Line+" Logger Start GetById "+Line);
            logger.info(result);
            logger.info(Line+" Logger End GetById "+Line);
            return ResponseHandler.generateResponse("Sukses SearchById", HttpStatus.OK, result);
        } catch (Exception e){
            return ResponseHandler.generateResponse("Gagal SearchById", HttpStatus.MULTI_STATUS, null);
        }
    }


    @PostMapping("/film")
    public ResponseEntity<Object> createFilm(@Valid @RequestBody Films films) {
        try {
            Films result = filmsService.createFilm(films);
            logger.info(Line+" Logger Start Create "+Line);
            logger.info(result);
            logger.info(Line+" Logger End Create "+Line);
            return ResponseHandler.generateResponse("Sukses Create", HttpStatus.OK, result);
        } catch (Exception e){
            return ResponseHandler.generateResponse("Gagal", HttpStatus.MULTI_STATUS, null);
        }
    }


    @PutMapping("/film/{filmId}")
    public ResponseEntity<Object> updateFilms(@PathVariable(value = "filmId") Long filmId, @Valid @RequestBody Films filmsdetails) {
        try {
            Films films = filmsdetails;
            films.setFilmId(filmId);
            Films filmsUpdate = filmsService.updateFilm(films,filmId);
            logger.info(Line+" Logger Start Update "+Line);
            logger.info(filmsUpdate);
            logger.info(Line+" Logger End Update "+Line);
            return ResponseHandler.generateResponse("Sukses Update", HttpStatus.ACCEPTED, filmsUpdate);
        } catch (Exception e){
            return ResponseHandler.generateResponse("Gagal", HttpStatus.MULTI_STATUS, null);
        }

    }


    @DeleteMapping("/film/{filmId}")
    public ResponseEntity<Object> deleteFilms(@PathVariable Long filmId) {
        try {
            filmsService.deleteFilmById(filmId);
            Boolean result = Boolean.TRUE;
            logger.info(Line+" Logger Delete "+Line);
            logger.info(result);
            logger.info(Line+" Logger Delete "+Line);
            return ResponseHandler.generateResponse("Sukses Delete", HttpStatus.OK, result);
        } catch (Exception e){
            return ResponseHandler.generateResponse("Gagal", HttpStatus.MULTI_STATUS, null);
        }
    }


    //custom Challange 4 slide 8 nomor 1
    @PostMapping("/film/isPlaying")
    public ResponseEntity<Object> findFilm(@RequestBody Films films){
        try {
            List<Films> result = filmsService.getByIsPlaying(films.getIsPlaying());
            logger.info(Line+" Logger Start SearchFilm "+Line);
            logger.info(result);
            logger.info(Line+" Logger End SearchFilm "+Line);
            return ResponseHandler.generateResponse("Sukses Search SearchFilm", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse("Gagal", HttpStatus.MULTI_STATUS, null);
        }
    }
}