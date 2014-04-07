import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import personal.tianjie.protobuf.AnnotatedProtobufCodec;
import personal.tianjie.util.Guid;

public class Test {
	public static void main(String[] args) throws Exception{
		String file = "/home/tianjie/Desktop/msg";
		/*Msg.Messsage.Builder msg = Msg.Messsage.newBuilder();
		msg.setId(150);
		msg.setName("");
		Message body = msg.build();
		CodedOutputStream cos = CodedOutputStream.newInstance(new FileOutputStream(file));
		cos.writeMessageNoTag(body);
		cos.flush();*/
		
		
		/*CodedInputStream cis = CodedInputStream.newInstance(new FileInputStream(file));
		Msg.Messsage.Builder msgr = Msg.Messsage.newBuilder();
		cis.readMessage(msgr,ExtensionRegistryLite.newInstance());
		System.out.println(msgr.getId());*/
		
		AnnotatedProtobufCodec codec = new AnnotatedProtobufCodec(Message1.class);
		String file1 = "/home/tianjie/Desktop/msg1";
		/*Message1 msg = new Message1(1);
		List<String > strs = new ArrayList<String>();
		strs.add("s1");
		strs.add("s1");
		strs.add("s1");
		msg.setStrs(strs);
		Guid gid =  Guid.randomGuid();
		msg.setGid(gid);
		Date d = new Date(System.currentTimeMillis());
		msg.setDate(d);*/
//		new FileOutputStream(file1) .write(codec.encode(msg));
		long s = System.currentTimeMillis();
		FileInputStream inputStream = new FileInputStream(file1);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int b=0;
		while((b = inputStream.read())!=-1){
			bos.write(b);
		}
		Message1 r = codec.decode(bos.toByteArray());
		long e = System.currentTimeMillis();
		System.out.println(r.getId());
		System.out.println(r.getGid().toStr());
		System.out.println(r.getDate());
		System.out.println("所耗时间："+(e-s));
		
		
	}
}