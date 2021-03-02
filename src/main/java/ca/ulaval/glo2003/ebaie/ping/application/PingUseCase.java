package ca.ulaval.glo2003.ebaie.ping.application;

import ca.ulaval.glo2003.ebaie.ping.application.assemblers.PingAssembler;
import ca.ulaval.glo2003.ebaie.ping.application.dtos.PingCreationDto;
import ca.ulaval.glo2003.ebaie.ping.application.dtos.PingDto;
import ca.ulaval.glo2003.ebaie.ping.entities.Ping;
import ca.ulaval.glo2003.ebaie.ping.entities.PingFactory;
import ca.ulaval.glo2003.ebaie.ping.entities.PingRepository;
import java.util.List;

public class PingUseCase {

  private final PingFactory pingFactory;
  private final PingRepository pingRepository;
  private final PingAssembler pingAssembler;

  public PingUseCase(PingFactory pingFactory,
                     PingRepository pingRepository,
                     PingAssembler pingAssembler) {
    this.pingFactory = pingFactory;
    this.pingRepository = pingRepository;
    this.pingAssembler = pingAssembler;
  }

  public void createPing(PingCreationDto dto) {
    Ping ping = pingFactory.create(dto.timestamp);
    pingRepository.save(ping);
  }

  public List<PingDto> getAllPings() {
    List<Ping> pings = pingRepository.findAll();
    return pingAssembler.toDtos(pings);
  }

  public void resetPing(int id) {
    Ping ping = pingRepository.findById(id);
    ping.reset();
    pingRepository.save(ping);
  }
}
