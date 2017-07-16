package com.cts.debtmanager.routes;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.debtmanager.model.Payment;
import com.cts.debtmanager.model.Plan;
import com.cts.debtmanager.service.RepaymentPlanService;

@Component
public class DebtManagerRouter extends RouteBuilder {
	static final Logger log = LoggerFactory.getLogger(DebtManagerRouter.class);

	@Autowired
	private ProducerTemplate producerTemplate;

	@Autowired
	private ConsumerTemplate consumerTemplate;

	@Override
	public void configure() throws Exception {
		from("{{inbound.endpoint}}")
		.unmarshal().json(JsonLibrary.Jackson, Plan.class)
		.bean(RepaymentPlanService.class, "processPlan")
		.process(new Processor(){

			@Override
			public void process(Exchange exchange) throws Exception {
				Payment payment = exchange.getIn().getBody(Payment.class);
				log.info("Payment Message.....:"+payment.getPayment());
			}
			
		})
		.marshal().json(JsonLibrary.Jackson)
		.to("{{outbound.endpoint}}")
		.log(LoggingLevel.INFO, log, "Message Sent.")
		.end();
	}

//	@Override
//	public void configure() throws Exception {
//		from("{{inbound.endpoint}}")
//		.transacted()
//		.log(LoggingLevel.INFO, log, "Received Message")
//		.process(new Processor(){
//
//			@Override
//			public void process(Exchange exchange) throws Exception {
//				String message = exchange.getIn().getBody(String.class);
//				log.info("Message.....:"+message);
//				log.info("Exchange: {}", exchange);
//			}
//			
//		})
//		.loop()
//		.simple("{{outboud.loop.count}}")
//		.to("{{outbound.endpoint}}")
//		.log(LoggingLevel.INFO, log, "Message Sent. Iteration: ${property.CamelLoopIndex}")
//		.end();
//	}

//	@Override
//	public void configure() throws Exception {
//
//		// Producer route
//		from("timer://test?period=5000").process(new Processor() {
//			@Override
//			public void process(Exchange exchange) throws Exception {
//				String message = UUID.randomUUID().toString();
//				log.info("**********************************");
//				log.info("Send message '{}' to queue....", message);
//				producerTemplate.sendBody("activemq://test-queue", message);
//			}
//		});
//
//		// Consumer queue
//		from("activemq://test-queue").process(new Processor() {
//			@Override
//			public void process(Exchange exchange) throws Exception {
//
//				String message = exchange.getIn().getBody(String.class);
//				log.info("--------------------------------");
//				log.info("Receive message '{}' from queue.", message);
//			}
//		});

//	}

}
