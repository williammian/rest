package br.com.livrosonline.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import br.com.livrosonline.model.Estoque;
import br.com.livrosonline.model.Livro;
import br.com.livrosonline.model.LivroJaExisteException;
import br.com.livrosonline.model.rest.Livros;

@Path("/livros")
@Consumes({MediaType.TEXT_XML,MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Produces({MediaType.TEXT_XML,MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class LivroService {
	
	private static final int TAMANHO_PAGINA = 1;

	private static Estoque estoque = new Estoque();

	private static HashMap<Object, Object> EXTENSOES;
	
	static {
		EXTENSOES = new HashMap<>();
		EXTENSOES.put("image/png", ".png");
	}
	
	@GET
	public Livros listeTodosOsLivros(@QueryParam("pagina") int pagina) {

		List<Livro> livros = estoque.listarLivros(pagina, TAMANHO_PAGINA);

		return new Livros(livros);
	}
	
	@GET
	@Path("{nome}")
	public Livro encontreLivro(@PathParam("nome") String nomeDoLivro) {
		Livro livro = estoque.recuperarLivroPeloNome(nomeDoLivro);
		if (livro != null)
			return livro;

		throw new WebApplicationException(Status.NOT_FOUND);

	}
	
	@POST
	public Response criarLivro(Livro livro) {
		try {
			estoque.adicionarLivro(livro);
		}
		catch (LivroJaExisteException e) {
			throw new WebApplicationException(Status.CONFLICT);
		}

		URI uri = UriBuilder.fromPath("livros/{nome}").build(livro.getNome());

		return Response.created(uri).entity(livro).build();
	}
	
	@PUT
	@Path("{nome}")
	public void atualizarLivro(@PathParam("nome") String nome, Livro livro) {
		encontreLivro(nome);
		livro.setNome(nome);
		estoque.atualizarLivro(livro);
	}
	
	@DELETE
	@Path("{nome}")
	public void apagarLivro(@PathParam("nome") String nome) {
		estoque.apagarLivro(nome);
	}
	
	@GET
	@Path("{nome}")
	@Produces("image/*")
	public Response recuperaImagem(@PathParam("nome") String nomeDoLivro) throws IOException {
		InputStream is = LivroService.class.getResourceAsStream("/"
				+ nomeDoLivro + ".png");

		if (is == null)
			throw new WebApplicationException(Status.NOT_FOUND);

		byte[] dados = new byte[is.available()];
		is.read(dados);
		is.close();

		return Response.ok(dados).type("image/png").build();
	}
	
	@POST
	@Path("{nome}")
	@Consumes("image/*")
	public Response criaImagem(@PathParam("nome") String nomeDaImagem, @Context HttpServletRequest req, byte[] dados) throws IOException, InterruptedException {

		String userHome = System.getProperty("user.home");
		String mimeType = req.getContentType();
		FileOutputStream fos = new FileOutputStream(userHome
				+ java.io.File.separator + nomeDaImagem
				+ EXTENSOES.get(mimeType));

		fos.write(dados);
		fos.flush();
		fos.close();

		return Response.ok().build();
	}
	
}
