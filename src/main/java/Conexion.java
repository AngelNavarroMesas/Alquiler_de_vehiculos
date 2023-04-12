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

    public VehiculosEntity leerCoche(String matricula){
        abrir();
        VehiculosEntity vehiculo = session.get(VehiculosEntity.class, matricula);
        cerrar();
        System.out.println(vehiculo.getMatricula()+" "+vehiculo.getMarca()+" "+vehiculo.getModelo());

        return vehiculo;
    }

    public List listar() {
        abrir();
        List lista= session.getNamedQuery("listaCompleta").getResultList();
        cerrar();
        return lista;
    }

    public int guardarE(EmpresasEntity empresa) {
        abrir();
        int id = (int) session.save(empresa);
        cerrar();
        return id;
    }
    public int guardarV(VehiculosEntity vehiculos) {
        abrir();
        int id = (int) session.save(vehiculos);
        cerrar();
        return id;
    }

    private void abrir(){
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    private void cerrar(){
        transaction.commit();
        session.close();
    }


}
