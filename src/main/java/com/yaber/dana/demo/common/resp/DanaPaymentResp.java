package com.yaber.dana.demo.common.resp;

import com.yaber.dana.demo.common.exception.DanaException;
import lombok.Data;
import lombok.Getter;

/**
 * 发起支付resp
 * @author tangyabo
 * @date 2024/5/6
 */
@Data
public class DanaPaymentResp extends DanaResp{

    //dana系统中的唯一id
    private String referenceNo;

    //交易唯一id， 重试支付需用相同id
    private String partnerReferenceNo;

    //dana支付跳转url
    private String webRedirectUrl;


    public boolean isSuccessful(){
        if(Code.SUCCESSFUL.codeEquals(this)){
            return true;
        }else{
            throw new DanaException(this);
        }
    }

    @Getter
    public enum Code {
        SUCCESSFUL("2005400", "Successful", "Success to be processed"),
        BAD_REQUEST("4005400", "Bad Request", "General request failed error"),
        INVALID_FIELD_FORMAT("4005401", "Invalid Field Format", "Invalid format for certain field"),
        INVALID_MANDATORY_FIELD("4005402", "Invalid Mandatory Field", "Missing or invalid format on mandatory field"),
        UNAUTHORIZED("4015400", "Unauthorized", "General unauthorized error"),
        EXCEEDS_TRANSACTION_AMOUNT_LIMIT("4035402", "Exceeds Transaction Amount Limit", "Exceeds transaction amount limit"),
        DO_NOT_HONOR("4035405", "Do Not Honor", "Account or user status is abnormal"),
        TRANSACTION_NOT_PERMITTED("4035415", "Transaction Not Permitted", "Transaction not permitted"),
        INVALID_MERCHANT("4045408", "Invalid Merchant", "Merchant does not exist or status abnormal"),
        INCONSISTENT_REQUEST("4045418", "Inconsistent Request", "Inconsistent request parameter found for the same partner reference number/transaction identifier"),
        TOO_MANY_REQUESTS("4295400", "Too Many Requests", "Maximum transaction limit exceeded"),
        GENERAL_ERROR("5005400", "General Error", "General error non retry-able"),
        INTERNAL_SERVER_ERROR("5005401", "Internal Server Error", "Unknown internal server failure, please retry the process again");

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
