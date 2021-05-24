package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

public class MyCompressorOutputStream extends OutputStream {

    private OutputStream out;
//    private int compLen;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(byte[] b) throws IOException {

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 12; i++) {
            list.add((int)b[i]);
            out.write((int)b[i]);
        }

        int listPos = 12;
        while (listPos < b.length) {
            String take8 = "";
            if (b.length - listPos > 8) {
                for (int i = listPos; i < listPos + 8; i++) {
                    take8 =take8 + b[i];
                }
            } else {
                for (int i = listPos; i < b.length; i++) {
                    take8 =take8 + b[i];
                }
            }
            int decimalValue = Integer.parseInt(take8, 2);
            list.add(decimalValue);
            out.write(decimalValue);
            listPos+=8;
        }
        //this.compLen=list.size();
    }

    @Override
    public void write(int i) throws IOException {
        out.write((byte) i);
    }

//    public int getCompLen() {
//        return compLen;
//    }

}
