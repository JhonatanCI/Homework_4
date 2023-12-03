
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PrinterI implements Demo.Printer {

    String tiempoRespuesta;

    public String printString(String msg, com.zeroc.Ice.Current current) {
        long tiempoInicio;
        long tiempoFin;
        String lastCommandResponse = "";
        try{
            tiempoInicio = System.currentTimeMillis();
            System.out.println(msg);
            String completeMessage[] = msg.split(" ");
            String command = "";
            if(completeMessage[1].matches("[0-9]+")){
                lastCommandResponse = listPrimeNumbers(Integer.parseInt(completeMessage[1]));
            }else if((completeMessage[1].equalsIgnoreCase("listports") && completeMessage[2].matches("\\d\\d?\\d?\\.\\d\\d?\\d?\\.\\d\\d?\\d?\\.\\d\\d?\\d?"))){
                command = "nmap "+completeMessage[2];
                lastCommandResponse = executeCommand(command);
            }else if(completeMessage[1].equalsIgnoreCase("listifs")){
                command = "ifconfig";
                lastCommandResponse = executeCommand(command);
            }else if(completeMessage[1].charAt(0) == '!'){
                command = completeMessage[1].substring(1);
                if (completeMessage.length > 2) {
                    String[] message = new String[completeMessage.length - 2];
                    for (int i = 2; i < completeMessage.length; i++) {
                        message[i - 2] = completeMessage[i];
                    }
                    for (String elemento : message) {
                        command += " "+elemento;
                    }
                }
                lastCommandResponse = executeCommand(command);
            }
            tiempoFin = System.currentTimeMillis();
            lastCommandResponse += "\n"+"Tiempo de respuesta: "+ (tiempoFin-tiempoInicio) + "\n";
        }catch(IOException e){
            e.printStackTrace();
        }
        return lastCommandResponse;
    }

    private String executeCommand(String command) throws IOException{
        Process process = new ProcessBuilder(command).start();
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));   
        String line;
        StringBuilder output = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            //System.out.println(line);
            output.append(line).append("\n");
        }
        return output.toString();
    }

    private String listPrimeNumbers(int numero){
        StringBuilder output = new StringBuilder();
        output.append("Factors: ");
        if (numero < 0) {
            numero *= -1;
        }
        if (numero == 0){
            output.append("None.").append("\n");
        }
        if (numero == 1){
            output.append("1").append("\n");
        }
        if(numero > 1){
            for (int factor = 2; factor <= numero; factor++) {
                while (numero % factor == 0) {
                    output.append(Integer.toString(factor)).append(" ");
                    numero /= factor;
                }
            }
            output.append("\n");
        }
        return output.toString();
    }

}
