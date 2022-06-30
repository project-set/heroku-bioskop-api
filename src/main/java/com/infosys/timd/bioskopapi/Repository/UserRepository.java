package com.infosys.timd.bioskopapi.Repository;

import com.infosys.timd.bioskopapi.DTO.*;
import com.infosys.timd.bioskopapi.Model.*;
import com.infosys.timd.bioskopapi.Exception.*;
import com.infosys.timd.bioskopapi.Response.*;
import com.infosys.timd.bioskopapi.Service.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}

