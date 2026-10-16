package exo1.with_builder.builder;

import java.util.Map;

class HttpRequest {
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