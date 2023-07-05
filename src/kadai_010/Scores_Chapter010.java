package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Scores_Chapter010 {

	public static void main(final String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/challenge_java", "root", "test");) {
			System.out.println("データベース接続成功:" + con);
			// SQLクエリを準備
			final String sql1 = "UPDATE scores1 SET score_math=?, score_english=? WHERE id=?;";
			try (PreparedStatement statement = con.prepareStatement(sql1);) {
				// 更新するデータのセット
				statement.setInt(1, 65);
				statement.setInt(2, 45);
				statement.setInt(3, 5);

				// 点数データを更新 SQLクエリを実行（DBMSに送信）
				System.out.println("レコード更新を実行します。");
				final int rowCnt = statement.executeUpdate();
				System.out.println(rowCnt + "件のレコードが更新されました。");

				// 点数順に並べる
				final String sql2 = "SELECT * FROM scores1 ORDER BY score_math DESC, score_english DESC;";
				// SQLクエリを実行（DBMSに送信）
				System.out.println("数学・英語の点数が高い順に並べ替えました");
				final ResultSet result = statement.executeQuery(sql2);

				// SQLクエリの実行結果を抽出
				while (result.next()) {
					final int id = result.getInt("id");
					final String name = result.getString("name");
					final int scoreMath = result.getInt("score_math");
					final int scoreEnglish = result.getInt("score_english");

					System.out.println(result.getRow() + "件目：生徒ID＝" + id + "/氏名＝" + name + "/数学＝" + scoreMath + "/英語＝"
							+ scoreEnglish);
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
