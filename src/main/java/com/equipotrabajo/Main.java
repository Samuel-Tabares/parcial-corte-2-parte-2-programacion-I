package com.equipotrabajo;

import com.equipotrabajo.entity.TeamMember;
import com.equipotrabajo.repository.JpaTeamMemberRepository;
import com.equipotrabajo.service.TeamMemberService;
import com.equipotrabajo.service.TeamMemberServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TeamMemberService service = new TeamMemberServiceImpl(new JpaTeamMemberRepository());

        // Crear miembros del equipo
        TeamMember member1 = new TeamMember("Alice", "Developer");
        TeamMember member2 = new TeamMember("Bob", "Designer");
        TeamMember member3 = new TeamMember("Charlie", "Manager");

        // Agregar miembros al equipo
        service.addTeamMember(member1);
        service.addTeamMember(member2);
        service.addTeamMember(member3);

        // Listar todos los miembros
        List<TeamMember> members = service.getAllTeamMembers();
        System.out.println("Lista de miembros del equipo:");
        for (TeamMember member : members) {
            System.out.println(member);
        }

        // Actualizar un miembro
        member1.setRole("Senior Developer");
        service.updateTeamMember(member1);

        // Obtener y mostrar el miembro actualizado
        TeamMember updatedMember = service.getTeamMember(member1.getId());
        System.out.println("Miembro actualizado: " + updatedMember);

        // Eliminar un miembro
        service.removeTeamMember(member2.getId());

        // Mostrar la lista actualizada de miembros después de la eliminación
        members = service.getAllTeamMembers();
        System.out.println("Lista de miembros del equipo después de la eliminación:");
        for (TeamMember member : members) {
            System.out.println(member);
        }
    }
}
