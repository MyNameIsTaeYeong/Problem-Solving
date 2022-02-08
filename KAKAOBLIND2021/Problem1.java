import java.util.*;

class Solution {

    static StringBuilder stepOne(StringBuilder id){
        StringBuilder sb = new StringBuilder("");

        for(int i=0; i<id.length(); i++){
            if(0 <= id.charAt(i) - 'A' && id.charAt(i) - 'A' <= 25)
                sb.append((char)(id.charAt(i) + 32));
            else
                sb.append(id.charAt(i));
        }

        return sb;
    }

    static StringBuilder stepTwo(StringBuilder id){
        StringBuilder sb = new StringBuilder("");

        for(int i=0; i<id.length(); i++){
            if(0 <= id.charAt(i) - 'a' && id.charAt(i) - 'a' <= 25)
                sb.append(id.charAt(i));
            else if(0 <= id.charAt(i) - '0' && id.charAt(i) - '0' <= 9)
                sb.append(id.charAt(i));
            else if(id.charAt(i) == '-'
                    || id.charAt(i) == '_'
                    || id.charAt(i) == '.')
                sb.append(id.charAt(i));
        }

        return sb;
    }

    static StringBuilder stepThree(StringBuilder id){
        StringBuilder sb = new StringBuilder("");

        for(int i=0; i<id.length(); i++){
            if(sb.length() >= 1 && id.charAt(i) == '.' && sb.charAt(sb.length()-1) == '.')
                continue;
            sb.append(id.charAt(i));
        }

        return sb;
    }

    static void stepFour(StringBuilder id){
        if(id.length() >= 1 && id.charAt(0) == '.'){
            id.deleteCharAt(0);
        }

        if(id.length() >= 1 && id.charAt(id.length() - 1) == '.'){
            id.deleteCharAt(id.length() - 1);
        }
    }

    static void stepFive(StringBuilder id){
        if(id.length() == 0){
            id.append("a");
        }
    }

    static StringBuilder stepSix(StringBuilder id){
        StringBuilder sb = new StringBuilder("");


        if(id.length() >= 16){
            for(int i=0; i<15; i++){
                sb.append(id.charAt(i));
            }

            if(sb.charAt(14) == '.')
                sb.deleteCharAt(14);

            return sb;
        }

        return id;
    }

    static void stepSeven(StringBuilder id){

        char lastChar = id.charAt(id.length() - 1);

        while(id.length() <= 2){
            id.append(lastChar);
        }
    }

    public String solution(String new_id) {
        String answer = "";

        StringBuilder sb = new StringBuilder(new_id);

        sb = stepOne(sb);
        sb = stepTwo(sb);
        sb = stepThree(sb);
        stepFour(sb);
        stepFive(sb);
        sb = stepSix(sb);
        stepSeven(sb);

        answer = sb.toString();

        return answer;
    }
}