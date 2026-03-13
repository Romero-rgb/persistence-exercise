/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.gestors;

import ex2.model.Estacio;
import ex2.model.Pista;

import javax.xml.bind.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;

/**
 *
 * @author Antonio Fernández Romero
 */
public class GestorEstacio {


    /**
     * Retorna un objecte de classe Estacio a partir d'un fitxer XML
     *
     * @param nomFitxer el nom del fitxer
     * @return l'objecte de classe Estacio
     * @throws ex2.gestors.GestorEstacioException si no s'ha pogut llegir el
     * fitxer
     */
    public Estacio llegirFitxerXML(String nomFitxer) throws GestorEstacioException {


        try {
            File originFile = new File(nomFitxer);
            JAXBContext jaxbContext = JAXBContext.newInstance(Estacio.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            return (Estacio) jaxbUnmarshaller.unmarshal(originFile);


        }catch (Exception ex){
            throw new GestorEstacioException("No s'ha pogut llegir l'arxiu Estacio", ex);
        }
    }

    /**
     * Crea un fitxer XML a partir d'un objecte de la classe Estacio
     *
     * @param nomFitxer nom del fitxer que es crearà
     * @param estacio objecte de la classe Estacio
     * @throws ex2.gestors.GestorEstacioException si no s'ha pogut gravar el
     * fitxer
     */
    public void gravarFitxerXML(String nomFitxer, Estacio estacio) throws GestorEstacioException {

        try {
            File destinyFile = new File(nomFitxer);


            JAXBContext jaxbContext = JAXBContext.newInstance(Estacio.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(estacio, destinyFile);

        }catch (JAXBException ex){
            throw new GestorEstacioException("No s'ha pogut gravar a l'arxiu Estacio", ex);
        } catch (NullPointerException n) {
            throw new GestorEstacioException("L'arxiu no pot ser nul",n);
        }
    }

    /**
     * Retorna un objecte de classe Estacio a partir d'un fitxer JSON
     *
     * @param nomFitxer el nom del fitxer
     * @return l'objecte de classe Estacio
     * @throws ex2.gestors.GestorEstacioException si no s'ha pogut llegir el
     * fitxer
     */
    public Estacio llegirFitxerJSON(String nomFitxer) throws GestorEstacioException {
        try(FileReader fileReader = new FileReader(nomFitxer)) {

            Gson gson = new Gson();

            return gson.fromJson(fileReader, Estacio.class);

        }catch(FileNotFoundException ex){
                throw new GestorEstacioException("No s'ha pogut llegir l'arxiu", ex);
        }catch (IOException io){
            throw new GestorEstacioException("Error a la lectura de l'arxiu", io);
        }

    }

    /**
     * Crea un fitxer JSON a partir d'un objecte de la classe Estacio
     *
     * @param nomFitxer nom del fitxer que es crearà
     * @param estacio objecte de la classe Estacio
     * @throws ex2.gestors.GestorEstacioException si no s'ha pogut escriure el
     * fitxer
     */
    public void gravarFitxerJSON(String nomFitxer, Estacio estacio) throws GestorEstacioException, FileNotFoundException {

        try(FileWriter fileWriter = new FileWriter(nomFitxer)) {

            Gson gson = new Gson();
            gson.toJson(estacio, fileWriter);


        }catch(IOException ex){
            throw new GestorEstacioException("No s'ha pogut gravar l'arxiu", ex);
        } catch (NullPointerException n) {
            throw new GestorEstacioException("L'arxiu no pot ser nul",n);
        }

    }

}
