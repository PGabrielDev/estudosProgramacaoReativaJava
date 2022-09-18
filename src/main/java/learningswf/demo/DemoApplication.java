package learningswf.demo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import learningswf.demo.teste.Comentarios;
import learningswf.demo.teste.Usuario;
import learningswf.demo.teste.UsuarioComentario;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	public void run(String... args) throws Exception {
		//exemploIterable();
		//exemploFlatMap();
		//exemploString();
		exemploComentarioUsuarioFlapMap();

	}
	public void exemploIterable() throws Exception {

		// Canal paulofifa = new Canal("Paulo Fifa");
		// Inscrito ins1 = new Inscrito("Debora");
		// ins1.isncrever(paulofifa);
		// Inscrito ins2 = new Inscrito("Paulino");
		// ins2.isncrever(paulofifa);
		// Inscrito ins3 = new Inscrito("Nicolle");
		// ins3.isncrever(paulofifa);
		// Inscrito ins4 = new Inscrito("Paula");
		// ins4.isncrever(paulofifa);
		// Inscrito ins5 = new Inscrito("Roberdan");
		// ins5.isncrever(paulofifa);
		// paulofifa.inscrever(ins1);
		// paulofifa.inscrever(ins2);
		// paulofifa.inscrever(ins3);
		// paulofifa.inscrever(ins4);
		// paulofifa.inscrever(ins5);
		// paulofifa.uploadVideo("Como bater Falta no fifa 22");
		List<String> nomesLi = new ArrayList<String>();
		nomesLi.add("Debora");
		nomesLi.add("Debora");
		nomesLi.add("Paulino");
		nomesLi.add("Roberdan");
		nomesLi.add("Paula");

		//Flux<String> nomes = Flux.just("Gabriel", "Debora","debora", "Josuel", "Paulino").map(n -> n.toUpperCase());

		Flux<String> nomes = Flux.fromIterable(nomesLi).map(n -> n.toUpperCase()).doOnNext(t-> {
			if(t.isEmpty()){
				throw new RuntimeException("Valores incorretos");
			}
		})
			.doOnComplete(new Runnable() {
				public void run () {
					System.out.println("Terminou tudo entrando no runnable");
				}
			});
		
		nomes.subscribe(n -> System.out.println(n.toString()), erro -> log.info(erro.getMessage()), () -> System.out.println("Terminou Tudo"));

		var novoFlux = nomes.map(n -> new Usuario(n, n.toLowerCase()))
			.filter(user -> user.getNome().equalsIgnoreCase("Debora"));
		novoFlux.subscribe(n -> System.out.println(n.toString()), erro -> log.info(erro.getMessage()), () -> System.out.println("Terminou Tudo"));
	}

	public void exemploFlatMap() { 
		List<String> nomesLi = new ArrayList<String>();
		nomesLi.add("Debora");
		nomesLi.add("Debora");
		nomesLi.add("Paulino");
		nomesLi.add("Roberdan");
		nomesLi.add("Paula");

		//Flux<String> nomes = Flux.just("Gabriel", "Debora","debora", "Josuel", "Paulino").map(n -> n.toUpperCase());

		Flux<String> nomes = Flux.fromIterable(nomesLi).map(n -> n.toUpperCase());


		nomes.subscribe(n -> System.out.println(n.toString()), erro -> log.info(erro.getMessage()), () -> System.out.println("Terminou Tudo"));
		var novoFlux = nomes.map(n -> new Usuario(n, n.toLowerCase()))
			.flatMap(user -> {
				if(user.getApelido().equalsIgnoreCase("debora")){
					return Mono.just(user);
				}
				return Mono.empty();
			});
		novoFlux.subscribe(n -> System.out.println(n.toString()), erro -> log.info(erro.getMessage()), () -> System.out.println("Terminou Tudo"));
	}

	public void exemploString(){
		List<Usuario> nomesLi = new ArrayList<>();
		nomesLi.add(new Usuario("Debora", "Santos"));
		nomesLi.add(new Usuario("Debora", "Santos"));
		nomesLi.add(new Usuario("Paulono", "Estevez"));
		nomesLi.add(new Usuario("Paulo", "Matias"));

		//Flux<String> nomes = Flux.just("Gabriel", "Debora","debora", "Josuel", "Paulino").map(n -> n.toUpperCase());

		Flux<String> nomes = Flux.fromIterable(nomesLi)
			.map(n -> n.getApelido().concat(n.getNome()))
			.flatMap(nome -> {
				if(nome.contains("Santos"))
					return Mono.just(nome);
			
				return Mono.empty();

			});
		
		nomes.subscribe(n -> System.out.println(n.toString()), erro -> log.info(erro.getMessage()), () -> System.out.println("Terminou Tudo"));
	}

	public void exemploComentarioUsuarioFlapMap(){
		Mono<Usuario> ususarioMono = Mono.fromCallable(() -> new Usuario("Paulo", "Gabriel"));

		Mono<Comentarios> comentariosMono = Mono.fromCallable(() -> {
			Comentarios comentiros = new Comentarios();
			comentiros.addComentario("Que bonita");
			comentiros.addComentario("Cara que legal essa fota");
			comentiros.addComentario("Parabens");
			return comentiros;
		});
		ususarioMono.
			flatMap(
				u -> comentariosMono
				.map(c -> new UsuarioComentario(u, c))
			)
			.subscribe(e -> log.info(e.toString()));
	}
}
