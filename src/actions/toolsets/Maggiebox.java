package actions.toolsets;


public abstract class Maggiebox {
	
	public static boolean equal(boolean[] a, boolean[] b) {
		if(a.length != b.length)
			return false;
		for(int i = 0; i<a.length ;i++) {
			if(a[i] != b[i])
				return false;
		}
		return true;
	}
	public static boolean[] subset(int start, int end, boolean [] array) {
		boolean[] result = new boolean[end-start+1];
		for(int i = 0; i< end-start+1 ;i++) {
			result[i] = array[start+i];
		}
		return result;
	}
	
	public static int bool_to_decimal(boolean[] pc) {
		int pc_num = 0;
		for(int i = 0; i<pc.length; i++) {
			if(pc[i]==true) {
				pc_num += Math.pow(2, i);
			}
		}
		return pc_num;
	}
	public static void mask(boolean[] source, boolean[] destination, int start) {
		if(source.length>destination.length || source.length+start > destination.length) {
			System.out.println("Error in masking");
			return;
		}
		for(int i = 0 ; i<source.length; i++) {
			destination[i+start] = source[i];
		}
	}
	
	public static boolean[] decimal_to_bool(long pc_num) {
		boolean[] zero = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,
				false,false,false,false,false,false,false,false,false,false,false,false,false,false};
		boolean[]result = new boolean[32];
		if(pc_num<0) {
			System.out.println("error in conversion");
			return zero;
		}
		if(pc_num == 0) {
			return zero;
		}
		for(int i = 0; pc_num>=1;i++) {
			int mod = (int) (pc_num%2);
			pc_num = pc_num/2;
			if(mod==1)
				result[31-i] = true;
		}
		return result;
	}

}
