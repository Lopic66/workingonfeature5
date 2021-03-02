package ca.ulaval.glo2003.ebaie.ping.api.assemblers;

import ca.ulaval.glo2003.ebaie.ping.api.dtos.PingRequest;
import ca.ulaval.glo2003.ebaie.ping.api.dtos.PingResponseItem;
import ca.ulaval.glo2003.ebaie.ping.application.dtos.PingCreationDto;
import ca.ulaval.glo2003.ebaie.ping.application.dtos.PingDto;
import ca.ulaval.glo2003.ebaie.ping.api.dtos.PingsResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PingDtoAssembler {

  public PingCreationDto fromRequest(PingRequest request) {
    PingCreationDto dto = new PingCreationDto();
    dto.timestamp = LocalDateTime.parse(request.timestamp);

    return dto;
  }

  public PingResponseItem toResponse(PingDto pingDto) {
    PingResponseItem response = new PingResponseItem();
    response.id = pingDto.id;
    response.timestamp = pingDto.timestamp.toString();

    return response;
  }

  public PingsResponse toResponse(List<PingDto> pingDtos) {
    PingsResponse response = new PingsResponse();
    response.pings = pingDtos.stream().map(this::toResponse).collect(Collectors.toList());

    return response;
  }
}
