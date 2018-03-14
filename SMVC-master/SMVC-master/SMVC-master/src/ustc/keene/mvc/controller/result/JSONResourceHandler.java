package ustc.keene.mvc.controller.result;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ustc.keene.mvc.tools.ConstantValues;
/** 
 *  该类为结果处理责任链上一个节点类，负责处理请求结果为json资源时结果的分发
 */
public class JSONResourceHandler extends ResultHandler {

	@Override
	public boolean handled(ResultDescriptor rd, HttpServletRequest req, HttpServletResponse resp) {
		String resultTarget=rd.getResultTarget();
		if(resultTarget.endsWith("json")){
		   String hostName = req.getServletContext().getContextPath();
		   String hostPath=req.getRequestURL().toString();
		   String realPath=hostPath.substring(0,hostPath.indexOf(hostName)) + hostName + "/";
		   try {
				if (rd.getResultType().equals(ConstantValues.DEFAULTRESULTTYPE)) {
					req.getServletContext().getRequestDispatcher("/" + resultTarget).forward(req, resp);
				} else {
					resp.sendRedirect(realPath + resultTarget);
				}
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
	}
}
