/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.gm.hxOracle.factory;

import java.sql.Connection;
import com.gm.hxOracle.dao.*;
import com.gm.hxOracle.jdbc.*;

public class EmployeesDaoFactory
{
	/**
	 * Method 'create'
	 * 
	 * @return EmployeesDao
	 */
	public static EmployeesDao create()
	{
		return new EmployeesDaoImpl();
	}

	/**
	 * Method 'create'
	 * 
	 * @param conn
	 * @return EmployeesDao
	 */
	public static EmployeesDao create(Connection conn)
	{
		return new EmployeesDaoImpl( conn );
	}

}
