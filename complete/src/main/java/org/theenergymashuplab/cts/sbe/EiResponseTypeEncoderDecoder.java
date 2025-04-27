package org.theenergymashuplab.cts.sbe;

import java.time.Instant;

import org.theenergymashuplab.cts.EiResponseType;
import org.theenergymashuplab.cts.RefIdType;
import org.theenergymashuplab.cts.generated_files.EiResponseTypeDecoder;
import org.theenergymashuplab.cts.generated_files.EiResponseTypeEncoder;

public class EiResponseTypeEncoderDecoder {
    public static void Encode(EiResponseTypeEncoder eiResponseTypeEncoder, EiResponseType eiResponse) {

        eiResponseTypeEncoder.createdDateTime().seconds(eiResponse.getCreatedDateTime().getEpochSecond());
        eiResponseTypeEncoder.createdDateTime().nano(eiResponse.getCreatedDateTime().getNano());

        eiResponseTypeEncoder.inResponseTo(eiResponse.getInResponseTo().getMyUidId());

        eiResponseTypeEncoder.responseCode(eiResponse.getResponseCode());

        // TODO: fix
        int length = eiResponse.getResponseDescription().length();
        String description = eiResponse.getResponseDescription();

        eiResponseTypeEncoder.responseDescription().buffer().putStringUtf8(0, description, length);

        eiResponseTypeEncoder.responseDetail(ConvertResponseDetailType(eiResponse.getResponseDetail()));
    }

    public static EiResponseType Decode(EiResponseTypeDecoder eiResponseTypeDecoder) {

        EiResponseType eiResponse = new EiResponseType();

        eiResponse.setCreatedDateTime(Instant.ofEpochSecond(
                eiResponseTypeDecoder.createdDateTime().seconds(),
                eiResponseTypeDecoder.createdDateTime().nano()));

        RefIdType inResponseTo = new RefIdType();
        inResponseTo.setMyUidId(eiResponseTypeDecoder.inResponseTo());
        eiResponse.setInResponseTo(inResponseTo);

        eiResponse.setResponseCode(eiResponseTypeDecoder.responseCode());

        // TODO: fix
        eiResponse.setResponseDescription(eiResponseTypeDecoder.responseDescription().buffer().getStringUtf8(0));

        eiResponse.setResponseDetail(ConvertResponseDetailType(eiResponseTypeDecoder.responseDetail()));

        return eiResponse;
    }

    private static org.theenergymashuplab.cts.generated_files.ResponseDetailType ConvertResponseDetailType(
            org.theenergymashuplab.cts.ResponseDetailType responseDetail) {

        switch (responseDetail) {
            case UNSPECIFIED:
                return org.theenergymashuplab.cts.generated_files.ResponseDetailType.UNSPECIFIED;
            case RULES_VIOLATION:
                return org.theenergymashuplab.cts.generated_files.ResponseDetailType.RULES_VIOLATION;
            case INVALID_REFERENCE:
                return org.theenergymashuplab.cts.generated_files.ResponseDetailType.INVALID_REFERENCE;
            case DUPLICATE:
                return org.theenergymashuplab.cts.generated_files.ResponseDetailType.DUPLICATE;
            case TRADING_CLOSED:
                return org.theenergymashuplab.cts.generated_files.ResponseDetailType.TRADING_CLOSED;
            case PARTY_RESTRICTED:
                return org.theenergymashuplab.cts.generated_files.ResponseDetailType.PARTY_RESTRICTED;
            case INVALID_INSTRUMENT:
                return org.theenergymashuplab.cts.generated_files.ResponseDetailType.INVALID_INSTRUMENT;
            case FORCE_MAJEURE:
                return org.theenergymashuplab.cts.generated_files.ResponseDetailType.FORCE_MAJEURE;
            case INVALID_MARKET:
                return org.theenergymashuplab.cts.generated_files.ResponseDetailType.INVALID_MARKET;
            case INVALID_SEGMENT:
                return org.theenergymashuplab.cts.generated_files.ResponseDetailType.INVALID_SEGMENT;
            case SUCCESS:
                return org.theenergymashuplab.cts.generated_files.ResponseDetailType.SUCCESS;
            case NOT_AUTHORIZED:
                return org.theenergymashuplab.cts.generated_files.ResponseDetailType.NOT_AUTHORIZED;
            case INVALID_ARTIFACT:
                return org.theenergymashuplab.cts.generated_files.ResponseDetailType.INVALID_ARTIFACT;
            default:
                return org.theenergymashuplab.cts.generated_files.ResponseDetailType.NULL_VAL;
        }

    }

    private static org.theenergymashuplab.cts.ResponseDetailType ConvertResponseDetailType(
            org.theenergymashuplab.cts.generated_files.ResponseDetailType responseDetail) {

        switch (responseDetail) {
            case UNSPECIFIED:
                return org.theenergymashuplab.cts.ResponseDetailType.UNSPECIFIED;
            case RULES_VIOLATION:
                return org.theenergymashuplab.cts.ResponseDetailType.RULES_VIOLATION;
            case INVALID_REFERENCE:
                return org.theenergymashuplab.cts.ResponseDetailType.INVALID_REFERENCE;
            case DUPLICATE:
                return org.theenergymashuplab.cts.ResponseDetailType.DUPLICATE;
            case TRADING_CLOSED:
                return org.theenergymashuplab.cts.ResponseDetailType.TRADING_CLOSED;
            case PARTY_RESTRICTED:
                return org.theenergymashuplab.cts.ResponseDetailType.PARTY_RESTRICTED;
            case INVALID_INSTRUMENT:
                return org.theenergymashuplab.cts.ResponseDetailType.INVALID_INSTRUMENT;
            case FORCE_MAJEURE:
                return org.theenergymashuplab.cts.ResponseDetailType.FORCE_MAJEURE;
            case INVALID_MARKET:
                return org.theenergymashuplab.cts.ResponseDetailType.INVALID_MARKET;
            case INVALID_SEGMENT:
                return org.theenergymashuplab.cts.ResponseDetailType.INVALID_SEGMENT;
            case SUCCESS:
                return org.theenergymashuplab.cts.ResponseDetailType.SUCCESS;
            case NOT_AUTHORIZED:
                return org.theenergymashuplab.cts.ResponseDetailType.NOT_AUTHORIZED;
            case INVALID_ARTIFACT:
                return org.theenergymashuplab.cts.ResponseDetailType.INVALID_ARTIFACT;
            default:
                return null;
        }

    }
}
