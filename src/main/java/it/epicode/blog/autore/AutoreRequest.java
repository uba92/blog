package it.epicode.blog.autore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoreRequest {

    @NotBlank(message = "Il campo nome non può essere vuoto")
    private String nome;

    @NotBlank(message = "Il campo cognome non può essere vuoto")
    private String cognome;

    @NotNull(message = "Data inserita non valida")
    private Date dataDiNascita;

    @NotBlank(message = "Il campo email non può essere vuoto")
    @Email(message = "Il campo deve contenere un indirizzo email valido")
    private String email;

    private String avatar;


}
