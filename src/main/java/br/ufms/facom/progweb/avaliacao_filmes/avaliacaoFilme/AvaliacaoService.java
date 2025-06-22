package br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufms.facom.progweb.avaliacao_filmes.filmes.FilmeRepository;
import br.ufms.facom.progweb.avaliacao_filmes.filmes.Filmes;
import br.ufms.facom.progweb.avaliacao_filmes.usuarios.Usuarios;
import br.ufms.facom.progweb.avaliacao_filmes.usuarios.UsuariosDto;
import br.ufms.facom.progweb.avaliacao_filmes.usuarios.UsuariosRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository repository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    // Services
    public Iterable<Avaliacao> listarTodasAvaliacoes() {
        return repository.findAll(); // retorna todas as avaliações (sem parametro)
    }

    public Iterable<Avaliacao> listarAvaliacoesPorFilme(long filmeId) {
        return repository.findAllByFilmeId(filmeId); // retorna avaliações de um filme específico
    }

    private AvaliacaoDto converterParaDto(Avaliacao avaliacao) {
        UsuariosDto usuarioDto = new UsuariosDto(
            avaliacao.getUsuario().getId(),
            avaliacao.getUsuario().getNome(),
            avaliacao.getUsuario().getEmail(),
            avaliacao.getUsuario().getIdade()
            );

        return new AvaliacaoDto(
            usuarioDto,
            avaliacao.getFilme().getId(),
            avaliacao.getNota(),
            avaliacao.getComentario()
        );
    }

    // public AvaliacaoDto encontrarAvaliacaoPorId(long id) {
    //     Avaliacao avaliacao = repository.findById(id);
    //     if (avaliacao == null) {
    //         throw new RuntimeException("Avaliação não encontrada com o ID: " + id);
    //     }

    //     // Ajuste o construtor conforme definido em AvaliacaoDto
    //     AvaliacaoDto avaliacaoDto = new AvaliacaoDto(
    //         avaliacao.getUsuarioId(),
    //         avaliacao.getFilme().getId(),
    //         avaliacao.getNota(),
    //         avaliacao.getComentario()
    //     );
    //     return avaliacaoDto;
    // }

    @Transactional(readOnly = true)
    public List<AvaliacaoDto> listarPorFilmeId(Long filmeId) {
        List<Avaliacao> avaliacoesDoFilme = repository.findAllByFilmeId(filmeId);

        return avaliacoesDoFilme.stream()
            .map(this::converterParaDto) // Usando um método helper para mais clareza
            .collect(Collectors.toList());
    }

    public void salvarAvaliacao(AvaliacaoRequestDto dto) {
        Filmes filme = filmeRepository.findById(dto.getFilmeId());
        if (filme == null) {
            throw new IllegalArgumentException("Filme não encontrado com o ID: " + dto.getFilmeId());
        }

        Usuarios usuario = usuariosRepository.findById(dto.getUsuarioId());
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado com o ID: " + dto.getUsuarioId());
        }

        Avaliacao novaAvaliacao = new Avaliacao(
            dto.getNota(),
            dto.getComentario(),
            LocalDateTime.now(),
            filme,
            usuario
        );

        repository.save(novaAvaliacao);
    }
}
