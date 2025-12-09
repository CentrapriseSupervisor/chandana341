/* AboutHandler.java */
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class AboutHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Only allow GET requests for the about page
        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }

        String response = getAboutPage();
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, bytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    private String getAboutPage() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>About</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                "            background: linear-gradient(135deg, #f6d365 0%, #fda085 100%);\n" +
                "            min-height: 100vh;\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "            align-items: center;\n" +
                "            padding: 20px;\n" +
                "            color: #333;\n" +
                "        }\n" +
                "        .card {\n" +
                "            background: white;\n" +
                "            border-radius: 12px;\n" +
                "            padding: 40px;\n" +
                "            max-width: 760px;\n" +
                "            box-shadow: 0 10px 40px rgba(0,0,0,0.15);\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        h1 {\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "        p {\n" +
                "            color: #555;\n" +
                "            line-height: 1.6;\n" +
                "        }\n" +
                "        a.button {\n" +
                "            display: inline-block;\n" +
                "            margin-top: 20px;\n" +
                "            background: #667eea;\n" +
                "            color: white;\n" +
                "            padding: 12px 28px;\n" +
                "            border-radius: 999px;\n" +
                "            text-decoration: none;\n" +
                "            font-weight: bold;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"card\">\n" +
                "        <h1>About This Server</h1>\n" +
                "        <p>This is a small demonstration web server built with Java's built-in HttpServer.\n" +
                "           It serves a simple home page and this About page. It's intended as a learning\n" +
                "           exercise and starting point for adding more routes and features.</p>\n" +
                "        <a class=\"button\" href=\"/\">Back to Home</a>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }
}