package com.yxnne.criminalintent.entity;

import java.util.Date;
import java.util.UUID;

/**
 * 模型层
 * Created by yxnne
 * on 2016/12/20.
 */

public class Crime {
    //生成通用唯一标示码
    private UUID mId;
    private String mTittle;
    private Date mDate;
    private boolean mSolved;

    public Crime(){
        //随机生成通用唯一标示码
        mId = UUID.randomUUID();
        //时间
        mDate = new Date();
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

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
