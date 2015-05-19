package org.purple.model;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Dao<T> {
  protected Connection connect = null;
   
  public Dao(Connection co){
    this.connect = co;
  }
   
  /**
  * Méthode de création
  * @param obj
  * @return boolean
  */
  public abstract boolean create(T obj);

  /**
  * Méthode pour effacer
  * @param obj
  * @return boolean
  */
  public abstract boolean delete(T obj);

  /**
  * Méthode de mise à jour
  * @param obj
  * @return boolean
  */
  public abstract boolean update(T obj);

  /**
  * Méthode de recherche des informations
  * @param id
  * @return T
  */
  public abstract T select(String id);
  
  /**
   * Retourne un boolean si l'élément id est en base
   * @param id
   * @return T
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