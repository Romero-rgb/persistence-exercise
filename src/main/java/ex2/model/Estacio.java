/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.google.gson.*;
import com.google.gson.annotations.SerializedName;

/**
 * Representa una estació d'esquí amb la seva informació general i llistat de pistes.
 * Inclou anotacions per a la serialització en formats XML (JAXB) i JSON (Gson).
 * * @author Antonio Fernández Romero
 * @version 1.0
 */
@XmlRootElement(name = "estacio")
@XmlType(propOrder = {"nom", "comarca", "web", "altitudMaxima", "qualificacio", "estatObertura", "pistes"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Estacio {


    @XmlAttribute
    private String id;

    private String nom;
    private String comarca;
    private String web;

    @XmlElement(name = "altitud-maxima")
    @SerializedName("altitud-maxima")
    private int altitudMaxima;
    private String qualificacio;

    @XmlElement(name = "estat-obertura-percentatge")
    @SerializedName("estat-obertura-percentatge")
    private int estatObertura;

    @XmlElementWrapper(name = "pistes")
    @XmlElement(name = "pista")
    private List<Pista> pistes = new ArrayList<>();


    public Estacio() {
    }

    /**
     * Constructor amb paràmetres per inicialitzar una estació.
     * * @param id Identificador únic de l'estació.
     * @param nom Nom de l'estació.
     * @param comarca Comarca on es troba.
     * @param web Adreça URL de la web oficial.
     * @param altitudMaxima Altitud màxima de l'estació en metres.
     * @param qualificacio Valoració o categoria de l'estació.
     * @param estatObertura Percentatge inicial de pistes obertes.
     */
    public Estacio(String id, String nom, String comarca, String web, int altitudMaxima, String qualificacio, int estatObertura) {
        this.id = id;
        this.nom = nom;
        this.comarca = comarca;
        this.web = web;
        this.altitudMaxima = altitudMaxima;
        this.qualificacio = qualificacio;
        this.estatObertura = estatObertura;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getComarca() {
        return comarca;
    }

    public void setComarca(String comarca) {
        this.comarca = comarca;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public int getAltitudMaxima() {
        return altitudMaxima;
    }

    public void setAltitudMaxima(int altitudMaxima) {
        this.altitudMaxima = altitudMaxima;
    }

    public String getQualificacio() {
        return qualificacio;
    }

    public void setQualificacio(String qualificacio) {
        this.qualificacio = qualificacio;
    }

    public int getEstatObertura() {
        return estatObertura;
    }

    public void setEstatObertura(int estatObertura) {
        this.estatObertura = estatObertura;
    }

    public List<Pista> getPistes() {
        return pistes;
    }

    public void setPistes(List<Pista> pistes) {
        this.pistes = pistes;
    }

    /**
     * Calcula el percentatge de pistes obertes en comparació al total de pistes
     * de l'estació. Actualitza l'atribut estatObertura.
     *
     * La fórmula utilitzada és: (pistes obertes * 100) / total de pistes.
     *
     */
    public void actualitzaEstatObertura() {
        int opens = 0;

        for (Pista pista : pistes) {
            if (pista.isOberta())
                opens++;
        }

        int percentOpens = (int) (opens * 100.0) / pistes.size(); //Conversió explícita de double a int
        setEstatObertura(percentOpens);

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.nom);
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.comarca);
        hash = 11 * hash + Objects.hashCode(this.web);
        hash = 11 * hash + Objects.hashCode(this.pistes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Estacio other = (Estacio) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.comarca, other.comarca)) {
            return false;
        }
        if (!Objects.equals(this.web, other.web)) {
            return false;
        }
        return Objects.equals(this.pistes, other.pistes);
    }

}
