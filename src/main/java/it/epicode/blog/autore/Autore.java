package it.epicode.blog.autore;

import it.epicode.blog.posts.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
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
    private LocalDate dataDiNascita;
    private String email;
    private String avatar = "https://www.google.com/url?q=https://ui-avatars.com/api/?name%3DMario%2BRossi&sa=D&source=editors&ust=1739280781274328&usg=AOvVaw2k-Vv3EXcEL7hkqMKebxov";

    @OneToMany(mappedBy = "autore")
    private Set<Post> posts = new HashSet<>();
}
