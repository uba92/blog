package it.epicode.blog.posts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.blog.autore.Autore;
import it.epicode.blog.autore.AutoreResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String categoria;
    private String titolo;
    private int tempoDiLettura;
    private String nomeAutore;

}
