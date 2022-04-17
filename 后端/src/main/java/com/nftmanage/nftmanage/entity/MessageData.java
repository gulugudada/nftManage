package com.nftmanage.nftmanage.entity;

import lombok.Data;

@Data
public class MessageData {
    private String fromUserId;//发送者id
    private String toUserId;//接受者id
    private String msgData;//信息内容
}
