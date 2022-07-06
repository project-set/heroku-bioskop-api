package com.infosys.timd.bioskopapi.ControllerMVC;

import com.infosys.timd.bioskopapi.Model.Seats;
import com.infosys.timd.bioskopapi.Service.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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
        model.addAttribute("pageTitle", "Add New Seats");

        return "seats_form";
    }

    @PostMapping("/seats/save")
    public String createseat(Seats seat, RedirectAttributes ra) {
        seatsService.createseat(seat);
        ra.addFlashAttribute("message", "Seats has been added");

        return "redirect:/seats";
    }

    @GetMapping("/seats/update/{seatId}")
    public String showUpdateForm(@PathVariable("seatId") Long seatId, Model model, RedirectAttributes ra) {
        try {
            Optional<Seats> seat = seatsService.findbyid(seatId);
            model.addAttribute("seats", seat);
            model.addAttribute("pageTitle", "Update Seats (ID: " + seatId + ")");
            return "seats_form";
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());

            return "redirect:/seats";
        }
    }

    @GetMapping("/seats/delete/{seatId}")
    public String deleteSeat(@PathVariable("seatId") Long seatId, RedirectAttributes ra) {
        try {
            seatsService.deleteseat(seatId);
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/seats";
    }
}

