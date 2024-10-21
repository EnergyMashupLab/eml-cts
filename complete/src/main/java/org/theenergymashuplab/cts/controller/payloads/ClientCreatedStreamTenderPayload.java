package org.theenergymashuplab.cts.controller.payloads;

import java.util.List;

public class ClientCreatedStreamTenderPayload{
	private List<Long> ctsStreamTenderIds;
	private Boolean success = false;
	private String info = "ClientCreatedStreamTenderPayload";

	//JSON
	public ClientCreatedStreamTenderPayload() {
	}

	public ClientCreatedStreamTenderPayload(List<Long> ctsStreamTenderIds){
		this.ctsStreamTenderIds = ctsStreamTenderIds;
		this.success = true;
	}

	public ClientCreatedStreamTenderPayload(List<Long> ctsStreamTenderIds, Boolean success, String info) {
		this.ctsStreamTenderIds = ctsStreamTenderIds;
		this.success = success;
		this.info = info;
	}

	public List<Long> getCtsStreamTenderId() {
		return this.ctsStreamTenderIds;
	}

	public void setCtsStreamTenderId(List<Long> ctsStreamTenderIds) {
		this.ctsStreamTenderIds = ctsStreamTenderIds;
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
				"ctsStreamTenderIds=" + ctsStreamTenderIds.toString() +
				", success=" + success +
				", info='" + info + '\'' +
				'}';
	}
}
