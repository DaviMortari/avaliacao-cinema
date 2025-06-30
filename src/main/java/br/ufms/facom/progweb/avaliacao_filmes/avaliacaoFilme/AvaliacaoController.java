package br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<AvaliacaoRequestDto> salvarAvaliacao(@RequestBody AvaliacaoRequestDto dto,
        @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        service.salvarAvaliacao(dto, username);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoRequestDto> alterarAvaliacao(@PathVariable long id ,@RequestBody AvaliacaoRequestDto dto, 
        @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        
        service.mudarAvaliacao(id, dto, username);
        
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping("/{id}")
    public void excluirAvaliacao(@PathVariable long id, 
        @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        
        service.excluirAvaliacao(id, username);
    }
}
