/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.gm.hxOracle.jdbc;

import com.gm.hxOracle.dao.*;
import com.gm.hxOracle.factory.*;
import java.util.Date;
import com.gm.hxOracle.dto.*;
import com.gm.hxOracle.exceptions.*;
import java.sql.Connection;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class EmployeesDaoImpl extends AbstractDAO implements EmployeesDao
{
	/** 
	 * The factory class for this DAO has two versions of the create() method - one that
takes no arguments and one that takes a Connection argument. If the Connection version
is chosen then the connection will be stored in this attribute and will be used by all
calls to this DAO, otherwise a new Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	/** 
	 * All finder methods in this class use this SELECT constant to build their queries
	 */
	protected final String SQL_SELECT = "SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET EMPLOYEE_ID = ?, FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, PHONE_NUMBER = ?, HIRE_DATE = ?, JOB_ID = ?, SALARY = ?, COMMISSION_PCT = ?, MANAGER_ID = ?, DEPARTMENT_ID = ? WHERE EMPLOYEE_ID = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE EMPLOYEE_ID = ?";

	/** 
	 * Index of column EMPLOYEE_ID
	 */
	protected static final int COLUMN_EMPLOYEE_ID = 1;

	/** 
	 * Index of column FIRST_NAME
	 */
	protected static final int COLUMN_FIRST_NAME = 2;

	/** 
	 * Index of column LAST_NAME
	 */
	protected static final int COLUMN_LAST_NAME = 3;

	/** 
	 * Index of column EMAIL
	 */
	protected static final int COLUMN_EMAIL = 4;

	/** 
	 * Index of column PHONE_NUMBER
	 */
	protected static final int COLUMN_PHONE_NUMBER = 5;

	/** 
	 * Index of column HIRE_DATE
	 */
	protected static final int COLUMN_HIRE_DATE = 6;

	/** 
	 * Index of column JOB_ID
	 */
	protected static final int COLUMN_JOB_ID = 7;

	/** 
	 * Index of column SALARY
	 */
	protected static final int COLUMN_SALARY = 8;

	/** 
	 * Index of column COMMISSION_PCT
	 */
	protected static final int COLUMN_COMMISSION_PCT = 9;

	/** 
	 * Index of column MANAGER_ID
	 */
	protected static final int COLUMN_MANAGER_ID = 10;

	/** 
	 * Index of column DEPARTMENT_ID
	 */
	protected static final int COLUMN_DEPARTMENT_ID = 11;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 11;

	/** 
	 * Index of primary-key column EMPLOYEE_ID
	 */
	protected static final int PK_COLUMN_EMPLOYEE_ID = 1;

	/** 
	 * Inserts a new row in the EMPLOYEES table.
	 */
	public EmployeesPk insert(Employees dto) throws EmployeesDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			stmt = conn.prepareStatement( SQL_INSERT );
			int index = 1;
			stmt.setInt( index++, dto.getEmployeeId() );
			stmt.setString( index++, dto.getFirstName() );
			stmt.setString( index++, dto.getLastName() );
			stmt.setString( index++, dto.getEmail() );
			stmt.setString( index++, dto.getPhoneNumber() );
			stmt.setTimestamp(index++, dto.getHireDate()==null ? null : new java.sql.Timestamp( dto.getHireDate().getTime() ) );
			stmt.setString( index++, dto.getJobId() );
			if (dto.isSalaryNull()) {
				stmt.setNull( index++, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( index++, dto.getSalary() );
			}
		
			if (dto.isCommissionPctNull()) {
				stmt.setNull( index++, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( index++, dto.getCommissionPct() );
			}
		
			if (dto.isManagerIdNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getManagerId() );
			}
		
			if (dto.isDepartmentIdNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getDepartmentId() );
			}
		
			System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
			reset(dto);
			return dto.createPk();
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new EmployeesDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Updates a single row in the EMPLOYEES table.
	 */
	public void update(EmployeesPk pk, Employees dto) throws EmployeesDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			System.out.println( "Executing " + SQL_UPDATE + " with DTO: " + dto );
			stmt = conn.prepareStatement( SQL_UPDATE );
			int index=1;
			stmt.setInt( index++, dto.getEmployeeId() );
			stmt.setString( index++, dto.getFirstName() );
			stmt.setString( index++, dto.getLastName() );
			stmt.setString( index++, dto.getEmail() );
			stmt.setString( index++, dto.getPhoneNumber() );
			stmt.setTimestamp(index++, dto.getHireDate()==null ? null : new java.sql.Timestamp( dto.getHireDate().getTime() ) );
			stmt.setString( index++, dto.getJobId() );
			if (dto.isSalaryNull()) {
				stmt.setNull( index++, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( index++, dto.getSalary() );
			}
		
			if (dto.isCommissionPctNull()) {
				stmt.setNull( index++, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( index++, dto.getCommissionPct() );
			}
		
			if (dto.isManagerIdNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getManagerId() );
			}
		
			if (dto.isDepartmentIdNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setInt( index++, dto.getDepartmentId() );
			}
		
			stmt.setInt( 12, pk.getEmployeeId() );
			int rows = stmt.executeUpdate();
			reset(dto);
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new EmployeesDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the EMPLOYEES table.
	 */
	public void delete(EmployeesPk pk) throws EmployeesDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			System.out.println( "Executing " + SQL_DELETE + " with PK: " + pk );
			stmt = conn.prepareStatement( SQL_DELETE );
			stmt.setInt( 1, pk.getEmployeeId() );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new EmployeesDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns the rows from the EMPLOYEES table that matches the specified primary-key value.
	 */
	public Employees findByPrimaryKey(EmployeesPk pk) throws EmployeesDaoException
	{
		return findByPrimaryKey( pk.getEmployeeId() );
	}

	/** 
	 * Returns all rows from the EMPLOYEES table that match the criteria 'EMPLOYEE_ID = :employeeId'.
	 */
	public Employees findByPrimaryKey(int employeeId) throws EmployeesDaoException
	{
		Employees ret[] = findByDynamicSelect( SQL_SELECT + " WHERE EMPLOYEE_ID = ?", new Object[] {  new Integer(employeeId) } );
		return ret.length==0 ? null : ret[0];
	}

	/** 
	 * Returns all rows from the EMPLOYEES table that match the criteria ''.
	 */
	public Employees[] findAll() throws EmployeesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY EMPLOYEE_ID", null );
	}

	/** 
	 * Returns all rows from the EMPLOYEES table that match the criteria 'MANAGER_ID = :managerId'.
	 */
	public Employees[] findByEmployees(int managerId) throws EmployeesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE MANAGER_ID = ?", new Object[] {  new Integer(managerId) } );
	}

	/** 
	 * Returns all rows from the EMPLOYEES table that match the criteria 'EMPLOYEE_ID = :employeeId'.
	 */
	public Employees[] findWhereEmployeeIdEquals(int employeeId) throws EmployeesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE EMPLOYEE_ID = ? ORDER BY EMPLOYEE_ID", new Object[] {  new Integer(employeeId) } );
	}

	/** 
	 * Returns all rows from the EMPLOYEES table that match the criteria 'FIRST_NAME = :firstName'.
	 */
	public Employees[] findWhereFirstNameEquals(String firstName) throws EmployeesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE FIRST_NAME = ? ORDER BY FIRST_NAME", new Object[] { firstName } );
	}

	/** 
	 * Returns all rows from the EMPLOYEES table that match the criteria 'LAST_NAME = :lastName'.
	 */
	public Employees[] findWhereLastNameEquals(String lastName) throws EmployeesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE LAST_NAME = ? ORDER BY LAST_NAME", new Object[] { lastName } );
	}

	/** 
	 * Returns all rows from the EMPLOYEES table that match the criteria 'EMAIL = :email'.
	 */
	public Employees[] findWhereEmailEquals(String email) throws EmployeesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE EMAIL = ? ORDER BY EMAIL", new Object[] { email } );
	}

	/** 
	 * Returns all rows from the EMPLOYEES table that match the criteria 'PHONE_NUMBER = :phoneNumber'.
	 */
	public Employees[] findWherePhoneNumberEquals(String phoneNumber) throws EmployeesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE PHONE_NUMBER = ? ORDER BY PHONE_NUMBER", new Object[] { phoneNumber } );
	}

	/** 
	 * Returns all rows from the EMPLOYEES table that match the criteria 'HIRE_DATE = :hireDate'.
	 */
	public Employees[] findWhereHireDateEquals(Date hireDate) throws EmployeesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE HIRE_DATE = ? ORDER BY HIRE_DATE", new Object[] { hireDate==null ? null : new java.sql.Timestamp( hireDate.getTime() ) } );
	}

	/** 
	 * Returns all rows from the EMPLOYEES table that match the criteria 'JOB_ID = :jobId'.
	 */
	public Employees[] findWhereJobIdEquals(String jobId) throws EmployeesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE JOB_ID = ? ORDER BY JOB_ID", new Object[] { jobId } );
	}

	/** 
	 * Returns all rows from the EMPLOYEES table that match the criteria 'SALARY = :salary'.
	 */
	public Employees[] findWhereSalaryEquals(float salary) throws EmployeesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE SALARY = ? ORDER BY SALARY", new Object[] {  new Float(salary) } );
	}

	/** 
	 * Returns all rows from the EMPLOYEES table that match the criteria 'COMMISSION_PCT = :commissionPct'.
	 */
	public Employees[] findWhereCommissionPctEquals(float commissionPct) throws EmployeesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE COMMISSION_PCT = ? ORDER BY COMMISSION_PCT", new Object[] {  new Float(commissionPct) } );
	}

	/** 
	 * Returns all rows from the EMPLOYEES table that match the criteria 'MANAGER_ID = :managerId'.
	 */
	public Employees[] findWhereManagerIdEquals(int managerId) throws EmployeesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE MANAGER_ID = ? ORDER BY MANAGER_ID", new Object[] {  new Integer(managerId) } );
	}

	/** 
	 * Returns all rows from the EMPLOYEES table that match the criteria 'DEPARTMENT_ID = :departmentId'.
	 */
	public Employees[] findWhereDepartmentIdEquals(int departmentId) throws EmployeesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE DEPARTMENT_ID = ? ORDER BY DEPARTMENT_ID", new Object[] {  new Integer(departmentId) } );
	}

	/**
	 * Method 'EmployeesDaoImpl'
	 * 
	 */
	public EmployeesDaoImpl()
	{
	}

	/**
	 * Method 'EmployeesDaoImpl'
	 * 
	 * @param userConn
	 */
	public EmployeesDaoImpl(final java.sql.Connection userConn)
	{
		this.userConn = userConn;
	}

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows)
	{
		this.maxRows = maxRows;
	}

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows()
	{
		return maxRows;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "HR.EMPLOYEES";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected Employees fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			Employees dto = new Employees();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected Employees[] fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Employees dto = new Employees();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		Employees ret[] = new Employees[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(Employees dto, ResultSet rs) throws SQLException
	{
		dto.setEmployeeId( rs.getInt( COLUMN_EMPLOYEE_ID ) );
		dto.setFirstName( rs.getString( COLUMN_FIRST_NAME ) );
		dto.setLastName( rs.getString( COLUMN_LAST_NAME ) );
		dto.setEmail( rs.getString( COLUMN_EMAIL ) );
		dto.setPhoneNumber( rs.getString( COLUMN_PHONE_NUMBER ) );
		dto.setHireDate( rs.getTimestamp(COLUMN_HIRE_DATE ) );
		dto.setJobId( rs.getString( COLUMN_JOB_ID ) );
		dto.setSalary( rs.getFloat( COLUMN_SALARY ) );
		if (rs.wasNull()) {
			dto.setSalaryNull( true );
		}
		
		dto.setCommissionPct( rs.getFloat( COLUMN_COMMISSION_PCT ) );
		if (rs.wasNull()) {
			dto.setCommissionPctNull( true );
		}
		
		dto.setManagerId( rs.getInt( COLUMN_MANAGER_ID ) );
		if (rs.wasNull()) {
			dto.setManagerIdNull( true );
		}
		
		dto.setDepartmentId( rs.getInt( COLUMN_DEPARTMENT_ID ) );
		if (rs.wasNull()) {
			dto.setDepartmentIdNull( true );
		}
		
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Employees dto)
	{
	}

	/** 
	 * Returns all rows from the EMPLOYEES table that match the specified arbitrary SQL statement
	 */
	public Employees[] findByDynamicSelect(String sql, Object[] sqlParams) throws EmployeesDaoException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			// construct the SQL statement
			final String SQL = sql;
		
		
			System.out.println( "Executing " + SQL );
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new EmployeesDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns all rows from the EMPLOYEES table that match the specified arbitrary SQL statement
	 */
	public Employees[] findByDynamicWhere(String sql, Object[] sqlParams) throws EmployeesDaoException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			// construct the SQL statement
			final String SQL = SQL_SELECT + " WHERE " + sql;
		
		
			System.out.println( "Executing " + SQL );
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new EmployeesDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

}
