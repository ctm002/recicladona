package cl.vikost.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Donativo implements Serializable {

    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Postulante> getPostulantes() {
        return postulantes;
    }

    public void setPostulantes(List<Postulante> postulantes) {
        this.postulantes = postulantes;
    }

}
