package webtest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;


public class eatTest {
	
	public static Map<String, Object> sikYakResult(int iter) throws IOException, JDOMException{
		Map<String, Object> map = new HashMap<String,Object>();
		
		
		SAXBuilder parser = new SAXBuilder();
        parser.setValidation(false);
        parser.setIgnoringElementContentWhitespace(true);
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1470000/FoodExaathrInfoService/getFoodExaathrList"); /*URL*/
        try {
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=2IQ41dJiflfmj%2BBvL8O180rNs76zlDyk19n2gc%2FtXL%2BfXIWQkCJc21zwW%2BgIX0FWpe%2Bi5U99YPqyz2wSR4RtTA%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(String.valueOf(iter), "UTF-8")); /*페이지 번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과수*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	//        System.out.println("Response code: " + conn.getResponseCode());
	        InputSource is= null;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	        	is = new InputSource(conn.getInputStream());
	            Document doc = parser.build(is);
	            Element root = doc.getRootElement();
	            Element body = root.getChild("body");
	            Element items = body.getChild("items");
	            List<?> itemEls = items.getChildren("item");//item의 갯수가 0개면 정상적인 호출이 아님
	            String totalCount = body.getChildTextTrim("totalCount");
	            map.put("totalCount", totalCount);
	            if(itemEls.size() > 0){
	            	map.put("itemsEl", items);
	            }
	        } else {
	        	is = new InputSource(conn.getErrorStream());
	        }
	        StringBuilder sb = new StringBuilder();
			System.out.println(sb.toString());
			conn.disconnect();
	        } catch (UnsupportedEncodingException e) {
				e.printStackTrace();
	        } catch (MalformedURLException e) {
				e.printStackTrace();
			}
		return map;
		
	}
	
	public static void main(String[] args) throws IOException {
		int pageNo = 0;
		String totalCount = "";
		try{
			SAXBuilder parser = new SAXBuilder();
	        parser.setValidation(false);
	        parser.setIgnoringElementContentWhitespace(true);
	        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1470000/FoodExaathrInfoService/getFoodExaathrList"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=2IQ41dJiflfmj%2BBvL8O180rNs76zlDyk19n2gc%2FtXL%2BfXIWQkCJc21zwW%2BgIX0FWpe%2Bi5U99YPqyz2wSR4RtTA%3D%3D");
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        InputSource is= null;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	        	is = new InputSource(conn.getInputStream());
	            Document doc = parser.build(is);
	            Element root = doc.getRootElement();
	            Element body = root.getChild("body");
	            Element items = body.getChild("items");
	            List<?> itemEls = items.getChildren("item");//item의 갯수가 0개면 정상적인 호출이 아님
	            totalCount = body.getChildTextTrim("totalCount");
	            System.out.println(" ================"+totalCount+" ================");
	        } else {
	        	is = new InputSource(conn.getErrorStream());
	        }
	
	        pageNo = (int)Math.ceil(Integer.parseInt(totalCount)/ 10.0);
	        System.out.println(pageNo);
	        for (int j = 1; j <= pageNo; j++) {
	        	
	        	Map<String, Object> listData = sikYakResult(j);
	        	
	        	if(listData != null && listData.get("itemsEl") != null){
	                Element itemsEl = (Element) listData.get("itemsEl");
	                List<?> itemEl = itemsEl.getChildren("item");
	                for(Object el : itemEl){
	                	Element element = (Element)el;
	//                  LinkedHashMap<String, String> list = new LinkedHashMap<String,String>();
	                    List<?> li = element.getChildren();
	                    for(int k= 0; k<li.size(); k++){
	                    	Element liEl = (Element)li.get(k);
							String tmpKey = liEl.getName();
							String value = element.getChild(tmpKey).getText().trim();
							System.out.println(tmpKey+" : " + value);
	                    }
	                    System.out.println();
	                }
	        	}
			}
	        conn.disconnect();
	        }catch(Exception e){
	        	e.printStackTrace();
        	}
	 }
}
