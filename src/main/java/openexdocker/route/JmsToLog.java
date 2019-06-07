package openexdocker.route;

import openexdocker.configuration.MyConfiguration;
import com.oxit.flow.message.Message;
import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JmsToLog extends RouteBuilder {

    @BeanInject("message")
    private Message message;

    @Autowired
    private MyConfiguration properties;

    @Override
    public void configure() throws Exception {
        /**
         * local-broker endpoint exists because a bean with this name has been defined in the OpenEx starter.
         */
        from("local-broker:" + properties.getName())
            .log("Message read from " + properties.getName() + " with id ${id}")
            .onCompletion().wireTap("direct-vm:traceMessage");
    }
}
