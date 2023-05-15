package com.domain.dto;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel
public class ApiResponse<T> {
    private MetaInfo meta;
    private String message;
    private boolean status;

    private Object data;
    

    public ApiResponse() {
        super();
    }

    public void setApiResponse(int total, int offset, int limit, String message, boolean status, Object data) {
        MetaInfo metaInfo = new MetaInfo(total, offset, limit);
        this.meta = metaInfo;
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public MetaInfo getMeta() {
        return meta;
    }

    public void setMeta(int total, int offset, int limit) {
        this.meta = new MetaInfo(total, offset, limit);
    }
}

class MetaInfo {
    private int total;
    private int offset;
    private int limit;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public MetaInfo(int total, int offset, int limit) {
        super();
        this.total = total;
        this.offset = offset;
        this.limit = limit;
    }
}
