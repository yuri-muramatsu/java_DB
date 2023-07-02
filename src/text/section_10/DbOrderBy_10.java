package text.section_10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DbOrderBy_10 {

	public static void main(final String[] args) {
		System.out.println("0（昇順）か 1（降順）を入力してください。");
		try (Scanner scanner = new Scanner(System.in);) {
			final String order = switch (scanner.nextInt()) {
			case 0 -> "ASC;";
			case 1 -> "DESC;";
			default -> "ASC;";
			};
			// データベースに接続
			try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java_kadai", "root", "test");) {
				System.out.println("データベース接続成功");

				// SQLクエリを準備
				try (Statement statement = con.createStatement();) {
					final String sql = "SELECT * FROM users ORDER BY age " + order;

					// SQLクエリを実行（DBMSに送信）
					System.out.println("データ取得を実行：" + sql);
					final ResultSet result = statement.executeQuery(sql);

					// SQLクエリの実行結果を抽出
					while (result.next()) {
						final int id = result.getInt("id");
						final String name = result.getString("name");
						final int age = result.getInt("age");
						System.out.println(result.getRow() + "件目：id＝" + id + "/name＝" + name + "/age＝" + age);
					}
				} catch (final Exception e) {
					System.out.println("エラー発生");
				}
			} catch (final Exception e) {
				System.out.println("エラー発生");
			}
		} catch (final Exception e) {
			System.out.println("エラー発生");
		}
	}

}
