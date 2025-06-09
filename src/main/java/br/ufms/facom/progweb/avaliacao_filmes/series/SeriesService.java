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

    public Series encontrarFilme(String titulo){
        return repository.findByTituloContaining(titulo).stream().findFirst().orElse(null);
    }

    public Series salvarFilme(SeriesDto dto) {
        Series newSerie = new Series(
            dto.getTitulo(),
            dto.getGenero(),
            dto.getDiretor(),
            dto.getAnoLancamento(),
            dto.getSinopse(),
            dto.getTemporadas()
        );
        return repository.save(newSerie);
    }
    
    public void excluirFilme(String id) {
        repository.deleteById(id);
    }
}
