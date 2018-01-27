package ustc.keene.mvc.dao;

import java.sql.ResultSet;

/**
 * @author WXL
 * 该接口用于处理ResultSet结果，即数据库查询所得到的结果     
 */
public interface IResultSetHandler {
	public Object handler(ResultSet rs);
}
