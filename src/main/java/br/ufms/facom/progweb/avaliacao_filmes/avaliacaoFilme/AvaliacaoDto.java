package br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme;

import br.ufms.facom.progweb.avaliacao_filmes.usuarios.Usuarios;

public class AvaliacaoDto {
    private Usuarios usuario;
    private Long filmeId;
    private int nota;
    private String comentario;

    public AvaliacaoDto() {}

    public AvaliacaoDto(Usuarios usuario, Long filmeId, int nota, String comentario) {
        this.usuario = usuario;
        this.filmeId = filmeId;
        this.nota = nota;
        this.comentario = comentario;
    }

    public long getUsuarioId() {
        return usuario.getId();
    }
    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public long getFilmeId() {
        return filmeId;
    }
    public void setFilmeId(Long filmeId) {
        this.filmeId = filmeId;
    }

    public int getNota() {
        return nota;
    }
    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
