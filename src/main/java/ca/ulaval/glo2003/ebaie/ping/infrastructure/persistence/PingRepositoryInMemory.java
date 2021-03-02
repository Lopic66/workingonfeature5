package ca.ulaval.glo2003.ebaie.ping.infrastructure.persistence;

import ca.ulaval.glo2003.ebaie.ping.entities.Ping;
import ca.ulaval.glo2003.ebaie.ping.entities.PingRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PingRepositoryInMemory implements PingRepository {

  private final Map<Integer, Ping> pingsById = new HashMap<>();

  @Override public List<Ping> findAll() {
    return new ArrayList<>(pingsById.values());
  }

  @Override public Ping findById(int id) {
    return pingsById.get(id);
  }

  @Override public void save(Ping ping) {
    pingsById.put(ping.getId(), ping);
  }
}
