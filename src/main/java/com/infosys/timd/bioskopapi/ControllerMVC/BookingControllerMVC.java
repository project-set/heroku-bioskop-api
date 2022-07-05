package com.infosys.timd.bioskopapi.ControllerMVC;

import com.infosys.timd.bioskopapi.Controller.BookingController;
import com.infosys.timd.bioskopapi.Model.Booking;
import com.infosys.timd.bioskopapi.Model.Schedule;
import com.infosys.timd.bioskopapi.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookingControllerMVC {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking")
    public String showBooking(Model model){
        List<Booking> bookings = bookingService.getAll();
        model.addAttribute("bookings", bookings);

        return "bookings";
    }

    @GetMapping("/booking/new")
    public String showNewSchedule(Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("pageTitle", "Add New Schedules");

        return "booking_form";
    }

//    @PostMapping("/schedule/save")
//    public String saveBooking(Booking booking, RedirectAttributes ra){
//        bookingService.createBooking(Booking booking);
//        ra.addFlashAttribute("message", "Booking saved successfully");
//
//        return "redirect:/booking";
//    }
}
