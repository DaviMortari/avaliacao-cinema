package br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufms.facom.progweb.avaliacao_filmes.filmes.FilmeRepository;
import br.ufms.facom.progweb.avaliacao_filmes.filmes.Filmes;
import br.ufms.facom.progweb.avaliacao_filmes.series.Series;
import br.ufms.facom.progweb.avaliacao_filmes.series.SeriesRepository;
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

    @Autowired
    private SeriesRepository seriesRepository;

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
        if(avaliacao.getTipoItemAvaliado().toString() == "FILME") {
            return new AvaliacaoDto(
                usuarioDto,
                avaliacao.getFilme().getId(),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getId()
            );
        }
        else{
            return new AvaliacaoDto(
                usuarioDto,
                avaliacao.getSerie().getId(),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getId()
            );
        }
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

    @Transactional(readOnly = true)
    public List<AvaliacaoDto> listarPorSerieId(Long serieId) {
        List<Avaliacao> avaliacoesDaSerie = repository.findAllBySerieId(serieId);

        return avaliacoesDaSerie.stream()
            .map(this::converterParaDto) // Usando um método helper para mais clareza
            .collect(Collectors.toList());
    }

    public void salvarAvaliacao(AvaliacaoRequestDto dto, String username) {
        Usuarios usuario = usuariosRepository.findByEmail(username);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado com o Email: " + username);
        }

        if(repository.existsByUsuario_IdAndFilme_Id(usuario.getId(), dto.getFilmeId()) && "FILME".equals(dto.getTipoItemAvaliado())) {
            // throw new IllegalArgumentException("Usuário já avaliou este filme.");
            Avaliacao avaliacaoId = repository.findIdByFilme_idAndUsuario_Email(dto.getFilmeId(), usuario.getEmail());
            mudarAvaliacao(avaliacaoId.getId(), dto, usuario.getEmail());
        }

        else if(repository.existsByUsuario_IdAndSerie_Id(usuario.getId(), dto.getSerieId()) && "SERIE".equals(dto.getTipoItemAvaliado())) {
            // throw new IllegalArgumentException("Usuário já avaliou esta série.");
            Avaliacao avaliacaoId = repository.findIdBySerie_idAndUsuario_Email(dto.getSerieId(), usuario.getEmail());
            mudarAvaliacao(avaliacaoId.getId(), dto, usuario.getEmail());
        }

        else{
            Avaliacao novaAvaliacao;

            if("FILME".equals(dto.getTipoItemAvaliado())){
                
                Filmes filme = filmeRepository.findById(dto.getFilmeId());
                if (filme == null) {
                    throw new IllegalArgumentException("Filme não encontrado com o ID: " + dto.getFilmeId());
                }

                novaAvaliacao = new Avaliacao(
                    dto.getNota(),
                    dto.getComentario(),
                    LocalDateTime.now(),
                    filme,
                    usuario
                );
            }
            else{
                Series serie = seriesRepository.findById(dto.getSerieId());
                if(serie == null) {
                    throw new IllegalArgumentException("Série não encontrada com o ID: " + dto.getSerieId());
                }

                novaAvaliacao = new Avaliacao(
                    dto.getNota(),
                    dto.getComentario(),
                    LocalDateTime.now(),
                    serie,
                    usuario
                );
            }
            

            repository.save(novaAvaliacao);
        }  
    }

    public void excluirAvaliacao(long id, String username) {
        Avaliacao avaliacao = repository.findById(id);
        if (avaliacao == null) {
            throw new IllegalArgumentException("Avaliação não encontrada com o ID: " + id);
        }

        Usuarios usuario = usuariosRepository.findByEmail(username);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado com o Email: " + username);
        }

        if(avaliacao.getUsuario().getId().equals(usuario.getId()) || "ADMIN".equals(usuario.getTipoUsuario().toString())) {
            repository.delete(avaliacao);
        } else {
            throw new SecurityException("Usuário não tem permissão para excluir esta avaliação.");
        }
    }

    public void mudarAvaliacao(long id, AvaliacaoRequestDto dto, String username) {
        Avaliacao avaliacao = repository.findById(id);
        if (avaliacao == null) {
            throw new IllegalArgumentException("Avaliação não encontrada com o ID: " + id);
        }

        Usuarios usuario = usuariosRepository.findByEmail(username);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado com o Email: " + username);
        }

        if(avaliacao.getUsuario().getId().equals(usuario.getId())) {
            avaliacao.setNota(dto.getNota());
            avaliacao.setComentario(dto.getComentario());
            repository.save(avaliacao);
        } else {
            throw new SecurityException("Usuário não tem permissão para alterar esta avaliação.");
        }
    }
}
