package assignment8.main;

import assignment8.dataServices.MessageService;
import assignment8.dataServices.UserService;
import com.owlike.genson.GensonBuilder;
import com.owlike.genson.ext.jaxrs.GensonJaxRSFeature;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class Application extends ResourceConfig {
    public Application() {
        super();
        registerClasses(getServiceClasses());
        packages("org.glassfish.jersey.examples.linking");
        register(DeclarativeLinkingFeature.class);
        register(MultiPartFeature.class);
        register(CorsFilter.class);
        register(new GensonJaxRSFeature().use(
                new GensonBuilder().setSkipNull(true)
                        .useIndentation(true)
                        .useDateAsTimestamp(false)
                        .useDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"))
                        .create()));
    }

    protected Set<Class<?>> getServiceClasses() {
        final Set<Class<?>> returnValue = new HashSet<>();

        returnValue.add(UserService.class);
        returnValue.add(MessageService.class);
        returnValue.add(StartService.class);

        return returnValue;
    }
}
