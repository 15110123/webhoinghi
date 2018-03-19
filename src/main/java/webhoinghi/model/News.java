package webhoinghi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="news")
public class News implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String title;
	private String body;
	private String imgurl;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_created;
	
	
	public News() {}

	public News(String title, String body, String imgurl, Date date_created) {
		super();
		this.title = title;
		this.body = body;
		this.imgurl = imgurl;
		this.date_created = date_created;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public String getImgurl() {
		return imgurl;
	}


	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}


	public Date getDate_created() {
		return date_created;
	}


	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", body=" + body + ", imgurl=" + imgurl + ", date_created="
				+ date_created + "]";
	}
	
	
	
	
}
