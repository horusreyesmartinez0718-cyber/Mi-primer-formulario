package view;

import controller.GamerController;
import model.Gamer;
import java.util.Scanner;

public class GamerView {
    private GamerController controller;
    private Scanner scanner;

    public GamerView() {
        this.controller = new GamerController();
        this.scanner = new Scanner(System.in);
    }

    public void iniciarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n=== GESTIÓN DE GAMERS (FORMULARIO VISTA) ===");
            System.out.println("1. CREATE (Crear/Agregar jugador)");
            System.out.println("2. READ (Buscar jugador por ID)");
            System.out.println("3. UPDATE (Modificar jugador)");
            System.out.println("4. DELETE (Borrar jugador)");
            System.out.println("5. Ver todos los jugadores");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    procesoCreate();
                    break;
                case 2:
                    procesoRead();
                    break;
                case 3:
                    procesoUpdate();
                    break;
                case 4:
                    procesoDelete();
                    break;
                case 5:
                    listarJugadores();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }

    private void procesoCreate() {
        System.out.println("\n--- CREATE: Agregar Jugador ---");
        System.out.print("Ingrese ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Ingrese Nombre: ");
        String name = scanner.nextLine();
        
        System.out.print("Ingrese Usuario: ");
        String user = scanner.nextLine();
        
        System.out.print("Ingrese Contraseña: ");
        String pass = scanner.nextLine();
        
        System.out.print("Ingrese Puntaje (score): ");
        long score = scanner.nextLong();
        scanner.nextLine();
        
        System.out.print("Ingrese Nivel: ");
        String level = scanner.nextLine();

        // Creación del objeto e inserción a través del controlador
        Gamer nuevoGamer = new Gamer(id, name, user, pass, score, level);
        controller.addGamer(nuevoGamer);
        System.out.println("¡Jugador agregado exitosamente!");
    }

    private void procesoRead() {
        System.out.println("\n--- READ: Buscar Jugador ---");
        System.out.print("Ingrese el ID del jugador a buscar: ");
        int id = scanner.nextInt();

        Gamer encontrado = controller.searchGamer(id);
        if (encontrado != null) {
            System.out.println("¡Jugador Encontrado!");
            System.out.println("Nombre: " + encontrado.getName());
            System.out.println("Usuario: " + encontrado.getUser());
            System.out.println("Puntaje: " + encontrado.getScore());
            System.out.println("Nivel: " + encontrado.getLevel());
        } else {
            System.out.println("Error: El jugador con ID " + id + " no fue encontrado.");
        }
    }

    private void procesoUpdate() {
        System.out.println("\n--- UPDATE: Modificar Jugador ---");
        System.out.print("Ingrese el ID del jugador que desea actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Gamer existente = controller.searchGamer(id);
        if (existente != null) {
            System.out.print("Nuevo Nombre (" + existente.getName() + "): ");
            existente.setName(scanner.nextLine());
            
            System.out.print("Nuevo Usuario (" + existente.getUser() + "): ");
            existente.setUser(scanner.nextLine());

            System.out.print("Nuevo Puntaje (" + existente.getScore() + "): ");
            existente.setScore(scanner.nextLong());
            scanner.nextLine();

            controller.modifyGamer(existente);
            System.out.println("¡Jugador actualizado correctamente!");
        } else {
            System.out.println("No se puede actualizar. El ID no existe.");
        }
    }

    private void procesoDelete() {
        System.out.println("\n--- DELETE: Eliminar Jugador ---");
        System.out.print("Ingrese el ID del jugador a eliminar: ");
        int id = scanner.nextInt();

        Gamer existente = controller.searchGamer(id);
        if (existente != null) {
            controller.deleteGamer(existente);
            System.out.println("¡Jugador eliminado correctamente de la base de datos!");
        } else {
            System.out.println("Error: No se encontró el jugador a eliminar.");
        }
    }

    private void listarJugadores() {
        System.out.println("\n--- LISTA DE JUGADORES EN MEMORIA ---");
        for (Gamer g : controller.getGamerList()) {
            System.out.println("ID: " + g.getIdGamer() + " | Nombre: " + g.getName() + " | Usuario: " + g.getUser() + " | Nivel: " + g.getLevel());
        }
    }
}
