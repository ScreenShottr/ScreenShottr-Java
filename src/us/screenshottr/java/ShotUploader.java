package us.screenshottr.java;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class ShotUploader {

    public static final URL UPLOAD_URL;
    public static final String USER_AGENT = "ScreenShottr/" + ScreenShottr.VERSION;
    public static final String ERROR_RESPONSE = "https://www.screenshottr.us/error?type=error";
    public static final String INVALID_RESPONSE = "https://www.screenshottr.us/error?type=invalid";

    static {
        URL tempUrl = null;
        try {
            tempUrl = new URL("https://screenshottr.us/upload"); //?base64=true
        } catch (MalformedURLException ex) {
            ShotUtil.handleError(ex);
        }

        UPLOAD_URL = tempUrl;
    }

    public static String uploadScreenShot(BufferedImage image) {

        final byte[] bytes = getBytes(image);

        ScreenShottr.LOGGER.info("Uploading screenshot...");

        try {
            try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
                final HttpPost postRequest = new HttpPost(UPLOAD_URL.toURI());

                // Format multipart
                final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                builder.setBoundary("----BOUNDARYBOUNDARY----");
                builder.addBinaryBody("imagedata", bytes, ContentType.create("image/png"), "gyazo.com"); //builder.addBinaryBody("imagedata", bytes);

                final HttpEntity entity = builder.build();

                postRequest.setEntity(entity);

                try (final CloseableHttpResponse response = client.execute(postRequest)) {
                    if (response.getStatusLine().getStatusCode() != 200) {
                        throw new IOException("Error whilst uploading image: Reponse code is " + response.getStatusLine().getStatusCode());
                    }

                    final String responseString = readStream(response.getEntity().getContent());

                    ScreenShottr.LOGGER.info("URL: " + responseString);

                    return responseString;
                }
            }
        } catch (Throwable ex) {
            ShotUtil.handleError(ex);
        }

        ShotUtil.handleError(new IllegalStateException("Shouln't get here."));
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
