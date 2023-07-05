package text.section_08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DbUpdate_08 {

	public static void main(final String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_kadai", "root", "test");) {
			System.out.println("データベース接続成功");
			// SQLクエリを準備
			final String sql = "UPDATE users SET name = ? WHERE id = ?;";
			try (PreparedStatement statement = con.prepareStatement(sql);) {
				// 更新するデータのセット
				statement.setString(1, "徳川秀忠");
				statement.setInt(2, 3);
				// SQLクエリを実行（DBMSに送信）
				System.out.println("レコード更新：" + statement.toString());
				final int rowCnt = statement.executeUpdate(sql);
				System.out.println(rowCnt + "件のレコードが更新されました。");
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
