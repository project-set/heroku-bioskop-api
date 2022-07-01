package com.infosys.timd.bioskopapi.Service;

import com.infosys.timd.bioskopapi.DTO.*;
import com.infosys.timd.bioskopapi.Model.*;
import com.infosys.timd.bioskopapi.Exception.*;
import com.infosys.timd.bioskopapi.Response.*;
import com.infosys.timd.bioskopapi.Service.*;
import com.infosys.timd.bioskopapi.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatsServiceImpl implements SeatsService {

    private final SeatsRepository seatRepository;

    @Autowired
    public SeatsServiceImpl(SeatsRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seats> findAllseats() {

        List<Seats> optionalSeats = seatRepository.findAll();
        if (optionalSeats.isEmpty()){
            throw new ResourceNotFoundException("table seats have not value");
        }
        return seatRepository.findAll();
    }

    public Optional<Seats> findbyid(Long id){

        Optional<Seats> optionalSeats = seatRepository.findById(id);
        if (optionalSeats.isEmpty()){
            throw new ResourceNotFoundException(" Seats not Exist with id :" + id);
        }
        return seatRepository.findById(id);
    }

    public Seats createseat(Seats seat) {
        return seatRepository.save(seat);}

    public Seats updateseat(Seats seat, Long seatId) {
        Optional<Seats> optionalSeats = seatRepository.findById(seatId);
        if (optionalSeats.isEmpty()){
            throw new ResourceNotFoundException("Films not exist with id" + seatId);
        }
        return seatRepository.save(seat);
    }


    public void deleteseat(Long seatId){
        Optional<Seats> optionalSeats = seatRepository.findById(seatId);
        if (optionalSeats.isEmpty()){
            throw new ResourceNotFoundException("Seats not exist with id :" + seatId);
        }
        Seats seats = seatRepository.getReferenceById(seatId);
        this.seatRepository.delete(seats);

    }

    @Override
    public Seats getReferenceById(Long id) {
        return this.seatRepository.getReferenceById(id);
    }

    @Override
    public List<Seats> getSeatAvailable(Integer isAvailable) {
        return this.seatRepository.getSeatAvailable(isAvailable);
    }

}

