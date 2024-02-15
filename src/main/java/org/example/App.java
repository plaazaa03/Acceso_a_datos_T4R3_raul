package org.example;

import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Session session;
    public static void main( String[] args )
    {
        SessionFactory sessionFactory = (SessionFactory) new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();

        if (session != null) {
            System.out.println("Se inició la sesión");

            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("========MENU========");
                System.out.println("1. Insercion de alumnos");
                System.out.println("2. Insercion de asignaturas");
                System.out.println("3. Insercion de matriculas");
                System.out.println("4. Mostar todos los alumnos");
                System.out.println("5. Mostrar todas las asignaturas");
                System.out.println("6. Mostrar asignatura en la que este registrado un alumno");
                System.out.println("7. Mostrar los alumnos que esten em una determinada asignatura");
                System.out.println("8. Mostrar un listado con todos los alumnos y sus datos y que aparezca en que aignatura estan registrados");
                System.out.println("9. Mostrar el numero de alumnos matriculados en cada asignatura");
                System.out.println("10. Salir del programa");
                System.out.println("Introduzca su opcion: ");

                opcion=scanner.nextInt();

                switch (opcion){
                    case 1:
                        insercionAlumnos();
                        break;
                    case 2:
                        insercionAsignaturas();
                        break;
                    case 3:
                        insercionMatriculas();
                        break;
                    case 4:
                        mostrarAlumnos();
                        break;
                    case 5:
                        mostrarAsignaturas();
                        break;
                    case 6:
                        mostrarAsignaturasAlumnos();
                        break;
                    case 7:
                        mostrarAlumnosAsignatura();
                        break;
                    case 8:
                        mostrarLisadoAlumno();
                        break;
                    case 9:
                        mostrarAlumnosMatriculados();
                        break;
                    case 10:
                        System.out.println("Saliendo del programa, hasta la proxima!!!!");
                }
            }while(opcion != 10);
            scanner.close();
        }
        else {
            System.out.println("Error abriendo la sesión");
        }
    }

    private static void insercionAlumnos() {
        Scanner scanner = new Scanner(System.in);
        Transaction transaction = session.beginTransaction();
        AlumnoClass alumnoClass = new AlumnoClass();

        System.out.println("Introduce el codigo del alumno: ");
        int codAlumno = scanner.nextInt();

        System.out.println("Introduce el nombre del alumno: ");
        String nomAlumno = scanner.next();

        System.out.println("Introduce el apellido del alumno: ");
        String apellAlumno = scanner.next();

        System.out.println("Introduce el telefono del alumno: ");
        String tlfAlumno = scanner.next();

        alumnoClass.setCodigo(codAlumno);
        alumnoClass.setNombre(nomAlumno);
        alumnoClass.setApellidos(apellAlumno);
        alumnoClass.setTfno(tlfAlumno);

        session.save(alumnoClass);
        transaction.commit();
    }

    private static void insercionAsignaturas() {
        Scanner scanner = new Scanner(System.in);
        Transaction transaction = session.beginTransaction();
        AsignaturaClass asignaturaClass = new AsignaturaClass();

        System.out.println("Introduce el codigo de la asignatura: ");
        int codAsignatura = scanner.nextInt();

        System.out.println("Introduce el nombre de la asignatura: ");
        String nomAsignatura = scanner.next();

        System.out.println("Introduce los creditos de la asignatura: ");
        int creditosAsignatura = scanner.nextInt();

        asignaturaClass.setCodigo(codAsignatura);
        asignaturaClass.setNombre(nomAsignatura);
        asignaturaClass.setCreditos(creditosAsignatura);

        session.save(asignaturaClass);
        transaction.commit();
    }

    private static void insercionMatriculas() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Introduce el codigo del alumno: ");
    int codAlumno = scanner.nextInt();

    System.out.println("Introduce el codigo de la asignatura: ");
    int codAsignatura = scanner.nextInt();

    Transaction transaction = session.beginTransaction();

    AlumnoClass alumnoClass = session.get(AlumnoClass.class, codAlumno);
    if (alumnoClass == null){
        transaction.commit();
        return;
    }

    AsignaturaClass asignatura = session.get(AsignaturaClass.class, codAsignatura);
    if (asignatura == null) {
        transaction.commit();
        return;
    }

    List<AsignaturaClass> asignaturaClasses = alumnoClass.getLista_asignatura();
    if (asignaturaClasses == null){
        asignaturaClasses = new ArrayList<>();
    }
    asignaturaClasses.add(asignatura);
    alumnoClass.setLista_asignatura(asignaturaClasses);

    session.update(alumnoClass);
    transaction.commit();


    }

    private static void mostrarAlumnos() {

        AlumnoClass alumnoClass = new AlumnoClass();
        Query query = session.createQuery("from AlumnoClass ");
        List<AlumnoClass> lista = query.list();
        Iterator <AlumnoClass> iterator = lista.iterator();

        while (iterator.hasNext()){
            alumnoClass = (AlumnoClass) iterator.next();
            System.out.println("==========================");
            System.out.println("Codigo: " + alumnoClass.getCodigo());
            System.out.println("Nombre: " + alumnoClass.getNombre());
            System.out.println("Apellidos: " + alumnoClass.getApellidos());
            System.out.println("Telefono: " + alumnoClass.getTfno());
            System.out.println("==========================");
            System.out.println("");
        }

    }

    private static void mostrarAsignaturas() {
        AsignaturaClass asignaturaClass = new AsignaturaClass();
        Query query = session.createQuery("from AsignaturaClass");
        List<AsignaturaClass> lista = query.list();
        Iterator <AsignaturaClass> iterator = lista.iterator();

        while (iterator.hasNext()){
            asignaturaClass = (AsignaturaClass) iterator.next();
            System.out.println("==========================");
            System.out.println("Codigo: " + asignaturaClass.getCodigo());
            System.out.println("Nombre: " + asignaturaClass.getNombre());
            System.out.println("Creditos: " + asignaturaClass.getCreditos());
            System.out.println("==========================");
            System.out.println("");
        }
    }

    private static void mostrarAsignaturasAlumnos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el código del Alumno: ");
        int codAlumno = scanner.nextInt();

        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from AlumnoClass where codigo = :codigo");
        query.setParameter("codigo", codAlumno);

        AlumnoClass alumnoClass = (AlumnoClass) query.uniqueResult();

        if (alumnoClass != null) {
            System.out.println("Asignaturas en las que está matriculado el alumno con código:  " + codAlumno);
            List<AsignaturaClass> asignaturaClasses = alumnoClass.getLista_asignatura();
            if (!asignaturaClasses.isEmpty()) {
                for (AsignaturaClass asignaturaClass : asignaturaClasses) {
                    System.out.println("==========================");
                    System.out.println("Codigo: " + asignaturaClass.getCodigo());
                    System.out.println("Nombre: " + asignaturaClass.getNombre());
                    System.out.println("");
                }
            } else {
                System.out.println("El alumno no está matriculado en ninguna asignatura.");
            }
        } else {
            System.out.println("No se ha encontrado ningun alumno con ese codigo.");
        }

        transaction.commit();
    }


    private static void mostrarAlumnosAsignatura() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el codigo de la asignatura: ");
        int codAsignatura = scanner.nextInt();

        Query query = session.createQuery("from AsignaturaClass where codigo =: codigo");
        query.setParameter("codigo", codAsignatura);

        AsignaturaClass asignaturaClass = session.get(AsignaturaClass.class, codAsignatura);

        if (asignaturaClass != null){
            System.out.println("Codigo: " + asignaturaClass.getCodigo());
            System.out.println("==========================");

            List<AlumnoClass> alumnoClasses = asignaturaClass.getLista_alumnos();

            for (AlumnoClass alumnoClass :  alumnoClasses){
                System.out.println("==========================");
                System.out.println("Codigo: " + alumnoClass.getCodigo());
                System.out.println("Nombre: " + alumnoClass.getNombre());
                System.out.println("Apellidos: " + alumnoClass.getApellidos());
                System.out.println("Telefono: " + alumnoClass.getTfno());
            }
        }else{
            System.out.println("No se ha encontrado ninguna asignatura con ese codigo.");
        }

    }

    private static void mostrarLisadoAlumno() {
        Query query = session.createQuery("select a, asignatura from AlumnoClass a join a.lista_asignatura asignatura");
        List<Object[]> resultados = query.getResultList();

        for (Object[] resultado : resultados){
            AlumnoClass alumnoClass = (AlumnoClass) resultado[0];
            AsignaturaClass asignaturaClass = (AsignaturaClass) resultado[1];

            System.out.println("=========Datos Alumno=============");
            System.out.println("Codigo: " + alumnoClass.getCodigo());
            System.out.println("Nombre: " + alumnoClass.getNombre());
            System.out.println("Apellido: " + alumnoClass.getApellidos());
            System.out.println("Telefono: " + alumnoClass.getTfno());
            System.out.println("=========Datos Asignatura=========");
            System.out.println("Codigo: " + asignaturaClass.getCodigo());
            System.out.println("Nombre: " + asignaturaClass.getNombre());
            System.out.println("Creditos: " + asignaturaClass.getCreditos());
        }
    }



    private static void mostrarAlumnosMatriculados() {
        Query query = session.createQuery("select a.nombre, a.nombre, count(m.codigo) from AsignaturaClass a join a.lista_alumnos m group by a.nombre");
        List<Object[]> resultados = query.getResultList();

        for (Object[] resultado : resultados){
            String nombreAsignatura = (String) resultado[0];
            Long numeroAlumnos = (Long) resultado[1];

            System.out.println("==========================");
            System.out.println("Asignatura: " + nombreAsignatura);
            System.out.println("Número de alumnos matriculados: " + numeroAlumnos);
            System.out.println("==========================");
        }
    }


}
