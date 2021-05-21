public class ApplicableItem{

	@SerializedName("recurrence")
	private String recurrence;

	@SerializedName("redirect")
	private boolean redirect;

	@SerializedName("code")
	private String code;

	@SerializedName("method")
	private String method;

	@SerializedName("registration")
	private String registration;

	@SerializedName("links")
	private Links links;

	@SerializedName("operationType")
	private String operationType;

	@SerializedName("label")
	private String label;

	@SerializedName("grouping")
	private String grouping;

	@SerializedName("selected")
	private boolean selected;

	@SerializedName("inputElements")
	private List<InputElementsItem> inputElements;

	@SerializedName("contractData")
	private ContractData contractData;

	public String getRecurrence(){
		return recurrence;
	}

	public boolean isRedirect(){
		return redirect;
	}

	public String getCode(){
		return code;
	}

	public String getMethod(){
		return method;
	}

	public String getRegistration(){
		return registration;
	}

	public Links getLinks(){
		return links;
	}

	public String getOperationType(){
		return operationType;
	}

	public String getLabel(){
		return label;
	}

	public String getGrouping(){
		return grouping;
	}

	public boolean isSelected(){
		return selected;
	}

	public List<InputElementsItem> getInputElements(){
		return inputElements;
	}

	public ContractData getContractData(){
		return contractData;
	}
}
