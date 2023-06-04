public class Polynomial{
	
	double array[] = new double[1];
	
	public Polynomial(){
		array[0] = 0;		
	}
	
	public Polynomial(double array1[])
	{
		this.array = new double[array1.length];
		
		for(int y = 0; y <= (array1.length - 1); y++)
		{
			this.array[y] = array1[y];
		}		
		
	}
	
	public Polynomial add(Polynomial arr)
	{
		Polynomial check = new Polynomial();
		if(this.array.length >= arr.array.length){
			check = new Polynomial(this.array);
		}
		else if(this.array.length < arr.array.length){
			check = new Polynomial(arr.array);
		}
		
		for(int z = 0; z < (check.array.length - 1); z++)
		{
			check.array[z] = this.array[z] + arr.array[z];
		}
		
		return check;

	}
	
	public double evaluate(double x)
	{
		double r = this.array[0];
		for(int c = 1; c <= (this.array.length - 1); c++)
		{
			r = r + this.array[c] * Math.pow(x, c);
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
	
	
}