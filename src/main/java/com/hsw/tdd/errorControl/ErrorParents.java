package com.hsw.tdd.errorControl;

public class ErrorParents {
    Exception e;

    public ErrorParents(Exception e) {
        this.e = e;
    }

    public void getMessage() {
        System.out.println(e.getClass());
    }
}
