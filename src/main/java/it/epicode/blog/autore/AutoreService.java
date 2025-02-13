package it.epicode.blog.autore;

import it.epicode.blog.mail.EmailService;
import it.epicode.blog.posts.PostService;
import it.epicode.blog.responses.CreateResponse;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


@Service
@RequiredArgsConstructor
@Validated
public class AutoreService {
    private final AutoreRepository autoreRepository;
    private final PostService postService;
    private final EmailService emailService;

    //metodo GET per tutti gli autori
    public Page<Autore> findAll(Pageable pageable) {
        return autoreRepository.findAll(pageable);
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

    //metodo GET che trova autore con i suoi dettagli tramite il suo id
    @Transactional
    public AutoreDettaglioResponse findAutoreResponseById(Long id){
        if (!autoreRepository.existsById(id)) {
            throw new EntityExistsException("Autore non trovato!");
        }

        Autore autore = autoreRepository.findById(id).get();
        AutoreDettaglioResponse response = new AutoreDettaglioResponse();
        BeanUtils.copyProperties(autore, response);
        response.setPosts(autore.getPosts());
        return response;

    }

    // crea un nuovo autore
    public CreateResponse create(@Valid AutoreRequest request){
        if (autoreRepository.existsByNomeAndCognome(request.getNome(), request.getCognome())) {
            throw new EntityExistsException("Autore già esistente");

        }
        Autore autore = new Autore();
        BeanUtils.copyProperties(request, autore);
        autoreRepository.save(autore);

        //invio una mail alla creazioine di un autore
        try {
            emailService.sendEmail("u.tramontano92@libero.it", "AUTORE AGGIUNTO AL DB", "Autore " + autore.getNome() + " " + autore.getCognome() +" aggiunto al database!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

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
