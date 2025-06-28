package br.ufms.facom.progweb.avaliacao_filmes.series;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SeriesRepository extends CrudRepository<Series, Long> {
    long countByGenero(String genero);
    List<Series> findByGenero(String genero);
    List<Series> findByTituloContaining(String titulo);
    boolean existsByTitulo(String titulo);
    Series findById(long id);
    @Query("SELECT s FROM Series s JOIN s.avaliacoes a GROUP BY s.id ORDER BY AVG(a.nota) DESC LIMIT 3")
List<Series> findTop3ByOrderByMediaAvaliacoesDesc();
    
}

