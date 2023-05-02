import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Conexion {
    private static SessionFactory sessionFactory = null;
    private Session session;
    private Transaction transaction;

    protected void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    public VehiculosEntity leerCoche(String matricula) throws Exception {
        abrir();
        VehiculosEntity vehiculo = session.get(VehiculosEntity.class, matricula);
        cerrar();
        System.out.println(vehiculo.getMatricula()+" "+vehiculo.getMarca()+" "+vehiculo.getModelo());

        return vehiculo;
    }

    public AlquileresEntity leerAlquiler(String id) throws Exception {
        abrir();
        AlquileresEntity alquiler = session.get(AlquileresEntity.class, id);
        cerrar();
        System.out.println(alquiler.getMatricula()+" "+alquiler.getMarca()+" "+alquiler.getModelo());

        return alquiler;
    }

    public List listar() throws Exception {
        abrir();
        List lista= session.getNamedQuery("listaVehiculos").getResultList();
        cerrar();
        return lista;
    }

    public String guardarE(EmpresasEntity empresa) throws Exception {
        abrir();
        String id = (String) session.save(empresa);
        cerrar();
        return id;
    }
    public String guardarV(VehiculosEntity vehiculos) throws Exception {
        abrir();
        String id = (String) session.save(vehiculos);
        cerrar();
        return id;
    }
    public String guardarA(AlquileresEntity alquiler) throws Exception {
        abrir();
        String id = (String) session.save(alquiler);
        cerrar();
        return id;
    }

    public void actualizarV(VehiculosEntity vehiculos) throws Exception {
        abrir();
        session.update(vehiculos);
        cerrar();
    }

    private void abrir() throws Exception {
        setUp();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    private void cerrar(){
        transaction.commit();
        session.close();
    }


}
