package ca.ulaval.glo2003.ebaie.ping.entities;

import java.time.LocalDateTime;
import java.util.Random;

public class PingFactory {

  private final Random random;

  public PingFactory(Random random) {
    this.random = random;
  }

  public Ping create(LocalDateTime timestamp) {
    int id = random.nextInt() & Integer.MAX_VALUE; // prevents negative ids
    return new Ping(id, timestamp);
  }
}
