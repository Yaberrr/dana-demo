package com.yaber.dana.demo.common.resp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yaber.dana.demo.common.enums.ServiceCodeEnum;
import com.yaber.dana.demo.common.enums.TransactionStatusEnum;
import com.yaber.dana.demo.common.exception.DanaException;
import com.yaber.dana.demo.common.model.DanaAdditionalInfo;
import com.yaber.dana.demo.common.model.DanaMoney;
import com.yaber.dana.demo.common.serializer.DanaZoneDeserializer;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

/**
 * 查询支付resp
 * @author tangyabo
 * @date 2024/5/6
 */
@Data
public class DanaPaymentQueryResp extends DanaResp {

    //交易唯一id
    private String originalPartnerReferenceNo;

    //dana系统中的唯一id
    private String originalReferenceNo;

    //交易类型
    private ServiceCodeEnum serviceCode;

    //最新交易状态
    private TransactionStatusEnum latestTransactionStatus;

    //状态描述
    private String transactionStatusDesc;

    //交易金额
    private DanaMoney amount;

    //订单标题
    private String title;

    //支付时间
    @JsonDeserialize(using = DanaZoneDeserializer.class)
    private Date paidTime;

    private DanaAdditionalInfo additionalInfo;


    public boolean isSuccessful(){
        if(Code.SUCCESSFUL.codeEquals(this)){
            return true;
        }else{
            throw new DanaException(this);
        }
    }


    @Getter
    public enum Code {
        SUCCESSFUL("2005500", "Successful", "Success to be processed"),
        BAD_REQUEST("4005500", "Bad Request", "General request failed error"),
        INVALID_FIELD_FORMAT("4005501", "Invalid Field Format", "Invalid format for certain field"),
        INVALID_MANDATORY_FIELD("4005502", "Invalid Mandatory Field", "Missing or invalid format on mandatory field"),
        UNAUTHORIZED("4015500", "Unauthorized", "General unauthorized error"),
        INVALID_TOKEN_B2B("4015501", "Invalid Token (B2B)", "Authorization token are invalid, perhaps due to token expiry for B2B"),
        TRANSACTION_NOT_FOUND("4045501", "Transaction Not Found", "Transaction not found"),
        TOO_MANY_REQUESTS("4295500", "Too Many Requests", "Maximum transaction limit exceeded"),
        GENERAL_ERROR("5005500", "General Error", "General error non retry-able"),
        INTERNAL_SERVER_ERROR("5005501", "Internal Server Error", "Unknown internal server failure, please retry the process again");

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
