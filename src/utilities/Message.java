package utilities;

import java.net.DatagramPacket;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

public class Message {

	private static byte[] body;
	private String messageType;
	private String version;
	private String sender_id;
	private String file_id;
	private String chunkNumber;
	private String replicationDegree;

	//recebe um packet e ira futuramente analisar cada conteudo separando em header e body
	public Message(DatagramPacket packet) {

		String msg = new String(packet.getData());
		String[] cmd = msg.split("\\r\\n\\r\\n"); //<CRLF><CRLF>

		int body_index=cmd[0].length()+4;//header_size+ CRLF><CRLF

		getBodyfromMessage(packet.getData(),body_index,packet.getLength());

		String[] header=cmd[0].split(" "); //separar por espaços

		getHeader(header);


	}

	public static void getBodyfromMessage(byte[] data, int body_index,int length) {

		int body_size=length - body_index;
		body =new byte[body_size];
		int i;

		//percorre cada elemento da data do packet, começando na zona do body e passa para body
		for(i=body_index; i < length; i++) {
            body[i - body_index] = data[i];
        }
	}

	//funçao para separar o header por cada conteudo
	public void getHeader(String[] header) {

		if( header[0].equals("PUTCHUNK")) {

	        this.setMessageType(header[0]);
			this.setVersion(header[1]);
			this.setSender_id(header[2]);
			this.setFile_id(header[3]);
			this.setChunkNumber(header[4]);
			this.setReplicationDegree(header[5]);
	    }
		else if(header[0].equals("STORED") || header[0].equals("CHUNK") || header[0].equals("GETCHUNK") || header[0].equals("REMOVED")) {
			 this.setMessageType(header[0]);
			 this.setVersion(header[1]);
			 this.setSender_id(header[2]);
			 this.setFile_id(header[3]);
			 this.setChunkNumber(header[4]);
		}
		else if(header[0].equals("DELETE")) {
			 this.setMessageType(header[0]);
			 this.setVersion(header[1]);
			 this.setSender_id(header[2]);
			 this.setFile_id(header[3]);
		}

	}

	public String createResponse(String type, String version, String sender_id,String file_id,String chunkno) {

		String response=type +" "+version+" "+sender_id+" "+file_id+" "+chunkno+" "+"\r\n\r\n";

		return response;
		}

	//codifica uma string para SHa-256
	public static String getHash(String input) {
		String hashValue="";

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(input.getBytes("UTF-8"));
			byte[] digestedBytes= messageDigest.digest();
			hashValue= DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
		}
		catch(Exception e) {
		}
		return hashValue;
		}


	public String getMessageType() {
		return messageType;
	}


	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public String getSender_id() {
		return sender_id;
	}


	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}


	public String getFile_id() {
		return file_id;
	}


	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}


	public String getChunkNumber() {
		return chunkNumber;
	}


	public void setChunkNumber(String chunkNumber) {
		this.chunkNumber = chunkNumber;
	}


	public String getReplicationDegree() {
		return replicationDegree;
	}


	public void setReplicationDegree(String replicationDegree) {
		this.replicationDegree = replicationDegree;
	}

	public byte[] getBody(){
	    return Message.body;
	  }

}
