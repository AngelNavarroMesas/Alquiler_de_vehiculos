import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Conexion con;

    public static void main(String[] args) throws Exception {
        con = new Conexion();
        menu();
    }
    public static void menu() throws Exception {
        int op=0;
        while(op!=9) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Elige una opcion");
            System.out.println("1- insertar");
            System.out.println("2- listar coche");
            System.out.println("3- modificar");
            System.out.println("4- borrar");
            System.out.println("5- alquilar coche");
            System.out.println("6- buscar coche por matricula");
            System.out.println("9- salir");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    insertar();
                    break;
                case 2: listarcoche();
                    break;
                //case 3: modificar();
                //    break;
                //case 4: borrar();
                //    break;
                case 5:
                    alquilarCoche();
                    break;
                case 6:
                    buscarcoche();
                    break;
                case 9:
                    salir();
                    break;
            }
        }
    }

    private static void buscarcoche() {
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Inserte la matricula del coche");
            String matricula = sc.nextLine();
            VehiculosEntity vehiculo = con.leerCoche(matricula);
        }catch (Exception e){

        }
    }

    private static void listarcoche() throws Exception {
        System.out.println(con.listar());
    }

    public static void alquilarCoche() throws Exception {
        Scanner sc = new Scanner(System.in);
        java.util.Date fecha = new Date();
        AlquileresEntity alquiler = new AlquileresEntity();
        System.out.println("Introduzca el cif de la empresa");
        alquiler.setCIF(sc.nextLine());
        List lista = con.primerCoche();
        alquiler.setMatricula(lista.toString());
        alquiler.setFechaInicio(fecha);
        alquiler.setFechaFin(null);
        alquiler.setImporte(0);
        con.guardarA(alquiler);
    }

    public static void insertar() throws Exception {
        Scanner sc = new Scanner(System.in);
        if(con!=null) {
            System.out.println("¿Que desea insertar?");
            System.out.println("1. empresa");
            System.out.println("2. vehiculo");
            int op = sc.nextInt();

            switch (op){
                case 1:
                    EmpresasEntity empresa = new EmpresasEntity();
                    System.out.println("introduce nombre");
                    empresa.setNombre(sc.nextLine());
                    empresa.setNombre(sc.nextLine());
                    System.out.println("introduce CIF");
                    empresa.setCIF(sc.nextLine());
                    con.guardarE(empresa);
                break;
                case 2:
                    VehiculosEntity vehiculo = new VehiculosEntity();
                    System.out.println("introduce matricula");
                    vehiculo.setMatricula(sc.nextLine());
                    vehiculo.setMatricula(sc.nextLine());
                    System.out.println("introduce modelo");
                    vehiculo.setModelo(sc.nextLine());
                    System.out.println("introduce marca");
                    vehiculo.setMarca(sc.nextLine());
                    vehiculo.setEstaEnTaller(false);
                    con.guardarV(vehiculo);
                break;
            }

        }else{
            System.out.println("La conexion no funciona");
        }
        sc.close();
    }

    public static void salir(){}

}
