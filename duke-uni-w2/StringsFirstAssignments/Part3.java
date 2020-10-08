
public class Part3 {
    public boolean twoOccurrences (String stringa, String stringb){
        //stringb = stringa.toLowerCase();
        int one = stringb.indexOf(stringa);
        if (one == -1) {
            //System.out.println("one: "+ one);
            return false;
        }else{
            //System.out.println("one:: "+ one);
            int two = stringb.indexOf(stringa, one+ (int)stringa.length() );
            //System.out.println("two: "+ two);
            if (two == -1) {
                return false;
            }else{
            return true;
            }
        }
    }
    
    public void testing(){
        String[][] mydata = { {"by","A story by Abby Long"}, {"a", "banana"}, {"xox","xoooxooox"} };
        for(String[] arr: mydata){
            System.out.println("data: "+ arr[0] +","+arr[1]);
            System.out.println("twoOccurrences: "+ twoOccurrences(arr[0],arr[1]) );
            System.out.println("lastpart: "+ lastPart(arr[0],arr[1]) );
        }
        // System.out.println("twoOccurrences::: "+ twoOccurrences("a","ababab") );
    }
    
    public String lastPart(String stringa, String stringb){
        int one = stringb.indexOf(stringa);
        if (one == -1) {
            return stringb;
        }else{
            int strt = one + stringa.length();
            return stringb.substring(strt);
        }
    }
}
