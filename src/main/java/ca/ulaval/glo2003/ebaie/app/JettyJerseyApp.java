package ca.ulaval.glo2003.ebaie.app;

import ca.ulaval.glo2003.ebaie.ping.application.assemblers.PingAssembler;
import ca.ulaval.glo2003.ebaie.ping.infrastructure.persistence.PingRepositoryInMemory;
import ca.ulaval.glo2003.ebaie.ping.api.PingResource;
import ca.ulaval.glo2003.ebaie.ping.api.assemblers.PingDtoAssembler;
import ca.ulaval.glo2003.ebaie.ping.application.PingUseCase;
import ca.ulaval.glo2003.ebaie.ping.entities.PingFactory;
import ca.ulaval.glo2003.ebaie.ping.entities.PingRepository;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.ws.rs.core.Application;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class JettyJerseyApp implements EBaieApplication {

  private final Server server;

  public JettyJerseyApp() {
    // Instantiate application context (dependencies)
    PingFactory pingFactory = new PingFactory(new Random());
    PingRepository pingRepository = new PingRepositoryInMemory();
    PingAssembler pingAssembler = new PingAssembler();
    PingUseCase pingUseCase = new PingUseCase(pingFactory, pingRepository, pingAssembler);

    PingDtoAssembler pingDtoAssembler = new PingDtoAssembler();
    PingResource pingResource = new PingResource(pingUseCase, pingDtoAssembler);

    // Setup api endpoints
    ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
    contextHandler.setContextPath("/api");
    ResourceConfig resourceConfig = ResourceConfig.forApplication(new Application() {
      @Override
      public Set<Object> getSingletons() {
        Set<Object> resources = new HashSet<>();
        resources.add(pingResource);
        return resources;
      }
    });

    // Setup server
    ServletContainer servletContainer = new ServletContainer(resourceConfig);
    ServletHolder servletHolder = new ServletHolder(servletContainer);
    contextHandler.addServlet(servletHolder, "/*");

    ContextHandlerCollection contexts = new ContextHandlerCollection();
    contexts.setHandlers(new Handler[]{contextHandler});
    server = new Server(new InetSocketAddress("localhost", 8080));
    server.setHandler(contexts);
  }

  @Override public void start() {
    try {
      server.start();
      server.join();
    } catch (Exception exception) {
      exception.printStackTrace();
    } finally {
      server.destroy();
    }
  }
}
