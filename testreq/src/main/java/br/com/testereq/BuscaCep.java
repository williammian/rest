package br.com.testereq;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class BuscaCep {
	
	private final static String URL_BUSCA_CEP = "http://www.devmedia.com.br/devware/cep/service/";
	private final static String CHAVE_ACESSO = "72XUI2TMSG";
	
	public static void main(String[] args) {
		try{
			String cep = "13250000";
			String resultado = executarConsultaBuscaCepNaDevmediaEmJson(cep);
			System.out.println(resultado);
		}catch(Exception err){
			err.printStackTrace();
		}
	}
	
	/**
	 * Executa a consulta do CEP
	 * @param cep String CEP
	 * @return String
	 */
	private static String executarConsultaBuscaCepNaDevmediaEmJson(String cep)  {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(URL_BUSCA_CEP);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	
			String urlParameters = montarParametrosParaEnvio(cep);
			connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes("UTF-8").length));
			connection.setRequestProperty("Content-Language", "en-US");
			   
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
	
			// Send request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			// Executar
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer json = new StringBuffer();
		   
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		   
			return json.toString();
		}catch(IOException err) {
			throw new RuntimeException("Erro ao tentar conexão com o Web-Service dos Correios.", err);
		}finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
	/**
	 * Monta a URL para o envio ao servidor e poder realizar a busca do CEP
	 * @param cep String CEP
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
	private static String montarParametrosParaEnvio(String cep) throws UnsupportedEncodingException {
		StringBuilder urlParams = new StringBuilder();
		urlParams.append("cep=").append(URLEncoder.encode(cep, "UTF-8"));
		urlParams.append("&").append("chave=").append(URLEncoder.encode(CHAVE_ACESSO, "UTF-8"));
		urlParams.append("&").append("formato=").append(URLEncoder.encode("xml", "UTF-8"));
		return urlParams.toString();
	}

}
