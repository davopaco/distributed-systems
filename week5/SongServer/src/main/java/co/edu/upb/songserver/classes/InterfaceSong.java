package co.edu.upb.songserver.classes;

import java.util.List;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;

@WebService(targetNamespace = "http://classes.songserver.upb.edu.co/", name = "InterfaceSong")
public interface InterfaceSong {
    @WebMethod
    List<Song> searchByTitle(String title);

    @WebMethod
    List<Song> searchByGenre(String genre);

    @WebMethod
    List<Song> searchByAuthor(String author);
}
