package br.com.livrosonline.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.livrosonline.model.Livro;
import br.com.livrosonline.model.rest.Livros;

public class Cliente {
	
	public static void main(String[] args) {
		
		List<Livro> livros = recuperarLivros();

		for (Livro livro : livros) {
			System.out.println("Nome: " + livro.getNome());
		}

		Livro livro = new Livro();
		livro.setNome("Spring");
		livro.setDescricao("Spring MVC");
		livro.setAutor("Roberval Antunes");
		livro.setEditora("Casa do Código");
		
		livro = criarLivro(livro);
		
		System.out.println("Novo livro: " + livro.getNome());
		
	}
	
	public static List<Livro> recuperarLivros() {

		Client client = ClientBuilder.newClient();

		return recuperarLivros(client.target(Constants.HOST));
	}
	
	private static List<Livro> recuperarLivros(WebTarget target) {
		Livros livros = target.path("livros").request(MediaType.APPLICATION_XML).get(Livros.class);

		List<Livro> livroList = new ArrayList<>();

		for (Livro livro : livros.getLivros()) {
			livroList.add(livro);
		}
		return livroList;
	}
	
	public static Livro criarLivro(Livro livro) {
		Client cliente = ClientBuilder.newClient();
		
		return criarLivro(cliente.target(Constants.HOST), livro);
	}
	
	private static Livro criarLivro(WebTarget target, Livro livro) {
		Response response = ClientBuilder.newClient().target(Constants.HOST).path("livros")
				.request(MediaType.APPLICATION_XML).post(Entity.xml(livro));
		
		if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {

			Link link = Link.fromUri(response.getLocation()).build();

			livro = ClientBuilder.newClient().invocation(link)
					.accept(MediaType.APPLICATION_XML).get(Livro.class);
			
			return livro;

		}
		throw new RuntimeException("Código retornado diferente do esperado");
	}

}
