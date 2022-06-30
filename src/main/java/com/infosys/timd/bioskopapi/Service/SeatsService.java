package com.infosys.timd.bioskopapi.Service;

import com.infosys.timd.bioskopapi.DTO.*;
import com.infosys.timd.bioskopapi.Model.*;
import com.infosys.timd.bioskopapi.Exception.*;
import com.infosys.timd.bioskopapi.Response.*;
import com.infosys.timd.bioskopapi.Service.*;

import java.util.List;
import java.util.Optional;

public interface SeatsService {

    List<Seats> findAllseats();
    Optional<Seats> findbyid(Long id);
    Seats createseat(Seats seat);
    Seats updateseat(Seats seat);
    void deleteseat(Seats seat);
    Seats getReferenceById (Long id);
    List<Seats> getSeatAvailable(Integer isAvailable);
}
