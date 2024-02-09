package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;

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

        transaction.commit();
        session.save(alumnoClass);
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

        transaction.commit();
        session.save(asignaturaClass);

    }

    private static void insercionMatriculas() {

    }

    private static void mostrarAlumnos() {
    }

    private static void mostrarAsignaturas() {
    }

    private static void mostrarAsignaturasAlumnos() {
    }

    private static void mostrarAlumnosAsignatura() {
    }

    private static void mostrarLisadoAlumno() {
    }

    private static void mostrarAlumnosMatriculados() {
    }
}
