package ca.ulaval.glo2003.ebaie.ping.api;

import ca.ulaval.glo2003.ebaie.ping.application.dtos.PingCreationDto;
import ca.ulaval.glo2003.ebaie.ping.application.dtos.PingDto;
import ca.ulaval.glo2003.ebaie.ping.api.assemblers.PingDtoAssembler;
import ca.ulaval.glo2003.ebaie.ping.api.dtos.PingRequest;
import ca.ulaval.glo2003.ebaie.ping.api.dtos.PingsResponse;
import ca.ulaval.glo2003.ebaie.ping.application.PingUseCase;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("ping")
public class PingResource {

  private final PingUseCase pingUseCase;
  private final PingDtoAssembler pingDtoAssembler;

  public PingResource(PingUseCase pingUseCase,
                      PingDtoAssembler pingDtoAssembler) {
    this.pingUseCase = pingUseCase;
    this.pingDtoAssembler = pingDtoAssembler;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllPings() {
    List<PingDto> pings = pingUseCase.getAllPings();
    PingsResponse response = pingDtoAssembler.toResponse(pings);
    return Response.ok().entity(response).build();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createPing(PingRequest pingRequest) {
    PingCreationDto dto = pingDtoAssembler.fromRequest(pingRequest);
    pingUseCase.createPing(dto);
    return Response.status(Status.CREATED).build();
  }

  @Path("{id}/reset")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response resetPing(@PathParam("id") String pingIdString) {
    int pingId = Integer.parseInt(pingIdString);
    pingUseCase.resetPing(pingId);
    return Response.noContent().build();
  }
}
