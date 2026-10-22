package exo1.without_builder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * SOLUTION (Q1 + Q2) — the two requests built with the 9-argument constructor.
 *
 * <p>Readability verdict (Q1, to discuss in class): positional arguments force
 * the reader to count parameters; {@code null}s hide which options are simply
 * "not used"; the two booleans at the end ({@code true, false}) are
 * meaningless without the parameter names; swapping two same-typed arguments
 * (e.g. url/host) compiles fine and fails at runtime.
 */
public class Main1 {

	public static void main(String[] args) {
		// GET /users?limit=10 — no body (null), host mandatory.
		Map<String, String> query = new HashMap<>();
		query.put("limit", "10");
		HttpRequest get = new HttpRequest("GET", "/users", "api.example.com",
				Collections.emptyMap(), query, null, true, false, 5000);
		valider(get);

		// POST /login with a JSON body.
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		HttpRequest post = new HttpRequest("POST", "/login", "api.example.com",
				headers, Collections.emptyMap(),
				"{\"user\":\"ada\",\"pass\":\"secret\"}", true, false, 5000);
		valider(post);

		System.out.println(get);
		System.out.println(post);
	}

	/**
	 * SOLUTION (Q2) — consistency rules WITHOUT touching HttpRequest.
	 * Every client must remember to call this, and every new rule means
	 * editing every client: this is exactly why it does not scale, and why
	 * Q3 moves the checks into the builder.
	 */
	static void valider(HttpRequest req) {
		if (req.getHost() == null || req.getHost().isEmpty()) {
			throw new IllegalArgumentException("a request must have a host");
		}
		if ("GET".equalsIgnoreCase(req.getMethod()) && req.getBody() != null) {
			throw new IllegalArgumentException("a GET request cannot have a body");
		}
		if (req.isKeepAlive() && req.isFollowRedirects()) {
			throw new IllegalArgumentException("keepAlive and followRedirects are incompatible");
		}
	}
}
