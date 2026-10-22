package exo1.with_builder.builder;

import java.util.HashMap;
import java.util.Map;

/**
 * SOLUTION (Q3) — fluent builder with centralized consistency checks.
 *
 * <p>Every setter returns {@code this} so calls chain. Defaults
 * ({@code keepAlive = true}, {@code followRedirects = false},
 * {@code timeoutMs = 5000}) mean the client only sets what matters.
 * {@code build()} is the single place where the object is validated and
 * created — impossible to forget, unlike the Q2 helper method.
 */
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
		this.method = method;
		return this;
	}

	public HttpRequestBuilder url(String url) {
		this.url = url;
		return this;
	}

	public HttpRequestBuilder host(String host) {
		this.host = host;
		return this;
	}

	public HttpRequestBuilder header(String key, String value) {
		this.headers.put(key, value);
		return this;
	}

	public HttpRequestBuilder query(String key, String value) {
		this.queryParams.put(key, value);
		return this;
	}

	public HttpRequestBuilder body(String body) {
		this.body = body;
		return this;
	}

	public HttpRequestBuilder keepAlive(boolean keepAlive) {
		this.keepAlive = keepAlive;
		return this;
	}

	public HttpRequestBuilder followRedirects(boolean followRedirects) {
		this.followRedirects = followRedirects;
		return this;
	}

	public HttpRequestBuilder timeout(int ms) {
		this.timeoutMs = ms;
		return this;
	}

	public HttpRequest build() {
		if (host == null || host.isEmpty()) {
			throw new IllegalStateException("host is required");
		}
		if ("GET".equalsIgnoreCase(method) && body != null) {
			throw new IllegalStateException("a GET request cannot have a body");
		}
		if (keepAlive && followRedirects) {
			throw new IllegalStateException("keepAlive and followRedirects cannot both be true");
		}
		return new HttpRequest(method, url, host, headers, queryParams,
				body, keepAlive, followRedirects, timeoutMs);
	}
}
