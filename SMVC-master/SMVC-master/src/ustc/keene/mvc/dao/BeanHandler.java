package ustc.keene.mvc.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
/**
 * @author WXL
 * 实现了IResultSetHandler接口，主要还是运用了反射，对查询数据库所得的结果封装成对象；
 */
public class BeanHandler implements IResultSetHandler{
	private Class<?> beanClass;  
	  
    public BeanHandler(Class<?> beanClass) {  
        this.beanClass = beanClass;  
    }  
    @Override
    public Object handler(ResultSet rs) {  
        try {  
            if (!rs.next()) {  
                return null;  
            }  
            // 用于封装数据的bean  
            Object bean = beanClass.newInstance();   
            ResultSetMetaData meta = rs.getMetaData();  
            int columnCount = meta.getColumnCount();  
            for (int i = 0; i < columnCount; i++) {  
                String columnName = meta.getColumnName(i + 1);  
                Object columnValue = rs.getObject(columnName);  
                try {  
                 //属性修改器  
                    PropertyDescriptor pd = new PropertyDescriptor(columnName,  
                            bean.getClass());  
                    Method method = pd.getWriteMethod();  
                    method.invoke(bean, columnValue);  
                } catch (Exception e) {  
                    continue;  
                }  
            }  
            return bean;  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
}
