package learningswf.demo.teste;

public class Usuario {
    private String nome;
    private String apelido;



    public Usuario(String nome, String apelido) {
        this.nome = nome;
        this.apelido = apelido;
    }
    


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return this.apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    @Override
    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            ", apelido='" + getApelido() + "'" +
            "}";
    }


}
