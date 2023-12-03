import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;

public class CallbackSenderI implements Demo.CallbackSender{
    HashMap<String, Demo.CallbackReceiverPrx> users = new HashMap<String, Demo.CallbackReceiverPrx>();
    String tiempoRespuesta;

    public void login(String hostName, Demo.CallbackReceiverPrx proxy, com.zeroc.Ice.Current current){
        users.put(hostName, proxy);
        System.out.println(hostName + " joined."+ "\n");
        proxy.callback("Logged." +"\n");
    }
    
    public void notify(){
      
        for (Demo.CallbackReceiverPrx sub : users.values()) {
                sub.update();
        }
        
    } 

    public String printString(String msg, com.zeroc.Ice.Current current) {
        long tiempoInicio;
        long tiempoFin;
        String lastCommandResponse = "";
        String msgToSend;
        try{
            tiempoInicio = System.currentTimeMillis();
            
            tiempoFin = System.currentTimeMillis();
            lastCommandResponse += "\n"+"Tiempo de respuesta: "+ (tiempoFin-tiempoInicio) + "";
        }catch(IOException e){
            e.printStackTrace();
        }
        return lastCommandResponse;
    }
}