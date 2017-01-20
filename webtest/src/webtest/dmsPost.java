package webtest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
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

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class dmsPost {
	private String IP = "122.199.152.76";
	private String PORT = "8629";
	private String TB_SID = "tibero";
	private String userid = "ijnet3_sejong";
	private String pwd = "ijnetP@ssw0rd";
	static String memPath = "C:/Users/sayit/Downloads/DMS/DMS_MEMBER.txt";
	static String orgPath = "C:/Users/sayit/Downloads/DMS/DMS_ORG.txt";
	static String orgdeptPath = "C:/Users/sayit/Downloads/DMS/DMS_ORG_DEPT.txt";
	static String TypeURL = "http://localhost:8080/webtest.jsp";
	
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	//DB 커넥션
	private Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.tmax.tibero.jdbc.TbDriver");
		Connection connect = DriverManager.getConnection("jdbc:tibero:thin:@"+IP+":"+PORT+":"+TB_SID,userid, pwd);
		System.out.println("Tibero DB접속 Success! \n");
		return connect;
	}
	
	
	public ArrayList<String> dmsMember(Connection conn) throws SQLException{
		String selectQuery ="SELECT * from DMS_MEMBER";

		ArrayList<String> list = new ArrayList<String>();
		pstmt = conn.prepareStatement(selectQuery.toString());
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
		return list;
	}
	
	
	public ArrayList<String> dmsOrgmemrel(Connection conn) throws SQLException{
		String selectQuery = "SELECT * from DMS_MEM_ORG_REL";
		
		ArrayList<String> list = new ArrayList<String>();
		pstmt = conn.prepareStatement(selectQuery.toString());
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
		return list;
	}
	
	
	public ArrayList<String> dmsOrgdept(Connection conn) throws SQLException{
		String selectQuery = "SELECT * from DMS_ORG_DEPT";
		
		ArrayList<String> list = new ArrayList<String>();
		pstmt = conn.prepareStatement(selectQuery.toString());
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
		return list;
	}
	
	
	public static void main(String[] args) {
		dmsPost main= new dmsPost();
		Connection conn = null;
		try {

			conn = main.connect();
			conn.setAutoCommit(false);

			long startTime = System.currentTimeMillis();

			ArrayList<String> DMS_MEMBER = main.dmsMember(conn);
			System.out.println("DMS_MEMBER count("+DMS_MEMBER.size()+") \n");
//			BufferedWriter Mem_writer = new BufferedWriter(new FileWriter("C:/Users/sayit/Downloads/DMS_MEMBER.txt"));// 기본적으로 UTF-8 로 설정
			BufferedWriter Mem_writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(memPath), "UTF-8"));
			for(int i = 0; i<DMS_MEMBER.size(); i++){
//				System.out.println(DMS_MEMBER.get(i));
				Mem_writer.write(DMS_MEMBER.get(i));
				Mem_writer.newLine();
			}
			if(Mem_writer != null){
				Mem_writer.close();
			}
			
			ArrayList<String> DMS_ORGMEMREL = main.dmsOrgmemrel(conn);
			System.out.println("DMS_ORG_MEM_REL count("+DMS_ORGMEMREL.size()+") \n");
//			BufferedWriter ORG_writer = new BufferedWriter(new FileWriter("C:/Users/sayit/Downloads/DMS_ORG.txt"));// 기본적으로 UTF-8 로 설정
			BufferedWriter ORG_writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(orgPath), "UTF-8"));
			for(int i = 0; i<DMS_ORGMEMREL.size(); i++){
				//System.out.println(DMS_ORG.get(i));
				ORG_writer.write(DMS_ORGMEMREL.get(i));
				ORG_writer.newLine();
			}
			if(ORG_writer != null){
				ORG_writer.close();
			}
			ArrayList<String> DMS_ORG_DEPT = main.dmsOrgdept(conn);
			System.out.println("DMS_ORG_DEPT count("+DMS_ORG_DEPT.size()+") \n");
//			BufferedWriter ORG_DEPT_writer = new BufferedWriter(new FileWriter("C:/Users/sayit/Downloads/DMS_ORG_DEPT.txt")); // 기본적으로 UTF-8 로 설정
			BufferedWriter ORG_DEPT_writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(orgdeptPath), "UTF-8"));
			for(int i = 0; i<DMS_ORG_DEPT.size(); i++){
//				System.out.println(DMS_ORG_DEPT.get(i));
				ORG_DEPT_writer.write(DMS_ORG_DEPT.get(i));
				ORG_DEPT_writer.newLine();
			}
			if(ORG_DEPT_writer != null){
				ORG_DEPT_writer.close();
			}
			long endTime = System.currentTimeMillis();
			long lTime = endTime - startTime;
			System.out.println("수행 시간  : " + lTime + "(ms)");
	
			CloseableHttpClient http = HttpClients.createDefault();
			//StringBuffer result = new StringBuffer();
	        try{
	        	MultipartEntityBuilder params = MultipartEntityBuilder.create();
	        	File memfile = new File(memPath);
	        	File orgfile = new File(orgPath);
	        	File orgdeptfile = new File(orgdeptPath);
	    		if(memfile.exists() && orgfile.exists() && orgdeptfile.exists()){
		            params.addPart("file1",new FileBody(memfile, ContentType.create("application/octet-stream"),
		                    URLEncoder.encode(memfile.getName(), "UTF-8")));
		            params.addPart("file2",new FileBody(orgfile, ContentType.create("application/octet-stream"),
		                    URLEncoder.encode(orgfile.getName(), "UTF-8"))); 
		            params.addPart("file3",new FileBody(orgdeptfile, ContentType.create("application/octet-stream"),
		                    URLEncoder.encode(orgdeptfile.getName(), "UTF-8"))); 
	    		
	            HttpPost post = new HttpPost(TypeURL);
//	            post.setHeader("ENCTYPE","multipart/form-data");
	            post.setEntity(params.build());
	             
	            CloseableHttpResponse response = http.execute(post);
//	            response.setHeader("ENCTYPE","multipart/form-data");
	             
	            
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		}
			
	}
}
