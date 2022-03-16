<%--
  Created by IntelliJ IDEA.
  User: habib
  Date: 3/16/22
  Time: 12:14 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="includes/header.jsp"%>
<%@ include file="includes/navigation.jsp"%>

<div class="container">
  <h3>Your Cart</h3>
  <div class="row">
    <table class="table table-hover">
      <thead>
      <tr>
        <th class="w-50">Name - Description</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>#</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="cartItem" items="${cart.cartItems}">
        <tr>
          <td>
            <c:out value="${cartItem.product.name}"/> 
            -
            <c:out value="${cartItem.product.description}"/>
          </td>
          <td>
            <div class="btn-group" role="group">
              <a class="btn btn-outline-warning"> - </a>
              <button type="button" class="btn">
                <c:out value="${cartItem.quantity}"></c:out>
              </button>
              <a class="btn btn-outline-success"> + </a>
            </div>
          </td>
          <td>
            <a href="#" class="btn-outline-warning">Remove</a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
      
      <tfoot>
      <tr>
        <td>
          <h4>
            Subtotal (<c:out value="${cart.totalItem} items"/>):
            $<c:out value="${cart.totalPrice}"/>
          </h4>
        </td>
        <td></td>
        <td>
          <a href="<c:url value="/" />" class="btn btn-outline-success text-right">Proceed to Checkout</a>
        </td>
      </tr>
      </tfoot>
    </table>
    
  </div>
</div>

<%@ include file="includes/footer.jsp"%>
