package com.infosys.timd.bioskopapi.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmsResponseDTO {
    private Long filmId;
    private String name;
    private Integer isPlaying;

    @Override
    public String toString() {
        return "FilmsResponseDTO{" +
                "filmId=" + filmId +
                ", name='" + name + '\'' +
                ", isPlaying=" + isPlaying +
                '}';
    }
}
