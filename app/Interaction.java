public class Interaction{

	@SerializedName("reason")
	private String reason;

	@SerializedName("code")
	private String code;

	public String getReason(){
		return reason;
	}

	public String getCode(){
		return code;
	}
}
