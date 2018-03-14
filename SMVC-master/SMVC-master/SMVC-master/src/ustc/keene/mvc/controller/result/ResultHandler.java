package ustc.keene.mvc.controller.result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ustc.keene.mvc.tools.ExceptionThrower;

/**
 * @author WXL
 * 
 *         该类负责定义请求结果Result的处理责任链父类
 */
public abstract class ResultHandler {

	protected ResultHandler nextHandler;

	/**
	 * 该方法定义了一个抽象方法，具体实现由子类完成
	 * 
	 * @param rd
	 * @param req
	 * @param resp
	 */
	public abstract boolean handled(ResultDescriptor rd, HttpServletRequest req, HttpServletResponse resp);

	/**
	 * 该方法为模板方法，规定了子类处理必须按此算法方式工作
	 * 
	 * @param rd
	 * @param req
	 * @param resp
	 */
	public void handleResult(ResultDescriptor rd, HttpServletRequest req, HttpServletResponse resp) {
		if (!handled(rd, req, resp)) {
			passToNextHandler(rd, req, resp);
		}
	}

	/**
	 * 该方法负责将请求传递到下一个责任链节点，如果本节点为末端，则抛出异常
	 * 
	 * @param rd
	 * @param req
	 * @param resp
	 */
	public void passToNextHandler(ResultDescriptor rd, HttpServletRequest req, HttpServletResponse resp) {
		if (nextHandler != null) {
			nextHandler.handleResult(rd, req, resp);
		} else {
			ExceptionThrower.throwResultCannotBeHandled();
		}
	}

}
