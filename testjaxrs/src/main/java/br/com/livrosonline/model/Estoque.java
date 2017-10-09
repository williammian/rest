package br.com.livrosonline.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estoque {

	private Map<String, Livro> livros = new HashMap<>();

	public Estoque() {
		Livro primeiroLivro = new Livro("Java 9", "Java 9: Interativo, Reativo e Modularizado", "Rodrigo Turini", "Casa do Código");
		Livro segundoLivro = new Livro("Rest", "Rest: Construa API's inteligentes de maneira simples", "Alexandre Saudate", "Casa do Código");
		this.livros.put("Java 9", primeiroLivro);
		this.livros.put("Rest", segundoLivro);
	}

	public List<Livro> listarLivros() {
		return new ArrayList<>(this.livros.values());
	}

	public List<Livro> listarLivros(int numeroPagina, int tamanhoPagina) {
		int indiceInicial = numeroPagina * tamanhoPagina;
		int indiceFinal = indiceInicial + tamanhoPagina;

		List<Livro> cervejas = listarLivros();
		
		if (cervejas.size() > indiceInicial) {
			if (cervejas.size() > indiceFinal) {
				cervejas = cervejas.subList(indiceInicial, indiceFinal);
			} else {
				cervejas = cervejas.subList(indiceInicial, cervejas.size());
			}
		} else {
			cervejas = new ArrayList<>();
		}
		return cervejas;
	}

	public void adicionarLivro(Livro livro) {
		if (this.livros.containsKey(livro.getNome())) {
			throw new LivroJaExisteException();
		}
		this.livros.put(livro.getNome(), livro);
	}

	public Livro recuperarLivroPeloNome(String nome) {
		return this.livros.get(nome);
	}

	public void apagarLivro(String nome) {
		this.livros.remove(nome);
	}

	public void atualizarLivro(Livro livro) {
		if (!this.livros.containsKey(livro.getNome())) {
			throw new LivroNaoEncontradoException();
		}
		livros.put(livro.getNome(), livro);
	}
	
}
