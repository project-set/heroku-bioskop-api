package com.infosys.timd.bioskopapi.ControllerMVC;

import com.infosys.timd.bioskopapi.Model.Seats;
import com.infosys.timd.bioskopapi.Service.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SeatsControllerMVC {
    @Autowired
    private SeatsService seatsService;

    @GetMapping("/seats")
    public String showSeats(Model model) {
        List<Seats> seats = seatsService.findAllseats();
        model.addAttribute("seats", seats);

        return "seats";
    }

    @GetMapping("/seats/new")
    public String showNewForm(Model model) {
        model.addAttribute("seats", new Seats());
//        model.addAttribute("pageTitle", "Add New Seats");

        return "seats_form";
    }

    @PostMapping("/seats/save")
    public String createseat(Seats seat, RedirectAttributes ra) {
        seatsService.createseat(seat);
        ra.addAttribute("message", "Seats has been added");

        return "redirect:/seats";
    }
}
