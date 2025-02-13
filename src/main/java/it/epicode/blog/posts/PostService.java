package it.epicode.blog.posts;

import it.epicode.blog.autore.Autore;
import it.epicode.blog.autore.AutoreRepository;
import it.epicode.blog.mail.EmailService;
import it.epicode.blog.responses.CreateResponse;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private AutoreRepository autoreRepository;
    @Autowired
    private EmailService emailService;

    //metodo GET per tutti gli autori
    public List<PostResponse> findAll(){
        List<PostResponse> posts = postResponseListFromEntityList(postRepository.findAll());
        return posts;

    }

    //metodo GET trova post da Id
    public Post findById(Long id){
        if (!postRepository.existsById(id)) {
            throw new EntityNotFoundException("Post non trovato");
        }
        return postRepository.findById(id).get();
    }

    // crea un nuovo post
    //invio una email di notifica all'inserimento di un post
    public CreateResponse create(PostRequest request){
        if (postRepository.existsByTitolo(request.getTitolo())) {
            throw new EntityExistsException("Post già esistente");
        }
        Autore autore = autoreRepository.findById(request.getAutoreId()).get();
        Post post = new Post();
        BeanUtils.copyProperties(request, post);
        post.setAutore(autore);
        postRepository.save(post);

        try {
            emailService.sendEmail("u.tramontano92@libero.it", "Test invio mail", "Email di prova dopo l'inserimento di un post " + post.getTitolo() + " di: " + post.getAutore().getNome() + " " + post.getAutore().getCognome());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


        return new CreateResponse(post.getId());
    }

    //metodo PUT per modificare un post
    public CreateResponse update(Long id, PostRequest request){
        Post post = findById(id);
        BeanUtils.copyProperties(request, post);
        postRepository.save(post);
        return new CreateResponse(post.getId());
    }

    //metodo DELETE per eliminare un post
    public void delete(Long id){
        Post post = findById(id);
        postRepository.delete(post);
    }

    public PostResponse postResponseFromEntity(Post post){
        PostResponse response = new PostResponse();
        BeanUtils.copyProperties(post, response);
        response.setNomeAutore(post.getAutore().getNome());
        return response;
    }

    public List<PostResponse> postResponseListFromEntityList(List<Post> posts) {
        return posts.stream().map(this::postResponseFromEntity).toList();
    }



}
