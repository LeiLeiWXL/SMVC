package ustc.keene.mvc.test;


import java.util.HashMap;

import ustc.keene.mvc.controller.ControllerConfiguration;
import ustc.keene.mvc.controller.PageControllerDescriptor;
import ustc.keene.mvc.controller.interceptor.IInterceptor;

/**
 * @author WXL
 */
public class MainEntrance {

	public static void main(String[] args) {
		ControllerConfiguration cc = ControllerConfiguration.getInstance();
		HashMap<String, IInterceptor> interceptorList = cc.getInterceptorList();
		HashMap<String, PageControllerDescriptor> pageControllerList = cc.getPageControllerList();

		interceptorList.forEach((key, val) -> System.out.println(key + ":::" + val.toString()));
		pageControllerList.forEach((key, val) -> System.out.println(
				key + ":::" + val.getClassName() + ":::" + val.getMethodName() + ":::" + val.getInterceptorsList()));

		pageControllerList.forEach((key, val) -> val.getResultsMap().forEach((rkey, rval) -> System.out
				.println(rkey + ":::" + rval.getResultType() + ":::" + rval.getResultTarget())));
	}

}