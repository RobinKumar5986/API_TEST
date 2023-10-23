import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class Main {

    public static void main(String[] args) {
        try {
            String username = "APIUSER";
            String password = "Sail@123";

            String apiUrl = "http://www.sail-steel.com/qrcode/QRCode_FetchSet?sap-client=600";

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");

            // Enable input and output streams for the connection
            connection.setDoOutput(true);

            // Set the Authorization header with the Basic Authentication credentials
            String credentials = username + ":" + password;
            String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

            connection.setRequestProperty("Authorization", "Basic " + base64Credentials);
            connection.setRequestProperty("Accept", "application/json ");
            connection.setRequestProperty("X-Requested-With","X");

            System.out.println(base64Credentials);

            // Get the response code
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                System.out.println("Response: " + response.toString());
            } else {
                System.err.println("Request failed with response code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("Error : "+e.getMessage());
        }
    }
}
