package com.procialize.ingenratytour.GetterSetter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizData {

    @SerializedName("quiz_list")
    @Expose
    private List<QuizList_> quizList = null;
    @SerializedName("quiz_flag")
    @Expose
    private Integer quizFlag;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("status")
    @Expose
    private String status;

    public List<QuizList_> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<QuizList_> quizList) {
        this.quizList = quizList;
    }

    public Integer getQuizFlag() {
        return quizFlag;
    }

    public void setQuizFlag(Integer quizFlag) {
        this.quizFlag = quizFlag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
