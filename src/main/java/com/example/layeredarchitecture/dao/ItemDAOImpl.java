package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDao {
    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Item");

            ArrayList <ItemDTO> itemDTOS=new ArrayList<>();

            while (rst.next()){
                itemDTOS.add(new ItemDTO(
                        rst.getString("code"),
                        rst.getString("description"),
                        rst.getBigDecimal("unitPrice"),
                        rst.getInt("qtyOnHand")
                ));
            }
            return getAllItems();
        }

        @Override
        public  boolean SaveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");

            pstm.setString(1, dto.getCode());
            pstm.setString(2, dto.getDescription());
            pstm.setBigDecimal(3, dto.getUnitPrice());
            pstm.setInt(4,dto.getQtyOnHand());

           return pstm.executeUpdate()>0;
        }

        @Override
        public boolean  updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");

            pstm.setString(1, dto.getDescription());
            pstm.setBigDecimal(2,dto.getUnitPrice());
            pstm.setInt(3, dto.getQtyOnHand());
            pstm.setString(4, dto.getCode());
            return pstm.executeUpdate() >0;
        }

        @Override
        public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
            pstm.setString(1,code);
            return pstm.executeUpdate()>0;

        }

        @Override
        public boolean existItem (String code) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
            pstm.setString(1, code);
            return pstm.executeQuery().next();
        }

        @Override
        public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();
            ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");

            return rst;
        }

}
