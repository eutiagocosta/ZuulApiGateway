package br.com.beblue.api.gateway.filters.pre;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;


import org.apache.commons.io.IOUtils;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;

import br.com.beblue.domain.Cryptography;
import br.com.beblue.util.Param;

public class DecryptionFilter {

	private static DecryptionFilter instance;
	private JsonObject json;
	private String body;

	public HttpServletRequest parse(HttpServletRequest request) {

		try {
			InputStream is = request.getInputStream();
			this.body = IOUtils.toString(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.json = new JsonParser().parse(body).getAsJsonObject();

		try {
			this.body = Cryptography.decrypt(
					new Param(json.get("param").getAsString(), 
							  json.get("param1").getAsString()), "src/main/resources/private.pem");
		} catch (Exception e) {
			ZuulException zuulException = new ZuulException("Erro ao decriptar o payload", 400,
					"Erro ao decriptar o payload");
			throw new ZuulRuntimeException(zuulException);
		}

		return modifyRequest(request, body);
	}

	private static HttpServletRequestWrapper modifyRequest(HttpServletRequest request, String body) {
		System.out.println("body: " + body);
		return new HttpServletRequestWrapper(request) {

			@Override
			public ServletInputStream getInputStream() {
				return new ServletInputStreamWrapper(body.getBytes());
			}

		};
	}

	public static DecryptionFilter getInstance() {
		if (instance == null)
			instance = new DecryptionFilter();
		return instance;
	}

}
