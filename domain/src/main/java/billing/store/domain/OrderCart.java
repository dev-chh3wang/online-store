package billing.store.domain;

import java.math.BigDecimal;
import java.util.*;

public class OrderCart {

    private Set<OrderLine> items;

    public OrderCart() {
        items = new LinkedHashSet<>();
    }

    public void add(Product item,int qty){
        Optional<OrderLine> itemAlreadyExists = findOrderItem(item);
        if (itemAlreadyExists.isPresent()) {
            OrderLine or = itemAlreadyExists.get();
            or.setQty(or.getQty()+qty);
        }else{
            OrderLine orderLine = new OrderLine(item, qty);
            items.add(orderLine);
        }
    }


    public void remove(Product item){
        if(findOrderItem(item).isPresent()){
            items.remove(findOrderItem(item).get());
        }
    }

    public void update(Product item, int newQty){
        findOrderItem(item).ifPresent(order->{
            order.setQty(newQty);
        });
    }

    private Optional<OrderLine> findOrderItem(Product item) {
        return items.stream().filter(it -> it.getProduct().equals(item)).findFirst();
    }

    public List<OrderLine> getItems() {
        return new ArrayList(items);
    }

    public BigDecimal totalPrice(){
        return items.stream().map(it->it.price()).reduce(BigDecimal.valueOf(0),(subtotal,perPrice)->subtotal.add(perPrice));
    }

}
