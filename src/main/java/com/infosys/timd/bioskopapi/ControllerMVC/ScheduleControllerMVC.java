package com.infosys.timd.bioskopapi.ControllerMVC;

import com.infosys.timd.bioskopapi.Exception.ResourceNotFoundException;
import com.infosys.timd.bioskopapi.Model.Films;
import com.infosys.timd.bioskopapi.Model.Schedule;
import com.infosys.timd.bioskopapi.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/schedule/new")
    public String showNewSchedule(Model model) {
        model.addAttribute("schedules", new Schedule());
        model.addAttribute("pageTitle", "Add New Schedules");

        return "schedule_form";
    }

    @PostMapping("/schedule/save")
    public String saveSchedule(Schedule schedules, RedirectAttributes ra){
        scheduleService.createSchedule(schedules);
        ra.addFlashAttribute("message", "Schedule saved successfully");

        return "redirect:/schedule";
    }

    @GetMapping("/schedule/edit/{scheduleId}")
    public String showEditForm(@PathVariable("scheduleId") Integer id, Model model, RedirectAttributes ra) {
        try {
            Optional<Schedule> schedules = scheduleService.getScheduleById(id);
            model.addAttribute("schedules", schedules);
            model.addAttribute("pageTitle", "Edit Schedule (ID: " + id + ")");
            return "schedule_form";
        } catch (ResourceNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());

            return "redirect:/schedule";
        }
    }

    @GetMapping("/schedule/delete/{scheduleId}")
    public String showDeleteForm(@PathVariable("scheduleId") Integer id, RedirectAttributes ra) {
        try {
            scheduleService.deleteScheduleById(id);
            ra.addFlashAttribute("message", "Successfully deleted schedule.");
        } catch (ResourceNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/schedule";
    }

    @PostMapping("/schedule")
    public String search(Films film, Model model, String name) {
        if(name!=null) {
            List<Schedule> list = scheduleService.getScheduleByFilmNameLike(film.getName());
            model.addAttribute("list", list);
        }else {
            List<Schedule> list = scheduleService.getAll();
            model.addAttribute("list", list);}
        return "schedule";
    }
}
