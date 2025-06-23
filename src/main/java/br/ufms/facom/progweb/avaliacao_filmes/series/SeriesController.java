package br.ufms.facom.progweb.avaliacao_filmes.series;

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
@RequestMapping("/api/series")
public class SeriesController {

    @Autowired
    private SeriesService service;

    @GetMapping("/listar")
    public List<SeriesCardDto> listarTodos() {
        return service.buscarTodasAsSeries();
    }

    //@GetMapping("/info") 
    //public Iterable<Series> listarTodos() {
     //   return service.listarTodos();
    //}

    @GetMapping("/contar")
    public long contarPorGenero(String genero) {
        return service.contarPorGenero(genero);
    }

    @GetMapping("/encontrar")
    public Series encontrarFilme(String titulo) {
        return service.encontrarSerie(titulo);
    }

    @PostMapping
    public ResponseEntity<SeriesDto> salvarFilme(@RequestBody SeriesDto serie) {
        service.salvarSerie(serie);

        return ResponseEntity.status(HttpStatus.CREATED).body(serie);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirSerie(String id) {
        var serie = service.encontrarSerie(id);
        if (serie != null) {
            this.service.excluirSerie(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}