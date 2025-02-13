package it.epicode.blog.autore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.blog.posts.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoreDettaglioResponse {
    private Long id;
    private String nome;
    private String cognome;
    private Date dataDiNascita;
    private String email;
    private String avatar;
    @JsonIgnoreProperties("autore")
    private List<Post> posts;
}
