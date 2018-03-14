package ustc.keene.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ustc.keene.mvc.controller.result.ResultDispatcher;
import ustc.keene.mvc.tools.ExceptionThrower;

/**
 * @author WXL
 * 
 *         该类负责Request分发
 */
public class RequestDispatcher {

	/**
	 * 传入HttpServlet请求参数HttpServletRequest和HttpServletResponse
	 * 
	 * @param req
	 * @param resp
	 */
	public static void dispatch(HttpServletRequest req, HttpServletResponse resp) {
		//requestURI="/SMVC/login.sc"
		String requestURI = req.getRequestURI();
		if (requestURI.endsWith(".sc")) {
			int startIndex = requestURI.lastIndexOf("/");
			int endIndex = requestURI.lastIndexOf(".sc");
			String controllerName = requestURI.substring(startIndex + 1, endIndex);
			//controllerName为login,表单提交的action请求；
			PageControllerDescriptor pcd = ControllerConfiguration.getInstance().findController(controllerName);
			ExceptionThrower.throwPageControllerNotFoundException(pcd);
			IPageController ipc = new PageControllerProxy(pcd);
			String result = ipc.handle(initPageControllerContext(req));
			//result的结果为success或者failure;
			ResultDispatcher.dispatch(result, pcd, req, resp);
		}
	}

	private static PageControllerContext initPageControllerContext(HttpServletRequest req) {
		return null;
	}

}
