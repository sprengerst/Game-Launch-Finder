package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class Utility {

    static void runExeWithAdmin(String path) throws IOException {

        System.out.println("Trying to launch: " + path);

        String command = "powershell.exe start-process '" + path + "' -Verb RunAs";
        Process process = Runtime.getRuntime().exec(command);
        InputStream is = process.getInputStream();//Get an inputstream from the process which is being executed
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);//Prints all the outputs.Which is coming from the executed Process
        }
    }

}
