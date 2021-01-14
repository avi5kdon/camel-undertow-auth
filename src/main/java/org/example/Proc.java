package org.example;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.servlet.http.HttpServletRequest;

public class Proc implements Processor {
    @Override
    public void process(Exchange oldExchange) throws Exception {
        System.out.println(">>> "+oldExchange.getIn(HttpServletRequest.class));
        HttpServletRequest httpServletRequest = oldExchange.getIn().getBody(HttpServletRequest.class);
       /* ConsumerTemplate consumerTemplate = oldExchange.getContext().createConsumerTemplate();
        while (true) {
            consumerTemplate.start();
            Exchange newExchange = consumerTemplate.receive("file:/home/avinash/Music/test/first/", 5000);
            if (newExchange == null){
                break;
            }
            oldExchange.getContext().createProducerTemplate().send("direct:business", newExchange);
            consumerTemplate.doneUoW(newExchange);
            consumerTemplate.stop();
        }*/
        System.out.println(httpServletRequest);
    }
}
