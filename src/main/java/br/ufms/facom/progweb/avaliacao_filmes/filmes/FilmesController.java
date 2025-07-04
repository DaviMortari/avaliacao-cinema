package br.ufms.facom.progweb.avaliacao_filmes.filmes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/filmes")
public class FilmesController {

    @Autowired
    private FilmesService service;


    @GetMapping("/listar")
    public List<FilmesCardDto> listarTodos() {
        return service.buscarTodosOsFilmes();
    }
    //@GetMapping("/info") 
    //public Iterable<Filmes> listarTodos() {
    //    return service.listarTodos();
    //}

    @GetMapping("/contar")
    public long contarPorGenero(String genero) {
        return service.contarPorGenero(genero);
    }

    @GetMapping("/encontrar")
    public Filmes encontrarFilme(String titulo) {
        return service.encontrarFilme(titulo);
    }

    @PostMapping
    public ResponseEntity<FilmesDto> salvarFilme(@RequestBody FilmesDto filme) {
        service.salvarFilme(filme);

        return ResponseEntity.status(HttpStatus.CREATED).body(filme);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirFilme(long id) {
        var filme = service.encontrarFilmePorId(id);
        if (filme != null) {
            this.service.excluirFilme(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
