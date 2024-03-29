package text.section_07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbWhere_07 {

	public static void main(final String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_kadai", "root", "test");) {
			System.out.println("データベース接続成功");
			// SQLクエリを準備
			final String sql = "SELECT * FROM users WHERE age BETWEEN ? AND ?;";
			try (PreparedStatement statement = con.prepareStatement(sql);) {
				// 検索するデータのセット
				statement.setInt(1, 30);
				statement.setInt(2, 60);

				final ResultSet result = statement.executeQuery(sql);
				// SQLクエリの実行結果を抽出
				while (result.next()) {
					final int id = result.getInt("id");
					final String name = result.getString("name");
					final int age = result.getInt("age");
					System.out.println(result.getRow() + "件目：id " + id + "/name= " + name + "/age= " + age);
				}
			} catch (final Exception e) {
				System.err.println("エラー発生： " + e.getLocalizedMessage());
				e.printStackTrace();
			}
		} catch (final Exception e) {
			System.err.println("エラー発生： " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}
