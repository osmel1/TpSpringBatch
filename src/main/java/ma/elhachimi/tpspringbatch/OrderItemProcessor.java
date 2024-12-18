package ma.elhachimi.tpspringbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class OrderItemProcessor implements ItemProcessor<Order,Order> {
    private static final Logger logger=  LoggerFactory.getLogger(OrderItemProcessor.class);

    @Override
    public Order process(Order order) throws Exception {
        Order transformedOrder = new Order(order.orderId(), order.customerName(), order.amount() * 0.9);
        logger.info("Processing order "+order.orderId() +" for customer "+ order.customerName());
        return transformedOrder;
    }
}
