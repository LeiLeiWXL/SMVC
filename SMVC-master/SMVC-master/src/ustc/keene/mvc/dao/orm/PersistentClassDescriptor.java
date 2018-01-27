package ustc.keene.mvc.dao.orm;

/**
 * @author WXL 
 * 如类名，表名，属性，列，延迟加载标志等
 * 
 */
public class PersistentClassDescriptor {
	
     private String ClassName;//类名
     private String TableName;//表名
     private String Property;//属性
     private String Column;//列名
     private String Lazy;//延迟加载
     
     public void setClassName(String className){
    	 this.ClassName=className;
     }
     public String getClassName(){
    	 return ClassName;
     }
     public void setTableName(String tableName){
    	 this.TableName=tableName;
     }
     public String getTableName(){
    	 return TableName;
     }
     public void setProperty(String property){
    	 this.Property=property;
     }
     public String getProperty(){
    	 return Property;
     }
     public void setCloumn(String cloumn){
    	 this.Column=cloumn;
     }
     public String getCloumn(){
    	 return Column;
     }
     public void setLazy(String lazy){
    	 this.Lazy=lazy;
     }
     public String getLazy(){
    	 return Lazy;
     }
}
