package br.ufms.facom.progweb.avaliacao_filmes.series;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufms.facom.progweb.avaliacao_filmes.filmes.Filmes;
import br.ufms.facom.progweb.avaliacao_filmes.filmes.FilmesCardDto;

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

    private SeriesCardDto converterParaCardDto(Series serie){
        SeriesCardDto dto = new SeriesCardDto();
        dto.setId(serie.getId());
        dto.setTitulo(serie.getTitulo());
        dto.setGenero(serie.getGenero());
        dto.setImagem(serie.getImagem());
        dto.setDiretor(serie.getDiretor());
        dto.setTemporadas(serie.getTemporadas());
        dto.setAnoLancamento(serie.getAnoLancamento());
        dto.setSinopse(serie.getSinopse());
        double mediaAvaliacoes = serie.getAvaliacoes().stream()
            .mapToDouble(avaliacao -> avaliacao.getNota())
            .average()
            .orElse(0.0);
        dto.setMediaAvaliacoes(mediaAvaliacoes);
        dto.setAvaliacoes(serie.getAvaliacoes());
        dto.setTipo(serie.getTipo());
        return dto;
    }

    public List<SeriesCardDto> buscarTodasAsSeries(){
        List<Series> series = (List<Series>) repository.findAll();
        return series.stream()
            .map(this::converterParaCardDto)
            .collect(Collectors.toList());
    }
}
