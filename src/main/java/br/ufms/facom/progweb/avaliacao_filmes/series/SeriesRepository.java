package br.ufms.facom.progweb.avaliacao_filmes.series;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SeriesRepository extends CrudRepository<Series, String> {
    long countByGenero(String genero);
    List<Series> findByGenero(String genero);
    List<Series> findByTituloContaining(String titulo);
    boolean existsByTitulo(String titulo);
    
}

