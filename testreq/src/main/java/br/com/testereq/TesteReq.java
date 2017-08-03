package br.com.testereq;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TesteReq {

	public static void main(String[] args) {
		try{
			URL url = new URL("http://localhost:8080/cap-05/cervejas");
			//URL url = new URL("http://localhost:8080/cap-05/cervejas/Stella+Artois");
			
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");
						
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer resultado = new StringBuffer();
			
			String linha;
			while ((linha = in.readLine()) != null){
				resultado.append(linha);
			}
			in.close();
			
			System.out.println(resultado);
			
		}catch (Exception err) {
			err.printStackTrace();
		}
	}
	
}
