package webtest;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dmssecond {
	private String IP = "122.199.152.76";
	private String PORT = "8629";
	private String TB_SID = "tibero";
	private String userid = "ijnet3_sejong";
	private String pwd = "ijnetP@ssw0rd";
	
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	//DB 커넥션
	private Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.tmax.tibero.jdbc.TbDriver");
		Connection connect = DriverManager.getConnection("jdbc:tibero:thin:@"+IP+":"+PORT+":"+TB_SID,userid, pwd);
		System.out.println("Tibero DB접속 Success!");
		return connect;
	}
	
	
	public ArrayList<String> dmsMember(Connection conn,String string) throws SQLException{
		StringBuffer selectQuery = new StringBuffer();
		ArrayList<String> list = new ArrayList<String>();
		if(string.equals("mem")){
		selectQuery.append("SELECT * from DMS_MEMBER \n");
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
		}
		if(string.equals("org")){
			selectQuery.append("SELECT * from DMS_ORG \n");
			pstmt = conn.prepareStatement(selectQuery.toString());
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(
					rs.getString("ORG_SEQ")
					+"|" + rs.getString("ORG_NM")
					+"|" + rs.getString("ADDRESS")
					+"|" + rs.getString("TEL")
					+"|" + rs.getString("MEMBER_NM")
					+"|" + rs.getString("FAX")
					+"|" + rs.getString("REC_STS")
					+"|" + rs.getString("CREATE_DATE")
					+"|" + rs.getString("CHARGER_ID")
					+"|" + rs.getString("CHARGER_NM")
				);
			}
			}
		if(string.equals("org_dept")){
			selectQuery.append("SELECT * from DMS_ORG_DEPT \n");
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
		}
		return list;
	}
	
	public static void main(String[] args) {
		Dmssecond main= new Dmssecond();
		Connection conn = null;
		try {

			conn = main.connect();
			conn.setAutoCommit(false);
			
			long startTime = System.currentTimeMillis();
			
			String text[]={"mem", "org", "org_dept"};
			for(int tt=0 ; tt < text.length; tt++)
			{
				ArrayList<String> DMS_MEMBER = main.dmsMember(conn,text[tt]);
				if(tt==0){
				System.out.println("DMS_MEMBER count("+DMS_MEMBER.size()+")");
				BufferedWriter Mem_writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:/Users/sayit/Downloads/DMS_MEMBER.txt"), "UTF-8"));
				for(int i = 0; i<DMS_MEMBER.size(); i++){
//					System.out.println(DMS_MEMBER.get(i));
					Mem_writer.write(DMS_MEMBER.get(i));
					Mem_writer.newLine();
				}
				if(Mem_writer != null){
					Mem_writer.close();
				}
				}
				if(tt==1){
					System.out.println("DMS_ORG count("+DMS_MEMBER.size()+")");
					BufferedWriter ORG_writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:/Users/sayit/Downloads/DMS_ORG.txt"), "UTF-8"));
					for(int i = 0; i<DMS_MEMBER.size(); i++){
//						System.out.println(DMS_MEMBER.get(i));
						ORG_writer.write(DMS_MEMBER.get(i));
						ORG_writer.newLine();
					}
					if(ORG_writer != null){
						ORG_writer.close();
					}
					}
				if(tt==2){
					System.out.println("DMS_ORG_DEPT count("+DMS_MEMBER.size()+")");
					BufferedWriter ORG_DEPT_writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:/Users/sayit/Downloads/DMS_ORG_DEPT.txt"), "UTF-8"));
					for(int i = 0; i<DMS_MEMBER.size(); i++){
//						System.out.println(DMS_MEMBER.get(i));
						ORG_DEPT_writer.write(DMS_MEMBER.get(i));
						ORG_DEPT_writer.newLine();
					}
					if(ORG_DEPT_writer != null){
						ORG_DEPT_writer.close();
					}
					}
				
			}
			long endTime = System.currentTimeMillis();
			long lTime = endTime - startTime;
			System.out.println("수행 시간  : " + lTime + "(ms)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
