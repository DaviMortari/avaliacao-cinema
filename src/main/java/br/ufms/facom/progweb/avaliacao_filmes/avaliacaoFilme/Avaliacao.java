package br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.ufms.facom.progweb.avaliacao_filmes.filmes.Filmes;
import br.ufms.facom.progweb.avaliacao_filmes.series.Series;
import br.ufms.facom.progweb.avaliacao_filmes.usuarios.Usuarios;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;

@Entity
public class Avaliacao implements Serializable{
    public enum TipoItemAvaliado {
        FILME,
        SERIE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private double nota;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "tipo_item_avaliado", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoItemAvaliado tipoItemAvaliado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filme_id", nullable = true)
    @JsonBackReference
    private Filmes filme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serie_id", nullable = true)
    @JsonBackReference
    private Series serie;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    // construtor para avaliação de filme
    public Avaliacao(double nota, String comentario, LocalDateTime data, Filmes filme, Usuarios usuario) {
        this.nota = nota;
        this.comentario = comentario;
        this.filme = filme;
        this.usuario = usuario;
        this.tipoItemAvaliado = TipoItemAvaliado.FILME; // Define o tipo como FILME
    }

    // construtor para avaliação de série
    public Avaliacao(double nota, String comentario, LocalDateTime data, Series serie, Usuarios usuario) {
        this.nota = nota;
        this.comentario = comentario;
        this.serie = serie;
        this.usuario = usuario;
        this.tipoItemAvaliado = TipoItemAvaliado.SERIE; // Define o tipo como SERIE
    }

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        // Validação para garantir consistência
        if (this.tipoItemAvaliado == TipoItemAvaliado.FILME && (this.filme == null || this.serie != null)) {
            throw new IllegalStateException("Avaliação de FILME deve ter 'filme' preenchido e 'serie' nulo.");
        }
        if (this.tipoItemAvaliado == TipoItemAvaliado.SERIE && (this.serie == null || this.filme != null)) {
            throw new IllegalStateException("Avaliação de SERIE deve ter 'serie' preenchido e 'filme' nulo.");
        }
    }

    public Avaliacao() {}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public double getNota() {
        return nota;
    }
    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Filmes getFilme() {
        return filme;
    }
    public void setFilme(Filmes filme) {
        this.filme = filme;
    }

    public Series getSerie() {
        return serie;
    }
    public void setSerie(Series serie) {
        this.serie = serie;
    }

    public Usuarios getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public TipoItemAvaliado getTipoItemAvaliado() {
        return tipoItemAvaliado;
    }
}
