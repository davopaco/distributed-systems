package co.edu.upb.songserver.classes;

import jakarta.xml.ws.Endpoint;

public class SOAPServer {

    private final InterfaceSong service;

    public SOAPServer(InterfaceSong service) {
        this.service = service;
    }

    public void run() {
        try {
            Endpoint.publish("http://localhost:6969/song", service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
