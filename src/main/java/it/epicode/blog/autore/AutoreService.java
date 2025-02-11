package it.epicode.blog.autore;

import it.epicode.blog.responses.CreateResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class AutoreService {
    private final AutoreRepository autoreRepository;

    //metodo GET per tutti gli autori
    public List<Autore> findAll(){
        return autoreRepository.findAll();
    }

    //metodo GET trova autore da Id
    public Autore findById(Long id){
        if (!autoreRepository.existsById(id)) {
            throw new EntityNotFoundException("Autore non trovato");
        }
        return autoreRepository.findById(id).get();
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
        Autore autore = findById(id);
        BeanUtils.copyProperties(request, autore);
        autoreRepository.save(autore);
        return new CreateResponse(autore.getId());
    }

    //metodo DELETE per eliminare un autore
    public void delete(Long id){
        Autore autore = findById(id);
        autoreRepository.delete(autore);
    }
}
