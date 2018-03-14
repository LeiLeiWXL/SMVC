package ustc.keene.mvc.dao.orm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @author WXL
 * 该类是获取类对象上下文信息；
 */
public class ObjectContext {
	
	/**
	 * 该方法获取类对象的映射上下文信息；
	 * @param obj
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public Object getObjectContext(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String sql=null;
		//此处需使用PersistentClassDescriptor持久化类描述符；
		Class<?> clz=obj.getClass();
		   Field[] field=clz.getDeclaredFields();
		   for(int i=0;i<field.length;i++){
			   if(field[i]!=null){
				   System.out.println(field[i].getGenericType());
			   }
			   if(field[i].getGenericType().toString().equals("class java.lang.String")){
				   try {
					Method method=obj.getClass().getMethod("get"+field[i].getName());
					String variable=(String) method.invoke(obj);//获得变量getter方法；
					if(variable!=null){
						if(field[i].getName().equals("MysqlHelper")){
							//构造sql语句；
							sql="sleect * from "+ORMContext.UserBeanTable+"where"+ORMContext.UserID+"="+variable;
							System.out.println(sql+"sql语句");
						}
					}					
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
		   }
		   return null;
	   }
}
