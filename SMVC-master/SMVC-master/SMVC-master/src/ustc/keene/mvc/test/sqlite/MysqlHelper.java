package ustc.keene.mvc.test.sqlite;

import java.sql.SQLException;

import ustc.keene.mvc.dao.DBSession;

/**
 * @author WXL
 *该类负责Mysql数据库访问帮助，提供基本的数据库访问，SQL解析或构造功能
 */
public class MysqlHelper {
	public int id;	
	/**
	 * 该方法执行Mysql数据库查询操作
	 * @param sql
	 * @throws SQLException
	 */
	public void Query(Object sql) throws SQLException{
		DBSession dbs=new DBSession();
		dbs.initDBParam("jdbc:mysql://localhost:3306/Hello2?useUnicode=true&characterEncoding=utf-8"
				,"com.mysql.jdbc.Driver","admin","admin");
		dbs.query(MysqlHelper.class,sql);
	}
	
	/**
	 * 该方法负责Mysql数据库的更新操作；
	 * @param sql
	 * @throws SQLException
	 */
	public void Updata(Object sql) throws SQLException{
		DBSession dbs=new DBSession();
		dbs.initDBParam("jdbc:mysql://localhost:3306/Hello2?useUnicode=true&characterEncoding=utf-8"
				,"com.mysql.jdbc.Driver","admin","admin");
		dbs.UpDate(sql);
	}

	/**
	 * 该方法负责Mysql数据库的删除操作；
	 * @param sql
	 * @throws SQLException
	 */
	public void Delete(Object sql) throws SQLException{
		DBSession dbs=new DBSession();
		dbs.initDBParam("jdbc:mysql://localhost:3306/Hello2?useUnicode=true&characterEncoding=utf-8"
				,"com.mysql.jdbc.Driver","admin","admin");
		dbs.Delete(sql);
	}
	
	/**
	 * 该方法负责Mysql数据库的插入操作；
	 * @param sql
	 * @throws SQLException
	 */
	public void insert(Object sql) throws SQLException{
		DBSession dbs=new DBSession();
		dbs.initDBParam("jdbc:mysql://localhost:3306/Hello2?useUnicode=true&characterEncoding=utf-8"
				,"com.mysql.jdbc.Driver","admin","admin");
		dbs.Insert(sql);
	}
}
