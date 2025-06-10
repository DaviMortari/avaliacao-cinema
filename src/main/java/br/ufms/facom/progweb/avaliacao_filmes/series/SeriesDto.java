package br.ufms.facom.progweb.avaliacao_filmes.series;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SeriesDto {
    @NotBlank(message = "O título da série não pode estar vazio.")
    private String titulo;

    @NotBlank(message = "O gênero da série não pode estar vazio.")
    private String genero;

    @NotBlank(message = "O diretor da série não pode estar vazio.")
    private String diretor;

    @NotNull
    @Positive(message = "O ano de lançamento deve ser um número positivo.")
    private int anoLancamento;

    @NotBlank(message = "A sinopse da série não pode estar vazia.")
    private String sinopse;

    @NotNull
    @Positive(message = "O número de temporadas deve ser um número positivo.")
    private int temporadas;

    public SeriesDto() {}

    public SeriesDto(String titulo, String genero, String diretor, int anoLancamento, String sinopse, int temporadas) {
        this.titulo = titulo;
        this.genero = genero;
        this.diretor = diretor;
        this.anoLancamento = anoLancamento;
        this.sinopse = sinopse;
        this.temporadas = temporadas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }
}
