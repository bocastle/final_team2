package bit.or.eesotto.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
	
	private String userid;
	private String nick;
	private String pwd;
	private String loc;
	private String cpnumber;
	private String enabled;
	private String profile;
	private String rtime;
	private String lat;
	private String lot;
	private int point;
	
}