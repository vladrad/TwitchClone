package streamer.com.myapplication.tools;

/**
 * Created by Vladi on 2/20/17.
 */

public class M3U8Parse {
    public static String getFirstLink(String info){
        String[] allLines = info.split("\n"); // split by newLine
        for(String line : allLines){
            if(line.startsWith("http")){ // grab the first stream link
                return line;
            }
        }
        return "";
    }

}
