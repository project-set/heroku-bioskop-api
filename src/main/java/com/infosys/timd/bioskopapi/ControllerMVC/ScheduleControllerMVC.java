package com.infosys.timd.bioskopapi.ControllerMVC;

import com.infosys.timd.bioskopapi.Model.Schedule;
import com.infosys.timd.bioskopapi.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ScheduleControllerMVC {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/schedule")
    public String showSchedule(Model model) {
        List<Schedule> schedules = scheduleService.getAll();
        model.addAttribute("schedules", schedules);

        return "schedule";
    }

}