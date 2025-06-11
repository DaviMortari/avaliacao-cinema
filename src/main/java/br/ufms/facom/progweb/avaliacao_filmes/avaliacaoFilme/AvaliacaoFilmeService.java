package br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufms.facom.progweb.avaliacao_filmes.filmes.FilmeRepository;
import br.ufms.facom.progweb.avaliacao_filmes.filmes.Filmes;
import br.ufms.facom.progweb.avaliacao_filmes.usuarios.Usuarios;
import br.ufms.facom.progweb.avaliacao_filmes.usuarios.UsuariosRepository;

@Service
public class AvaliacaoFilmeService {

    @Autowired
    private AvaliacaoFilmeRepository repository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    // Services
    public Iterable<AvaliacaoFilme> listarTodasAvaliacoes() {
        return repository.findAll(); // retorna todas as avaliações (sem parametro)
    }

    public Iterable<AvaliacaoFilme> listarAvaliacoesPorFilme(long filmeId) {
        return repository.findAllByFilmeId(filmeId); // retorna avaliações de um filme específico
    }

    public AvaliacaoFilme encontrarAvaliacaoPorId(long id) {
        AvaliacaoFilme avaliacao = repository.findById(id);
        if (avaliacao == null) {
            throw new RuntimeException("Avaliação não encontrada com o ID: " + id);
        }
        return avaliacao;
    }

    public void salvarAvaliacao(AvaliacaoFilmeDto dto) {
        Filmes filme = filmeRepository.findById(dto.getFilmeId());
        if (filme == null) {
            throw new IllegalArgumentException("Filme não encontrado com o ID: " + dto.getFilmeId());
        }

        Usuarios usuario = usuariosRepository.findById(dto.getUsuarioId());
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado com o ID: " + dto.getUsuarioId());
        }

        AvaliacaoFilme novaAvaliacao = new AvaliacaoFilme(
            dto.getAvaliacao(),
            dto.getComentario(),
            LocalDateTime.now(),
            filme,
            usuario
        );

        repository.save(novaAvaliacao);
    }
}
