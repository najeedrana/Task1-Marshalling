package com.webservices.network;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

/** TCP server that sends a local XML file to one client over localhost. */
public class XmlServer {
    public static void main(String[] args) throws Exception {
        Path xml = Path.of(args.length > 0 ? args[0] : "student.xml");
        try (ServerSocket server = new ServerSocket(5000)) {
            System.out.println("XML server waiting on port 5000...");
            // accept() waits until a client connects.
            try (Socket client = server.accept(); OutputStream output = client.getOutputStream()) {
                Files.copy(xml, output); // Send the file bytes through the TCP connection.
                output.flush();
                System.out.println("Sent XML file: " + xml.toAbsolutePath());
            }
        }
    }
}
