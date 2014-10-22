package us.screenshottr.java;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class ShotUploader {

    public static final String USER_AGENT = "ScreenShottr/" + ScreenShottr.VERSION;
    @Deprecated
    public static final String RESPONSE_ERROR = "https://www.screenshottr.us/error?type=error";
    @Deprecated
    public static final String RESPONSE_INVALID = "https://www.screenshottr.us/error?type=invalid";

    public static JSONObject uploadScreenShot(BufferedImage image, URL url) {

        final byte[] bytes = getBytes(image);

        ScreenShottr.LOGGER.info("Uploading screenshot...");

        try {
            try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
                final HttpPost postRequest = new HttpPost(url.toURI());
                postRequest.setHeader(new BasicHeader("User-Agent", USER_AGENT));

                // Add multipart
                postRequest.setEntity(MultipartEntityBuilder
                        .create()
                        .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                        .addBinaryBody("imagedata", bytes, ContentType.create("image/png"), ShotURL.BASE)
                        .build());

                try (final CloseableHttpResponse response = client.execute(postRequest)) {
                    if (response.getStatusLine().getStatusCode() != 200) {
                        throw new IOException("Error whilst uploading image: Reponse code is " + response.getStatusLine().getStatusCode());
                    }

                    final String responseString = readStream(response.getEntity().getContent());

                    ScreenShottr.LOGGER.info("Response: " + responseString);
                    return (JSONObject) JSONValue.parse(responseString);
                }
            }
        } catch (Throwable ex) {
            ShotUtil.handleError(ex);
        }

        ShotUtil.handleError(new IllegalStateException("Shouldn't get here."));
        return null;
    }

    private static byte[] getBytes(BufferedImage image) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final byte[] bytes;

        try {
            ImageIO.write(image, "png", baos);
            baos.flush();
            bytes = baos.toByteArray();
            return bytes;
        } catch (IOException ex) {
            ShotUtil.handleError(ex);
            return null;
        } finally {
            try {
                baos.close();
            } catch (IOException ex) {
                ShotUtil.handleError(ex);
            }
        }
    }

    private static String readStream(InputStream stream) throws IOException {
        final StringBuilder response;

        try (final BufferedReader in = new BufferedReader(new InputStreamReader(stream))) {
            response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        return response.toString();
    }
}
