package com.hsw.tdd.errorControl;

public class ErrorChild {
    public void childError() {
        Exception error = new NoSuchFieldException("file fail");
        new ErrorParents(error).getMessage();
    }
}
