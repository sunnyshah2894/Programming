import java.util.Arrays;
import java.io.*;
class countByFactor
{
	    static int prime[]={2,3,5,7,11,13,17,19,23,29,31};
        public static void main(String arg[])throws IOException{
      
        		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));	
                String num=br.readLine();
                int ans[] =new int[50];
                int n=0;
                if(isNumeric(num)){ n=Integer.parseInt(num); 
                if((n>1))
                {	
                	
                		int i=0,j=0,count=0;
                        int fact = fact(n);
                        while(fact!=1)
                        {
                                if((fact%prime[i])==0)
                                {
                                       	fact=fact/prime[i];
                                        count++ ;
                                        j++;
                                }
                                else
                                {
                                        ans[i]=count ;
                                        count=0;
                                        i++;
                                }
                        }
                        ans[i]=1;
                        //System.out.println(Arrays.toString(ans));
                        for(int l=0;l<n;l++)
                        {
                        		if(ans[l]==0)break;
                                        System.out.print(ans[l]+" ");
                               
                        }
                }
                }
                else
                {
                        System.out.println("Invalid Input");
                }
        }
        
        public static int fact(int n){
        	int temp=1;
        	for(int  i=1;i<=n;i++)
            {
                    temp=temp*i;
            }
        	return temp;
        	
        }
        

    	public static boolean isNumeric(String input) {
    		try {
    			Integer.parseInt(input);
    			return true;
    		} catch (NumberFormatException e) {
    			// s is not numeric
    			return false;
    		}

    	}

}