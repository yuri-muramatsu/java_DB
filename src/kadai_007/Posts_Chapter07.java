package kadai_007;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Posts_Chapter07 {

	public static void main(final String[] args) {
		Connection con = null;
		PreparedStatement statement1 = null;

		try {
			// データベースに接続
			con = DriverManager.getConnection("jdbc:mysql://localhost/challenge_java", "root", "test");
			System.out.println("データベース接続成功：" + con);
			// 投稿データを追加
			final String sql1 = """
					INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES
					(?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?),(?, ?, ?, ?);
					""";
			// SQLクエリを準備
			statement1 = con.prepareStatement(sql1);
			// 追加する投稿データのセット
			statement1.setInt(1, 1003);
			statement1.setDate(2, new Date(System.currentTimeMillis()));
			statement1.setString(3, "昨日の夜は徹夜でした・・");
			statement1.setInt(4, 13);
			statement1.setInt(5, 1002);
			statement1.setString(6, "2023-02-08");
			statement1.setString(7, "お疲れ様です！");
			statement1.setInt(8, 12);
			statement1.setInt(9, 1003);
			statement1.setString(10, "2023-02-09");
			statement1.setString(11, "今日も頑張ります！");
			statement1.setInt(12, 18);
			statement1.setInt(13, 1001);
			statement1.setString(14, "2023-02-09");
			statement1.setString(15, "無理は禁物ですよ！");
			statement1.setInt(16, 17);
			statement1.setInt(17, 1002);
			statement1.setString(18, "2023-02-10");
			statement1.setString(19, "明日から連休ですね！");
			statement1.setInt(20, 20);
			// SQLクエリを実行（DBMSに送信）
			final int rowCnt = statement1.executeUpdate();
			System.out.println("レコード追加を実行します。");
			System.out.println(rowCnt + "件のレコードが追加されました。");

			// データの検索
			final String sql2 = "SELECT * FROM posts WHERE user_id=?;";
			final PreparedStatement statement2 = con.prepareStatement(sql2);
			// 検索するユーザーIDのセット
			statement2.setInt(1, 1002);
			System.out.println("ユーザーIDが1002のレコードを検索しました。");
			// SQLクエリを実行（DBMSに送信）
			final ResultSet result = statement2.executeQuery();

			while (result.next()) {
				final Date postedAt = result.getDate("posted_at");
				final String content = result.getString("post_content");
				final int likes = result.getInt("likes");
				System.out.println(result.getRow() + "件目：投稿日時＝" + postedAt + "/投稿内容＝ " + content + "/いいね数＝ " + likes);
			}
		} catch (final SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
			if (statement1 != null) {
				try {
					statement1.close();
				} catch (final Exception ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (final Exception ignore) {
				}
			}
		}
	}
}
