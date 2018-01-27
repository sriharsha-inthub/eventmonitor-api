package com.inthub.eventmonitor.models;

public class Total {

    private long count;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Total(){
        super();
    }

    public Total(long count){
        this.count = count;
    }

    @Override
    public String toString() {
        return "Total{" +
                "count=" + count +
                '}';
    }

}
