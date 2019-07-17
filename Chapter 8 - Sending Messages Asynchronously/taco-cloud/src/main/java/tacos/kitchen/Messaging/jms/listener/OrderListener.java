package tacos.kitchen.Messaging.jms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import tacos.Order;
import tacos.kitchen.KitchenUI;

@Profile("jms-listener")
@Component
public class OrderListener {
	private KitchenUI ui;
	
	@Autowired
	public OrderListener(KitchenUI ui) {
		this.ui = ui;
	}
	
	/*
	 * listens for messages at 'tacocoud.order.queue.
	 * When messages arrive, the receiveOrder() method is invoked automatically with the messages's Order
	 * Payload as a parameter.
	 */	
	@JmsListener(destination = "tacocloud.order.queue")
	public void receiveOrder(Order order) {
		ui.displayOrder(order);
	}
}
