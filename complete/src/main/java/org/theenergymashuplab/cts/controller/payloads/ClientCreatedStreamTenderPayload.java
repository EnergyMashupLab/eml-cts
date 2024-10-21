package org.theenergymashuplab.cts.controller.payloads;


public class ClientCreatedStreamTenderPayload{
	long ctsStreamTenderId;
	private Boolean success = false;
	private String info = "ClientCreatedStreamTenderPayload";

	//JSON
	public ClientCreatedStreamTenderPayload() {
	}

	public ClientCreatedStreamTenderPayload(long id){
		this.ctsStreamTenderId = id;
		this.success = true;
	}

	public ClientCreatedStreamTenderPayload(long ctsStreamTenderId, Boolean success, String info) {
		this.ctsStreamTenderId = ctsStreamTenderId;
		this.success = success;
		this.info = info;
	}



	public long getCtsStreamTenderId() {
		return ctsStreamTenderId;
	}

	public void setCtsStreamTenderId(long ctsStreamTenderId) {
		this.ctsStreamTenderId = ctsStreamTenderId;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "ClientCreatedStreamTenderPayload{" +
				"ctsStreamTenderId=" + ctsStreamTenderId +
				", success=" + success +
				", info='" + info + '\'' +
				'}';
	}
}
