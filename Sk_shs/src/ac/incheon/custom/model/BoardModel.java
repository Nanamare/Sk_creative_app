package ac.incheon.custom.model;

import java.util.Date;

public class BoardModel {
	
	private int id;
	private String name;
	private String content;
	private String boardName;
	private String attachUrl;
	private Date regiDatetime;
	private Date updateDatetime;
	private String regiUserId;
	private String updateUserId;
	private String user_id;
	private String password;
	
	public String getuserId(){
		return user_id;
	}
	public void SetuserId(String userid){
		this.user_id = userid;
	}
	
	public String getPassWord(){
		return password;
	}
	
	public void SetPassword(String password){
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getAttachUrl() {
		return attachUrl;
	}
	public void setAttachUrl(String attachUrl) {
		this.attachUrl = attachUrl;
	}
	public Date getRegiDatetime() {
		return regiDatetime;
	}
	public void setRegiDatetime(Date regiDatetime) {
		this.regiDatetime = regiDatetime;
	}
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String getRegiUserId() {
		return regiUserId;
	}
	public void setRegiUserId(String regiUserId) {
		this.regiUserId = regiUserId;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	
	

}
