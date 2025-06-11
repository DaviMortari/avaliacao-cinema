package br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme;

import br.ufms.facom.progweb.avaliacao_filmes.filmes.Filmes;
import br.ufms.facom.progweb.avaliacao_filmes.usuarios.Usuarios;

public class AvaliacaoFilmeDto {
    private Usuarios usuario;
    private Filmes filme;
    private int avaliacao;
    private String comentario;

    public AvaliacaoFilmeDto() {}

    public AvaliacaoFilmeDto(Usuarios usuario, Filmes filme, int avaliacao, String comentario) {
        this.usuario = usuario;
        this.filme = filme;
        this.avaliacao = avaliacao;
        this.comentario = comentario;
    }

    public long getUsuarioId() {
        return usuario.getId();
    }

    public long getFilmeId() {
        return filme.getId();
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public String getComentario() {
        return comentario;
    }
}
