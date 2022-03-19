package com.habib.eshop.repository;

import com.habib.eshop.domain.CartItem;
import com.habib.eshop.jdbc.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class JdbcCartItemRepositoryImpl implements CartItemRepository{
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcCartItemRepositoryImpl.class);

    private DataSource dataSource = ConnectionPool.getInstance().getDataSource();

    private static final String INSERT_CART_ITEM = "INSERT INTO cart_item (" +
            "quantity, " +
            "price, " +
            "product_id " +
            "version, " +
            "date_created, " +
            "date_last_updated) " +
            "VALUES (?,?,?,?,?,?)";
    @Override
    public CartItem save(CartItem cartItem){
        try(var connection = dataSource.getConnection(); var preparedStatement = connection.prepareStatement(INSERT_CART_ITEM, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setInt(1, cartItem.getQuantity());
            preparedStatement.setBigDecimal(2, cartItem.getPrice());
            preparedStatement.setLong(3, cartItem.getProduct().getId());
            preparedStatement.setLong(4, 0L);
            preparedStatement.setTimestamp(5, Timestamp.valueOf(cartItem.getDateCreated()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(cartItem.getDateLastUpdated()));

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows == 0){
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                if(generatedKeys.next()){
                    long cretItemId = generatedKeys.getLong(1);
                    cartItem.setId(cartItemId);
                    return cartItem;
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException ex){
            LOGGER.info("Unable to insert cart item in database: {}", cartItem, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public CartItem update(CartItem cartItem){
        cartItem.setVersion(cartItem.getVersion()+1);

        var cartItemToUpdate = findOne(cartItem.getId()).orElseThrow(()-> new CartNotFoundException(
                "Cart item not found by Id, +" + cartItem.getId()
        ));

        if(cartItem.getVersion() <= (cartItemToUpdate.getVersion())){
            throw new OptimisticLockingFailureException("CartItem is already updated by another request");
        }

        cartItemToUpdate.setDateLastUpdated(LocalDateTime.now());
        cartItemToUpdate.setVersion(cartItem.getVersion());
        cartItemToUpdate.setProduct(cartItem.getProduct());
        cartItemToUpdate.setQuantity(cartItem.getQuantity());
        cartItemToUpdate.setPrice(cartItem.getPrice());

        try(var connection = dataSource.getConnection(); var preparedStatement = connection.prepareStatement(UPDATE_CART_ITEM)){
            preparedStatement.setInt(1, cartItemToUpdate.getQuantity());
            preparedStatement.setBigDecimal(2, cartItemToUpdate.getPrice());
            preparedStatement.setLong(3, cartItemToUpdate.getVersion());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(cartItemToUpdate.getDateLastUpdated()));
            preparedStatement.setLong(5, cartItemToUpdate.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            LOGGER.error("Unable to updatee cart time: {}", cartItem, e);
            throw new RuntimeException("Unable to update cartItem", e);
        }
        return cartItemToUpdate;
    }

    @Override
    public void remove(CartItem cartItem){
    }
}
