package top.anyel.ncapas.shared.utils.logger;


import org.springframework.stereotype.Component;

@Component
public class CustomLoggerFactoryService {
    public CustomLogger getLogger(Class<?> clazz) {
        return new CustomLogger(clazz);
    }
}
