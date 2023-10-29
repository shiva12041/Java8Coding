import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;


/**
 * Adding some comments to push the file to git repo
 */
public class MinimunWindowSubString {

    public static void main(String[] args){
        System.out.println(minWindow());
    }
    public static String minWindow(){
        String s = "cabwefgewcwaefgcf";
        String t = "cae";
        if("".equalsIgnoreCase(s) || "".equalsIgnoreCase(t)){
            return "";
        }

        if(t.length() > s.length()){
            return "";
        }
        Map<Character, Integer> charArrayMap = new HashMap<>();
        Map<Character, Integer> mainArrayMap = new HashMap<>();
        char[] charArray= t.toCharArray();
        char[] mainArray = s.toCharArray();
        int start = -1, end = -1, finalStart = -1, finalEnd = -1;
        boolean allFound = false;
        int minWindow = Integer.MAX_VALUE;
        String minSubString = "";

        for(int i = 0; i < charArray.length; i++){
            charArrayMap.put(charArray[i], charArrayMap.get(charArray[i]) == null ? 1 : charArrayMap.get(charArray[i]).intValue()+1);
        }
        for(int i =0; i < mainArray.length; i++){
            if(charArrayMap.containsKey(mainArray[i])){
                mainArrayMap.put(mainArray[i],mainArrayMap.get(mainArray[i]) == null ? 1 : mainArrayMap.get(mainArray[i]).intValue()+1);
                if(start == -1) {
                    start = i;
                }

                if(!allFound) {
                    for (Map.Entry<Character, Integer> entry : charArrayMap.entrySet()) {
                        if ((mainArrayMap.get(entry.getKey()) != null
                                && mainArrayMap.get(entry.getKey()).intValue() >= entry.getValue().intValue())) {
                            allFound = true;
                        } else {
                            allFound = false;
                            break;
                        }
                    }
                }
                if(allFound) {
                    end = i;
                    if(end-start < minWindow){
                        finalStart = start;
                        finalEnd = end;
                        minWindow = end-start;
                    }
                    while (start < end) {
                        if (!mainArrayMap.containsKey(mainArray[start])
                                || (mainArrayMap.get(mainArray[start]).intValue()) - 1 >= charArrayMap.get(mainArray[start]).intValue()) {
                            if (mainArrayMap.containsKey(mainArray[start]))
                                mainArrayMap.put(mainArray[start], mainArrayMap.get(mainArray[start]) - 1);
                            start++;
                            if(end-start < minWindow){
                                finalStart = start;
                                finalEnd = end;
                                minWindow = end-start;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        if(!allFound){
            return "";
        }
        return s.substring(finalStart, finalEnd+1);


    }
}
