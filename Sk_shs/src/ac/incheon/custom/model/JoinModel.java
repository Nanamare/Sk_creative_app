package ac.incheon.custom.model;

import java.util.Date;

public class JoinModel {
	
	//user_id, password, user_name, board_name, regi_datetime
	private String user_id;
	private String password;
	//private String user_name;
	private String board_name;
	private String user_name;
	private Date regiDatetime;
	
	public String getId(){
		return user_id;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setId(String userid){
		user_id=userid;
	}
	
	public void setPassword(String password2){
		this.password=password2;
	}
	
	public String getBoardName() {
		return board_name;
	}
	public void setBoardName(String boardName) {
		this.board_name = boardName;
	}
	

	
	public String getUsername(){
		return user_name;
	}
	
	public void setUserName(String username){
		this.user_name = username;
	}
	
	public Date getRegiDatetime() {
		return regiDatetime;
	}
	public void setRegiDatetime(Date regiDatetime) {
		this.regiDatetime = regiDatetime;
	}

	
}
