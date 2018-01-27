package ustc.keene.mvc.dao.orm;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author WXL
 * 解析对象关系映射文件，构建ORMContext对象
 * 
 */

public class ORMParser {
	public String[] strJdbc=new String[8];//jdbc子节点信息
	public String[] strClass=new String[7];//class子节点信息
	ORMContext ormc=null;
	/**
	 * 该方法获得ORM配置文件
	 * @param DocumentPath
	 * @return document
	 * @throws ParserConfigurationException 
	 */
    public Document getORMDocument(String DocumentPath) throws ParserConfigurationException{
    	Document document=null;
    	DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
    	DocumentBuilder db=dbf.newDocumentBuilder();
    	try {
			  document=db.parse(DocumentPath);
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  return document;
    }
  /**
   * 该方法负责解析OR-Mapping文件，获得JDBC节点信息； 
 * @return 
   */
    public String[] ParserORMDocumentJDBC(String DOCPath){   	
    	Document document=null;
    	try {
			 document=getORMDocument(DOCPath);
			 NodeList jdbcList=document.getElementsByTagName("jdbc");
			 for(int i=0;i<jdbcList.getLength();i++){
					Element jdbc=(Element)jdbcList.item(i);
					NodeList property=jdbc.getElementsByTagName("property");
					for(int j=0;j<property.getLength();j++){
						Element n=(Element)property.item(j);
						NodeList name=n.getElementsByTagName("name");
						NodeList value=n.getElementsByTagName("value");
						String nameContent=name.item(0).getTextContent();
						strJdbc[i]=nameContent;
						String valueContent=value.item(0).getTextContent();
						strJdbc[++i]=valueContent;
						 ++i;
					}
				}			
				for(int i=0;i<strJdbc.length;i++){
					System.out.println(strJdbc[i]);
				}			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return strJdbc;
    }
    
    /**
     * 该方法负责解析OR-Mapping文件获得Class节点信息；
     * @return 
     */
    public String[] ParserORMDocumentCLASS(String DOCPath){
    	Document document=null;
    	try {
			document=getORMDocument(DOCPath);
			NodeList classList=document.getElementsByTagName("class");
			for(int i=0;i<classList.getLength();i++){
				Element Class=(Element)classList.item(i);
				NodeList name=Class.getElementsByTagName("name");
				NodeList table=Class.getElementsByTagName("table");
				strClass[i]=(String)name.item(0).getTextContent();
				strClass[++i]=(String)table.item(0).getTextContent();
				NodeList idList=Class.getElementsByTagName("id");
				for(int j=0;j<idList.getLength();j++){
					Element id=(Element)idList.item(0);
					NodeList idname=id.getElementsByTagName("name");
					strClass[++i]=idname.item(0).getTextContent();
				}
				NodeList propertyList=Class.getElementsByTagName("property");
				for(int k=0;k<propertyList.getLength();k++){
					Element propertyname=(Element)propertyList.item(k);
					NodeList pname=propertyname.getElementsByTagName("name");
					NodeList pcolumn=propertyname.getElementsByTagName("column");
					NodeList ptype=propertyname.getElementsByTagName("type");
					NodeList plazy=propertyname.getElementsByTagName("lazy");
					strClass[++i]=pname.item(0).getTextContent();		
				    strClass[++i]=pcolumn.item(0).getTextContent();	
				    strClass[++i]=ptype.item(0).getTextContent();
				    strClass[++i]=plazy.item(0).getTextContent();
				}		
			}
			for(int i=0;i<strClass.length;i++){
			System.out.println(strClass[i]);
		}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return strClass;
    }
}
    
