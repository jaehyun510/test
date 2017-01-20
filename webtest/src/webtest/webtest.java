package webtest;
/* 
 * 세종시 결재시스템 회원 연계 모듈
 * 박재현 2017 01 17 HttpPost
 * 
 */
import java.io.BufferedReader;
import org.apache.commons.codec.binary.Base64;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.oreilly.servlet.Base64Encoder;

public class webtest {
	private final String MEM_PATH = "C:/Users/sayit/Downloads/test/DMS_MEMBER.txt";
	private final String ORG_PATH = "C:/Users/sayit/Downloads/test/DMS_ORG.txt";
	private final String ORG_DEPT_PATH = "C:/Users/sayit/Downloads/test/DMS_ORG_DEPT.txt";
	private final String TYPE_URL = "http://localhost:8081/webtest.jsp";
	
	private String className="";
	private String url = "";
	private String userName = "";
	private String password = "";
	
	private final String PROPERTY_FILE = "datasource.properties";
	
	public webtest() {
		try{
			loadProperty();
			
			Class.forName(this.className);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void loadProperty() {
		Properties prop = new Properties();
		try{
			prop.load(webtest.class.getClassLoader().getResourceAsStream(PROPERTY_FILE));
			
			this.className = prop.getProperty("className");
			this.url = prop.getProperty("url");
			this.userName = prop.getProperty("userName");
			this.password = prop.getProperty("password");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() throws Exception{
		return DriverManager.getConnection(url, userName, password);
	}
	
	public ArrayList<String> getDmsMember(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectQuery ="SELECT * from DMS_MEMBER";

		ArrayList<String> list = new ArrayList<String>();
		
		try {
			pstmt = conn.prepareStatement(selectQuery);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(
					rs.getString("MEMBER_SEQ")
					+"|" + rs.getString("MEMBER_NM")
					+"|" + rs.getString("MEMBER_ID")
					+"|" + rs.getString("PASSWD")
					+"|" + rs.getString("EMAIL")
					+"|" + rs.getString("MOBILE")
					+"|" + rs.getString("MOBILE_OPEN_YN")
					+"|" + rs.getString("PUSH_USE_YN")
					+"|" + rs.getString("PHOTO_ID")
					+"|" + rs.getString("REC_STS")
					+"|" + rs.getString("CREATE_DATE")
					+"|" + rs.getString("ALLIM_SMS_YN")
					+"|" + rs.getString("MEET_SMS_YN")
					+"|" + rs.getString("GALLERY_SMS_YN")
					+"|" + rs.getString("DOC_SMS_YN")
					+"|" + rs.getString("STATE")
					+"|" + rs.getString("POSITION_NM")
					+"|" + rs.getString("AGREE_YN")					
				);
			}	
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(Exception ee){}
			if(pstmt != null) try {pstmt.close();} catch(Exception ee){}
		}
		
		return list;
	}
	
	
	public ArrayList<String> getDmsOrgMemRel(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectQuery = "SELECT * from DMS_MEM_ORG_REL";
		
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			pstmt = conn.prepareStatement(selectQuery);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(
					rs.getString("MEMBER_SEQ")
					+"|" + rs.getString("ORG_SEQ")
					+"|" + rs.getString("ORG_DEPT_SEQ")
					+"|" + rs.getString("DEPT_SEQ")
					+"|" + rs.getString("CREATE_DATE")
					+"|" + rs.getString("POSITION_NM")
				);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(Exception ee){}
			if(pstmt != null) try {pstmt.close();} catch(Exception ee){}
		}
		
		return list;
	}
	
	
	public ArrayList<String> getDmsOrgDept(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectQuery = "SELECT * from DMS_ORG_DEPT";
		
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			pstmt = conn.prepareStatement(selectQuery);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(
					rs.getString("ORG_DEPT_SEQ")
					+"|" + rs.getString("ORG_DEPT_PARENT_SEQ")
					+"|" + rs.getString("ORG_DEPT_NM")
					+"|" + rs.getString("REC_STS")
					+"|" + rs.getString("DEPTH")
					+"|" + rs.getString("RANK")
					+"|" + rs.getString("TOP_DEPT_SEQ")
					+"|" + rs.getString("DEPT_SEQ")
					+"|" + rs.getString("ADMIN_YN")
					+"|" + rs.getString("AGENCY")
					+"|" + rs.getString("VOICE_TEL")
				);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(Exception ee){}
			if(pstmt != null) try {pstmt.close();} catch(Exception ee){}
		}
		
		return list;
	}
	
	/**
	 * 데이터베이스로부터 파일을 만든다.
	 * @param conn
	 * @throws Exception
	 */
	private void doFileWrite(Connection conn) throws Exception {
		BufferedWriter memWriter = null;
		BufferedWriter orgWriter = null;
		BufferedWriter orgDeptWriter = null;
		
		try {
			long startTime = System.currentTimeMillis();

			ArrayList<String> memberList = getDmsMember(conn);
			System.out.println("DMS_MEMBER count("+memberList.size()+") \n");
			
			memWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(MEM_PATH), "UTF-8"));
			for(int i = 0; i<memberList.size(); i++){
				//Base64 인코딩
				byte[] encoded = Base64.encodeBase64(memberList.get(i).getBytes("UTF-8"));
				memWriter.write(new String(encoded));
//				byte[] decoded = Base64.decodeBase64(encoded);
//				System.out.println(new String(decoded));
				memWriter.newLine();
			}
			memWriter.flush();
			
//			 BufferedReader in = new BufferedReader(new FileReader(MEM_PATH));
//			 String s;
//
//		      while ((s = in.readLine()) != null) {
//		    	  byte[] decoded = Base64.decodeBase64(s);
//					System.out.println(new String(decoded));
//		      }
//		      in.close();
			 
			ArrayList<String> orgMemList = getDmsOrgMemRel(conn);
			System.out.println("DMS_ORG_MEM_REL count("+orgMemList.size()+") \n");
			
			orgWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ORG_PATH), "UTF-8"));
			for(int i = 0; i<orgMemList.size(); i++){
				//Base64 인코딩
				orgWriter.write(Base64Encoder.encode(orgMemList.get(i)));
				orgWriter.newLine();
			}
			orgWriter.flush();
			
			ArrayList<String> orgDeptList = getDmsOrgDept(conn);
			System.out.println("DMS_ORG_DEPT count("+orgDeptList.size()+") \n");
			
			orgDeptWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ORG_DEPT_PATH), "UTF-8"));
			for(int i = 0; i<orgDeptList.size(); i++){
				//Base64인코딩
				orgDeptWriter.write(Base64Encoder.encode(orgDeptList.get(i)));
				orgDeptWriter.newLine();
			}
			orgDeptWriter.flush();
			
			long endTime = System.currentTimeMillis();
			long lTime = endTime - startTime;
			System.out.println("수행 시간  : " + lTime + "(ms)");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(memWriter != null) try {memWriter.close();} catch(Exception ee){}
			if(orgWriter != null) try {orgWriter.close();} catch(Exception ee){}
			if(orgDeptWriter != null) try {orgDeptWriter.close();} catch(Exception ee){}
		}
	}
	
	/**
	 * 파일을 결재서버에 전송한다.
	 * @throws Exception
	 */
	private void doFileSend() throws Exception {
		CloseableHttpClient http = HttpClients.createDefault();
		
        try{
        	MultipartEntityBuilder params = MultipartEntityBuilder.create();
        	File memfile = new File(MEM_PATH);
        	File orgfile = new File(ORG_PATH);
        	File orgdeptfile = new File(ORG_DEPT_PATH);
    		if(memfile.exists() && orgfile.exists() && orgdeptfile.exists()){
	            params.addPart("file1",new FileBody(memfile, ContentType.create("application/octet-stream"),
	                    URLEncoder.encode(memfile.getName(), "UTF-8")));
	            params.addPart("file2",new FileBody(orgfile, ContentType.create("application/octet-stream"),
	                    URLEncoder.encode(orgfile.getName(), "UTF-8"))); 
	            params.addPart("file3",new FileBody(orgdeptfile, ContentType.create("application/octet-stream"),
	                    URLEncoder.encode(orgdeptfile.getName(), "UTF-8"))); 
    		
	            HttpPost post = new HttpPost(TYPE_URL);
	            post.setEntity(params.build());
	             
	            CloseableHttpResponse response = http.execute(post);
	             
                HttpEntity res = response.getEntity();
                BufferedReader br = new BufferedReader(
                                    new InputStreamReader(res.getContent(), Charset.forName("UTF-8")));
                 
                while( (br.readLine())!=null ){
                    //result.append(buffer).append("\r\n");
                }
                response.close();
    		}
        }finally{
            http.close();
        }
	}
	
	public static void main(String[] args) {
		webtest main= new webtest();
		Connection conn = null;
		
		try {
			conn = main.getConnection();
	
			main.doFileWrite(conn);
			main.doFileSend();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null){try {conn.close();	} catch (SQLException e) {}}
		}	
	}
}
