package ustc.keene.mvc.dao;

/**
 * @author WXL
 * 
 * 该类负责封装数据库配置相关信息，如URL，用户名，密码，Driver和OR mapping相关信息
 * 
 */
public class DBConfiguration {
	/**
	 * 数据库链接DBurl,用户名DBuserName,用户密码DBuserPassword;数据库驱动DBdriver
	 */
   private String DBurl;
   private String DBuserName;
   private String DBuserPassword;
   private String DBdriver;
   public void setDBurl(String url){
	   this.DBurl=url;
   }
   public String getDBurl(){
	   return DBurl;
   }
   public void setDBuserName(String userName){
	   this.DBuserName=userName;
   }
   public String getDBuserName(){
	   return DBuserName;
   }
   public void setDBuserPassword(String userPassword){
	   this.DBuserPassword=userPassword;
   }
   public String getDBuserPassword(){
	   return DBuserPassword;
   }
   public void setDBdriver(String driver){
	   this.DBdriver=driver;
   }
   public String getDBdriver(){
	   return DBdriver;
   }
}
