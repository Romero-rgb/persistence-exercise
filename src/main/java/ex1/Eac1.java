/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ex1;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 *Classe principal per a la gestió de fitxers (copiar o moure)
 * basant-se en filtres de mida o nom.
 * @author Antonio Fernández Romero
 * @version 1.0
 */
public class Eac1 {

    /**
     * Punt d'entrada del programa. Realitza la validació d'arguments i executa
     * l'operació de còpia o moviment de fitxers segons els criteris especificats.
     *
     * @param args Arguments de línia de comandes:
     * <ol>
     * <li>Acció de l'usuari (C per copiar, M per moure)</li>
     * <li>Ruta del directori destí</li>
     * <li>Filtre d'arxiu (M per mida, N per nom)</li>
     * <li>Valor del filtre (mida en bytes o cadena de text)</li>
     * <li>(Opcional) 'H' per incloure fitxers ocults</li>
     * </ol>
     */
    public static void main(String[] args) {
        if (args.length > 5 || args.length < 4) {
            System.err.println("El nombre d'arguments han de ser 4 o 5");
            System.exit(2);
        }

        File origin = new File("src/main");

        String action = args[0];
        File destiny = new File(args[1]);
        String selectFilter = args[2];
        String value = args[3];

        int valueInt = 0; //Emmagatzema la mida mínima si el filtre es "M"
        boolean selectedHidden = false; //Variable per saber si hem de processar arxius ocults


        if (!action.equalsIgnoreCase("C") && !action.equalsIgnoreCase("M")) {
            System.err.println("El primer argument ha de ser C o M");
            System.exit(2);
        }
        if (action.equalsIgnoreCase("M") && !origin.canWrite()) {
            System.err.println("L'arxius no es pot moure. No te permisos d'escriptura");
            System.exit(2);
        }

        if (!destiny.isDirectory() || !destiny.canWrite()) {
            System.err.println("El destí no es un directori vàlid o no té permisos d'escriptura");
            System.exit(2);
        }

        if (selectFilter.equalsIgnoreCase("M") || selectFilter.equalsIgnoreCase("N")) {

            if (selectFilter.equalsIgnoreCase("M")) {
                try {
                    valueInt = Integer.parseInt(value);

                } catch (NumberFormatException e) {
                    System.err.println("El quart argument ha de ser un nombre integer");
                    System.exit(2);
                }
            }

        } else {
            System.err.println("El tercer argument ha de ser M o N");
            System.exit(2);
        }


        if (args.length == 5) {
            String hidden = args[4];
            selectedHidden = true;
            if (!hidden.equalsIgnoreCase("H")) {
                System.err.println("El quint argument ha de ser H");
                System.exit(2);
            }
        }

        final int valueFilter = valueInt;
        final boolean hiddenFinal = selectedHidden;


        /**
         * El filtre discrimina els arxius de la següent manera:
         * Que sigui un fitxer (no directori).
         * La visibilitat (ocults).
         * Criteri de mida (bytes) o de nom (substring).
         */
        FileFilter filter = (File selectedFile) -> {
            if (!selectedFile.isFile()) {
                return false;
            }

            if (!hiddenFinal && selectedFile.isHidden()) {
                return false;
            }

            if (selectFilter.equalsIgnoreCase("M")) {
                return selectedFile.length() >= valueFilter;
            } else {
                return (selectedFile.getName().toLowerCase().contains(value.toLowerCase()));
            }
        };

        File[] listFiles = origin.listFiles(filter);

        int counter = 0;
        long totalBytes = 0;
        if (listFiles != null) {
            for (File file : listFiles) {
                Path fullDestiny = destiny.toPath().resolve(file.getName());
                if (action.equalsIgnoreCase("C")) {
                    try {
                        Files.copy(file.toPath(), fullDestiny, StandardCopyOption.REPLACE_EXISTING);
                        if (file.isHidden()) {
                            System.out.println(file.getName() + "(H)" + file.length() + "B");
                        } else {
                            System.out.println(file.getName() + " " + file.length() + "B");
                        }
                        counter++;
                        totalBytes += file.length();

                    } catch (IOException e) {
                        System.err.println("S'ha produit un error al copiar els arxius");
                        System.exit(2);
                    }



                }
                if (action.equalsIgnoreCase("M")) {
                    try {
                        Files.move(file.toPath(), fullDestiny);
                        if (file.isHidden()) {
                            System.out.println(file.getName() + "(H)" + file.length() + "B");
                        } else {
                            System.out.println(file.getName() + " " + file.length() + "B");
                        }

                        counter++;
                        totalBytes += file.length();

                    } catch (IOException e) {
                        System.err.println("S'ha produit un error al moure els arxius");
                        System.exit(2);
                    }

                }


            }

            if (action.equalsIgnoreCase("C")) {
                System.out.println("\n" + counter + "Fitxer copiats,  " + totalBytes + " B");
            }

            if (action.equalsIgnoreCase("M")) {
                System.out.println("\n" + counter + "Fitxer moguts,  " + totalBytes + " B");
            }

        }


    }

}
