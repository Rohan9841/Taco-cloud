package tacos.messaging;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import tacos.Order;

@Service
public class JmsOrderMessagingService implements OrderMessagingService{
	
	private JmsTemplate jms;
	
	@Autowired
	public JmsOrderMessagingService(JmsTemplate jms) {
		this.jms = jms;
	}
	
	/*
	 * Converts the Order(Json object) to message automatically(Using Mapping2JacksonConverter),
	 *  uses addSource method to add a Order_Source property to the converted message and sends 
	 *  it to tacocloud.order.queue destination.T
	 */
	@Override
	public void sendOrder(Order order) {
		jms.convertAndSend("tacocloud.order.queue",order, this::addOrderSource);
	}
	
	/* method to add an Order_Source property to the message in the parameter */
	private Message addOrderSource(Message message) throws JMSException{
		message.setStringProperty("Order_Source","WEB");
		return message;
	}
}
