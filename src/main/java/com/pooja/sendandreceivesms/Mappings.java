package com.pooja.sendandreceivesms;

import java.util.concurrent.atomic.AtomicLong;

public class Mappings {

    private final AtomicLong counter = new AtomicLong();
    private long id;
    private String phone1;

    public Mappings(String phone1, String phone2) {

        this.id = counter.incrementAndGet();
        this.phone1 = phone1;
        this.phone2 = phone2;
    }

    private String phone2;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
}
