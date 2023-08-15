package amazon;

//
public class AutoscalePolicy_UtilizationCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}
	
	public int finalInstances(int instances, int[] averageUtil) {
		
		if(instances==0 || averageUtil == null || averageUtil.length==0) return 0;
		
		for (int i=0; i<averageUtil.length-1; i++) {
			if (i<averageUtil.length && averageUtil[i]<25 && instances>1) {
				instances = (int)Math.ceil(instances/2.0);
				i+=10;
			}
			
			if (i<averageUtil.length && averageUtil[i]>60 && instances<200000000) {
				instances *= 2;
				i+=10;
			}
		}
		
		return instances;
	}


}
