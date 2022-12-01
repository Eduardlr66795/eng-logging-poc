package com.eng.logger;

import com.eng.logger.objects.LogEntry;
import com.eng.logger.utils.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;

public class Logger {

    private static final org.slf4j.Logger logFactor = LoggerFactory.getLogger(Logger.class);

    /**
     * The logic in the following method is used to process and log an INFO level event that has occurred.
     *
     * @param logEntry - Object that contains all the relevant logging information.
     */
    public static void info(LogEntry logEntry) {
        processLogEntry(LogLevel.INFO, logEntry);
    }

    /**
     * The logic in the following method is used to process and log a WARN level event that has occurred.
     *
     * @param logEntry - Object that contains all the relevant logging information.
     */
    public static void warn(LogEntry logEntry) {
        processLogEntry(LogLevel.WARN, logEntry);
    }

    /**
     * The logic in the following method is used to process and log an ERROR level event that has occurred.
     *
     * @param logEntry - Object that contains all the relevant logging information.
     */
    public static void error(LogEntry logEntry) {
        processLogEntry(LogLevel.ERROR, logEntry);
    }

    // ---------------------
    //    PRIVATE METHODS
    // ---------------------

    /**
     * Genetic method that will process the various logLevel entries based on their logLevel INFO, WARN, ERROR and DEBUG
     *
     * @param logLevel - Information telling how important a given log event is
     * @param logEntry - Object that contains all the relevant logging information.
     */
    private static void processLogEntry(LogLevel logLevel, LogEntry logEntry) {
        switch (logLevel) {
            case INFO -> logFactor.info(getLogString(logEntry));
            case WARN -> logFactor.warn(getLogString(logEntry));
            case ERROR -> logFactor.error(getLogString(logEntry));
            default -> logFactor.debug(getLogString(logEntry));
        }
    }

    /**
     * The logic in the following method is used to create a single well formulated string message using the information
     * contained in the logEntry object.
     *
     * @param logEntry - Object that contains all the relevant logging information.
     * @return
     */
    private static String getLogString(LogEntry logEntry) {
        StringBuilder builder = new StringBuilder();

        // Append the tag attribute to the logging string
        builder.append(logTagFormatted(logEntry));

        // Append the description attribute to the logging string
        builder.append(descriptionFormatted(logEntry));

        // Append the additional attributes to the logging string
        if (logEntry.hasAttributes()) {
            builder.append(attributesFormatted(logEntry));
        }

        // Append the MDC attributes to the logging string
        if (logEntry.hasMdcAttributes()) {
            builder.append(mdcAttributesFormatted(logEntry));
        }

        return builder.toString();
    }

    private static String logTagFormatted(LogEntry logEntry) {
        return "%s: %s".formatted(Constants.LOGGER_LOG_TAG, logEntry.getTag());
    }

    private static String descriptionFormatted(LogEntry logEntry) {
        return "%s: %s".formatted(Constants.LOGGER_DESCRIPTION, logEntry.getDescription());
    }

    private static String mdcAttributesFormatted(LogEntry logEntry) {
        return "%s: %s".formatted(Constants.LOGGER_MDC_ATTRIBUTES, logEntry.getMdcAttributes());
    }

    private static String attributesFormatted(LogEntry logEntry) {
        return "%s: %s".formatted(Constants.LOGGER_ATTRIBUTES, logEntry.getAttributes());
    }
}
