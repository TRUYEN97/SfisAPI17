/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SfisAPI17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 *
 * @author Administrator
 */
public class SfisAPI {

    public static final String SEND_SFIS__EXCEPTION = "Send SFIS Exception : ";
    private String checkSfisUrl;
    private String sendResultUrl;

    public void setCheckSfisUrl(String checkSfisUrl) {
        this.checkSfisUrl = checkSfisUrl;
    }

    public void setSendResultUrl(String sendResultUrl) {
        this.sendResultUrl = sendResultUrl;
    }

    public String sendCheck(String command) {
        return sendToSFIS(checkSfisUrl, command);
    }

    public String sendResult(String command) {
        return sendToSFIS(sendResultUrl, command);
    }

    public String sendToSFIS(String surl, String command) {
        HttpURLConnection con = null;
        try {
            con = getHttpConncetion(surl);
            postCommand(con, command);
            return readReponse(con);
        } catch (IOException ex) {
            ex.printStackTrace();
            return SEND_SFIS__EXCEPTION + ex.getMessage();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    private HttpURLConnection getHttpConncetion(String url) throws ProtocolException, IOException {
        HttpURLConnection con;
        con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        return con;
    }

    private String readReponse(HttpURLConnection con) throws IOException {
        StringBuilder response;
        try ( BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        return response.toString();
    }

    private void postCommand(HttpURLConnection con, String command) throws IOException {
        try ( OutputStream os = con.getOutputStream()) {
            byte[] input = command.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
    }
}
