package com.yxnne.criminalintent.entity;

import java.util.UUID;

/**
 * 模型层
 * Created by yxnne
 * on 2016/12/20.
 */

public class Crime {
    private UUID mId;
    private String mTittle;
    public Crime(){
        //随机生成通用唯一标示码
        mId = UUID.randomUUID();
    }

    public String getTittle() {
        return mTittle;
    }

    public void setTittle(String tittle) {
        mTittle = tittle;
    }

    public UUID getId() {
        return mId;
    }
}
