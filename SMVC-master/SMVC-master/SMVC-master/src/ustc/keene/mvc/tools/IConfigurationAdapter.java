package ustc.keene.mvc.tools;

import ustc.keene.mvc.controller.ControllerConfiguration;

/**
 * @author WXL
 * 
 * 该接口负责将文件类型的配置文件转换成ControllerConfiguration对象类型
 * 
 */
public interface IConfigurationAdapter {
	
	public void getCCFromFile(ControllerConfiguration cc,String file);

}
