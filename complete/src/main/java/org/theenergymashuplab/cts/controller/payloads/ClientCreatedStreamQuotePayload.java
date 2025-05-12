/*
 * Copyright 2019-2025 The Energy Mashup Lab
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.theenergymashuplab.cts.controller.payloads;

import java.util.List;

public class ClientCreatedStreamQuotePayload {
    private List<EiCreatedQuotePayload> ctsStreamQuotesIds;
    private Boolean success = false;
    private String info = "ClientCreatedStreamQuotePayload";

    public ClientCreatedStreamQuotePayload() {}

    public ClientCreatedStreamQuotePayload(List<EiCreatedQuotePayload> ctsStreamQuotesIds) {
        this.ctsStreamQuotesIds = ctsStreamQuotesIds;
        this.success = true;
    }

    public ClientCreatedStreamQuotePayload(List<EiCreatedQuotePayload> ctsStreamQuotesIds, Boolean success,
            String info) {
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
        return "ClientCreatedStreamQuotePayload{" + "ctsStreamQuotesIds=" + ctsStreamQuotesIds + ", success=" + success
                + ", info='" + info + '"' + '}';
    }
}
