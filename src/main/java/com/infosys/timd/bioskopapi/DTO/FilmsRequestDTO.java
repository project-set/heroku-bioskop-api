package com.infosys.timd.bioskopapi.DTO;

import com.infosys.timd.bioskopapi.DTO.*;
import com.infosys.timd.bioskopapi.Model.*;
import com.infosys.timd.bioskopapi.Exception.*;
import com.infosys.timd.bioskopapi.Response.*;
import com.infosys.timd.bioskopapi.Service.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmsRequestDTO {
    private Long ID_Film;
    private String name;
    private Integer isPlaying;

    public Films convertToEntity(){
        return Films.builder()
                .filmId(this.ID_Film)
                .name(this.name)
                .isPlaying(this.isPlaying)
                .build();
    }
}
