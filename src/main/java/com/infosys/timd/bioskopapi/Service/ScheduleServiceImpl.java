package com.infosys.timd.bioskopapi.Service;

import com.infosys.timd.bioskopapi.DTO.*;
import com.infosys.timd.bioskopapi.Model.*;
import com.infosys.timd.bioskopapi.Exception.*;
import com.infosys.timd.bioskopapi.Response.*;
import com.infosys.timd.bioskopapi.Service.*;
import com.infosys.timd.bioskopapi.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor // kalau udah pakai AllArgsConstructor ngga perlu lagi pakai anotasi @AutoWired :D
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    //Get All
    public List<Schedule> getAll(){
        return this.scheduleRepository.findAll();
    }

    //Get By ID
    public Optional<Schedule> getScheduleById(Integer Id) throws ResourceNotFoundException{
        Optional<Schedule> optionalSchedule = this.scheduleRepository.findById(Id);
        if(optionalSchedule.isEmpty()){
            throw new ResourceNotFoundException("Schedule not exist with id :" + Id);
        }
        return this.scheduleRepository.findById(Id);
    }

    //Post
    public Schedule createSchedule(Schedule schedule){
        return this.scheduleRepository.save(schedule);
    }

    //Update
    @Override
    public Schedule updateSchedule(Schedule schedule) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(schedule.getScheduleId());
        if(optionalSchedule.isEmpty()){
            throw new ResourceNotFoundException("User not exist with id :" + schedule.getScheduleId());
        }
        return this.scheduleRepository.save(schedule);
    }
    public Schedule getReferenceById (Integer id) {

        return this.scheduleRepository.getReferenceById(id);
    }

    //Delete
    public void deleteScheduleById(Integer Id){
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(Id);
        if(optionalSchedule.isEmpty()){
            throw new ResourceNotFoundException("Schedule not exist with id :" + Id);
        }
            Schedule schedule = scheduleRepository.getReferenceById(Id) ;
        this.scheduleRepository.delete(schedule);
    }

    //Custom Select
    @Override
    public List<Schedule> getScheduleByFilmName(String name) {
        List<Schedule> getScheduleByFilmName = this.scheduleRepository.getScheduleByFilmName(name);
        if (getScheduleByFilmName.isEmpty()){
            throw new ResourceNotFoundException("Schedule with film name " + name + " is not exist!!!!!");
        }
        return this.scheduleRepository.getScheduleByFilmName(name);
    }

    @Override
    public List<Schedule> getScheduleByFilmNameLike(String name) {
        return this.scheduleRepository.getScheduleFilmsNameLike(name);
    }

    public List<Schedule> getPriceFilmPrice(String name){
        return this.scheduleRepository.getScheduleFilmsNameLike(name);
    }

}