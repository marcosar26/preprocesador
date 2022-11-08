import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (;;) {
            System.out.println("""
                    Preprocesador de comentarios en Java
                    -------------------------------------
                    1. Eliminar comentarios
                    2. Salir
                    """);
            System.out.print("Selecciona opción: ");
            int opc = sc.nextInt();

            switch (opc) {
                default -> System.out.println("Vuelve a intentarlo.");
                case 1 -> {
                    System.out.print("Introduzca el nombre del fichero a tratar > ");
                    String arc = sc.next();

                    File file = new File(arc);

                    String datos = readFile(file);

                    if (datos == null) {
                        System.out.println("Error al leer el archivo.");
                        return;
                    }

                    String resultado = datos.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)", "");

                    new File("out/").mkdirs();

                    try {
                        File sol = new File("out/" + file.getName());

                        Files.write(sol.toPath(), resultado.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        System.out.println("Error al escribir.");
                    }
                }
                case 2 -> System.exit(0);
            }
        }
    }


    static String readFile(File file) {

        char[] buffer = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            buffer = new char[(int) file.length()];

            int i = 0;
            int c = bufferedReader.read();

            while (c != -1) {
                buffer[i++] = (char) c;
                c = bufferedReader.read();
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }

        if (buffer != null) {
            return new String(buffer);
        }

        return null;
    }

}