package exo1.with_builder.builder;

import java.util.Map;

/**
 * SOLUTION note: made public (was package-private like the without_builder
 * copy) so client code in other packages — e.g. Main2 — can name the type,
 * as the handout's Q4 snippet does. Alternative valid answer: keep it
 * package-private and put Main2 in the builder package.
 */
public class HttpRequest {
	private String method;
	private String url;
	private String host;
	private Map<String, String> headers;
	private Map<String, String> queryParams;
	private String body;
	private boolean keepAlive;
	private boolean followRedirects;
	private int timeoutMs;

	public HttpRequest(String method, String url, String host, Map<String, String> headers,
			Map<String, String> queryParams, String body, boolean keepAlive, boolean followRedirects, int timeoutMs) {
		this.method = method;
		this.url = url;
		this.host = host;
		this.headers = headers;
		this.queryParams = queryParams;
		this.body = body;
		this.keepAlive = keepAlive;
		this.followRedirects = followRedirects;
		this.timeoutMs = timeoutMs;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @return the headers
	 */
	public Map<String, String> getHeaders() {
		return headers;
	}

	/**
	 * @return the queryParams
	 */
	public Map<String, String> getQueryParams() {
		return queryParams;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @return the keepAlive
	 */
	public boolean isKeepAlive() {
		return keepAlive;
	}

	/**
	 * @return the followRedirects
	 */
	public boolean isFollowRedirects() {
		return followRedirects;
	}

	/**
	 * @return the timeoutMs
	 */
	public int getTimeoutMs() {
		return timeoutMs;
	}

	@Override
	public String toString() {
	    return "HttpRequest {\n" +
	            "  method='" + method + "',\n" +
	            "  url='" + url + "',\n" +
	            "  host='" + host + "',\n" +
	            "  headers=" + headers + ",\n" +
	            "  queryParams=" + queryParams + ",\n" +
	            "  body='" + body + "',\n" +
	            "  keepAlive=" + keepAlive + ",\n" +
	            "  followRedirects=" + followRedirects + ",\n" +
	            "  timeoutMs=" + timeoutMs + "\n" +
	            "}";
	}
}
