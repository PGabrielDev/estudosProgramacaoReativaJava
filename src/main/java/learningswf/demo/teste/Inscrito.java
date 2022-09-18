package learningswf.demo.teste;

public class Inscrito {
    
    private String name;
    private Canal canal = new Canal();
     

    public Inscrito() {
    
    }



    public Inscrito(String name) {
        this.name = name;
    }



    public String getName() {
        return this.name;
    }

    public void isncrever(Canal canal){
        this.canal = canal;
    }


    public void update(String title){
        System.out.println("Video novo no canal: " + title);
    }

}
