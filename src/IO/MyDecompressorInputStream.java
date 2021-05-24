package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;


public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read(byte[] b) throws IOException {

//         String cc="00";
//         int decimalValue = Integer.parseInt(cc, 2);
//        String s= Integer.toBinaryString(decimalValue);
    //    System.out.println();
        for (int i = 0; i < 12; i++) {
            b[i]=(byte) in.read();
        }
        int location=12;
        while (in.available()!=0) {
            int r=in.read();
            String x = Integer.toBinaryString(r);///check
            if(b.length-location>=8){
                int count=8-x.length();
                if(count>0)
                {
                    String add="";
                    for (int i = 0; i < count; i++) {
                        add=add+"0";
                    }
                    x=add+x;
                }
            }
            else if (x.length()<8){
                int rest=b.length-(x.length()+location);
                String add="";
                for (int i = 0; i < rest; i++) {
                    add=add+"0";
                }
                x=add+x;

            }
            for (int i = 0; i < x.length(); i++) {
                b[location]=(byte) Character.getNumericValue(x.charAt(i));
                location++;
            }
        }
        return 0;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }
}
