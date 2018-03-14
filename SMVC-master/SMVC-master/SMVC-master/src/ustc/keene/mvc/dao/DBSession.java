package ustc.keene.mvc.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author WXL
 * 该类负责封装一个数据库会话，及会话的基本管理，如打开，关闭等
 * 同时负责以面向对象方式访问数据库，实现基本的CRUD数据操作 
 */

public class DBSession {
	private Connection con=null;
	private DBConfiguration dbc=new DBConfiguration();
    /**
     * 该方法负责执行数据库连接及打开操作；
     */
	public Connection openDBConnection(){
		try {
			Class.forName(dbc.getDBdriver());
		    con = DriverManager.getConnection(dbc.getDBurl(),dbc.getDBuserName(),dbc.getDBuserPassword());
            System.out.println("数据库已经链接");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return con;
	}
	/**
	 * 该方法负责数据库关闭；
	 */
	public void closeDBConnection(){
		try {
			this.con.close();
			System.out.println("数据库已经关闭");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 初始化连接数据库的四个参数DBurl;DBdriver;DBuserName;DBpassword
	 */
	public void initDBParam(String DBurl, String DBdriver, String DBuserName, String DBpassword){
		dbc.setDBurl(DBurl);
		dbc.setDBdriver(DBdriver);
		dbc.setDBuserName(DBuserName);
		dbc.setDBuserPassword(DBpassword);
	}
	
	/**
	 * 该方法执行数据库查询操作；
	 * @throws SQLException 
	 */
	public Object query(Class<?> clazz,Object uuid) throws SQLException{
		Connection conn=openDBConnection();;  
        PreparedStatement ps = null;
        ResultSet rs=null;
        IResultSetHandler rsh=new BeanHandler(clazz);
		Object obj=null;  
        try {  
            obj = clazz.newInstance();            
            Field[] fields = obj.getClass().getDeclaredFields();  
            Object[] params = new Object[1];  
            int term=obj.getClass().getName().lastIndexOf(".")+1;  
            StringBuffer sql = new StringBuffer("SELECT * from  "  
                    + obj.getClass().getName().substring(term) + " WHERE "+fields[0].getName()+"=1;");  
            params[0]=uuid;                
            String strSQL = new String(sql);
            ps = conn.prepareStatement(strSQL);                        
            /**执行多条件查询是需要增加的参数添加
             * for (int i = 0; i < params.length; i++) {  
                ps.setObject(i + 1, params[i]);  
            } 
             */            
            rs = ps.executeQuery();
            while(rs.next())
            {
            	System.out.println(rs.getObject("id"));
            }                       
        } catch (Exception e) {  
            e.printStackTrace();  
        }         
        return rsh.handler(rs); 
	}
	
	/**
	 * 该方法执行数据库的更新操作
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public int UpDate(Object obj) throws SQLException{
		Connection conn=openDBConnection();
		PreparedStatement ps = null;
		 int result = 0;
        int term=obj.getClass().getName().lastIndexOf(".")+1;  
        StringBuffer sql = new StringBuffer("UPDATE "  
                + obj.getClass().getName().substring(term) + " SET ");  
        Field[] fields = obj.getClass().getDeclaredFields();        
            int size = fields.length;  
            Field field = null;  
            Object[] params = new Object[size+1];                
            try {  
            for (int i = 0; i < size - 1; i++) {  
                field = fields[i];  
                field.setAccessible(true);//运行时 把private 转成 public  
                params[i] = field.get(obj);  
                sql.append(field.getName() + "=?,");  
            }              
            fields[size-1].setAccessible(true);  
            params[size - 1] = fields[size - 1].get(obj);  
            sql.append(fields[size - 1].getName() + "=? WHERE " +fields[0].getName()+"=?");  
            params[size]=params[0];             
            String strSQL = new String(sql);        
            ps = conn.prepareStatement(strSQL);
            System.out.println(strSQL);
            for (int i = 0; i < params.length; i++) {  
                ps.setObject(i + 1, params[i]);  
            }  
             result=ps.executeUpdate(strSQL);  
             System.out.println(result);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        this.closeDBConnection();
		return result;
	}
	
	/**
	 * 该方法负责执行数据库删除操作
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public int Delete(Object obj) throws SQLException{
		Connection conn=openDBConnection();
		PreparedStatement ps = null;		
		int result=0;
		int term=obj.getClass().getName().lastIndexOf(".")+1;  
        StringBuffer sql = new StringBuffer("DELETE from  "  
                + obj.getClass().getName().substring(term) + " WHERE ");  
        Field[] fields = obj.getClass().getDeclaredFields();  
        sql.append(fields[0].getName()+"=?");  
      Object[] params = new Object[1];  
        try {  
            fields[0].setAccessible(true);  
            params[0]=fields[0].get(obj);  
              
            String strSQL = new String(sql); 
            ps = conn.prepareStatement(strSQL);
            System.out.println(strSQL);
            for (int i = 0; i < params.length; i++) {  
                ps.setObject(i + 1, params[i]);  
            }    
            result=ps.executeUpdate();
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }                       
		return result;
	}
	
	/**
	 * 该方法负责执行向数据库插入操作
	 * @param sql
	 * @return 返回result结果为1表示插入成功；
	 * @throws SQLException
	 */
	public int Insert(Object obj) throws SQLException{
		Connection conn=openDBConnection();
		PreparedStatement ps = null;
		int result=0;
		int term=obj.getClass().getName().lastIndexOf(".")+1;  
        StringBuffer sql = new StringBuffer("Insert into "  
                + obj.getClass().getName().substring(term) + " (");  
        Field[] fields = obj.getClass().getDeclaredFields();      
            int size = fields.length;  
            Field field = null;  
            Object[] params = new Object[size];              
            try {  
            for (int i = 0; i < size - 1; i++) {  
                field = fields[i];  
                field.setAccessible(true);//运行时 把private 转成 public  
                params[i] = field.get(obj);  
                sql.append(field.getName() + ",");  
            }  
              
            fields[size-1].setAccessible(true);  
            params[size - 1] = fields[size - 1].get(obj);  
            sql.append(fields[size - 1].getName() + ") VALUES (");  
            
            for(Object objValue:params){  
                System.out.print("  value："+objValue);  
            } 
            
            for (int i = 0; i < size - 1; i++) {  
            	sql.append("? , ");   
            }  
            sql.append("?) ");                
            String strSQL = new String(sql);  
            ps = conn.prepareStatement(strSQL);
            for (int i = 0; i < params.length; i++) {  
                ps.setObject(i + 1, params[i]);  
            }            
            result=ps.executeUpdate();    
            System.out.println(result);
            this.closeDBConnection();
        } catch (Exception e) {  
            e.printStackTrace();  
        }            
		return result;
	}
}
