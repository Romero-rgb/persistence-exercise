/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.model;

import javax.xml.bind.annotation.*;
import java.util.Objects;
import com.google.gson.*;
import com.google.gson.annotations.SerializedName;


/**
 * Representa una pista d'esquí individual amb les seves característiques tècniques
 * i l'estat actual de la neu i obertura.
 * Permet la serialització i deserialització en formats XML i JSON.
 * * @author Antonio Fernández Romero
 * @version 1.0
 */
@XmlRootElement(name = "pista")
@XmlType(propOrder = {"nom", "longitud", "desnivell" ,"color", "oberta", "gruixNeu", "qualitatNeu"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Pista {

    @XmlAttribute
    private String id;
    private String nom;
    private int longitud;
    private int desnivell;
    private String color;
    private boolean oberta;

    @SerializedName("gruix-neu")
    @XmlElement(name = "gruix-neu")
    private int gruixNeu;

    @SerializedName("qualitat-neu")
    @XmlElement(name = "qualitat-neu")
    private String qualitatNeu;

    public Pista() {
    }

    /**
     * Constructor amb tots els paràmetres per inicialitzar una pista d'esquí.
     * @param id Identificador únic de la pista.
     * @param nom Nom de la pista.
     * @param color Clasificació per color de la dificultat de la pista.
     * @param qualitatNeu Tipus de neu.
     * @param gruixNeu Gruix de la neu mesurat.
     * @param longitud Distància total de la pista en metres.
     * @param desnivell Diferència d'altitud entre l'inici i el final de la pista.
     * @param oberta Estat d'obertura de la pista. True si està oberta, False si està tancada.
     */
    public Pista(String id, String nom, String color, String qualitatNeu, int gruixNeu, int longitud, int desnivell, boolean oberta) {
        this.id = id;
        this.nom = nom;
        this.color = color;
        this.qualitatNeu = qualitatNeu;
        this.gruixNeu = gruixNeu;
        this.longitud = longitud;
        this.desnivell = desnivell;
        this.oberta = oberta;
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

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getDesnivell() {
        return desnivell;
    }

    public void setDesnivell(int desnivell) {
        this.desnivell = desnivell;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getGruixNeu() {
        return gruixNeu;
    }

    public void setGruixNeu(int gruixNeu) {
        this.gruixNeu = gruixNeu;
    }

    public String getQualitatNeu() {
        return qualitatNeu;
    }

    public void setQualitatNeu(String qualitatNeu) {
        this.qualitatNeu = qualitatNeu;
    }

    public boolean isOberta() {
        return oberta;
    }

    public void setOberta(boolean oberta) {
        this.oberta = oberta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.nom);
        hash = 71 * hash + Objects.hashCode(this.color);
        hash = 71 * hash + Objects.hashCode(this.qualitatNeu);
        hash = 71 * hash + this.gruixNeu;
        hash = 71 * hash + this.longitud;
        hash = 71 * hash + this.desnivell;
        hash = 71 * hash + (this.oberta ? 1 : 0);
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
        final Pista other = (Pista) obj;
        if (this.gruixNeu != other.gruixNeu) {
            return false;
        }
        if (this.longitud != other.longitud) {
            return false;
        }
        if (this.desnivell != other.desnivell) {
            return false;
        }
        if (this.oberta != other.oberta) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return Objects.equals(this.qualitatNeu, other.qualitatNeu);
    }

}
