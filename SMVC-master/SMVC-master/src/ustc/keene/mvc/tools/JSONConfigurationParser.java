package ustc.keene.mvc.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ustc.keene.mvc.controller.ControllerConfiguration;
import ustc.keene.mvc.controller.PageControllerDescriptor;
import ustc.keene.mvc.controller.result.ResultDescriptor;

/**
 * @author Weixiaolei
 *         该类用于解析控制器配置文件controller.json，并构造ControllerConfiguration对象
 * 
 */
public class JSONConfigurationParser implements IConfigurationAdapter {

	public JSONArray InterceptorString=null;//拦截器对象；
	public JSONArray PageControllerString=null;//页面控制器对象；
	@Override
	public void getCCFromFile(ControllerConfiguration cc,String file) {
        try {
			String JSONContext=ReaderJSONFile(file);			
			JSONArray jsonArray = JSONArray.fromObject(JSONContext); 
			JSONObject jsonObject = jsonArray.getJSONObject(0);
			InterceptorString=(JSONArray) jsonObject.get("interceptor");//获取Interceptor信息
			PageControllerString=(JSONArray) jsonObject.get("page_controller");//获取page_controller信息
			JSONConfigurationParser.getPageController(PageControllerString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 该方法负责读取controller.json文件内容；
	 * @param Path
	 * @return
	 * @throws IOException 
	 */
	public String ReaderJSONFile(String Path) throws IOException{
		BufferedReader reader=null;
		String LasterString="";
		try {
			FileInputStream fileInputStream=new FileInputStream(Path);
			InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream,"utf-8");
			reader=new BufferedReader(inputStreamReader);
			String templeString=null;
			while((templeString=reader.readLine())!=null){
				LasterString+=templeString;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {  
                reader.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return LasterString;
	}
	
	/**
	 * 获取interceptor节点信息
	 * @param interceptor
	 */
	public static void getInterceptor(JSONArray interceptor){
		for(int i=0;i<interceptor.size();i++){
			PageControllerDescriptor pcd = new PageControllerDescriptor();
		    JSONObject job = interceptor.getJSONObject(i); // 遍历 jsonarray 数组,得到 json 对象
		    String name=(String) job.get("name");
		    String className=(String)job.get("Class");
		    pcd.setName(name);
		    pcd.setClassName(className);
		   // System.out.println(pcd.getName()+"类的名字"+pcd.getClassName()) ;  
		  }
	}
	
	/**
	 * 获取PageController中的name和class以及method信息；
	 * @param PageControllerString
	 */
	public static HashMap<String, PageControllerDescriptor> getPageController(JSONArray PageControllerString){
		HashMap<String, PageControllerDescriptor> pageControllers = new HashMap<String, PageControllerDescriptor>();
		for(int i=0;i<PageControllerString.size()-1;i++){
			//ResultDescriptor rd = new ResultDescriptor();
			PageControllerDescriptor pcd = new PageControllerDescriptor();
			JSONObject job=PageControllerString.getJSONObject(i);
			String name=(String) job.get("name");
		    String className=(String)job.get("Class");
		    String method=(String)job.get("method");
		    JSONArray result=job.getJSONArray("result");
		    JSONConfigurationParser.PageControllerResult(result);//获取PageController的result信息；
		    pcd.setName(name);
		    pcd.setClassName(className);
		    pcd.setMethodName(method);		
		    pageControllers.put(name, pcd);
		}
		return pageControllers;
	}
	
	/**
	 * 获取interceptor_ref信息；
	 * @param PageControllerString
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<String> getRef(JSONArray PageControllerString){
		List<String> refs = new ArrayList<String>();
		for(int j=0;j<PageControllerString.size();j++){		
			JSONObject job=PageControllerString.getJSONObject(++j);
			JSONArray interceptor_refName=job.getJSONArray("interceptor_ref");
			for(int k=0;k<interceptor_refName.size();k++){
				JSONObject job1=interceptor_refName.getJSONObject(k);
				Iterator iterator = job1.keys();
				while(iterator.hasNext()){
				       String  key = (String) iterator.next();
				       String  value = job1.getString(key);
				       System.out.println(key+"   "+value);
				       refs.add(value);
				}				
			}			
		}
		return refs;
	}
	
	/**
	 * 获取result结果信息；
	 * @param result
	 */
	public static void PageControllerResult(JSONArray result){
		ResultDescriptor rd = new ResultDescriptor();//PageControlle的结果描述符；
		for(int i=0;i<result.size()-1;i++){
			JSONObject job=result.getJSONObject(i);
			String name=job.getString("name");
			String type=job.getString("Type");
			String target=job.getString("target");
			rd.setResultName(name);
			rd.setResultType(type);
			rd.setResultTarget(target);
			//System.out.println(rd.getResultName()+rd.getResultTarget()+rd.getResultType());
		}
	}
}
