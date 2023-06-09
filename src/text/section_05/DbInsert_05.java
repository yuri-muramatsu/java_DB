package text.section_05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbInsert_05 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		Connection con = null;
		PreparedStatement statement = null;
		
		String[][] userList = {//二次元配列
				{"織田信長", "50" },
				{"豊臣秀吉", "36" },
				{"徳川家康", "72" },
				{"伊達政宗", "38" }
		};
		
		try {
			//データベース接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/java_kadai",
					"root",
					"test"
					);
			
			System.out.println("データベース接続成功");
			
			String sql = "INSERT INTO users (name, age) VALUES (?, ?);";
			statement = con.prepareStatement(sql);
			
			int rowCnt = 0;
			for(int i =0; i < userList.length; i++) {
				//SQLクエリの”？”の部分をリストのデータに置き換え
				statement.setString(1, userList[i][0]);//名前
				statement.setString(2, userList[i][1]);//年齢
				
				//SQLクエリを実行（DBMSに送信）
				System.out.println("レコード追加：" + statement.toString() );
				rowCnt = statement.executeUpdate();
				System.out.println( rowCnt + "件のレコードが追加されました。");
			}
			
		}catch(SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		}finally {
			if( statement != null ) {
				try {statement.close();}catch(SQLException ignore) {}
			}
			if( con != null ) {
				try { con.close();} catch(SQLException ignore) {}
			}
		}
	
	}
}
