package ca.ulaval.glo2003.ebaie.ping.entities;

import java.util.List;

public interface PingRepository {

  List<Ping> findAll();

  Ping findById(int id);

  void save(Ping ping);
}
