package com.javarest.vo;

public class CommonResponse {
    private Long seqNo;
    private Long primaryTerm;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    public Long getPrimaryTerm() {
        return primaryTerm;
    }

    public void setPrimaryTerm(Long primaryTerm) {
        this.primaryTerm = primaryTerm;
    }
}
