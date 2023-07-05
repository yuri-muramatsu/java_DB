package kadai_004;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Employees_Chapter004 {

	public static void main(final String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/challenge_java", "root", "test");) {
			System.out.println("データベース接続成功");
			System.out.println(con);
			// SQLクエリを準備
			try (Statement statement = con.createStatement();) {
				final String sql = """
						CREATE TABLE IF NOT EXISTS employees (
						    id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
						    name VARCHAR(60) NOT NULL,
						    email VARCHAR(255) NOT NULL,
						    age INT(11),
						    address VARCHAR(255)
						    );
						""";
				// SQLクエリを実行（DBMSに送信）
				final int rowCnt = statement.executeUpdate(sql);
				System.out.println("社員テーブルを作成しました：更新レコード数=" + rowCnt);
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
