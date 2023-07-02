package text.section_06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbSelect_06 {

	public static void main(final String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_kadai", "root", "test");) {
			System.out.println("データベース接続成功");
			try (Statement statement = con.createStatement();) {
				final String sql = "SELECT id, name FROM users;";
				final ResultSet result = statement.executeQuery(sql);
				while (result.next()) {
					final int id = result.getInt("id");
					final String name = result.getString("name");
					System.out.println(result.getRow() + "件目：id =" + id + "/name= " + name);
				}
			} catch (final Exception e) {
				System.out.println("エラー発生");
			}
		} catch (final Exception e) {
			System.out.println("エラー発生");
		}
	}

}
