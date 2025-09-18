package com.taskmanager.controller;

import com.taskmanager.model.TeamMember;
import com.taskmanager.service.TeamMemberService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/team-members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamMemberController {

    @Inject
    TeamMemberService teamMemberService;

    @GET
    public List<TeamMember> getAllTeamMembers() {
        return teamMemberService.getAllTeamMembers();
    }

    @GET
    @Path("/{id}")
    public Response getTeamMemberById(@PathParam("id") Long id) {
        TeamMember teamMember = teamMemberService.getTeamMemberById(id);
        if (teamMember != null) {
            return Response.ok(teamMember).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response createTeamMember(TeamMember teamMember) {
        try {
            TeamMember created = teamMemberService.createTeamMember(teamMember);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateTeamMember(@PathParam("id") Long id, TeamMember teamMember) {
        try {
            TeamMember updated = teamMemberService.updateTeamMember(id, teamMember);
            if (updated != null) {
                return Response.ok(updated).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTeamMember(@PathParam("id") Long id) {
        boolean deleted = teamMemberService.deleteTeamMember(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
