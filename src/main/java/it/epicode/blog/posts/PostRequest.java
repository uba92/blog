package it.epicode.blog.posts;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private Time tempoDiLettura;
}
