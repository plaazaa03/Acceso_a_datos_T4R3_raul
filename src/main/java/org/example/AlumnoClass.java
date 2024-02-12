package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "alumno", schema = "instituto")
public class AlumnoClass {
    private int codigo;
    private String nombre;
    private String apellidos;
    private String tfno;
    private List<AsignaturaClass> lista_asignatura;

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigo", nullable = false)
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "nombre", nullable = true, length = 15)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "apellidos", nullable = true, length = 20)
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Basic
    @Column(name = "tfno", nullable = true, length = 9)
    public String getTfno() {
        return tfno;
    }

    public void setTfno(String tfno) {
        this.tfno = tfno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlumnoClass that = (AlumnoClass) o;

        if (codigo != that.codigo) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (apellidos != null ? !apellidos.equals(that.apellidos) : that.apellidos != null) return false;
        if (tfno != null ? !tfno.equals(that.tfno) : that.tfno != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (tfno != null ? tfno.hashCode() : 0);
        return result;
    }

    @ManyToMany
    @JoinTable(name = "matricula", schema = "instituto", joinColumns = @JoinColumn(name = "cod_alumno", referencedColumnName = "codigo", nullable = false), inverseJoinColumns = @JoinColumn(name = "cod_asig", referencedColumnName = "codigo", nullable = false))
    public List<AsignaturaClass> getLista_asignatura() {
        return lista_asignatura;
    }

    public void setLista_asignatura(List<AsignaturaClass> lista_asignatura) {
        this.lista_asignatura = lista_asignatura;
    }
}
