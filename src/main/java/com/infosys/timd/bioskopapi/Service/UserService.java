package com.infosys.timd.bioskopapi.Service;

import com.infosys.timd.bioskopapi.DTO.*;
import com.infosys.timd.bioskopapi.Model.*;
import com.infosys.timd.bioskopapi.Exception.*;
import com.infosys.timd.bioskopapi.Response.*;
import com.infosys.timd.bioskopapi.Service.*;
import com.infosys.timd.bioskopapi.Repository.*;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll();
    User createUser(User user);
    Optional<User> getUserById(Long users_Id);
    void deleteUserById(Long users_Id);
    User updateUser(User user) throws Exception;
    User getReferenceById(Long Id);
}
