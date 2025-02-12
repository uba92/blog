package it.epicode.blog.autore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoreRequest {
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    private String email;
    private String avatar;


}
