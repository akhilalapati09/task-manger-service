package com.taskmanager.controller;

import com.taskmanager.model.Member;
import com.taskmanager.service.MemberService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MemberController {

    @Inject
    MemberService memberService;

    @GET
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GET
    @Path("/{id}")
    public Response getMemberById(@PathParam("id") Long id) {
        Member member = memberService.getMemberById(id);
        if (member != null) {
            return Response.ok(member).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response createMember(Member member) {
        try {
            Member created = memberService.createMember(member);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateMember(@PathParam("id") Long id, Member member) {
        try {
            Member updated = memberService.updateMember(id, member);
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
    public Response deleteMember(@PathParam("id") Long id) {
        boolean deleted = memberService.deleteMember(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
