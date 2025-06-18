package br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/avaliacoes")
public class AvaliacaoController {
    private AvaliacaoService service;

    @GetMapping("/listar")
    public Iterable<Avaliacao> listarAvaliacoesPorFilme(Long filmeId) {
        return service.listarAvaliacoesPorFilme(filmeId);
    }

    // Método para salvar uma avaliação
    @PostMapping()
    public void salvarAvaliacao(@RequestBody AvaliacaoDto dto) {
        service.salvarAvaliacao(dto);
    }
}
