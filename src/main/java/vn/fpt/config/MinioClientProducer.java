package vn.fpt.config;

import io.minio.MinioClient;
import io.quarkus.arc.DefaultBean;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public class MinioClientProducer {

    @ConfigProperty(name = "quarkus.minio.url")
    String URL;

    @ConfigProperty(name = "quarkus.minio.access-key")
    String ACCESS_KEY;

    @ConfigProperty(name = "quarkus.minio.secret-key")
    String SECRET_KEY;

    @Produces
    @Singleton
    @DefaultBean
    public MinioClient produceMinioClient() {
        // Initialize MinioClient with your configuration
        return MinioClient.builder()
                .endpoint(URL)
                .credentials(ACCESS_KEY, SECRET_KEY)
                .build();
    }
}
