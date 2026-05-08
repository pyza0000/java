package com.example.quiz;

public class Produkt {
    private String nick;
    private String answer;
    private String ip;

    public Produkt(String nick, String answer, String ip) {
        this.nick = nick;
        this.answer = answer;
        this.ip = ip;
    }

    public String getNick() { return nick; }
    public String getAnswer() { return answer; }
    public String getIp() { return ip; }
}