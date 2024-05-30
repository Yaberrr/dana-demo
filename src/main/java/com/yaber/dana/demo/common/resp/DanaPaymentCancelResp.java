package com.yaber.dana.demo.common.resp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yaber.dana.demo.common.exception.DanaException;
import com.yaber.dana.demo.common.serializer.DanaZoneDeserializer;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

/**
 * 取消支付resp
 * @author tangyabo
 * @date 2024/5/7
 */
@Data
public class DanaPaymentCancelResp extends DanaResp{

    //交易唯一id
    private String originalPartnerReferenceNo;

    //dana系统中的唯一id
    private String originalReferenceNo;

    //提交支付时请求头中的externalId
    private String originalExternalId;

    //交易时间
    @JsonDeserialize(using = DanaZoneDeserializer.class)
    private Date transactionDate;

    //取消时间
    @JsonDeserialize(using = DanaZoneDeserializer.class)
    private Date cancelTime;


    public boolean isSuccessful(){
        if(Code.SUCCESSFUL.codeEquals(this)){
            return true;
        }else{
            throw new DanaException(this);
        }
    }

    @Getter
    public enum Code {
        SUCCESSFUL("2005700", "Successful", "Success to be processed"),
        REQUEST_IN_PROGRESS("2025700", "Request In Progress", "Cancel is on process"),
        BAD_REQUEST("4005700", "Bad Request", "General request failed error"),
        INVALID_FIELD_FORMAT("4005701", "Invalid Field Format", "Invalid format for certain field"),
        INVALID_MANDATORY_FIELD("4005702", "Invalid Mandatory Field", "Missing or invalid format on mandatory field"),
        UNAUTHORIZED("4015700", "Unauthorized", "General unauthorized error"),
        INVALID_TOKEN_B2B("4015701", "Invalid Token (B2B)", "Authorization token are invalid, perhaps due to token expiry for B2B"),
        TRANSACTION_EXPIRED("4035700", "Transaction Expired", "Transaction cannot be cancelled because cancel time is already expired"),
        DO_NOT_HONOR("4035705", "Do Not Honor", "Account or user status is abnormal"),
        INSUFFICIENT_FUNDS("4035714", "Insufficient Funds", "Insufficient funds of merchant’s account to cancel the order"),
        TRANSACTION_NOT_PERMITTED("4035715", "Transaction Not Permitted", "Transaction not permitted"),
        INVALID_TRANSACTION_STATUS("4045700", "Invalid Transaction Status", "Invalid transaction status"),
        TRANSACTION_NOT_FOUND("4045701", "Transaction Not Found", "Transaction not found"),
        INVALID_MERCHANT("4045708", "Invalid Merchant", "Merchant does not exist or status abnormal"),
        TOO_MANY_REQUESTS("4295700", "Too Many Requests", "Maximum transaction limit exceeded"),
        GENERAL_ERROR("5005700", "General Error", "General error non retry-able"),
        INTERNAL_SERVER_ERROR("5005701", "Internal Server Error", "Unknown internal server failure, please retry the process again");

        private final String code;
        private final String message;
        private final String remarks;

        Code(String code, String message, String remarks) {
            this.code = code;
            this.message = message;
            this.remarks = remarks;
        }

        public boolean codeEquals(DanaResp resp) {
            return this.code.equals(resp.getResponseCode());
        }
    }

}
