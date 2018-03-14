package ustc.keene.mvc.controller.result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ustc.keene.mvc.controller.PageControllerDescriptor;
import ustc.keene.mvc.tools.ExceptionThrower;

/**
 * @author WXL
 * 
 *         负责实现web请求结果的分发
 */
public class ResultDispatcher {
	public static void dispatch(String result, PageControllerDescriptor pcd, HttpServletRequest req,
			HttpServletResponse resp) {
		ResultDescriptor rd = pcd.getResultsMap().get(result);
		ExceptionThrower.throwResultNotFoundException(rd);
		ChainOfResultHandlerFactory.getResultHandlerChain().handleResult(rd, req, resp);
	}
}
