package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "asignatura", schema = "instituto")
public class AsignaturaClass {
    private int codigo;
    private String nombre;
    private Integer creditos;
    private List<AlumnoClass> lista_alumnos;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigo", nullable = false)
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "nombre", nullable = true, length = 30)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "creditos", nullable = true)
    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AsignaturaClass that = (AsignaturaClass) o;

        if (codigo != that.codigo) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (creditos != null ? !creditos.equals(that.creditos) : that.creditos != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (creditos != null ? creditos.hashCode() : 0);
        return result;
    }

    @ManyToMany(mappedBy = "lista_asignatura")
    public List<AlumnoClass> getLista_alumnos() {
        return lista_alumnos;
    }

    public void setLista_alumnos(List<AlumnoClass> lista_alumnos) {
        this.lista_alumnos = lista_alumnos;
    }
}
