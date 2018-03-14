/**
 * 
 */
package ustc.keene.mvc.controller.interceptor;

import ustc.keene.mvc.controller.PageControllerContext;

/**
 * @author WXL
 */
public interface IInterceptor {
 
	/**
	 * 该方法用于子类拦截器实现请求拦截功能，在PageController处理请求之前调用
	 * 返回String类型结果
	 * @return String
	 */
	public String preDo(PageControllerContext pcc);
	/**
	 * 该方法用于子类拦截器实现请求拦截功能，在PageController处理请求之后调用
	 * 返回String类型结果
	 * @return String
	 */
	public String afterDo(PageControllerContext pcc);
}
