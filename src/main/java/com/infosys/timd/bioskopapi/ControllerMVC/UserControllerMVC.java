package com.infosys.timd.bioskopapi.ControllerMVC;

import com.infosys.timd.bioskopapi.Model.User;
import com.infosys.timd.bioskopapi.Repository.UserRepository;
import com.infosys.timd.bioskopapi.Service.UserServiceImplements;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserControllerMVC {

    private final UserServiceImplements userServiceImplements;
    private final UserRepository userRepository;

    @GetMapping("/users")
    public String getAllUser(Model model) {
        List<User> users = userServiceImplements.getAll();
        model.addAttribute("users", users);

        return "users";
    }

//    @PostMapping("/users")

}
