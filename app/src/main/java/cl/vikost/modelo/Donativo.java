package cl.vikost.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Donativo implements Serializable {

    private String titulo;

    private String usuario;

    private List<Postulante> postulantes;

    public Donativo() {
        this.postulantes = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
