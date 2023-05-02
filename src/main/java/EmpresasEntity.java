import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Empresas")
@NamedQuery(name="listaEmpresas", query="FROM EmpresasEntity e")
public class EmpresasEntity implements Serializable {
    @Id
    @Column
    private String CIF;
    @Column
    private String nombre;

    public EmpresasEntity() {
    }

    public EmpresasEntity(String CIF, String nombre) {
        this.CIF = CIF;
        this.nombre = nombre;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
