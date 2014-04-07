import java.util.Date;
import java.util.List;

import personal.tianjie.protobuf.ProtoEntity;
import personal.tianjie.protobuf.ProtoMember;
import personal.tianjie.util.Guid;


public class Message1 extends ProtoEntity {
	@ProtoMember(1)
	private int id;
	@ProtoMember(2)
	private List<String> strs;
	@ProtoMember(3)
	private Guid gid;
	@ProtoMember(value=4)
	private Date date;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Message1(int id) {
		super();
		this.id = id;
	}  
	
	public List<String> getStrs() {
		return strs;
	}

	public void setStrs(List<String> strs) {
		this.strs = strs;
	}
	

	public Guid getGid() {
		return gid;
	}

	public void setGid(Guid gid) {
		this.gid = gid;
	}

	public Message1() {
	}  
	
}
