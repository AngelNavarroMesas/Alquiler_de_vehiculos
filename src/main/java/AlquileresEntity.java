import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Alquileres")
@NamedQuery(name="listaCompleta", query="FROM AlquileresEntity e")
public class AlquileresEntity implements Serializable{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String CIF;
    @Column
    private String matricula;
    @Column
    private Date fechaInicio;
    @Column
    private Date fechaFin;
    @Column
    private double importe;

    public AlquileresEntity() {
    }

    public AlquileresEntity(int id, String CIF, String matricula, Date fechaInicio, Date fechaFin, double importe) {
        this.id = id;
        this.CIF = CIF;
        this.matricula = matricula;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.importe = importe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {this.importe = importe;}
}
