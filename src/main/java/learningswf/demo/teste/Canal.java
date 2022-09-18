package learningswf.demo.teste;

import java.util.ArrayList;
import java.util.List;

public class Canal {
    
    private String nome;
    private List<String> videos = new ArrayList<>();
    private List<Inscrito> inscritos = new ArrayList<>();
    

    public Canal(String nome) {
        this.nome = nome;
    }
    public Canal() {
        
    }



    public void inscrever(Inscrito inscrito){
        this.inscritos.add(inscrito);
    }

    private void notificarInscritos(String titulo){
        for (Inscrito ins : this.inscritos){
            ins.update("olá " + ins.getName() + titulo);
        }
    }

    public void uploadVideo(String titulo){
        notificarInscritos(titulo);
    }
}
