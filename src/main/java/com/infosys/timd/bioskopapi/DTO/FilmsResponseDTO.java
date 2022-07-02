package com.infosys.timd.bioskopapi.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmsResponseDTO {
    private Long ID_Film;
    private String namaFilmTayang;
    private Integer isPlaying;

    @Override
    public String toString() {
        return "FilmsResponseDTO{" +
                "ID_Film=" + ID_Film +
                ", namaFilmTayang='" + namaFilmTayang + '\'' +
                ", isPlaying=" + isPlaying +
                '}';
    }

    //    @Override
//    public String toString() {
//        return "FilmsResponseDTO{" +
//                "filmId=" + filmId +
//                ", name='" + name + '\'' +
//                ", isPlaying=" + isPlaying +
//                '}';
//    }
}
