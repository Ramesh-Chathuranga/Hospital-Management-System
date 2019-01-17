package lk.pos.hospital.controller.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean isEmail(String mail){
        Pattern email=Pattern.compile("^([a-z\\d\\.-]+)@([a-z\\d]+)\\.([a-z]{2,8})(\\.[a-z]{2,8})?$");
        Matcher gmail=email.matcher(mail);
        return gmail.matches();
    }

    public  static  boolean isPhoneNumber(String tel_phone){
        Pattern phone=Pattern.compile("\\(?\\d{3}\\)?[-.\\s]?\\d{7}$");
        Matcher mphone=phone.matcher(tel_phone);
        return mphone.matches();
    }

    public static boolean isNIC(String nic){
        Pattern nics=Pattern.compile("^[0-9]{9}[V|v]$");
        Matcher pnic=nics.matcher(nic);
        return pnic.matches();

    }
}
