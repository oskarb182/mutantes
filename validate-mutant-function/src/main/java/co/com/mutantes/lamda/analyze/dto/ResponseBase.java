package co.com.mutantes.lamda.analyze.dto;

public class ResponseBase {
	private MsgResult result = new MsgResult();

	public MsgResult getResult() {
		return result;
	}

	public void setResult(MsgResult result) {
		this.result = result;
	}
}
