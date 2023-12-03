import java.util.Scanner;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) {
        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "client.cfg")) {

            Demo.CallbackSenderPrx chatManagerPrx = Demo.CallbackSenderPrx
                    .checkedCast(communicator.propertyToProxy("CallbackSender.Proxy"));
            
            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapter("Callback.Client");
            adapter.add(new CallbackReceiverI(), com.zeroc.Ice.Util.stringToIdentity("callbackReceiver"));
            adapter.activate();
            
            Demo.CallbackReceiverPrx clientPrx =
                Demo.CallbackReceiverPrx.uncheckedCast(adapter.createProxy(
                    com.zeroc.Ice.Util.stringToIdentity("callbackReceiver")));

            try {
                Scanner scanner = new Scanner(System.in);
                String msg;
                String user = System.getProperty("user.name");
                String hostname = "";
                InetAddress addr = InetAddress.getLocalHost();
                hostname = addr.getHostName();

                chatManagerPrx.login(hostname, clientPrx);

                String[] init;
                long tiempoLatenciaInicio;
                long tiempolatenciaFinal;
                

                if (args.length >= 1){
                    
                   long tiempoLatenciaInicioCallback = System.currentTimeMillis();
                    tiempoLatenciaInicioCallback = System.currentTimeMillis()-tiempoLatenciaInicioCallback;
    
                }
                
                while (true) {
                    
                        msg = scanner.nextLine();
                        tiempoLatenciaInicio = System.currentTimeMillis();
                        System.out.println(chatManagerPrx.printString(user + "@" + hostname + ": " + msg));
                        tiempolatenciaFinal = System.currentTimeMillis();
                        System.out.println("Latencia: "+ (tiempolatenciaFinal-tiempoLatenciaInicio) + "\n");
                        if (msg.equalsIgnoreCase("exit")) {
                            break;
                        }
            
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
