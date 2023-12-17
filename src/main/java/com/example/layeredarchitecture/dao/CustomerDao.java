package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDao {
    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
   boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;
   void updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;
  boolean existCustomer(String id) throws SQLException, ClassNotFoundException ;
  void deleteCustomer(String id) throws SQLException, ClassNotFoundException ;

   String genarateId() throws SQLException, ClassNotFoundException ;
}
