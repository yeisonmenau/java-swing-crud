package service;

import model.Amigo;
import dao.AmigoDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AmigoService implements AmigoDAO {
    @Override
    public File crearArchivo() throws IOException{
        try {
            File bd = new File ("src/data/bd.txt");
            if(!bd.exists()){
                bd.createNewFile();
            }
            return bd;
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el archivo");
        }
    }


    @Override
    public String create(Amigo amigo, File bdParametro) {
        String nuevoNombre = amigo.getNombre();
        Long nuevoId = amigo.getId();
        boolean found = false;

        if (nuevoNombre.isEmpty()){
            throw new RuntimeException("Nombre vacío");
        }

        try (RandomAccessFile raf = new RandomAccessFile(bdParametro, "rw")) {
            String line;
            while ((line = raf.readLine()) != null) {
                String[] lineSplit = line.split("!");
                if (lineSplit.length < 2) continue;

                String nombre = lineSplit[0];
                Long id = Long.parseLong(lineSplit[1]);

                if (id.equals(nuevoId)) {
                    // Puedo tener amigos con el mismo nombre, se permite nombres iguales.
                    found = true;
                    break;
                }
            }

            if (!found) {
                raf.seek(raf.length());
                String nuevaLinea = nuevoNombre + "!" + nuevoId + System.lineSeparator();
                raf.writeBytes(nuevaLinea);
                return nuevoNombre + " agregado exitosamente";
            } else {
                throw new IllegalArgumentException("Ya existe un amigo con ese ID.");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("No se encontró el archivo de base de datos.");
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo.");
        }
    }


    @Override
    public String read(File bd) {
        StringBuilder resultado = new StringBuilder();
        try (RandomAccessFile raf = new RandomAccessFile(bd, "r")) {
            String linea;
            boolean hayAmigos = false;

            while ((linea = raf.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] partes = linea.split("!");
                    if (partes.length == 2) {
                        String nombre = partes[0];
                        String id = partes[1];
                        resultado.append("contacto: ").append(id)
                                .append(" nombre: ").append(nombre)
                                .append("\n");
                        hayAmigos = true;
                    }
                }
            }

            if (!hayAmigos) {
                throw new RuntimeException("No hay amigos registrados en la base de datos.");
            }

            return resultado.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error al leer la base de datos.");
        }
    }

    @Override
    public String update(Long idBuscar, Amigo amigo, File bd) {
        Long nuevoId = amigo.getId();
        String nuevoNombre = amigo.getNombre();
        if (nuevoId == null && (nuevoNombre == null || nuevoNombre.trim().isEmpty())) {
            throw new RuntimeException("Debe ingresar al menos un nuevo valor para actualizar.");
        }

        try (RandomAccessFile raf = new RandomAccessFile(bd, "rw")) {
            StringBuilder nuevoContenido = new StringBuilder();
            String linea;
            boolean encontrado = false;

            while ((linea = raf.readLine()) != null) {
                String[] partes = linea.split("!");
                if (partes.length != 2) continue;

                String nombreActual = partes[0];
                Long idActual = Long.parseLong(partes[1]);

                if (idActual.equals(idBuscar)) {
                    // Actualizar los valores según lo ingresado
                    String nombreFinal = (nuevoNombre == null || nuevoNombre.trim().isEmpty()) ? nombreActual : nuevoNombre;
                    Long idFinal = (nuevoId == null) ? idActual : nuevoId;
                    nuevoContenido.append(nombreFinal).append("!").append(idFinal).append(System.lineSeparator());
                    encontrado = true;
                } else {
                    nuevoContenido.append(nombreActual).append("!").append(idActual).append(System.lineSeparator());
                }
            }

            if (!encontrado) {
                throw new RuntimeException("No se encontró el ID a actualizar.");
            }

            // Reescribir el archivo con el nuevo contenido
            raf.setLength(0);
            raf.writeBytes(nuevoContenido.toString());

            return "Amigo actualizado exitosamente.";
        } catch (IOException e) {
            throw new RuntimeException("Error al acceder al archivo.");
        }
    }

    @Override
    public String delete(Long id, File bd) {
        File temp = new File("src/data/temp.txt");
        try {
            if(!temp.exists()){
                temp.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error con el archivo temporal");
        }

        boolean encontrado = false;

        try (RandomAccessFile raf = new RandomAccessFile(bd, "rw");
             RandomAccessFile tmpraf = new RandomAccessFile(temp, "rw")) {
            raf.seek(0);

            // Leer línea por línea
            while (raf.getFilePointer() < raf.length()) {
                String linea = raf.readLine();
                if (linea == null || !linea.contains("!")) continue;
                String[] partes = linea.split("!");
                if (partes.length < 2) continue;

                Long idActual = Long.parseLong(partes[1].trim());

                if (idActual.equals(id)) {
                    encontrado = true; // Salto esta línea (no la copio)
                    continue;
                }

                tmpraf.writeBytes(linea);
                tmpraf.writeBytes(System.lineSeparator());
            }

            if (!encontrado) {
                raf.close();
                tmpraf.close();
                temp.delete();
                throw new RuntimeException("No se encontró ningún amigo con ese ID.");
            }

            // Reescribimos bd con contenido de temp
            raf.seek(0);
            tmpraf.seek(0);

            while (tmpraf.getFilePointer() < tmpraf.length()) {
                String linea = tmpraf.readLine();
                raf.writeBytes(linea);
                raf.writeBytes(System.lineSeparator());
            }

            raf.setLength(tmpraf.length()); // Ajustar el tamaño del archivo
            tmpraf.close();
            temp.delete();

            return "Amigo con ID " + id + " eliminado exitosamente.";

        } catch (IOException e) {
            throw new RuntimeException("Error al eliminar el amigo: " + e.getMessage());
        }
    }
}
