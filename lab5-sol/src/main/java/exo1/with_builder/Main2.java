package exo1.with_builder;

import exo1.with_builder.builder.HttpRequest;
import exo1.with_builder.builder.HttpRequestBuilder;

/**
 * SOLUTION (Q4) — the same two requests as Main1, rewritten with the builder.
 * Compare: named steps instead of positional arguments, no nulls, defaults
 * for the rest, invalid states rejected in build().
 */
public class Main2 {

	public static void main(String[] args) {
		HttpRequest get = new HttpRequestBuilder()
				.method("GET")
				.url("/users")
				.host("api.example.com")
				.query("limit", "10")
				.timeout(5000)
				.build();

		HttpRequest post = new HttpRequestBuilder()
				.method("POST")
				.url("/login")
				.host("api.example.com")
				.header("Content-Type", "application/json")
				.body("{\"user\":\"ada\",\"pass\":\"secret\"}")
				.timeout(5000)
				.build();

		System.out.println(get);
		System.out.println(post);
	}
}
