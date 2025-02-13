package it.epicode.blog.autore;

import it.epicode.blog.responses.CreateResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autori")
@RequiredArgsConstructor
public class AutoreController {

    private final AutoreService autoreService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Autore> findAll(@ParameterObject Pageable pageable) {
        return autoreService.findAll(pageable);
    }

//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public AutoreResponse findById(@PathVariable Long id) {
//        return autoreService.findById(id);
//    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AutoreDettaglioResponse findAutoreResponseById(@PathVariable Long id) {
        return autoreService.findAutoreResponseById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponse save(@RequestBody AutoreRequest request) {
        return autoreService.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreateResponse update(@PathVariable Long id, @RequestBody AutoreRequest request) {
        return autoreService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        autoreService.delete(id);
    }
}
