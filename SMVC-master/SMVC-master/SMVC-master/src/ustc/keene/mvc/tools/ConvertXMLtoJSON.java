package ustc.keene.mvc.tools;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

public class ConvertXMLtoJSON {

	/**@author WXL
	 * 该类是将xml文件转换为JSON格式文件；
	 */
	 public static void convertXMLtoJSON()  {
         InputStream is = ConvertXMLtoJSON.class.getResourceAsStream("/controller.xml");   
         String xml;    
         try {    
             xml = IOUtils.toString(is);    
             System.out.println(xml);    
             XMLSerializer xmlSerializer = new XMLSerializer(); 
             JSON json = xmlSerializer.read(xml);
             System.out.println(json);    
             System.out.println(json.toString(0));    
         } catch (IOException e) {    
             e.printStackTrace();    
         }    
     } 
}
