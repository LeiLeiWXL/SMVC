package ustc.keene.mvc.test.sqlite;

import java.sql.SQLException;
import ustc.keene.mvc.dao.DBSession;

/**
 * @author WXL
 * 该类负责SQLite数据库访问帮助，提供基本的数据库访问，SQL解析或构造功能
 */
public class SQLiteHelper{
	public void chaxun(String sql) throws SQLException{
		DBSession dbs=new DBSession();
		dbs.initDBParam("jdbc:mysql://localhost:3306/","com.mysql.jdbc.Driver","admin","admin");
		System.out.println("执行了");
		dbs.UpDate(sql);
	}
}
