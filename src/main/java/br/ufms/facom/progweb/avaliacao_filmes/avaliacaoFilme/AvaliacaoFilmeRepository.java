package br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AvaliacaoFilmeRepository extends CrudRepository<AvaliacaoFilme, Long> {
    AvaliacaoFilme findById(long id);
    boolean existsById(long id);
    List<AvaliacaoFilme> findAllByFilmeId(long filmeId);
}
