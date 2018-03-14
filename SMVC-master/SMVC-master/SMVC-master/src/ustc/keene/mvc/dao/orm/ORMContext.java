package ustc.keene.mvc.dao.orm;
/**
 * @author WXL
 * 构建对象关系映射上下文，封装基本的持久化类信息
 * 
 */
public class ORMContext {
	ORMParser ormp=new ORMParser();
	/**
	 * 定义映射变量，对解析信息进行封装； 
	 */
	public static String DriverName;
	public static String DriverValue;
	public static String URLName;
	public static String URLValue;
	public static String DBUserName;
	public static String DBValue;
	public static String DBPassword;
	public static String DBPasswordValue;
	public static String UserBeanName;
	public static String UserBeanTable;
	public static String UserID;
	public static String UserProeryName;
	public static String UserPropertyColumn;
	public static String UserPropertyType;
	public static String UserPropertyLazy;
	
	/**
	 * 对or-mapping.xml配置文件的JDBC节点信息进行封装；
	 */
	public void getParserContextJDBC(){
	String[] strJDBC=ormp.ParserORMDocumentJDBC("C:/Users/Weixiaolei/workspace"
			+ "/SMVC-master/SMVC-master/src/or_mapping.xml");
	for(int i=0;i<strJDBC.length;i++){
		switch(strJDBC[i]){
		case "driver_class":
			DriverName=strJDBC[i];
			break;
		case "org.postgresql.Driver":
			DriverValue=strJDBC[i];
			break;
		case "url_path":
			URLName=strJDBC[i];
			break;
		case "jdbc:postgresql://localhost/hello2":
			URLValue=strJDBC[i];
			break;
		case "db_username":
			DBUserName=strJDBC[i];
			break;
		case "user01":
			DBValue=strJDBC[i];
			break;
		case "db_userpassword":
			DBPassword=strJDBC[i];
			break;
		case "user01password":
			DBPasswordValue=strJDBC[i];
			break;	
		default:
			break;
		}
	}
	}
	
	/**
	 * 对or-mapping.xml文件的class节点信息进习性封装；
	 */
	public void getParserContextClass(){
		String[] strClass=ormp.ParserORMDocumentCLASS("C:/Users/Weixiaolei/workspace"
				+ "/SMVC-master/SMVC-master/src/or_mapping.xml");
		for(int i=0;i<strClass.length;i++){
			switch(strClass[i]){
			case "UserBean":
				UserBeanName=strClass[i];
				break;
			case "user":
				UserBeanTable=strClass[i];
				break;
			case "userID":
				UserID=strClass[i];
				break;
			case "userPass":
				UserProeryName=strClass[i];
				break;
			case "user_pass":
				UserPropertyColumn=strClass[i];
				break;
			case "String":
				UserPropertyType=strClass[i];
				break;
			case "true":
				UserPropertyLazy=strClass[i];
				break;
			default:
				break;
			}
		}
	}
	
   
}
