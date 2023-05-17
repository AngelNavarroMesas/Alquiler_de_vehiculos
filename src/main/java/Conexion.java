import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;
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

    public EmpresasEntity leerEmpresa(String CIF) throws Exception {
        abrir();
        EmpresasEntity empresa = session.get(EmpresasEntity.class, CIF);
        cerrar();
        System.out.println(empresa.getCIF()+" "+empresa.getNombre());

        return empresa;
    }

    public AlquileresEntity leerAlquiler(int id) throws Exception {
        abrir();
        AlquileresEntity alq = session.get(AlquileresEntity.class, id);
        cerrar();
        System.out.println(alq.getId()+" "+alq.getCIF()+" "+alq.getMatricula());

        return alq;
    }

    public List primerCoche() throws Exception {
        abrir();
        List lista=session.createQuery(
                "SELECT v.matricula FROM VehiculosEntity v left join AlquileresEntity a on v.matricula=a.matricula WHERE a.id is null or a.fechaFin is not null").setMaxResults(1).getResultList();
        cerrar();
        return lista;
    }

    public List buscarAlquiler(String cocheKBusco, String empresaKBusco) throws Exception {
        abrir();
        List lista=session.createQuery(
                "SELECT id FROM AlquileresEntity WHERE matricula like concat('%',:cocheBuscado,'%') and CIF like :empresaBuscada and fechaFin is null").setParameter("cocheBuscado", cocheKBusco).setParameter("empresaBuscada", empresaKBusco).getResultList();
        cerrar();
        return lista;
    }

    public void actualizarAlquiler(int id) throws Exception {
        abrir();
        java.util.Date fecha = new Date();
        AlquileresEntity alquiler = session.get(AlquileresEntity.class, id);
        alquiler.setFechaFin(fecha);
        session.update(alquiler);
        cerrar();
    }

    public void borrarCoche(VehiculosEntity coche) throws Exception {
        abrir();
        try{
            session.delete(coche);
            System.out.println("vehiculo borrado");

        }catch (Exception e){
            System.out.println("Error al intentar borrar el vehiculo");
        }
        cerrar();
    }

    public void borrarEmpresa(EmpresasEntity empresa) throws Exception {
        abrir();
        try{
            session.delete(empresa);
            System.out.println("empresa borrada");

        }catch (Exception e){
            System.out.println("Error al intentar borrar la empresa");
        }
        cerrar();
    }

    public void borrarAlquiler(AlquileresEntity alq) throws Exception {
        abrir();
        try{
            session.delete(alq);
            System.out.println("alquiler borrado");

        }catch (Exception e){
            System.out.println("Error al intentar borrar el alquiler");
        }
        cerrar();
    }


    public List listar() throws Exception {
        abrir();
        List lista=session.createQuery(
                "SELECT '*' FROM VehiculosEntity").getResultList();
        cerrar();
        return lista;
    }

    public String guardarE(EmpresasEntity empresa) throws Exception {
        abrir();
        String id = null;
        try {
            id = (String) session.save(empresa);
        } catch (Exception e) {
            System.out.println("Error");
        }
        cerrar();
        return id;
    }
    public String guardarV(VehiculosEntity vehiculos) throws Exception {
        abrir();
        String id = null;
        try {
            id = (String) session.save(vehiculos);
        } catch (Exception e) {
            System.out.println("Error");
        }
        cerrar();
        return id;
    }
    public String guardarA(AlquileresEntity alquiler) throws Exception {
        abrir();
        String id = null;
        try {
            id = (String) session.save(alquiler);
        } catch (Exception e) {
            System.out.println("Error");
        }
        cerrar();
        return id;
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
