package learningswf.demo.teste;

public class UsuarioComentario {
    private Usuario usuario;
    private Comentarios comentarios;
    

    public UsuarioComentario(Usuario usuario, Comentarios comentarios) {
        this.usuario = usuario;
        this.comentarios = comentarios;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Comentarios getComentarios() {
        return this.comentarios;
    }

    public void setComentarios(Comentarios comentarios) {
        this.comentarios = comentarios;
    }
    

    @Override
    public String toString() {
        return "{" +
            " usuario='" + getUsuario() + "'" +
            ", comentarios='" + getComentarios() + "'" +
            "}";
    }

}
