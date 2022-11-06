package com.ramiro.java17demo.handler;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class MyErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {

		return response.getStatusCode() != HttpStatus.OK;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
				
	}
	
	@Override
	public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
		throw new RuntimeException(
				String.format("Ocorreu o erro [StatusCode %d %s] na chamada [URL %S %s]."
						,response.getStatusCode().value()
						,response.getStatusText()
						,method.toString()
						,url.toString())
				);
	}

}
