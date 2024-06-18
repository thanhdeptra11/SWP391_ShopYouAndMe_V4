/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Cart;
import model.Item;
import model.User;

/**
 *
 * @author admin
 */
public class CartDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public void addCart(User u, Cart cart, String payment, String address, int phone) {
        String date = curDate.toString();
        
        try {
            String sql="insert into [bill] values(?,?,?,?,?,?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, u.getUser_id());
            ps.setDouble(2, cart.getTotalMoney());
            ps.setString(3, payment);
            ps.setString(4, address);
            ps.setString(5, date);
            ps.setInt(6, phone);
            ps.executeUpdate();
            
            String sql1="select top 1 bill_id from [bill] order by bill_id desc";
            ps = conn.prepareStatement(sql1);
            rs = ps.executeQuery();
            
            if(rs.next()){
                int bill_id=rs.getInt(1);
                for(Item i:cart.getItems()){
                String sql2="insert into [bill_detail] values(?,?,?,?,?,?)";
                double total = i.getQuantity()*i.getProduct().getProduct_price();
                ps = conn.prepareStatement(sql2);
                ps.setInt(1, bill_id);
                ps.setString(2, i.getProduct().getProduct_id());
                ps.setInt(3, i.getQuantity());
                ps.setString(4, i.getSize());
                ps.setString(5, i.getColor());
                ps.setDouble(6, total);
                ps.executeUpdate();
            }
            }

            String sql3 = "update product set quantity = quantity - ? "
                        + "where product_id = ?";
            ps = conn.prepareStatement(sql3);
            for(Item i:cart.getItems()){
                ps.setInt(1, i.getQuantity());
                ps.setString(2, i.getProduct().getProduct_id());
                ps.executeUpdate();
            }

        } catch (Exception e) {
        }
    }
    
}
