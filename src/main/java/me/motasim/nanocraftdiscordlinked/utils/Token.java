package me.motasim.nanocraftdiscordlinked.utils;

import me.motasim.nanocraftdiscordlinked.NanoCraftDiscordLinked;

import java.security.SecureRandom;

public class Token {

    private NanoCraftDiscordLinked main = NanoCraftDiscordLinked.getInstance();




    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public static String randomString(int len){
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }



}
