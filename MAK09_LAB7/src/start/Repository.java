package start;

import java.util.HashMap;

/**
 * @author - Andrey Myssak
 * @since - 18.10.2017
 */
public class Repository {
    private static HashMap<Integer,String> results = new HashMap<Integer, String>();

    public String getResult(int i) {
        return results.get(i);
    }

    public void setResult(int index, String result) {
        results.put(index, result);
    }

    public HashMap<Integer, String> getHashMap() {
        return Repository.results;
    }
}
