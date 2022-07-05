package com.infosys.timd.bioskopapi.ControllerMVC;

import com.infosys.timd.bioskopapi.Model.Booking;
import com.infosys.timd.bioskopapi.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class BookingControllerMVC {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking")
    public String showBooking(Model model) {
        List<Booking> booking = bookingService.getAll();
        model.addAttribute("booking", booking);

        return "booking";
    }
}
