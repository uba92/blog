package it.epicode.blog.autore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoreResponse {
    private Long id;
    private String nome;
    private String cognome;
}
