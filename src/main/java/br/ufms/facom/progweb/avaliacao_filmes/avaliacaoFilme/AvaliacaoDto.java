package br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme;

import br.ufms.facom.progweb.avaliacao_filmes.usuarios.UsuariosDto;

public class AvaliacaoDto {
    private UsuariosDto usuario;
    private Long itemId;
    private double nota;
    private String comentario;

    public AvaliacaoDto() {}

    public AvaliacaoDto(UsuariosDto usuario, Long itemId, double nota, String comentario) {
        this.usuario = usuario;
        this.itemId = itemId;
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
        return itemId;
    }
    public void setFilmeId(Long itemId) {
        this.itemId = itemId;
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
