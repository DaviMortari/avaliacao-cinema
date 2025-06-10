package br.ufms.facom.progweb.avaliacao_filmes.series;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeriesService {
    @Autowired
    private SeriesRepository repository;

    public Iterable<Series> listarTodos() {
        return repository.findAll();
    }

    public long contarPorGenero(String genero) {
        return repository.countByGenero(genero);
    }

    public Series encontrarSerie(String titulo){
        return repository.findByTituloContaining(titulo).stream().findFirst().orElse(null);
    }

    public Series salvarSerie(SeriesDto dto) {
        Series newSerie = new Series(
            dto.getTitulo(),
            dto.getGenero(),
            dto.getDiretor(),
            dto.getAnoLancamento(),
            dto.getSinopse(),
            dto.getTemporadas()
        );

        if(repository.existsByTitulo(newSerie.getTitulo())) {
            throw new IllegalArgumentException("Série com título '" + newSerie.getTitulo() + "' já existe.");
        }

        return repository.save(newSerie);
    }
    
    public void excluirSerie(String id) {
        repository.deleteById(id);
    }
}
