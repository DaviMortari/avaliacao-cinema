package br.ufms.facom.progweb.avaliacao_filmes.filmes;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FilmeRepository extends CrudRepository<Filmes, Long> {
    Filmes findById(long id);
    long countByGenero(String genero);
    List<Filmes> findByGenero(String genero);
    List<Filmes> findByTituloContaining(String titulo);
    boolean existsByTitulo(String titulo);
    
    // Métodos de consulta personalizados podem ser definidos aqui
    @Query("SELECT f FROM Filmes f JOIN f.avaliacoes a GROUP BY f.id ORDER BY AVG(a.nota) DESC LIMIT 3")
    List<Filmes> findTop3ByOrderByMediaAvaliacoesDesc();

    
    // Outros métodos de manipulação de dados podem ser adicionados conforme necessário
    
}
