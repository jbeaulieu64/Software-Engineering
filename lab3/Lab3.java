import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Lab3 {
	public static void main(String args[]) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.print("Please give me a number: ");
    BufferedImage inputPic = ImageIO.read(new File("sample.png"));
		int N = in.nextInt();

		System.out.println("\nAn integer is " + Integer.SIZE + " bits long.\n");
    //Hide Number
    int x = 0;
    int y = 0;
		for(int i=Integer.SIZE-1; i >= 0;) {
      int rgb = inputPic.getRGB(x,y);
			int A = (rgb>>24) & 0xff;
			int R = (rgb>>16) & 0xff;
      int G = (rgb>>8) & 0xff;
      int B = (rgb>>0) & 0xff;
      int[] bytes = {A,R,G,B};
      for(int j = 0; j < bytes.length;j++, i--){
				int byt = (N >> i) & 0x01;
        if(byt==1){
          bytes[j] |= (1<<0);
        }
        else{
          bytes[j] &= ~(1<<0);
        }
      }
      int new_rgb = /*(rgb & 0xffff0000)*/ (bytes[0]<<24) | (bytes[1]<<16) | (bytes[2]<<8) | (bytes[3]<<0);
      inputPic.setRGB(x++,y,new_rgb);
      if(x>=inputPic.getWidth()){
        x = 0;
        y++;
      }
		}


    ImageIO.write(inputPic,"png",new File("out2.png"));
		System.out.println();


    BufferedImage steganoPic = ImageIO.read(new File("out2.png"));
    //show Number
     x = 0;
     y = 0;
     N = 0;
		for(int i=Integer.SIZE-1; i >= 0;) {
	    int rgb = steganoPic.getRGB(x++,y);
			int A = (rgb>>24) & 0xff;
			int R = (rgb>>16) & 0xff;
      int G = (rgb>>8) & 0xff;
      int B = (rgb>>0) & 0xff;
      int[] bytes = {A,R,G,B};
      for(int j = 0; j < bytes.length;j++, i--){
        int bit=bytes[j] & 0x01;
        N = (N<<1) | bit;
      }
      if(x>=steganoPic.getWidth()){
        x = 0;
        y++;
      }
	 }
    System.out.println("The Hidden Number is: " + N);
  }
}
