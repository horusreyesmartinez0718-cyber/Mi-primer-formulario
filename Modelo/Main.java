package model;

public class Main {
    public static void main(String[] args) {
        
        Gamer player1 = new Gamer(1, "Carlos López", "carlos99", "1234", 1500, "Avanzado");

        
        Team team = new Team(10, "CyberNinjas", new java.util.ArrayList<>(), player1, 1500);

        System.out.println("=== MODELO CREADO EXITOSAMENTE ===");
        System.out.println("Equipo: " + team.getNameTeam());
        System.out.println("Líder del equipo: " + team.getLeader().getName());
        System.out.println("Nivel del líder: " + team.getLeader().getLevel());
    }
}
