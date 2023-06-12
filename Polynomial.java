import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class Polynomial{
	
	double array[] = new double[1];
	int exponents[] = new int[1];
	
	public Polynomial(){
		array[0] = 0;	
		exponents[0] = 0;
	}
	
	public Polynomial(double array1[], int exp[])
	{
		this.array = new double[array1.length];
		this.exponents = new int[exp.length];
		
		for(int y = 0; y <= (array1.length - 1); y++)
		{
			this.array[y] = array1[y];
		}		
		
		for(int z = 0; z <= (exp.length - 1); z++)
		{
			this.exponents[z] = exp[z];
		}
	}
	
	public Polynomial(File poly)
	{
		BufferedReader input = null;
		try
		{
			input = new BufferedReader(new FileReader(poly));
			String num = input.readLine();
			String num1[] = num.split("(?=[+-])");
			double array2[] = new double[num1.length];
			int exp[] = new int[num1.length];
			int z = 0;
			for(String num2 : num1)
			{
				String parts[] = num2.split("x");
				array2[z] = Double.parseDouble(parts[0]);
				if(parts.length > 1)
				{
					exp[z] = Integer.parseInt(parts[1]);
				}
				z++;
			}
			this.array = new double[array2.length];
			this.exponents = new int[exp.length];
			
			for(int y = 0; y <= (array2.length - 1); y++)
			{
				this.array[y] = array2[y];
			}
			for(int t = 0; t <= (exp.length - 1); t++)
			{
				this.exponents[t] = exp[t];
			}
			input.close();
			
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}

			
	}
	
	public Polynomial add(Polynomial arr)
	{
		Polynomial check = new Polynomial();
		if(this.array.length >= arr.array.length)
		{
			check = new Polynomial(this.array, this.exponents);
		}
		else if(this.array.length < arr.array.length)
		{
			check = new Polynomial(arr.array, arr.exponents);
		}
		
		int z = 0; 
		int y = 0;
		int q = 0;
		while(z < this.array.length && y < arr.array.length)
		{
			if(this.exponents[z] == arr.exponents[y])
			{
				check.array[q] = this.array[z] + arr.array[y];
				check.exponents[q] = this.exponents[z];
				q++;
				z++;
				y++;
			}
			
			else if(this.exponents[z] < arr.exponents[y])
			{
				check.array[q] = this.array[z];
				check.exponents[q] = this.exponents[z];
				q++;
				z++;
			}
			else
			{
				check.array[q] = arr.array[y];
				check.exponents[q] = arr.exponents[y];
				q++;
				y++;
			}
				
		}	
		
		return check;

	}
	
	public double evaluate(double x)
	{
		double r = 0;
		for(int c = 0; c < this.exponents.length; c++)
		{
			r = r + this.array[c] * Math.pow(x, this.exponents[c]);
		}
		
		return r;
	}
	
	public boolean hasRoot(double q)
	{
		double w = this.evaluate(q);
		if(w == 0){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean isin(int value)
	{
		boolean checker = false;
		for(int q = 0; q < this.exponents.length; q++)
		{
			if(this.exponents[q] == value && value != 0)
			{
				checker = true;
				break;
			}
		}
		return checker;
	}
	
	public Polynomial clean()
	{
		int x = 0;
		int theLength = 0;
		while(this.array[x] != 0)
		{
			theLength++;
			x++;
		}
		double temp[] = new double[theLength];
		int temp1[] = new int[theLength];
		
		Polynomial cleaned = new Polynomial(temp, temp1);
		
		for(int d = 0; d < theLength; d++)
		{
			cleaned.array[d] = this.array[d];
			cleaned.exponents[d] = this.exponents[d];
		}
		
		return cleaned;
	}
			
		
		

	public Polynomial multiply(Polynomial pro)
	{
		double temp[] = new double[this.array.length * pro.array.length];
		int temp1[] = new int[this.exponents.length * pro.exponents.length];
		Polynomial check = new Polynomial(temp, temp1);
		
		int x = 0;
		
		while(x < check.array.length)
		{
			for(int y = 0; y < this.array.length; y++)
			{
				for(int z = 0; z < pro.array.length; z++)
				{
					check.array[x] = this.array[y] * pro.array[z];
					check.exponents[x] = this.exponents[y] + pro.exponents[z];
					x++;
				}
			}
		}
		Polynomial check1 = new Polynomial(temp, temp1);
		int r = 0;
		int y = 0;
		int c = 0;
		
		while(r < check1.exponents.length)
		{
			
			while(c < check.exponents.length)
			{
				while(y <  check.exponents.length)	
				{
					boolean checker1 = check1.isin(check.exponents[c]);

					if(check.exponents[c] == check.exponents[y] && checker1 == false && c != y)
					{
						check1.array[r] = check.array[c] + check.array[y];
						check1.exponents[r] = check.exponents[c];
						r++;
						break;
					}
					y++;
				}
				boolean checker1 = check1.isin(check.exponents[c]);
				if(checker1 == false)
				{
					check1.array[r] = check.array[c];
					check1.exponents[r] = check.exponents[c];
					r++;
				}
				y = 0;
				c++;
				if(c == check.exponents.length)
				{
					r++;
				}
			}
			
		}
		check1 = check1.clean();
					
		
		return check1;
		
		
	}
	
	public void saveToFile(String line)
	{
		
		FileWriter output;
		try{
			output = new FileWriter(line);

			for(int i = 0; i < array.length; i++)
			{
				if(i > 0 && array[i] > 0)
					output.write("+");
				output.write((int)array[i] + "");
				if(i > 0) {
					output.write("x");
					output.write(exponents[i] + "");
				}
				}
			output.write("\n");
			output.close();
			
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
			
	
	
}