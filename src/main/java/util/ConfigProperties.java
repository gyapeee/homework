package util;

import org.aeonbits.owner.Config;

@Config.Sources("file:src/test/resources/config.properties")
public interface ConfigProperties extends Config {
  @Key("headless")
  boolean headless();

  @Key("disable.dev.shm.usage")
  boolean disableDevShmUsage();

  @Key("disable.gpu")
  boolean disableGpu();
}
