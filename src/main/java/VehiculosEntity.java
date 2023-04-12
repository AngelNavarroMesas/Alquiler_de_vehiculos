import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Vehiculos")
@NamedQuery(name="listaCompleta", query="FROM VehiculosEntity e")
public class VehiculosEntity implements Serializable{
    @Id
    @Column
    private String matricula;
    @Column
    private String modelo;
    @Column
    private String marca;
    @Column
    private boolean estaEnTaller;

    public VehiculosEntity() {
    }

    public VehiculosEntity(String matricula, String modelo, String marca, boolean estaEnTaller) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.marca = marca;
        this.estaEnTaller = estaEnTaller;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean isEstaEnTaller() {
        return estaEnTaller;
    }

    public void setEstaEnTaller(boolean estaEnTaller) {
        this.estaEnTaller = estaEnTaller;
    }
}
