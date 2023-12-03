public class TextReader {
    
    String ruta = lectura.next();
    ArrayList<String> lines = new ArrayList<String>();
    public void readTxt(){

        try (FileReader fr = new FileReader(ruta)) {
            BufferedReader br = new BufferedReader(fr);
            // Lectura del archivo

            
            String linea;
            while((linea=br.readLine())!=null)
                   System.out.println(linea);
                   lines.add(linea);    
             }
             catch(Exception e){
                e.printStackTrace();
             }

    }

    public ArrayList getLines(){
        return lines;
    }
   

}
