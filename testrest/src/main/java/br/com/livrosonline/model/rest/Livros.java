package br.com.livrosonline.model.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.livrosonline.model.Livro;

@XmlRootElement
public class Livros {

	private List<Livro> livros = new ArrayList<Livro>();
	
	public Livros() {
		
	}

	public Livros(List<Livro> livros) {
		super();
		this.livros = livros;
	}

	@XmlElement(name="livro")
	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
	
	
}
