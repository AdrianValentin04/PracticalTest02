package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.xml.transform.stream.StreamSource;

public class ClientThread extends Thread {

    private final String address;
    private final int port;

    private final String value = "";
    private final String currency = "";

    private final TextView bitcoinInfoTextView = null;

    private String bitcoinInfo;
    private Socket socket;

    public ClientThread(String address, int port, String city, String informationType) {
        this.address = address;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            // tries to establish a socket connection to the server
            socket = new Socket(address, port);

            // gets the reader and writer for the socket
            BufferedReader bufferedReader = Utilities.getReader(socket);
            PrintWriter printWriter = Utilities.getWriter(socket);

            printWriter.println(value);
            printWriter.flush();
            printWriter.println(currency);
            printWriter.flush();

            bitcoinInfoTextView.post(() -> bitcoinInfoTextView.setText(value));

            bitcoinInfo = bufferedReader.readLine();
            final String finalizedBitcoinInfo = bitcoinInfo;

            // updates the UI with the weather information. This is done using postt() method to ensure it is executed on UI thread
            bitcoinInfoTextView.post(() -> bitcoinInfoTextView.setText(finalizedBitcoinInfo));

        } // if an exception occurs, it is logged
        catch (IOException ioException) {
            Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
            if (Constants.DEBUG) {
                ioException.printStackTrace();
            }
        } finally {
            if (socket != null) {
                try {
                    // closes the socket regardless of errors or not
                    socket.close();
                } catch (IOException ioException) {
                    Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
                    if (Constants.DEBUG) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }

}