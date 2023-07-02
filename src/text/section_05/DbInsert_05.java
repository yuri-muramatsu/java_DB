package text.section_05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DbInsert_05 {

	public static void main(final String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_kadai", "root", "test");) {
			System.out.println("データベース接続成功");
			final String sql = "INSERT INTO users (name, age) VALUES (?, ?);";
			try (PreparedStatement statement = con.prepareStatement(sql);) {
				// 二次元配列 名前と年齢の格納
				final String[][] userList = { { "織田信長", "50" }, { "豊臣秀吉", "36" }, { "徳川家康", "72" }, { "伊達政宗", "38" },
						{ "上杉謙信", "45" } };
				int rowCnt = 0;
				for (final String[] element : userList) {
					statement.setString(1, element[0]);// 名前
					statement.setString(2, element[1]);// 年齢
					// SQLクエリを実行（DBMSに送信）
					System.out.println("レコード追加：" + statement.toString());
					rowCnt = statement.executeUpdate();
					System.out.println(rowCnt + "件のレコードが追加されました。");
				}
			} catch (final Exception e) {
				System.out.println("エラー発生");
			}
		} catch (final Exception e) {
			System.out.println("エラー発生");
		}
	}

}
