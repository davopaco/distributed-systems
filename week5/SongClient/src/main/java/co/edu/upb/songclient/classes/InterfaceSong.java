package co.edu.upb.songclient.classes;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "http://classes.songserver.upb.edu.co/")
public interface InterfaceSong {
    @WebMethod
    List<Song> searchByTitle(String title);

    @WebMethod
    List<Song> searchByGenre(String genre);

    @WebMethod
    List<Song> searchByAuthor(String author);
}
