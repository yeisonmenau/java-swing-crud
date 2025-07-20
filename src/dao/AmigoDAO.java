package dao;

import model.Amigo;

import java.io.File;
import java.io.IOException;

public interface AmigoDAO {
    File crearArchivo()throws IOException;
    String create(Amigo amigo, File bdParametro) throws IOException;
    String read(File bd);
    String update(Long idBuscar, Amigo amigo, File bd);
    String delete(Long id, File bd);
}
