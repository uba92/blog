package it.epicode.blog.autore;

//commento di prova
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.blog.posts.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "autori")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Autore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    private Date dataDiNascita;
    private String email;
    private String avatar = "https://www.google.com/url?q=https://ui-avatars.com/api/?name%3DMario%2BRossi&sa=D&source=editors&ust=1739280781274328&usg=AOvVaw2k-Vv3EXcEL7hkqMKebxov";

    @OneToMany(mappedBy = "autore")
    @JsonIgnoreProperties("autore")
    private List<Post> posts;
}
