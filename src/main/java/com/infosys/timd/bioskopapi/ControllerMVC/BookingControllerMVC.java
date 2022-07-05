package com.infosys.timd.bioskopapi.ControllerMVC;

import com.infosys.timd.bioskopapi.Controller.BookingController;
import com.infosys.timd.bioskopapi.Model.Booking;
import com.infosys.timd.bioskopapi.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
