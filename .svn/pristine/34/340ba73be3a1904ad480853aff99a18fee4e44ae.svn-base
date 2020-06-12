package com.t.httplib.yigou.bean.req;

import java.util.List;

public class ReqCommentInfo extends ReqTimeInfo {

    private String systemOrderNo;

    private List<ReqComment> comments;

    public String getSystemOrderNo() {
        return systemOrderNo;
    }

    public void setSystemOrderNo(String systemOrderNo) {
        this.systemOrderNo = systemOrderNo;
    }

    public List<ReqComment> getComments() {
        return comments;
    }

    public void setComments(List<ReqComment> comments) {
        this.comments = comments;
    }

    public static class ReqComment{

        private String goodsId;
        private String content;

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

    }

    @Override
    public String toString() {
        return "systemOrderNo" + systemOrderNo +
                "timestamp" + timestamp ;
    }
}
