package it.epicode.blog.posts;

import it.epicode.blog.responses.CreateResponse;
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

    //metodo GET per tutti gli autori
    public List<Post> findAll(){
        return postRepository.findAll();
    }

    //metodo GET trova post da Id
    public Post findById(Long id){
        if (!postRepository.existsById(id)) {
            throw new EntityNotFoundException("Post non trovato");
        }
        return postRepository.findById(id).get();
    }

    // crea un nuovo post
    public CreateResponse save(PostRequest request){
        if (postRepository.existsByTitolo(request.getTitolo())) {
            throw new EntityExistsException("Post già esistente");
        }
        Post post = new Post();
        BeanUtils.copyProperties(request, post);
        postRepository.save(post);
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
}
