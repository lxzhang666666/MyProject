package com.myself.project.eunm;

import com.myself.project.AbstractBiz;
import com.myself.project.impl.TestBiz1;

public enum TestEnum {
    CHECK("check",TestBiz1.getInstance());


    private String code;
    private AbstractBiz doAction;

    TestEnum(String code, AbstractBiz doAction) {
        this.code = code;
        this.doAction = doAction;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AbstractBiz getDoAction() {
        return doAction;
    }

    public void setDoAction(AbstractBiz doAction) {
        this.doAction = doAction;
    }

    public static AbstractBiz getActionByCode(String code){
        for (TestEnum testEnum : TestEnum.values()) {
            if(testEnum.getCode().equalsIgnoreCase(code)){
                return testEnum.getDoAction();
            }
        }
        return null;
    }
}
