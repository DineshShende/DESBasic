


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DESBasics {

	
	
    public static void main(String[] args) {
        
        
        int num[]=new int[17];
        int plain[]=new int[17];
        int bin_plain[]=new int[65];
        int bin[]=new int[65];
        int per[]=new int [65];
        int per_out[]=new int[65];
        int per_dec[]=new int[65];
        int per_sort[]=new int [65];
        int per_index[]=new int[65];
        int drop_table[]=new int[57];
        int key[]=new int [65];
        
        int key_comp[]=new int [49];
        int shift_count[]=new int [17];
        int round_key[][]=new int [17][49];
        
        
        
        
        
        int s[][][]={
    			{{14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
    				{0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
    	        
                {4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
                {15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}},   //s1
                
            	{{15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},
                {3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
                {0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},
                {13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}},  //s2
                
                {{10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},
         	    {13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
                {13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},
                {1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}},  //s3
        
                {{7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},
                {13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},
                {10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},
                {3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}},  //s4
        
                {{2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},
                {14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},
                {4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},
                {11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}},  //s5
        
        		{{12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},
                {10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},
    	        {9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},
                {4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}},  //s6
        
                {{4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},
                {13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
                {1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},
                {6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}},  //s7
    	        
        
                {{13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},
                {1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
                {7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},
                {2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}}};  //s8
        
        int ex_box[]={0,32,1,2,3,4,5,
 	           4,5,6,7,8,9,
 	           8,9,10,11,12,13,
 	           12,13,14,15,16,17,
 	           16,17,18,19,20,21,
 	           20,21,22,23,24,25,
 	           24,25,26,27,28,29,
 	           28,29,30,31,32,1};
        int str_box[]={0,16,7,20,21,
 	           29,12,28,17,
 	           1,15,23,26,
 	           5,18,31,10,
 	           2,8,24,14,
 	           32,27,3,9,
 	           19,13,30,6,
 	           22,11,4,25};
       
        num[1]=10;num[2]=10;num[3]=11;num[4]=11;num[5]=0;num[6]=9;num[7]=1;num[8]=8;
        num[9]=2;num[10]=7;num[11]=3;num[12]=6;num[13]=12;num[14]=12;num[15]=13;num[16]=13;
        
       String msg="abcdefghijklmnop";
       int msg_hex[]=new int[msg.length()+1]; 
       
       for(int i=0;i<msg.length();i++)
       {
    	   int temp=(int)msg.charAt(i)-97;
    	   
    	   {
    		   msg_hex[i+1]=temp;
    	   }   
       }	   
    	   
       for(int i=1;i<=16;i++)
    	   plain[i]=msg_hex[i];
       
        
        for(int i=1;i<=16;i++)
        {
        	if(i==1 || i==2|| i==9||i==16)
        		shift_count[i]=1;
        	else
        		shift_count[i]=2;
        	
        }	
        
        System.out.println("The entered numbers are:");
        for(int i=1;i<=16;i++)
        {
            System.out.print(num[i]+"\t");
        }
        System.out.println();
        
        hexbin(num,bin,16,64);
               
        for(int i=1;i<=64;i++)
            key[i]=bin[i];
        
        System.out.println("\nThe content of initial key are:");
        dis_array_num(bin,64);
        
        assign_drop_table(drop_table);
        assign_key_comp(key_comp);
        generate_key(key,drop_table,shift_count, key_comp, round_key);
        	
        
        
        
        
        
        hexbin(plain,bin_plain,16,64);
       
        System.out.println("\nThe content of message are:");
        dis_array_num(bin_plain,64);
        
        assign_per(per);
        
        for(int i=1;i<=64;i++)
            per_index[i]=i;
        
       
        
        for(int i=1;i<=64;i++)
            per_sort[i]=per[i];
        
        init_final(per_sort,per_index);
        int cip_msg[]=new int[65];
        int decp_msg[]=new int[65];
        char cip_msg_txt[]=new char[17];
        char decp_msg_txt[]=new char[17];
        
        
        encrypt(bin_plain,round_key,per,ex_box,s,str_box,per_index,cip_msg);
        System.out.println("\nThe encrypted message is:");
        dis_array_num(cip_msg,64);
        binhex(cip_msg,cip_msg_txt);
        
              
        decrypt(cip_msg,round_key,per,ex_box,s,str_box,per_index,decp_msg);
        System.out.println("\nThe original message is:");
        dis_array_num(decp_msg,64);
        
        binhex(decp_msg,decp_msg_txt);
        System.out.println("\nThe original message is:");
        System.out.println(msg);
        
        System.out.println("The encrypted message text:");
        dis_array_char(cip_msg_txt,16);
        
        System.out.println("\nThe encrypted message text:");
        dis_array_char(decp_msg_txt,16);
    }
    
    
    
    public static void binhex(int bin[],char hex[])
    {
    	int temp=0;;
    	for(int i=1;i<=16;i++)
    	{
    		temp=0;
    		int l=(4*(i-1))+1;
    		if(bin[l]==1)
    			temp=temp+8;
    		
    		if(bin[l+1]==1)
    			temp=temp+4;
    		
    		if(bin[l+2]==1)
    			temp=temp+2;
    		
    		if(bin[l+3]==1)
    			temp=temp+1;
    		
    			hex[i]=(char)(temp+97);
    	}	
    	
    }
    
    public static void encrypt(int bin_plain[],int round_key[][],int per[],int ex_box[],int s[][][],int str_box[],int per_index[],int cip_msg[])
    {
    	int bin_plain_per[]=new int[65];
        int bin_plain_left[]=new int [33];
        int bin_plain_right[]=new int [33];
        int bin_plain_right_exp[]=new int [49];
        int mix_plain[]=new int[49];
        int mix_plain_comp[]=new int[17];
        int mix_plain_comp_bin[]=new int[33];
       
        permute(64,64,bin_plain,bin_plain_per,per);
        
        for(int i=1;i<=16;i++)
        {	
        //System.out.println("\n\n\nThe key for round "+i+" is:");
        //dis_array_num(round_key[i],48);
        
        split(64,32,bin_plain_per,bin_plain_left,bin_plain_right);
        
        
        
        permute(32,48,bin_plain_right,bin_plain_right_exp,ex_box);
      
        
        ex_or(bin_plain_right_exp,round_key[i],mix_plain,48);
        
        sub_sub(mix_plain,s,mix_plain_comp);
        
        
        hexbin(mix_plain_comp,mix_plain_comp_bin,8,32);
        
        int cipher_round[]=new int[33];
        int cipher_right[]=new int[33];
        permute(32,32,mix_plain_comp_bin,cipher_round,str_box);
      
        ex_or(bin_plain_left,cipher_round,cipher_right,32);
       
        if(i!=16)
        	swap(bin_plain_right,cipher_right,bin_plain_per); 
        else
        	swap(cipher_right,bin_plain_right,bin_plain_per);
        
        //System.out.println("\nThe cipher  text is:");	
        //dis_array_num(bin_plain_per,64);
        }
      
        permute(64,64,bin_plain_per,cip_msg,per_index);
      
     
        
    	
    }
    
    public static void decrypt(int cip_msg[],int round_key[][],int per[],int ex_box[],int s[][][],int str_box[],int per_index[],int decp_msg[])
    {
    	int bin_plain_per[]=new int[65];
        int bin_plain_left[]=new int [33];
        int bin_plain_right[]=new int [33];
        int bin_plain_right_exp[]=new int [49];
        int mix_plain[]=new int[49];
        int mix_plain_comp[]=new int[17];
        int mix_plain_comp_bin[]=new int[33];
       
        permute(64,64,cip_msg,bin_plain_per,per);
        
        for(int i=1;i<=16;i++)
        {	
        //System.out.println("\n\n\nThe key for round "+i+" is:");
        //dis_array_num(round_key[17-i],48);
        
        split(64,32,bin_plain_per,bin_plain_left,bin_plain_right);
        
        
        
        permute(32,48,bin_plain_right,bin_plain_right_exp,ex_box);
      
        
        ex_or(bin_plain_right_exp,round_key[17-i],mix_plain,48);
        
        sub_sub(mix_plain,s,mix_plain_comp);
        
        
        hexbin(mix_plain_comp,mix_plain_comp_bin,8,32);
        
        int cipher_round[]=new int[33];
        int cipher_right[]=new int[33];
        permute(32,32,mix_plain_comp_bin,cipher_round,str_box);
      
        ex_or(bin_plain_left,cipher_round,cipher_right,32);
       
        if(i!=16)
        	swap(bin_plain_right,cipher_right,bin_plain_per); 
        else
        	swap(cipher_right,bin_plain_right,bin_plain_per);
        
        //System.out.println("\nThe plain  text is:");	
        //dis_array_num(bin_plain_per,64);
        }
      
        permute(64,64,bin_plain_per,decp_msg,per_index);
      
     
        
    	
    }
    
    public static void swap(int bin_plain_right[],int cipher_right[],int bin_plain_per[])
    {
    	for(int i=1;i<=32;i++)
    		bin_plain_per[i]=bin_plain_right[i];
    	
    	for(int i=1;i<=32;i++)
    		bin_plain_per[i+32]=cipher_right[i];
    	
    }
    public  static void sub_sub(int mix_plain[],int s[][][],int mix_plain_comp[])
    {
    	for(int k=0;k<=7;k++)
    	{
    		int l=(6*k)+1;
    		int h=l+5;
    		int row=(2*mix_plain[l])+mix_plain[h];
    		int col=0;
    		
    		for(int m=l+1;m<h;m++)
    			col=col+((int)(Math.pow(2, (h-m-1)))*mix_plain[m]);
    		
    		//System.out.println("\n"+row+"\t"+col);
    		
    		mix_plain_comp[k+1]=s[k][row][col];
    		
    	}	
    	
    	
    }
    
    public static void  ex_or(int bin_plain_right_exp[],int round_key[],int mix_plain[],int n)
    {
    	for(int i=1;i<=n;i++)
    	{
    		if(bin_plain_right_exp[i]==round_key[i])
    			mix_plain[i]=0;
    		else
    			mix_plain[i]=1;
    		
    	}	
    	
    }
    
    public static void hexbin(int hex[],int bin[],int no,int bin_no)
    {
        for(int j=1;j<=no;j++)
        {
            int number=hex[j];
            for(int k=3;k>=0;k--)
            {
                if(number>=Math.pow(2, k))
                {
                    bin[((j-1)*4)+(4-k)]=1;
                    number=(int) (number-Math.pow(2, k));
                
                }
                else
                	bin[((j-1)*4)+(4-k)]=0;
            }    
                
        }
        
    }
    
    public static void init_final(int per_sort[],int per_index[])
    {
        int temp;
        int temp1;
        for (int i=1;i<=64;i++)
        {
            for(int j=1;j<64;j++)
            {    
        
                if (per_sort[j]>per_sort[j+1])
                { 
                    temp=per_sort[j];
                    per_sort[j]=per_sort[j+1];
                    per_sort[j+1]=temp;
                    temp1=per_index[j];
                    per_index[j]=per_index[j+1];
                    per_index[j+1]=temp1;
                }    
            }    
       }
    
    }
    
    public static void permute(int in_size,int out_size,int in_block[],int out_block[],int ref_block[])
    {
        for(int i=1;i<=out_size;i++)
        {
            out_block[i]=in_block[ref_block[i]];
        }     
    
    } 
    
    
    
    public static void generate_key(int key[],int drop_table[],int shift_count[],int key_comp[],int round_key[][])
    {
    	int key_drop[]=new int [57];
        int key_left[]=new int [29];
        int key_right[]=new int [29];
        int key_1[]=new int [57];
        int key_round1[]=new int[49];
        
    	
    	permute(64,56,key,key_drop,drop_table);
        
        split(56,28,key_drop,key_left,key_right);
                       
        for(int round=1;round<=16;round++)
        {	
        	shift_left(key_left,shift_count[round]);
        
        	shift_left(key_right,shift_count[round]);
        
        	combine(28,56,key_left,key_right,key_1);
        	permute(56,48,key_1,key_round1,key_comp);
        
        	for(int index=1;index<=48;index++)
        	{
        		round_key[round][index]=key_round1[index];
        	}	
        	//System.out.println("The key for round is:"+round);
        	//dis_array_num(round_key[round],48);
        
        }
           	
    }
    
    
    public static void assign_key_comp(int key_comp[])
    {
    	int key_comp1[]={0,14,17,11,24,1,5,3,28,15,6,21,10,23,19,12,4,
    		               26,8,16,7,27,20,13,2,41,52,31,37,47,55,30,40,
    		               51,45,33,48,44,49,39,56,34,53,46,42,50,36,29,32
    					};
    	
    	key_comp=Arrays.copyOf(key_comp1, 49);
     
    
    }        
            
    public static void assign_drop_table(int drop_table[])
    {
       
    	int drop_table1[]={0,57,49,41,33,25,17,9,1,58,50,42,34,26,18,10,2,
    						59,51,43,35,27,19,11,3,60,52,44,36,63,55,47,39,
    						31,23,15,7,62,54,46,38,30,22,14,6,61,53,45,37,
    						29,21,13,5,28,20,12,4
    	};
     	
        drop_table=Arrays.copyOf(drop_table1, 57);
    	   
    }
    
    public static void dis_array(int array[],int num)
    {
       System.out.println("The content of  block:");
       
       for(int i=1;i<=num;i++)
       {
           System.out.print(array[i]+"\t");
           
           if(i %8 ==0)
               System.out.println();
       }
    
    }  
    
    public static void dis_array_num(int array[],int num)
    { 
   
    	for(int i=1;i<num+1;i++)
    	{
    		System.out.print(array[i]);
    		if(i % 4==0)
    				System.out.print("\t");
    	}
    }
    
    public static void dis_array_char(char array[],int num)
    { 
   
    	for(int i=1;i<num+1;i++)
    	{
    		System.out.print(array[i]);
           
    	}
    }
    
    public static void split(int in_size,int out_size,int cipher_key[],int left_key[],int right_key[])
    {
        for(int i=1;i<=out_size;i++)
            left_key[i]=cipher_key[i];
    
        for(int i=1;i<=out_size;i++)
        {
            right_key[i]=cipher_key[out_size+i];
        }    
    }        
    
    public static void shift_left(int key_left[],int shift)
    {
        int temp=0;
        
        for(int i=1;i<=shift;i++)
        {
            temp=key_left[1];
            for(int j=2;j<=28;j++)
                key_left[j-1]=key_left[j];
            
            key_left[28]=temp;
        
        }    
    
    }
    
    public static void combine(int in_size,int out_size,int key_left[],int key_right [],int key_round[])
    {
        for(int i=1;i<=in_size;i++)
            key_round[i]=key_left[i];
    
        for(int i=1;i<=in_size;i++)
        {
            key_round[in_size+i]=key_right[i];
            
        }    
    
    }
    
    public static void assign_per(int per[])
    {
    	
        per[1]=58;
        per[2]=50;
        per[3]=42;
        per[4]=34;
        per[5]=26;
        per[6]=18;
        per[7]=10;
        per[8]=2;
        
        per[9]=60;
        per[10]=52;
        per[11]=44;
        per[12]=36;
        per[13]=28;
        per[14]=20;
        per[15]=12;
        per[16]=4;
        
        per[17]=62;
        per[18]=54;
        per[19]=46;
        per[20]=38;
        per[21]=30;
        per[22]=22;
        per[23]=14;
        per[24]=6;
        
        per[25]=64;
        per[26]=56;
        per[27]=48;
        per[28]=40;
        per[29]=32;
        per[30]=24;
        per[31]=16;
        per[32]=8;
        
        per[33]=57;
        per[34]=49;
        per[35]=41;
        per[36]=33;
        per[37]=25;
        per[38]=17;
        per[39]=9;
        per[40]=1;
        
        per[41]=59;
        per[42]=51;
        per[43]=43;
        per[44]=35;
        per[45]=27;
        per[46]=19;
        per[47]=11;
        per[48]=3;
        
        per[49]=61;
        per[50]=53;
        per[51]=45;
        per[52]=37;
        per[53]=29;
        per[54]=21;
        per[55]=13;
        per[56]=5;
        
        per[57]=63;
        per[58]=55;
        per[59]=47;
        per[60]=39;
        per[61]=31;
        per[62]=23;
        per[63]=15;
        per[64]=7;
        
    	
    }
    
    
}
