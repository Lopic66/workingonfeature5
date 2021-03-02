package ca.ulaval.glo2003.ebaie;

import ca.ulaval.glo2003.ebaie.app.EBaieApplication;
import ca.ulaval.glo2003.ebaie.app.JettyJerseyApp;

@SuppressWarnings("all")
public class EBaieMain {

  public static void main(String[] args) throws Exception {
    EBaieApplication app = new JettyJerseyApp();
    app.start();
  }
}
