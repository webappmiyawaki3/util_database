package connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * table:connect_sample01
 * id SERIAL
 * name String
 * text String
 */

public class JdbcModel implements JdbcCRUD {
	@Override
	public boolean insert(SampleObject so) {
		String sql = "INSERT INTO connect_sample01(name,text) VALUES(?,?)";
		try (Connection conn = JdbcUtils.getConnection();
				PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, so.getName());
			pstm.setString(2, so.getText());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Map<String, SampleObject> selectAll() {
		String sql = "SELECT * FROM connect_sample01";
		try (Connection conn = JdbcUtils.getConnection();
				ResultSet rs = conn.prepareStatement(sql).executeQuery()) {
			Map<String, SampleObject> sampleMap = new HashMap<>();
			while (rs.next()) {
				sampleMap.put(rs.getString("id"),
						SampleObject.builder()
								.name(rs.getString("name"))
								.text(rs.getString("text"))
								.build());
			}
			return sampleMap;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *     Statement
	 *     注: Statementオブジェクトがクローズされるとき、
	 *     その現在のResultSetオブジェクトが存在すれば、それもクローズされます。
	 */
	@Override
	public SampleObject selectUnit(String strNum) {
		String sql = "SELECT * FROM connect_sample01 WHERE id=?";
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = JdbcUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, Integer.parseInt(strNum));
			rs = pstm.executeQuery();
			if (rs.next()) {
				return SampleObject.builder()
						.name(rs.getString("name"))
						.text(rs.getString("text"))
						.build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(pstm, conn, rs);
		}
		return null;
	}

	@Override
	public boolean update(String strNum) {
		String sql = "UPDATE connect_sample01 SET name='rename',text='replace' where ID = ?";
		try (Connection conn = JdbcUtils.getConnection();
				PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, Integer.parseInt(strNum));
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteAll() {
		String sql = "TRUNCATE connect_sample01";
		try (Connection conn = JdbcUtils.getConnection()) {
			conn.prepareStatement(sql).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteUnit(String strNum) {
		String sql = "DELETE FROM connect_sample01 WHERE ID = ?";
		try (Connection conn = JdbcUtils.getConnection();
				PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, Integer.parseInt(strNum));
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
