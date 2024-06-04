package top.anyel.ncapas.shared.utils.logger;

public interface ICustomLoggerService {
    void logInfo(String message);
    void logInfo(String message, Exception e);
    void logDebug(String message);
    void logDebug(String message, Exception e);
    void logWarn(String message);
    void logWarn(String message, Exception e);
    void logError(String message, Exception e);
    void logError(String message);

}
