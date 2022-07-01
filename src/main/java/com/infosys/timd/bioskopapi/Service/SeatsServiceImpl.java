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
        return seatRepository.findAll();
    }

    public Optional<Seats> findbyid(Long id){
        return seatRepository.findById(id);
    }

    public Seats createseat(Seats seat) {
        return seatRepository.save(seat);}

    public Seats updateseat(Seats seat) {
        return seatRepository.save(seat);
    }

    public void deleteseat(Seats seat){
        seatRepository.delete(seat);
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

