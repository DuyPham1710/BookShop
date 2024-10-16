package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.cart;
import models.product;
import DBConnection.ConnectJDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class productController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public productController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<product> allBook = new ArrayList<product>();
		String sql = "Select * from book";
		try {
			Connection conn = new ConnectJDBC().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {	
				allBook.add(new product(rs.getInt("code"), rs.getString("describe"), rs.getInt("cost")));	
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			response.getWriter().println("Error: " + ex.getMessage());
		}
		request.setAttribute("allBooks", allBook);
		
		sql = "select count(*) as totalItems from cart";
		try {
			Connection conn = new ConnectJDBC().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				request.setAttribute("totalItems", rs.getInt("totalItems"));
		    }
		}
		catch (Exception ex) {
			ex.printStackTrace();
			response.getWriter().println("Error: " + ex.getMessage());
		}
    	
    	
		RequestDispatcher req = request.getRequestDispatcher("/views/home.jsp");
		req.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("get")) {
			doGet(request, response);
		}
		else {
			String code = request.getParameter("code");
	        
	        String checkSql = "SELECT SoLuong FROM cart WHERE code = ?";
	        String updateSql = "UPDATE cart SET SoLuong = SoLuong + 1 WHERE code = ?";
	        String insertSql = "INSERT INTO cart (code, SoLuong) VALUES (?, ?)";
	        
	        try {
	            Connection conn = new ConnectJDBC().getConnection();
	            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
	            checkStmt.setInt(1, Integer.parseInt(code));
	            ResultSet rs = checkStmt.executeQuery();
	            if (rs.next()) {
	                // Nếu sản phẩm đã tồn tại, cập nhật số lượng
	                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
	                updateStmt.setInt(1, Integer.parseInt(code));
	                updateStmt.executeUpdate();
	            } 
	            else {
	                // Nếu sản phẩm chưa có, thêm mới sản phẩm vào giỏ hàng
	                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
	                insertStmt.setInt(1, Integer.parseInt(code));
	                insertStmt.setInt(2, 1);
	                insertStmt.executeUpdate();
	            }
	            
	        } 
	        catch (Exception ex) {
	            ex.printStackTrace();
	            response.getWriter().println("Error: " + ex.getMessage());
	        }
	    
	    	response.sendRedirect("cartController");
		}
	
	}

}
