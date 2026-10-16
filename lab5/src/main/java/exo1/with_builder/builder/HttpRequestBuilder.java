package exo1.with_builder.builder;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestBuilder {
	private String method;
	private String url;
	private String host;
	private Map<String, String> headers = new HashMap<>();
	private Map<String, String> queryParams = new HashMap<>();
	private String body;
	private boolean keepAlive = true;
	private boolean followRedirects = false;
	private int timeoutMs = 5000;

	public HttpRequestBuilder method(String method) {
		return null;
	}

	public HttpRequestBuilder url(String url) {
		return null;
	}

	public HttpRequestBuilder host(String host) {
		return null;
	}

	public HttpRequestBuilder header(String key, String value) {
		return null;
	}

	public HttpRequestBuilder query(String key, String value) {
		return null;
	}

	public HttpRequestBuilder body(String body) {
		return null;
	}

	public HttpRequestBuilder timeout(int ms) {
		return null;
	}

	public HttpRequest build() {
		return null;
		// TODO: validations + final construction
	}
}