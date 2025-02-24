package co.edu.upb.songclient.main;

import co.edu.upb.songclient.classes.SOAPClient;

import java.net.MalformedURLException;

public class SongClient {

    public static void main(String[] args){
        SOAPClient soapClient;
        try {
            soapClient = new SOAPClient();
            soapClient.run();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
