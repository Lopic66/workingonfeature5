package ca.ulaval.glo2003.ebaie.ping.entities;

import java.time.LocalDateTime;

public class Ping {

  private final int id;
  private LocalDateTime timestamp;

  public Ping(int id, LocalDateTime timestamp) {
    this.id = id;
    this.timestamp = timestamp;
  }

  public void reset() {
    timestamp = LocalDateTime.now();
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public int getId() {
    return id;
  }
}
