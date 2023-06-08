package text.section_03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect_1 {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		Connection con = null;
		Statement statement = null;
		
		try {
			//データベースに接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/java_kadai",
					"root",
					"test"//本来はファイルから読み込むか、入力式にする
			);
			
			System.out.println("データベース接続成功");
			System.out.println(con);
			
			//SQLクエリを準備
			statement = con.createStatement();
			String sql = """
					CREATE TABLE users(
						id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
						name VARCHAR(60) NOT NULL,
						age INT(11)
						);
					""";
			//SQLクエリを実行（DBMSに送信）
			int rowCnt = statement.executeUpdate(sql);
			System.out.println("テーブルを作成：rowCnt=" + rowCnt );
		}catch(SQLException e) {
			System.out.println("データベース接続失敗：" + e.getMessage());
		}finally {
			if( statement != null) {
				try { statement.close();} catch(SQLException ignore) {}
			}
			if( con != null) {
				try { con.close(); } catch(SQLException ignore) {}
			}
		}
	}

}
