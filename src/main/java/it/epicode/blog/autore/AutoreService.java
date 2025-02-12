package it.epicode.blog.autore;

import it.epicode.blog.posts.PostService;
import it.epicode.blog.responses.CreateResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutoreService {
    private final AutoreRepository autoreRepository;
    private final PostService postService;

    //metodo GET per tutti gli autori
    public List<AutoreResponse> findAll() {
        List<Autore> autori = autoreRepository.findAll();
        List<AutoreResponse> autoriResponse = new ArrayList<>();
        for (Autore autore : autori) {
            AutoreResponse autoreResponse = new AutoreResponse();
            BeanUtils.copyProperties(autore, autoreResponse);
            autoriResponse.add(autoreResponse);
        }
        return autoriResponse;
    }
    //metodo GET trova autore da Id
    public AutoreResponse findById(Long id){
        if (!autoreRepository.existsById(id)) {
            throw new EntityNotFoundException("Autore non trovato");
        }
        Autore autore = autoreRepository.findById(id).get();
        AutoreResponse autoreResponse = new AutoreResponse();
        BeanUtils.copyProperties(autore, autoreResponse);
        return autoreResponse;
    }

    // crea un nuovo autore
    public CreateResponse save(AutoreRequest request){
        if (autoreRepository.existsByNomeAndCognome(request.getNome(), request.getCognome())) {
            throw new EntityExistsException("Autore già esistente");

        }
        Autore autore = new Autore();
        BeanUtils.copyProperties(request, autore);
        autoreRepository.save(autore);
        return new CreateResponse(autore.getId());
    }

    //metodo PUT per modificare un autore
    public CreateResponse update(Long id, AutoreRequest request){
        Autore autore = autoreRepository.findById(id).get();
        BeanUtils.copyProperties(request, autore);
        autoreRepository.save(autore);
        return new CreateResponse(autore.getId());
    }

//    //metodo DELETE per eliminare un autore
    public void delete(Long id){
        Autore autore = autoreRepository.findById(id).get();
        autoreRepository.delete(autore);
    }

}
