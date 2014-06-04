import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: roee
 * Date: 5/2/14
 * Time: 9:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClientConnection extends  Thread{
    private Socket socket;
    private InputStream is ;
    private OutputStream os  ;

    public ClientConnection(Socket socket)
    {
        this.socket = socket;

        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void turnOffSocket() {
        socket = null;
    }

    public ClientConnection(Socket socket, String id)
    {
        this.socket = socket;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
            DataOutputStream dis = new DataOutputStream(os);
        }
        catch(IOException e)
        {
            socket = null;
        }
    }

    public void sendMsg(byte[] bytes)
    {
        try {
            DataOutputStream oos = new DataOutputStream(os);
            oos.write(bytes);
        }
        catch(IOException e)
        {

        }
    }

    public void run () {

        DataInputStream dis = new DataInputStream(is);
        DataOutputStream dos = new DataOutputStream(os);

        byte[] lat = new byte[8];
        byte[] lng = new byte[8];
        while (true)
        {
            try {
            	dis.read(lat);
            	dis.read(lng);
            	
            	System.out.println("Given latitude : " + ByteManipulation.bytesToDouble(lat));
            	System.out.println("Given longitude : " + ByteManipulation.bytesToDouble(lng));
            }
            catch(IOException e)
            {
                break;
            }
        }

    }


}
