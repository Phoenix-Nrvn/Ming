/**
 * ���������ࣺClass.forName();
 * ��ȡ���ݿ����ӣ�DriverManager.getConnection()
 * ����SQL���ִ�о����Connection.createStatement()
 * ִ��SQL��䣺Statement.executeUpdate()
 * �ͷ����ݿ���Դ��Connection.close()
 * ʹ��CreateStatement��PrepareStatement������ͬ��sql�﷨�Ƿ�ִ�ж�Σ�����֧��������ǰ��ÿ�ζ���Ҫ���±���
 */
package cn.NewTest.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnDB {

	private static final String driverName = "com.mysql.jdbc.Driver";
	// ���ݿ�ĵ�ַ(ͨ���˿ں�SID�ҵ���Ӧ�����ݿ�)
	//���ݿ����Ӵ�����Э��:��Э��://������:�˿ں�//���ݿ�
	private static final String URL = "jdbc:mysql://localhost:3306/db_database10?useUnicode=true&characterEncoding=utf-8";
	private static final String userName = "root";
	private static final String pwd = "654321";
	
	
	private static Connection connection = null;	
	private static Statement stat = null;	
	private static PreparedStatement ps = null;	
	private static ResultSet rs1 = null;	
	private static int rows = 0;
	
	public static Connection getConnection() {
		try {
			// ����mysql����
			Class.forName(driverName);
			// ��ȡ���ݿ������
			connection = DriverManager.getConnection(URL, userName, pwd);
			System.out.println("db_database10���ӳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static ResultSet query(String sql) { //������ѯ
		try {
			// getConnection�������һ�����ӵĶ���
			connection = getConnection();
			// �����ݿ��з���sql���
			stat = connection.createStatement();
			// ��ѯ���ص���Resultset����
			rs1 = stat.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs1;
	}
	
	public static int update(String sql) { //���ݿ����
		try {
			//System.out.println("Into update1");
			connection = getConnection();
			// ����ͨ��prepareStatement�����ݿⷢ��SQL���
			ps = connection.prepareStatement(sql); //����λ��
			//System.out.println("Into update2");
			rows = ps.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	public int delete(String sql) { //ɾ�����ݼ�¼
		try {
			connection = getConnection();
			stat = connection.createStatement();
			rows = ps.executeUpdate(sql);
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void close() { //�ͷ�����
		try {
			if (rs1 != null) {
				rs1.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
		}
	}
}
