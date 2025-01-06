package co.kh.dev.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public final class ConnectionPool {
	// 1. Oracle Drive를 정적처리해서 로드한다.
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<Connection> free;
	private ArrayList<Connection> used; // 사용중인 커넥션을 저장하는 변수
	private int initialCons = 10; // 최초로 초기 커넥션수
	private int maxCons = 20; // 최대 커넥션수
	private int numCons = 0; // 총 Connection 수
	private static ConnectionPool cp;

	public static ConnectionPool getInstance() {
		try {
			if (cp == null) {
				synchronized (ConnectionPool.class) {
					cp = new ConnectionPool();
				}
			}
		} catch (Exception e) {
		}
		return cp;
	}

	// 지정된 개수만큼 addConnection을 반복
	private ConnectionPool() {
		free = new ArrayList<Connection>();
		used = new ArrayList<Connection>();
		while (numCons < initialCons) {
			free.add(getNewConnection());
		}
	}
	
	private Connection getNewConnection() {
		Connection con = null;
		String filePath = "C:\\dev\\myPageWorkSpace\\jspHomePage\\src\\main\\java\\co\\kh\\dev\\common\\db.properties";
		Properties pt = new Properties();
		try {
			pt.load(new FileReader(filePath));
			String url = (pt.getProperty("url"));
			String id = (pt.getProperty("id"));
			String pw = (pt.getProperty("pw"));
			con = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		numCons++;
		return con;
	}

	public synchronized Connection getConnection()  {
		if (free.isEmpty()) {
			while (numCons < maxCons) {
				free.add(getNewConnection());
			}
		}
		Connection _con = null;
		_con = free.get(free.size() - 1);
		free.remove(_con);
		used.add(_con);
		return _con;
	}

	public synchronized void releaseConnection(Connection _con) throws SQLException {
		boolean flag = false;
		if (used.contains(_con)) {
			used.remove(_con);
			numCons--;
			flag = true;
		} else {
			throw new SQLException("ConnectionPool" + "에 있지않네요!!");
		}
		try {
			if (flag) {
				free.add(_con);
				numCons++;
			} else {
				_con.close();
			}
		} catch (SQLException e) {
			try {
				_con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

	}

	public void closeAll() {// 모든 Connection 자원을 반납함. // used에 있는 커넥션을 모두 삭제해 버림.
		for (int i = 0; i < used.size(); i++) {
			Connection _con = (Connection) used.get(i);
			used.remove(i--);
			try {
				_con.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		// free에 있는 커넥션을 모두 삭제해 버림.
		for (int i = 0; i < free.size(); i++) {
			Connection _con = (Connection) free.get(i);
			free.remove(i--);
			try {
				_con.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public void dbClose(Connection con, ResultSet rs, Statement... stmts) {
		if (con != null) {
			try {
				releaseConnection(con);
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		for (Statement data : stmts) {
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
	}

	public void dbClose(Connection con, Statement... stmts) {
		if (con != null) {
			try {
				releaseConnection(con);
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		for (Statement data : stmts) {
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
			}
		}
	}

	public int getFreeSize() {
		return free.size();
	}

	public int getUsedSize() {
		return used.size();
	}

}
