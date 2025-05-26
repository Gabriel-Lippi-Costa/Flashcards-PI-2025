/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cards;

/**
 *
 * @author zion
 */
public class Cards {
    private String answer;
    private String question;
    public Cards(String a, String b){
        question = a;
        answer = b;
    }
    public String getAnswer(){
        return answer;
    }
    public String getQuestion(){
        return question;
    }
    public void setQuestion(String a){
        question = a;
    }
    public void setAnswer(String b){
        answer = b;
    }
}
