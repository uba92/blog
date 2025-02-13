package it.epicode.blog.posts;


import it.epicode.blog.autore.Autore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    @NotBlank(message = "Il campo categoria non può essere vuoto")
    private String categoria;

    @NotBlank(message = "Il campo titolo non può essere vuoto")
    private String titolo;
    private String cover;
    private String contenuto;

    @NotNull(message = "Il campo tempoDiLettura non può essere null")
    private int tempoDiLettura;

    @NotNull(message = "Il campo autoreId non può essere null")
    private Long autoreId;
}
