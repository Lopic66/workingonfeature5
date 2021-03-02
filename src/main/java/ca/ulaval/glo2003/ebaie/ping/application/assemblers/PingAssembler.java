package ca.ulaval.glo2003.ebaie.ping.application.assemblers;

import ca.ulaval.glo2003.ebaie.ping.application.dtos.PingDto;
import ca.ulaval.glo2003.ebaie.ping.entities.Ping;
import java.util.List;
import java.util.stream.Collectors;

public class PingAssembler {

  public List<PingDto> toDtos(List<Ping> pings) {
    return pings.stream().map(this::toDto).collect(Collectors.toList());
  }

  public PingDto toDto(Ping ping) {
    PingDto dto = new PingDto();
    dto.id = ping.getId();
    dto.timestamp = ping.getTimestamp();

    return dto;
  }
}
