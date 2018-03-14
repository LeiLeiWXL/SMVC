package ustc.keene.mvc.test.login;

import ustc.keene.mvc.controller.PageControllerContext;
import ustc.keene.mvc.controller.interceptor.IInterceptor;

public class InterceptorTest implements IInterceptor {

	@Override
	public String preDo(PageControllerContext pcc) {
		/**
		 * 该方法记录客户端请求Action的名称及访问开始时间；
		 */
		System.out.println("predo");
		return null;
	}

	@Override
	public String afterDo(PageControllerContext pcc) {
		/**
		 * 记录Action访问结束时间及返回的结果；
		 */
		System.out.println("afterdo");
		return null;
	}

}
