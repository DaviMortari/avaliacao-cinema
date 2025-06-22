package br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme;

import br.ufms.facom.progweb.avaliacao_filmes.usuarios.UsuariosDto;

public class AvaliacaoDto {
    private UsuariosDto usuario;
    private Long filmeId;
    private double nota;
    private String comentario;

    public AvaliacaoDto() {}

    public AvaliacaoDto(UsuariosDto usuario, Long filmeId, double nota, String comentario) {
        this.usuario = usuario;
        this.filmeId = filmeId;
        this.nota = nota;
        this.comentario = comentario;
    }

    public long getUsuarioId() {
        return usuario.getId();
    }
    public UsuariosDto getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuariosDto usuario) {
        this.usuario = usuario;
    }

    public long getFilmeId() {
        return filmeId;
    }
    public void setFilmeId(Long filmeId) {
        this.filmeId = filmeId;
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
}
