package org.example;
import org.apache.commons.lang3.RandomStringUtils;

public class Generator {
    public String name (){
        return "Ivan " + RandomStringUtils.randomAlphanumeric(7, 10);
    }

    public String password (){
        return "asd" + RandomStringUtils.randomAlphanumeric(3);
    }

    public String email (){
        return "test@" + RandomStringUtils.randomAlphanumeric(3) + ".ru";
    }
}
