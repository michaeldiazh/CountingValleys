import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;


public class CountingValleys{
    
    private static int placementFunction(char movement,int placement){
        if (movement == 'U'){
            return placement+1;
        }
        return placement-1;
    }

    private static int valleyFunction(ArrayList<Character> tup, int placement){
        int new_placement = placementFunction(tup.get(1), placement);
        boolean prior_low_level = (placement < 0);
        boolean sealevel = (new_placement == 0);

        if (sealevel && prior_low_level){
            return 1;
        }
        return 0;
        
    }

    public static int countingValleys(int n, char[] s) {
        int valley = 0;
        int plment = placementFunction(s[0], 0);
        ArrayList<Character> tup = new ArrayList<Character>();
        for (int i = 1; i < n; i++) {
            tup.add(0, s[i - 1]);
            tup.add(1, s[i]);
            valley += valleyFunction(tup, plment);
            plment = placementFunction(s[i], plment);
        }
        return valley;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("input.txt");
        // Create a buffer to read the file
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        Vector<String> storage = new Vector<String>();

        // While str = read next line isn't null, store it to the storage vector
        while ((str = br.readLine()) != null) {
            storage.add(str);
        }

        // storger.get(0) -> size of array; storage.get(1) -> the array
        int n = Integer.parseInt(storage.get(0));
        char[] s = storage.get(1).toCharArray();
        System.out.println(countingValleys(n, s));
        


    }

}