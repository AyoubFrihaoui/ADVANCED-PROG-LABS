package exo1.with_builder.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/** SOLUTION tests (Q3) — reference tests the students write themselves. */
public class HttpRequestBuilderTest {

	@Test
	public void testValidPostBuild() {
		HttpRequest req = new HttpRequestBuilder()
				.method("POST")
				.url("/login")
				.host("api.example.com")
				.header("Content-Type", "application/json")
				.body("{\"user\":\"ada\"}")
				.timeout(3000)
				.build();
		assertEquals("POST", req.getMethod());
		assertEquals("/login", req.getUrl());
		assertEquals("api.example.com", req.getHost());
		assertEquals("application/json", req.getHeaders().get("Content-Type"));
		assertEquals("{\"user\":\"ada\"}", req.getBody());
		assertEquals(3000, req.getTimeoutMs());
		// defaults
		assertTrue(req.isKeepAlive());
		assertFalse(req.isFollowRedirects());
	}

	@Test
	public void testValidGetWithQuery() {
		HttpRequest req = new HttpRequestBuilder()
				.method("GET")
				.url("/users")
				.host("api.example.com")
				.query("limit", "10")
				.build();
		assertEquals("10", req.getQueryParams().get("limit"));
		assertNull(req.getBody());
	}

	@Test
	public void testMissingHost() {
		try {
			new HttpRequestBuilder().method("GET").url("/users").build();
			fail("expected IllegalStateException for missing host");
		} catch (IllegalStateException e) {
			// expected
		}
	}

	@Test
	public void testBodyForbiddenOnGet() {
		try {
			new HttpRequestBuilder()
					.method("GET").url("/users").host("h").body("nope").build();
			fail("expected IllegalStateException for body on GET");
		} catch (IllegalStateException e) {
			// expected
		}
	}

	@Test
	public void testKeepAliveAndFollowRedirectsIncompatible() {
		try {
			new HttpRequestBuilder()
					.method("POST").url("/login").host("h")
					.keepAlive(true).followRedirects(true).build();
			fail("expected IllegalStateException for keepAlive + followRedirects");
		} catch (IllegalStateException e) {
			// expected
		}
	}
}
