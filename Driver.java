import java.io.File;
public class Driver
{
    public static void main(String [] args)
    {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {4,6};
        int [] d1 = {0,1};
        Polynomial p1 = new Polynomial(c1, d1);
        double [] c2 = {3,2};
        int [] d2 = {0, 1};
        Polynomial p2 = new Polynomial(c2, d2);
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
        Polynomial multi = p1.multiply(p2);
        for(int i = 0; i < multi.array.length; i++)
        {
            System.out.println(multi.array[i] + ", Exponent: " + multi.exponents[i]);
        }
        System.out.println("multi(2) = " + multi.evaluate(2));
		System.out.println("Using file");
		File input1 = new File("C:\\Users\\thush\\Desktop\\CSCB07\\b07lab1\\poly.txt");
		Polynomial fileuser = new Polynomial(input1);
		for(int i = 0; i < fileuser.array.length; i++)
        {
            System.out.println(fileuser.array[i] + ", Exponent: " + fileuser.exponents[i]);
        }
		p2.saveToFile("C:\\Users\\thush\\Desktop\\CSCB07\\b07lab1\\test.txt");
		
    }
}