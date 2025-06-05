package br.ufms.facom.progweb.avaliacao_filmes.filmes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmes")
public class FilmesController {
    @Autowired
    private FilmesService service;

    @GetMapping("/info") 
    public Iterable<Filmes> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/contar")
    public long contarPorGenero(String genero) {
        return service.contarPorGenero(genero);
    }

    @GetMapping("/encontrar")
    public Filmes encontrarFilme(String titulo) {
        return service.encontrarFilme(titulo);
    }

    @PostMapping
    public ResponseEntity<Filmes> salvarFilme(@RequestBody Filmes filme) {
        service.salvarFilme(filme);
        return ResponseEntity.status(HttpStatus.CREATED).body(filme);
    }
}
