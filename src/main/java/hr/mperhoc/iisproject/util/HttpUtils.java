package hr.mperhoc.iisproject.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpUtils {
	private static HttpClient client;

	public static enum RequestType {
		GET, POST, PUT, DELETE;
	}

	private HttpUtils() {
	}

	public static void init() {
		client = HttpClient.newHttpClient();
	}

	public static HttpResponse<String> sendRequest(String url, String body, RequestType type, String... headers) {
		try {
			HttpRequest request;
			switch (type) {
			case DELETE:
				request = HttpRequest.newBuilder().uri(new URI(url)).headers(headers).DELETE().build();
				break;
			case GET:
				request = HttpRequest.newBuilder().uri(new URI(url)).headers(headers).GET().build();
				break;
			case POST:
				request = HttpRequest.newBuilder().uri(new URI(url)).headers(headers)
						.POST(HttpRequest.BodyPublishers.ofString(body)).build();
				break;
			case PUT:
				request = HttpRequest.newBuilder().uri(new URI(url)).headers(headers)
						.PUT(HttpRequest.BodyPublishers.ofString(body)).build();
				break;
			default:
				request = null;
				break;
			}

			if (request == null) return null; // Kinda stupid but prevents NullPointerException
			return client.send(request, BodyHandlers.ofString());
		} catch (URISyntaxException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static HttpResponse<String> sendGetRequest(String url, String... headers) {
		return sendRequest(url, "", RequestType.GET, headers);
	}

	public static HttpResponse<String> callSOAPServiceWebMethod(String url, String envelope) {
		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).headers("Content-Type", "text/xml")
					.POST(HttpRequest.BodyPublishers.ofString(envelope)).build();

			return client.send(request, BodyHandlers.ofString());
		} catch (URISyntaxException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
