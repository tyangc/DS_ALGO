package twopointers.slidingwindow;
/*
 There is a bookstore. On the next nn days, customer[i]customer[i]customers will arrive on the i-thi−th day and leave at the end of that day.

However, the bookstore owner's temper is sometimes good but sometimes bad. We use an array of grumpygrumpy to indicate his temper is good or bad every day. If grumpy[i] = 1grumpy[i]=1, it means that the owner's temper is very bad on the day of ii. If grumpy[i] = 0grumpy[i]=0, it means that the owner has a good temper on the first day.

If the owner of the bookstore has a bad temper one day, it will cause all customers who come on that day to give bad reviews to the bookstore. But if one day you have a good temper, then all customers will give the bookstore favorable comments on that day.

The boss wanted to increase the number of people who gave favorable comments to the bookstore as much as possible and came up with a way. He can keep a good temper for XX days in a row. But this method can only be used once.

So how many people in this bookstore can give favorable comments to the bookstore when they leave on this nn day?

1 \leq X \leq customers.length = grumpy.length \leq 20\,0001≤X≤customers.length=grumpy.length≤20000
0 \leq customers[i] \leq 1\,0000≤customers[i]≤1000
0 \leq grumpy[i] \leq 10≤grumpy[i]≤1
说明
样例
Input:
[1,0,1,2,1,1,7,5]
[0,1,0,1,0,1,0,1]
3
Output: 16
Explanation: The bookstore owner keeps themselves not grumpy for the last 3 days. 
The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
测试数据（每行一个）如何理解测试数据？


 */
public class GrumpyBookstoreOwner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        // write your code here
        if(customers.length!=grumpy.length || X>grumpy.length) return 0;
        
        int n=customers.length, sum=0;
        
        for (int i=0; i<n; i++) {
            if (i<X) {
                sum+=customers[i];
            } else {                    //if no else here the initialization is wrong. go through the emulation in mind, will find the error 
                sum+=(1-grumpy[i])*customers[i];
            }
        }
        
        int left=0, right=X, res=sum;
        while(right<n) {
            if (grumpy[right]==1) {  //Only conditionally change the total sum, not always changed by moving the right side of the sliding window!!!
                sum+=customers[right];
            }
            
            if (grumpy[left]==1) {
                sum-=customers[left];
            }
            
            res = Math.max(res, sum);
            left++;
            right++;
        }
        
        return res;
    }
}
