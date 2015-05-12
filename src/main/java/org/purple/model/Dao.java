package org.purple.model;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Dao<T> {
  protected Connection connect = null;
   
  public Dao(Connection co){
    this.connect = co;
  }
   
  /**
  * 
  * 
  * 
  */
  public abstract boolean create(T obj);

  /**
  * 
  * 
  *  
  */
  public abstract boolean delete(T obj);

  /**
  * 
  *
  * 
  */
  public abstract boolean update(T obj);

  /**
  * 
  *
  * 
  */
  public abstract T select(String id);
  
  /**
   *
   * 
   * 
   */
  
  public void close() {
		try {
			this.connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}