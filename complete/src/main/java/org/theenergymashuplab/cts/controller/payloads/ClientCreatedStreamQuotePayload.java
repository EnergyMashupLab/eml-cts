package org.theenergymashuplab.cts.controller.payloads;

import java.util.List;

public class ClientCreatedStreamQuotePayload {
    private List<EiCreatedQuotePayload> ctsStreamQuotesIds;
    private Boolean success = false;
    private String info = "ClientCreatedStreamQuotePayload";

    public ClientCreatedStreamQuotePayload() {
    }

    public ClientCreatedStreamQuotePayload(List<EiCreatedQuotePayload> ctsStreamQuotesIds) {
        this.ctsStreamQuotesIds = ctsStreamQuotesIds;
        this.success = true;
    }

    public ClientCreatedStreamQuotePayload(List<EiCreatedQuotePayload> ctsStreamQuotesIds, Boolean success, String info) {
        this.ctsStreamQuotesIds = ctsStreamQuotesIds;
        this.success = success;
        this.info = info;
    }

    public List<EiCreatedQuotePayload> getCtsStreamQuotesIds() {
        return ctsStreamQuotesIds;
    }

    public void setCtsStreamQuotesIds(List<EiCreatedQuotePayload> ctsStreamQuotesIds) {
        this.ctsStreamQuotesIds = ctsStreamQuotesIds;
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
        return "ClientCreatedStreamQuotePayload{" +
                "ctsStreamQuotesIds=" + ctsStreamQuotesIds +
                ", success=" + success +
                ", info='" + info + '"' +
        '}';
    }
}