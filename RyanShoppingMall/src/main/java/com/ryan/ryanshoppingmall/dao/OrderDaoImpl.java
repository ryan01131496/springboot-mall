package com.ryan.ryanshoppingmall.dao;

import com.ryan.ryanshoppingmall.model.Order;
import com.ryan.ryanshoppingmall.model.OrderItem;
import com.ryan.ryanshoppingmall.rowmapper.OrderItemRowMapper;
import com.ryan.ryanshoppingmall.rowmapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrder(Integer userId, int totalAmount) {
        String sql = "INSERT INTO `order` (user_id, total_amount, created_date, last_modified_date) \n" +
                "VALUES (:userId, :totalAmount, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);

        Date date = new Date();
        map.put("createdDate", date);
        map.put("lastModifiedDate", date);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map), keyHolder);

        int orderId = keyHolder.getKey().intValue();

        return orderId;
    }

    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {
        for (OrderItem orderItem : orderItemList) {
            String sql = "INSERT INTO order_item (order_id, product_id, quantity, amount) \n" +
                    "VALUES (:orderId, :productId, :quantity, :amount)";

            Map<String, Object> map = new HashMap<>();
            map.put("orderId", orderId);
            map.put("productId", orderItem.getProductId());
            map.put("quantity", orderItem.getQuantity());
            map.put("amount", orderItem.getAmount());

            namedParameterJdbcTemplate.update(sql, map);
        }
    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "SELECT order_id, user_id, total_amount, created_date, last_modified_date\n" +
                "FROM `order`\n" +
                "WHERE order_id = :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        OrderRowMapper orderRowMapper = new OrderRowMapper();

        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, orderRowMapper);

        if (orderList.size() > 0) {
            return orderList.get(0);
        } else {
            return null;
        }

    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        String sql = "SELECT oi.order_item_id, oi.order_id, oi.product_id, oi.quantity, oi.amount, p.product_name, p.image_url\n" +
                "FROM order_item AS oi\n" +
                "LEFT JOIN product AS p ON oi.product_id = p.product_id\n" +
                "WHERE oi.order_id = :orderId\n";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        OrderItemRowMapper orderItemRowMapper = new OrderItemRowMapper();

        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql, map, orderItemRowMapper);

        return orderItemList;
    }
}
