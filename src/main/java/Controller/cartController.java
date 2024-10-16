package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.cart;
import models.product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import DBConnection.ConnectJDBC;

public class cartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public cartController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    List<cart> carts = new ArrayList<cart>();
		String sql = "select book.code, describe, cost, SoLuong from cart, book where cart.code = book.code";
		try {
			Connection conn = new ConnectJDBC().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {	
				carts.add(new cart(rs.getInt("SoLuong"), new product(rs.getInt("code"), rs.getString("describe"), rs.getInt("cost"))));	
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			response.getWriter().println("Error: " + ex.getMessage());
		}
    	
    	// Tinh tong tien thanh toan
    	int total = 0;
    	for (cart item : carts) {
    		total += item.getCount() * item.getSp().getCost();
    	}
    	request.setAttribute("carts", carts);
    	request.setAttribute("total", total);
		 RequestDispatcher req = request.getRequestDispatcher("/views/cart.jsp");
	     req.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("put")) {
			doPut(request, response);
		}
		else {
			String code = request.getParameter("code");
			String deleteSQL = "delete from cart where code = ?";
			try {
				Connection conn = new ConnectJDBC().getConnection();
				PreparedStatement ps = conn.prepareStatement(deleteSQL);
				ps.setInt(1, Integer.parseInt(code));
				ps.executeUpdate();
			}
			catch (Exception ex) {
				ex.printStackTrace();
				response.getWriter().println("Error: " + ex.getMessage());
			}
			doGet(request, response);
		}
		
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String quatity = request.getParameter("quantity");
		String code = request.getParameter("code"); 
		String updateSQL = "update cart set SoLuong = ? where code = ?";
		try {
			Connection conn = new ConnectJDBC().getConnection();
			PreparedStatement ps = conn.prepareStatement(updateSQL);
			ps.setInt(1, Integer.parseInt(quatity));
			ps.setInt(2, Integer.parseInt(code));
			ps.executeUpdate();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			response.getWriter().println("Error: " + ex.getMessage());
		}
		doGet(request, response);
		
	}
}
