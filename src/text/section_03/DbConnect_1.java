package text.section_03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbConnect_1 {
	public static void main(final String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_kadai", "root", "test");) {
			System.out.println("データベース接続成功");
			System.out.println(con);
			// SQLクエリを準備
			try (Statement statement = con.createStatement();) {
				final String sql = """
						CREATE TABLE IF NOT EXISTS users(
						    id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
						    name VARCHAR(60) NOT NULL,
						    age INT(11)
						    );
						""";
				// SQLクエリを実行（DBMSに送信）
				final int rowCnt = statement.executeUpdate(sql);
				System.out.println("テーブルを作成：rowCnt=" + rowCnt);
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
