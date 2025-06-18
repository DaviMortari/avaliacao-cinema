package br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AvaliacaoRepository extends CrudRepository<Avaliacao, Long> {
    Avaliacao findById(long id);
    boolean existsById(long id);
    List<Avaliacao> findAllByFilmeId(long filmeId);
}
