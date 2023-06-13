package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*データベース接続成功：com.mysql.cj.jdbc.ConnectionImpl@xxxxxxxx
レコード更新を実行します
1件のレコードが更新されました
数学・英語の点数が高い順に並べ替えました
1件目：生徒ID=5／氏名=武者小路勇気／数学=95／英語=80
2件目：生徒ID=2／氏名=刀沢彩香／数学=85／英語=70
3件目：生徒ID=4／氏名=武士山美咲／数学=75／英語=95
4件目：生徒ID=3／氏名=戦国広志／数学=75／英語=85
5件目：生徒ID=1／氏名=侍健太／数学=65／英語=90*/

public class Scores_Chapter10 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Connection con = null;
		Statement statement = null;
		
		try {
			//データベースに接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"test"
					);
			
			System.out.println("データベース接続成功:" + con);
			
			//SQLクエリを準備
			statement = con.createStatement();
			String sql = "UPDATE scores1 SET score_math = 95 , score_english = 80 WHERE id = '5';";
			
			//点数データを更新　SQLクエリを実行（DBMSに送信）
			System.out.println("レコード更新を実行します。");
			int rowCnt = statement.executeUpdate(sql);
			System.out.println(rowCnt + "件のレコードが更新されました。");
			
			//点数順に並べる
			String sql1 = "SELECT * FROM scores1 ORDER BY score_math DESC, score_english DESC;";
			
			//SQLクエリを実行（DBMSに送信）
			System.out.println("数学・英語の点数が高い順に並べ替えました");
			ResultSet result = statement.executeQuery(sql1);
			
			//SQLクエリの実行結果を抽出
			while(result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int scoreMath = result.getInt("score_math");
				int scoreEnglish = result.getInt("score_english");
				System.out.println
					(result.getRow() + "件目：生徒ID＝" + id + "/氏名＝" + name + "/数学＝" +scoreMath + "/英語＝" + scoreEnglish);
			};
			
		} catch(SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		}finally {
			if( statement != null ) {
				try { statement.close();} catch (SQLException ignore) {}
			}
			if ( con != null ) {
				try { con.close();} catch(SQLException ignore) {}
			}
		}
	}


}
