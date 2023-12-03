public class CallbackReceiverI implements Demo.CallbackReceiver{
    
    public void callback(String msg, com.zeroc.Ice.Current current){
        System.out.println(msg);
    }
    public void update(){
        System.out.println("todo good");
    }
}