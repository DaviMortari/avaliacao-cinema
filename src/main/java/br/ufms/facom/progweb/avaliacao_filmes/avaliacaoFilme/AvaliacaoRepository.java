package br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AvaliacaoRepository extends CrudRepository<Avaliacao, Long> {
    Avaliacao findById(long id);
    boolean existsById(long id);
    List<Avaliacao> findAllByFilmeId(@Param("filmeId") long filmeId);
    List<Avaliacao> findAllBySerieId(@Param("serieId") long serieId);
    boolean existsByUsuario_IdAndFilme_Id(@Param("usuarioId") long usuarioId, @Param("filmeId") long filmeId);
    boolean existsByUsuario_IdAndSerie_Id(@Param("usuarioId") long usuarioId, @Param("serieId") long serieId);
    Avaliacao findIdByFilme_idAndUsuario_Email(@Param("filmeId") long filmeId, @Param("email") String email);
    Avaliacao findIdBySerie_idAndUsuario_Email(@Param("serieId") long serieId, @Param("email") String email);
}
