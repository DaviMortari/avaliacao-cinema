package br.ufms.facom.progweb.avaliacao_filmes.series;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/series")
public class SeriesController {

    @Autowired
    private SeriesService service;

    @GetMapping("/info") 
    public Iterable<Series> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/contar")
    public long contarPorGenero(String genero) {
        return service.contarPorGenero(genero);
    }

    @GetMapping("/encontrar")
    public Series encontrarFilme(String titulo) {
        return service.encontrarFilme(titulo);
    }

    @PostMapping
    public ResponseEntity<SeriesDto> salvarFilme(@RequestBody SeriesDto serie) {
        service.salvarFilme(serie);

        return ResponseEntity.status(HttpStatus.CREATED).body(serie);
    }
}