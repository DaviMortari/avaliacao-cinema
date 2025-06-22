package br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService service;

    @GetMapping("/listar")
    public Iterable<Avaliacao> listarAvaliacoesPorFilme(Long filmeId) {
        return service.listarAvaliacoesPorFilme(filmeId);
    }

    // Método para salvar uma avaliação
    @PostMapping()
    public ResponseEntity<AvaliacaoRequestDto> salvarAvaliacao(@RequestBody AvaliacaoRequestDto dto) {
        service.salvarAvaliacao(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
