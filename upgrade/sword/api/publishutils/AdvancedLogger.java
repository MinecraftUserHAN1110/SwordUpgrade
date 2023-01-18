package upgrade.sword.api.publishutils;

import org.bukkit.Bukkit;

import java.util.logging.Logger;

public class AdvancedLogger {
    private Logger logger;
    public AdvancedLogger(Logger logger) {
        this.logger = logger;
    }

    public AdvancedLogger(String name) {
        this.logger = Logger.getLogger(name);
    }

    public AdvancedLogger() {
        this.logger = Logger.getGlobal();
    }

    public AdvancedLogger changeToBukkitLogger() {
        this.logger = Bukkit.getLogger();
        return this;
    }

    public void info(String message) {
        logger.info(message);
    }

    public void warn(String message) {
        logger.warning(message);
    }

    public void info(String... str) {
        for (String value : str) {
            logger.info(value);
        }
    }

    public void warn(String... str) {
        for (String value : str) {
            logger.warning(value);
        }
    }

    public void info(String breakTime, String... str) {
        for (String value : str) {
            logger.info(value);

            if (value.equalsIgnoreCase(breakTime)) {
                break;
            }
        }
    }

    public void warn(String breakTime, String... str) {
        for (String value : str) {
            logger.warning(value);

            if (value.equalsIgnoreCase(breakTime)) {
                break;
            }
        }
    }
}
