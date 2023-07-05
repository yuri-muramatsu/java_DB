package text.section_09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DbDelete_09 {

	public static void main(final String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/challenge_java", "root", "test");) {
			// データベースに接続
			System.out.println("データベースに接続成功" + con);
			// SQクエリを準備
			final String sql = "DELETE FROM posts WHERE user_id=?;";
			try (PreparedStatement statement = con.prepareStatement(sql);) {
				// 削除のデータ入力
				statement.setInt(1, 1003);
				// SQLクエリを実行（DBMSに送信）
				System.out.println("レコード削除：" + statement.toString());
				final int rowCnt = statement.executeUpdate();
				System.out.println(rowCnt + "件のレコードが削除されました。");
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
