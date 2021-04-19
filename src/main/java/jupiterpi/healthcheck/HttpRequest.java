package jupiterpi.healthcheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
    private String url;

    public HttpRequest(String url) {
        this.url = url;
    }

    public static class Response {
        public int responseCode;
        public String responseText;
    }

    public Response send() {
        try {
            Response response = new Response();

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();
            response.responseCode = status;

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            response.responseText = content.toString();

            connection.disconnect();

            return response;
        } catch (IOException e) {
            Response response = new Response();
            response.responseCode = 0;
            response.responseText = "";
            return response;
        }
    }
}