package database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.sql.Connection;

public class DBConnection {
	
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;
	
	public DBConnection() {
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","yoy0317689*");
			st = con.createStatement();
		}
		catch(Exception e) 
		{
			System.out.println("데이터베이스 연결 오류 : "+e.getMessage());
		}
	}
	
	//회원가입 매소드, 성공하거나 실패하면 해당 메시지 출력
	public void signUp(String personal_id, String personal_password) {
		try
		{
			String SQL = "INSERT INTO login_table (personal_id, personal_password) VALUES('"+personal_id+"', '"+personal_password+"');";
			st = con.createStatement();
			st.executeUpdate(SQL);
			System.out.println("회원가입 성공!");

		}
		catch(Exception e)
		{
			System.out.println("회원가입 실패! 존재하는 아이디 입니다");
			//System.out.println(e.getMessage());
		}
		
	}
	
	//로그인 매소드, 로그인 성공하면 true, 실패하면 false 반환
	public boolean logIn(String personal_id, String personal_password) { 
		try
		{
			String SQL = "SELECT personal_password FROM login_table WHERE personal_id = '"+personal_id+"';";
			rs = st.executeQuery(SQL);
			if(rs.next()) {
				if(personal_password.equals(rs.getString("personal_password"))) {
					System.out.println("로그인 성공"); //지워도 상관 없음
					return true; //입력한 아이디 비밀번호가, sql에 있으면 true 반환
				}
				else {
					System.out.println("비밀번호 잘못입력");//지워도 상관 없음
					return false;
				}
			}

		}
		catch(Exception e)
		{
			System.out.println("데이터베이스 검색 오류 : "+e.getMessage());
		}
		System.out.println("아이디나 비밀번호 잘못 입력");
		return false;
	}
	
	//스케줄 정보들 반환하는 매소드(해당날짜)
	public String[][] bringSchedule(String personal_id, int schedule_date_year,int schedule_date_month, int schedule_date_day ) {
		String [][] array = null;
		int i=0;
		try
		{
			String SQL1 = "SELECT COUNT(*) FROM personal_schedule_table WHERE personal_id ='"+personal_id+
					"' AND schedule_date_year ="+schedule_date_year+
					" AND schedule_date_month ="+schedule_date_month+
					" AND schedule_date_day = "+schedule_date_day+";";
			rs = st.executeQuery(SQL1);
			if(rs.next()) {
				i=rs.getInt(1);
			}
			int j = 6; 
			array = new String[i][j];
		
			
			String SQL = "SELECT * FROM personal_schedule_table WHERE personal_id ='"+personal_id+
					"' AND schedule_date_year ="+schedule_date_year+
					" AND schedule_date_month ="+schedule_date_month+
					" AND schedule_date_day = "+schedule_date_day+";";
			rs = st.executeQuery(SQL);
			int k =0;
			while(rs.next()) {
					array[k][0] = rs.getString("schedule_id");
					array[k][1] = rs.getString("start_time");
					array[k][2] = rs.getString("end_time");//
					array[k][3] = rs.getString("done");
					array[k][4] = rs.getString("comment");
					k++;
			}
			
			for(k=0;k<i;k++) {
				String SQL2 = "SELECT * FROM schedule_measurement_table WHERE schedule_id ='"+array[k][0]+"';";
				rs = st.executeQuery(SQL2);
				if(rs.next()) {
					array[k][5] = rs.getString("schedule_measurement");
				}
			}
			
			

		}
		catch(Exception e)
		{
			System.out.println("데이터베이스 검색 오류 : "+e.getMessage());
		}
		return array; // array[][] 에서 첫번째[]는 스케줄들 마다 바뀌 두번째 []에서 0은 스케줄아이디, 1은 start_time, 2는 end_time
		
	}
	
	//로드맵 정보들 반환하는는 매소드(로드맵 아이디)
	public String[] bringRoadmap(String roadmap_id) {
			String [] array = new String[4];
			int i=0;
			try
			{
			
				
				String SQL = "SELECT * FROM roadmap_detail_table WHERE roadmap_id ='"+roadmap_id+"';";
				rs = st.executeQuery(SQL);
				int k =0;
				if(rs.next()) {
						array[0] = rs.getString("month_duration");//숫자형으로 반환해야 하는데 배열이 문자형이러서 문자형으로 반환
						array[1] = rs.getString("day_of_week");
						array[2] = rs.getString("day_duration");//
						array[3] = rs.getString("comment");
				}
				
	
				

			}
			catch(Exception e)
			{
				System.out.println("데이터베이스 검색 오류 : "+e.getMessage());
			}
			return array; // array[][] 에서 첫번째[]는 스케줄들 마다 바뀌 두번째 []에서 0은 스케줄아이디, 1은 start_time, 2는 end_time
			
		}
	
	//생성된 로드맵 id들 전부를 반환하는 매소드
	public String[] bringAllRoadmapId() {
				String [] array = null;
				int i=0;
				int j = 0;
				try
				{
				
					String SQL2 = "SELECT COUNT(*) FROM roadmap_detail_table";
					rs = st.executeQuery(SQL2);
					if(rs.next()) {
						i=rs.getInt(1);
					}
					array = new String[i];
					String SQL1 = "SELECT * FROM roadmap_detail_table";				
					rs = st.executeQuery(SQL1);
					while(rs.next()) {
						array[j]=rs.getString("roadmap_id");
						j++;
					}
		
				}
				catch(Exception e)
				{
					System.out.println("데이터베이스 검색 오류 : "+e.getMessage());
				}
				return array; 
				
			}
	
	//개인이 참가한 로드맵 id들을 반환하는 매소드
	public String[] bringParticipatedRoadmapId(String personal_id)  {
		String [] array = null;
		int i=0;
		int j = 0;
		try
		{
		
			String SQL2 = "SELECT COUNT(*) FROM personal_roadmap_table WHERE personal_id = '"+personal_id+"'";
			rs = st.executeQuery(SQL2);
			if(rs.next()) {
				i=rs.getInt(1);
			}
			array = new String[i];
			String SQL1 = "SELECT * FROM personal_roadmap_table WHERE personal_id = '"+personal_id+"'";				
			rs = st.executeQuery(SQL1);
			while(rs.next()) {
				array[j]=rs.getString("roadmap_id");
				j++;
			}

		}
		catch(Exception e)
		{
			System.out.println("데이터베이스 검색 오류 : "+e.getMessage());
		}
		return array; 
		
	}

	//개인의 하루 스케줄들의 진행률 확인
	public float completeRatioDay(String personal_id, int schedule_date_year,int schedule_date_month, int schedule_date_day) { 
		try
		{
			int planning_schedule=0;
			int done_schedule=0;
			float result=0;
			String SQL1 = "SELECT COUNT(*) FROM personal_schedule_table WHERE personal_id ='"+personal_id+
					"' AND schedule_date_year ="+schedule_date_year+
					" AND schedule_date_month ="+schedule_date_month+
					" AND schedule_date_day = "+schedule_date_day+";";
			rs = st.executeQuery(SQL1);
			if(rs.next()) {
				planning_schedule=rs.getInt(1);
			}
			String SQL2 = "SELECT COUNT(*) FROM personal_schedule_table WHERE personal_id ='"+personal_id+
					"' AND schedule_date_year ="+schedule_date_year+
					" AND schedule_date_month ="+schedule_date_month+
					" AND schedule_date_day ="+schedule_date_day+
					" AND done = 'done';";
			rs = st.executeQuery(SQL2);
			if(rs.next()) {
				done_schedule=rs.getInt(1);
			}
			result=((float)done_schedule/(float)planning_schedule);
			return result;

		}
		catch(Exception e)
		{
			System.out.println("데이터베이스 검색 오류 : "+e.getMessage());
		}
		System.out.println("오류입니다");
		return 0;
	}
	
	//개인의 입력한 기간 스케줄들의 진행률 확인
	public float completeRatio(String personal_id, int start_date_year,int start_date_month, int start_date_day, int end_date_year,int end_date_month, int end_date_day) { 
		try
		{
			int planning_schedule=0;
			int done_schedule=0;
			float result=0;
			
			//시작날과 끝날의 년,월이 같을 경우
			if(start_date_year==end_date_year && start_date_month==end_date_month) {
				String SQL1 = "SELECT COUNT(*) FROM personal_schedule_table WHERE schedule_date_year="+start_date_year+" AND schedule_date_month="+start_date_month+
						 " AND schedule_date_day >= "+start_date_day+" AND schedule_date_day <= "+end_date_day+" AND personal_id = '"+personal_id+"';";
				rs = st.executeQuery(SQL1);
				if(rs.next()) {
					planning_schedule=rs.getInt(1);
				}
				
				String SQL2 = "SELECT COUNT(*) FROM personal_schedule_table WHERE schedule_date_year="+start_date_year+" AND schedule_date_month="+start_date_month+
						 " AND schedule_date_day>="+start_date_day+" AND schedule_date_day<="+end_date_day+" AND personal_id = '"+personal_id+"' AND done = 'done';";;
				rs = st.executeQuery(SQL2);
				if(rs.next()) {
					done_schedule=rs.getInt(1);
				}
			}else if(start_date_year==end_date_year && start_date_month!=end_date_month) {
				String SQL1 = "SELECT COUNT(*) FROM personal_schedule_table WHERE personal_id = '"+personal_id+"' "+
						 "AND ((schedule_date_year="+start_date_year+" AND schedule_date_month="+start_date_month+" AND schedule_date_day>="+start_date_day+") "+
						 "OR(schedule_date_year="+start_date_year+" AND schedule_date_month>"+start_date_month+" AND schedule_date_month<"+end_date_month+") "+
						 "OR(schedule_date_year="+start_date_year+" AND schedule_date_month="+end_date_month+" AND schedule_date_day<="+end_date_day+"));";
				rs = st.executeQuery(SQL1);
				if(rs.next()) {
					planning_schedule=rs.getInt(1);
				}
				
				String SQL2 = "SELECT COUNT(*) FROM personal_schedule_table WHERE personal_id = '"+personal_id+"' AND done = 'done' "+
						 "AND ((schedule_date_year="+start_date_year+" AND schedule_date_month="+start_date_month+" AND schedule_date_day>="+start_date_day+") "+
						 "OR(schedule_date_year="+start_date_year+" AND schedule_date_month>"+start_date_month+" AND schedule_date_month<"+end_date_month+") "+
						 "OR(schedule_date_year="+start_date_year+" AND schedule_date_month="+end_date_month+" AND schedule_date_day<="+end_date_day+"));";
				rs = st.executeQuery(SQL2);
				if(rs.next()) {
					done_schedule=rs.getInt(1);
				}
			}else if(start_date_year!=end_date_year) {
				String SQL1 = "SELECT COUNT(*) FROM personal_schedule_table WHERE personal_id = '"+personal_id+"' "+
						 "AND ((schedule_date_year="+start_date_year+" AND schedule_date_month="+start_date_month+" AND schedule_date_day>="+start_date_day+") "+
						 "OR(schedule_date_year="+start_date_year+" AND schedule_date_month>"+start_date_month+") "+
						 "OR(schedule_date_year="+end_date_year+" AND schedule_date_month<"+end_date_month+") "+
						 "OR(schedule_date_year="+end_date_year+" AND schedule_date_month="+end_date_month+" AND schedule_date_day<="+end_date_day+"));";
				rs = st.executeQuery(SQL1);
				if(rs.next()) {
					planning_schedule=rs.getInt(1);
				}
				
				String SQL2 = "SELECT COUNT(*) FROM personal_schedule_table WHERE personal_id = '"+personal_id+"' AND done = 'done' "+
						 "AND ((schedule_date_year="+start_date_year+" AND schedule_date_month="+start_date_month+" AND schedule_date_day>="+start_date_day+") "+
						 "OR(schedule_date_year="+start_date_year+" AND schedule_date_month>"+start_date_month+") "+
						 "OR(schedule_date_year="+end_date_year+" AND schedule_date_month<"+end_date_month+") "+
						 "OR(schedule_date_year="+end_date_year+" AND schedule_date_month="+end_date_month+" AND schedule_date_day<="+end_date_day+"));";;
				rs = st.executeQuery(SQL2);
				if(rs.next()) {
					done_schedule=rs.getInt(1);
				}
			}
			
			
			
			
			result=((float)done_schedule/(float)planning_schedule);
			return result;



		}
		catch(Exception e)
		{
			System.out.println("데이터베이스 검색 오류 : "+e.getMessage());
		}
		return 0;
	}
		
	//스케줄 생성하는 매소드
	public void insertSchedule(String personal_id,int schedule_date_year,int schedule_date_month,int schedule_date_day,String start_time,String end_time,String schedule_measurement,String schedule_name,String schedule_url,String comment) {
		try
		{
			Scanner scanner = new Scanner(System.in); 
			String step1 =null;
			int step2=0;
			String schedule_id =null;
			String SQL1 = "SELECT schedule_id FROM personal_schedule_table ORDER BY id DESC;";
			st = con.createStatement();
			rs = st.executeQuery(SQL1);
			if(rs.next()) {
				step1=rs.getString("schedule_id");
			}else if(rs.next()==false){
				step1="S0";
			}
			step1=step1.substring(1);
			step2 = Integer.parseInt(step1);
			step2+=1;
			step1 = String.valueOf(step2);
			schedule_id = 'S'+step1; //스케줄아이디를 상황에 맞춰서 생성
				
			String SQL2 = "INSERT INTO personal_schedule_table (personal_id, schedule_id,schedule_date_year,schedule_date_month,schedule_date_day,start_time,end_time,done,comment) "+
					"VALUES('"+personal_id+"', '"+schedule_id+
					"',"+schedule_date_year+","+schedule_date_month+
					","+schedule_date_day+",'"+start_time+
					"','"+end_time+"','yet','"+comment+"');";
			st = con.createStatement();
			st.executeUpdate(SQL2);
			String SQL3 = "INSERT INTO schedule_measurement_table (schedule_id,schedule_measurement) "+
					"VALUES('"+schedule_id+"', '"+schedule_measurement+"');";
			st = con.createStatement();
			st.executeUpdate(SQL3);
			
			if(schedule_url != null) {
				String SQL4 = "INSERT INTO schedule_url_table (schedule_id,schedule_url) "
						+ "VALUES('"+schedule_id+"', '"+schedule_url+"');";
				st = con.createStatement();
				st.executeUpdate(SQL4);
			}
			
			String SQL5 = "INSERT INTO schedule_name_table (schedule_id,schedule_name) "+
					"VALUES('"+schedule_id+"', '"+schedule_name+"');";
			st = con.createStatement();
			st.executeUpdate(SQL5);
			
			System.out.println("스케줄 생성 완료!");
			
			
			scanner.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	//로드맵 생성하는 매소드
	public void insertRoadmap(int month_duration,String day_of_week,int day_duration,String roadmap_name,String comment) {
		try
		{
			Scanner scanner = new Scanner(System.in); 
			String step1 =null;
			int step2=0;
			String roadmap_id =null;
			String SQL1 = "SELECT roadmap_id FROM roadmap_detail_table ORDER BY id DESC";
			st = con.createStatement();
			rs = st.executeQuery(SQL1);
			if(rs.next()) {
				step1=rs.getString("roadmap_id");
			}else if(rs.next()==false){
				step1="R0";
			}
			step1=step1.substring(1);
			step2 = Integer.parseInt(step1);
			step2+=1;
			step1 = String.valueOf(step2);
			roadmap_id = 'R'+step1; //스케줄아이디를 상황에 맞춰서 생성
			
			
			String SQL2 = "INSERT INTO roadmap_detail_table (roadmap_id,month_duration,day_of_week,day_duration,comment) "+
					"VALUES('"+roadmap_id+"', "+month_duration+",'"+day_of_week+"',"+day_duration+",'"+comment+"');";
			st = con.createStatement();
			st.executeUpdate(SQL2);
			
			String SQL3 = "INSERT INTO roadmap_name_table (roadmap_id,roadmap_name) "+
					"VALUES('"+roadmap_id+"', '"+roadmap_name+"');";
			st = con.createStatement();
			st.executeUpdate(SQL3);
			
			System.out.println("로드맵 생성 완료");
			
			scanner.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	//스케줄 삭제하는 매소드
	public void deleteSchedule(String schedule_id) {
		try
		{
		
			
			
			String SQL2 = "DELETE FROM schedule_url_table WHERE schedule_id = '"+schedule_id+"';";
			st = con.createStatement();
			st.executeUpdate(SQL2);
			
			String SQL3 = "DELETE FROM schedule_measurement_table WHERE schedule_id = '"+schedule_id+"';";
			st = con.createStatement();
			st.executeUpdate(SQL3);
			
			String SQL4 = "DELETE FROM schedule_name_table WHERE schedule_id = '"+schedule_id+"';";
			st = con.createStatement();
			st.executeUpdate(SQL4);
			
			String SQL5 = "DELETE FROM personal_schedule_table WHERE schedule_id = '"+schedule_id+"';";
			st = con.createStatement();
			st.executeUpdate(SQL5);
			
			System.out.println("스케줄 삭제 완료!");
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	//로드맵 삭제하는 매소드
	public void deleteRoadmap(String roadmap_id) {
			try
			{
			
				
				String SQL1 = "DELETE FROM roadmap_name_table WHERE roadmap_id = '"+roadmap_id+"';";
				st = con.createStatement();
				st.executeUpdate(SQL1);
				
				String SQL2 = "DELETE FROM schedule_roadmap_table WHERE roadmap_id = '"+roadmap_id+"';";
				st = con.createStatement();
				st.executeUpdate(SQL2);
				
				String SQL3 = "DELETE FROM personal_roadmap_table WHERE roadmap_id = '"+roadmap_id+"';";
				st = con.createStatement();
				st.executeUpdate(SQL3);
				
				String SQL4 = "DELETE FROM roadmap_detail_table WHERE roadmap_id = '"+roadmap_id+"';";
				st = con.createStatement();
				st.executeUpdate(SQL4);
				
				System.out.println("로드맵 삭제 완료!");
				
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}
	
	//스케줄 완료 체크하는 매소드
	public void updateSchedule(String schedule_id,String done) {
		try
		{
			Scanner scanner = new Scanner(System.in);
			String SQL1 = "UPDATE personal_schedule_table SET done = '"+done+"' WHERE schedule_id = '"+schedule_id+"';";
			st = con.createStatement();
			st.executeUpdate(SQL1);
			
			System.out.println("스케줄 업데이트 완료!");
			scanner.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	//스케줄 id 받아서 스케줄이름 반환하는 매소드
	public String getSchedulename(String schedule_id) {
		try
		{
			String schedule_name = null;
			String SQL = "SELECT schedule_name FROM schedule_name_table WHERE schedule_id = '"+schedule_id+"';";
			rs = st.executeQuery(SQL);
			if(rs.next()) {
				schedule_name = rs.getString("schedule_name");
			}
			return schedule_name;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("오류 입니다!");
		return null;
	}
	
	//로드맵 id 받아서 로드맵이름 반환하는 매소드
	public String getRoadmapname(String roadmap_id) {
			try
			{
				String roadmap_name = null;
				String SQL = "SELECT roadmap_name FROM roadmap_name_table WHERE roadmap_id = '"+roadmap_id+"';";
				rs = st.executeQuery(SQL);
				if(rs.next()) {
					roadmap_name = rs.getString("roadmap_name");
				}
				return roadmap_name;
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			System.out.println("오류 입니다!");
			return null;
		}
	
	//로드맵 참가하는 매소드
	public void startRoadmap(String personal_id, String roadmap_id) {
		try
		{
			String[] weekDay = { "일", "월", "화", "수", "목", "금", "토" }; //일요일을 일주일의 시작으로 함
			int month_duration = 0;
			String day_of_week = null;
			int day_duration =0;
			String comment = null;
			String roadmap_name = getRoadmapname(roadmap_id);
			
			String step1 = null;
//			String SQL1 = "SELECT schedule_name FROM schedule_name_table WHERE schedule_id = '"+schedule_id+"';";
//			st = con.createStatement();
//			st.executeUpdate(SQL1);

			String SQL2 = "SELECT month_duration FROM roadmap_detail_table WHERE roadmap_id = '"+roadmap_id+"';";
			rs = st.executeQuery(SQL2);
			if(rs.next()) {
				month_duration = rs.getInt("month_duration");
				System.out.println(month_duration);
			}
			
			String SQL3 = "SELECT day_of_week FROM roadmap_detail_table WHERE roadmap_id = '"+roadmap_id+"';";
			rs = st.executeQuery(SQL3);
			if(rs.next()) {
				day_of_week = rs.getString("day_of_week");
				System.out.println(day_of_week);
			}
			
			String SQL4 = "SELECT day_duration FROM roadmap_detail_table WHERE roadmap_id = '"+roadmap_id+"';";
			rs = st.executeQuery(SQL4);
			if(rs.next()) {
				day_duration = rs.getInt("day_duration");
				System.out.println(day_duration);
			}
			
			String SQL7 = "SELECT comment FROM roadmap_detail_table WHERE roadmap_id = '"+roadmap_id+"';";
			rs = st.executeQuery(SQL7);
			if(rs.next()) {
				comment = rs.getString("comment");
				System.out.println(day_duration);
			}

			
			
			String SQL8 = "INSERT INTO personal_roadmap_table (personal_id,roadmap_id) "+
  					 "VALUES('"+personal_id+"', '"+roadmap_id+"');";
  			st = con.createStatement();
  			st.executeUpdate(SQL8);
  			
			Calendar cal = Calendar.getInstance(); 
		    int today_num = cal.get(Calendar.DAY_OF_WEEK)-1; //오늘의 요일 숫자화
	        String today = weekDay[today_num]; //오늘의 요일
	         
			//로드맵에서 받은 요일들을, split을 이용해서 배열들로 만듬
			String[] roadmap_weekdays = day_of_week.split(",");
			
			for(int l=0;l<roadmap_weekdays.length;l++) {
				cal = Calendar.getInstance();
				System.out.println(roadmap_weekdays[l]);
					switch(roadmap_weekdays[l]) {
					case "일": 
				    	for(int i=0;i<month_duration;i++) {
				        	for(int j=1;j<=4;j++) {
				        		cal.add(Calendar.DATE, 7);
				        		cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
				    	        int sub_year = cal.getTime().getYear()+1900;
				    	        int sub_month = cal.getTime().getMonth()+1;
				    	        int sub_date = cal.getTime().getDate();
				    	        
				    	        String SQL5 = "SELECT schedule_id FROM personal_schedule_table ORDER BY id DESC;";
				    			st = con.createStatement();
				    			rs = st.executeQuery(SQL5);
				    			if(rs.next()) {
				    				step1=rs.getString("schedule_id");
				    			}
				    	        insertSchedule(personal_id,sub_year,sub_month,sub_date,"-","-","check",roadmap_name,null,comment);
				    	        
				    	        String SQL6 = "INSERT INTO schedule_roadmap_table (schedule_id,roadmap_id,personal_id) "+
					   					 "VALUES('"+step1+"', '"+roadmap_id+"','"+personal_id+"');";
					   			st = con.createStatement();
					   			st.executeUpdate(SQL6);
				    	    		
					        }
				        }
				         break;
				    case "월": 
				    	for(int i=0;i<month_duration;i++) {
				        	for(int j=1;j<=4;j++) {
				        		cal.add(Calendar.DATE, 7);
				        		cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
				    	        int sub_year = cal.getTime().getYear()+1900;
				    	        int sub_month = cal.getTime().getMonth()+1;
				    	        int sub_date = cal.getTime().getDate();

				    	        String SQL5 = "SELECT schedule_id FROM personal_schedule_table ORDER BY id DESC;";
				    			st = con.createStatement();
				    			rs = st.executeQuery(SQL5);
				    			if(rs.next()) {
				    				step1=rs.getString("schedule_id");
				    			}
				    	        insertSchedule(personal_id,sub_year,sub_month,sub_date,"-","-","check",roadmap_name,null,comment);
				    	        
				    	        String SQL6 = "INSERT INTO schedule_roadmap_table (schedule_id,roadmap_id,personal_id) "+
					   					 "VALUES('"+step1+"', '"+roadmap_id+"','"+personal_id+"');";
					   			st = con.createStatement();
					   			st.executeUpdate(SQL6);
					   			
					        }
				        }
				         break;
				    case "화": 
				    	for(int i=0;i<month_duration;i++) {
				        	for(int j=1;j<=4;j++) {
				        		cal.add(Calendar.DATE, 7);
				        		cal.set(Calendar.DAY_OF_WEEK,Calendar.TUESDAY);
				    	        int sub_year = cal.getTime().getYear()+1900;
				    	        int sub_month = cal.getTime().getMonth()+1;
				    	        int sub_date = cal.getTime().getDate();

				    	        String SQL5 = "SELECT schedule_id FROM personal_schedule_table ORDER BY id DESC;";
				    			st = con.createStatement();
				    			rs = st.executeQuery(SQL5);
				    			if(rs.next()) {
				    				step1=rs.getString("schedule_id");
				    			}
				    	        insertSchedule(personal_id,sub_year,sub_month,sub_date,"-","-","check",roadmap_name,null,comment);
				    	        
				    	        String SQL6 = "INSERT INTO schedule_roadmap_table (schedule_id,roadmap_id,personal_id) "+
				   					 "VALUES('"+step1+"', '"+roadmap_id+"','"+personal_id+"');";
					   			st = con.createStatement();
					   			st.executeUpdate(SQL6);
					   			
					        }
				        }
				         break;
				    case "수": 
				    	for(int i=0;i<month_duration;i++) {
				        	for(int j=1;j<=4;j++) {
				        		cal.add(Calendar.DATE, 7);
				        		cal.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);
				    	        int sub_year = cal.getTime().getYear()+1900;
				    	        int sub_month = cal.getTime().getMonth()+1;
				    	        int sub_date = cal.getTime().getDate();

				    	        String SQL5 = "SELECT schedule_id FROM personal_schedule_table ORDER BY id DESC;";
				    			st = con.createStatement();
				    			rs = st.executeQuery(SQL5);
				    			if(rs.next()) {
				    				step1=rs.getString("schedule_id");
				    			}
				    	        insertSchedule(personal_id,sub_year,sub_month,sub_date,"-","-","check",roadmap_name,null,comment);
				    	        
				    	        String SQL6 = "INSERT INTO schedule_roadmap_table (schedule_id,roadmap_id,personal_id) "+
					   					 "VALUES('"+step1+"', '"+roadmap_id+"','"+personal_id+"');";
					   			st = con.createStatement();
					   			st.executeUpdate(SQL6);
					        }
				        }
				         break;
				    case "목": 
				    	for(int i=0;i<month_duration;i++) {
				        	for(int j=1;j<=4;j++) {
				        		cal.add(Calendar.DATE, 7);
				        		cal.set(Calendar.DAY_OF_WEEK,Calendar.THURSDAY);
				    	        int sub_year = cal.getTime().getYear()+1900;
				    	        int sub_month = cal.getTime().getMonth()+1;
				    	        int sub_date = cal.getTime().getDate();

				    	        String SQL5 = "SELECT schedule_id FROM personal_schedule_table ORDER BY id DESC;";
				    			st = con.createStatement();
				    			rs = st.executeQuery(SQL5);
				    			if(rs.next()) {
				    				step1=rs.getString("schedule_id");
				    			}
				    	        insertSchedule(personal_id,sub_year,sub_month,sub_date,"-","-","check",roadmap_name,null,comment);
				    	        
				    	        String SQL6 = "INSERT INTO schedule_roadmap_table (schedule_id,roadmap_id,personal_id) "+
					   					 "VALUES('"+step1+"', '"+roadmap_id+"','"+personal_id+"');";
					   			st = con.createStatement();
					   			st.executeUpdate(SQL6);
					        }
				        }
				         break;
				    case "금": 
				    	for(int i=0;i<month_duration;i++) {
				        	for(int j=1;j<=4;j++) {
				        		cal.add(Calendar.DATE, 7);
				        		cal.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
				    	        int sub_year = cal.getTime().getYear()+1900;
				    	        int sub_month = cal.getTime().getMonth()+1;
				    	        int sub_date = cal.getTime().getDate();

				    	        String SQL5 = "SELECT schedule_id FROM personal_schedule_table ORDER BY id DESC;";
				    			st = con.createStatement();
				    			rs = st.executeQuery(SQL5);
				    			if(rs.next()) {
				    				step1=rs.getString("schedule_id");
				    			}
				    	        insertSchedule(personal_id,sub_year,sub_month,sub_date,"-","-","check",roadmap_name,null,comment);
				    	        
				    	        String SQL6 = "INSERT INTO schedule_roadmap_table (schedule_id,roadmap_id,personal_id) "+
					   					 "VALUES('"+step1+"', '"+roadmap_id+"','"+personal_id+"');";
					   			st = con.createStatement();
					   			st.executeUpdate(SQL6);
					        }
				        }
				         break;
				    case "토": 
				    	for(int i=0;i<month_duration;i++) {
				        	for(int j=1;j<=4;j++) {
				        		cal.add(Calendar.DATE, 7);
				        		cal.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
				    	        int sub_year = cal.getTime().getYear()+1900;
				    	        int sub_month = cal.getTime().getMonth()+1;
				    	        int sub_date = cal.getTime().getDate();

				    	        String SQL5 = "SELECT schedule_id FROM personal_schedule_table ORDER BY id DESC;";
				    			st = con.createStatement();
				    			rs = st.executeQuery(SQL5);
				    			if(rs.next()) {
				    				step1=rs.getString("schedule_id");
				    			}
				    	        insertSchedule(personal_id,sub_year,sub_month,sub_date,"-","-","check",roadmap_name,null,comment);
				    	        
				    	        String SQL6 = "INSERT INTO schedule_roadmap_table (schedule_id,roadmap_id,personal_id) "+
					   					 "VALUES('"+step1+"', '"+roadmap_id+"','"+personal_id+"');";
					   			st = con.createStatement();
					   			st.executeUpdate(SQL6);
					        }
				        }
				         break;
				    
				    default: 
				    	System.out.println("잘못된 값 입력");
				         break;
				}
			}
			System.out.println("로드맵 참가 완료!");
			
			
	        
			
			
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	//로드맵 부분검색 매소드 로드맵 id 반환해줌 - 검색할 문자열 입력
	public String[] searchRoadmap(String search_roadmap ) {
		int i=0;
		int j = 0;
		String [] array=null;
		try
		{
			String SQL2 = "SELECT COUNT(*) FROM roadmap_name_table";
			rs = st.executeQuery(SQL2);
			if(rs.next()) {
				i=rs.getInt(1);
			}
			
			array = new String[i];
			
			String SQL1 = "SELECT * FROM roadmap_name_table WHERE roadmap_name LIKE ?";
			String keyword = "%" + search_roadmap + "%";
			pst = con.prepareStatement(SQL1);
			pst.setString(1, keyword);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				array[j]=rs.getString("roadmap_id");
				j++;
			}
			
			

		}
		catch(Exception e)
		{
			System.out.println("데이터베이스 검색 오류 : "+e.getMessage());
		}
		return array; // 
		
	}
		
}
