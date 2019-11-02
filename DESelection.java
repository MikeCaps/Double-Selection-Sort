import java.util.*;
public class DESelection{
	public static void main (String[] args){
	
		//test method
		Random gen = new Random();
		int[] sorter = new int[20];
		
		for(int i = 0; i < 20; i++){
			sorter[i] = gen.nextInt(99) + 1;
			System.out.print(sorter[i] + ", ");
		}
		
		System.out.println();
		
		deSort(sorter, 20);
		
		for(int i = 0; i < 20; i++){
			System.out.print(sorter[i] + ", ");
		}
	}//end of main
	
	
	public static void deSort(int [] sortThis, int n){
		int p = 0; //for left side of array
		int q = 0;//for right side of array
		//pairwise sorting loop
		for(int i = 0; i < n/2; i++){
			if(sortThis[i] > sortThis[n-i-1])
				swap(sortThis, i, n-i-1);
		} 
		int m = 0;
		int min = Integer.MAX_VALUE; //must be set to max
		int max = -1;//must be < 0, assuming our array is natural numbers
		while(m < n/2){
			//find min index
			min = sortThis[m];
			for(int i = m; i < n/2; i++){
				if(sortThis[i] <= min){
					min = sortThis[i];
					p = i;
				}
			}
			//find max index
			max = sortThis[n-m-1];
			for(int j = n-m-1; j >= n/2; j--){
				if(sortThis[j] >= max){
					max = sortThis[j];
					q = j;
				}
			}
			swap(sortThis, p, m);//swap A[p] and A[m]
			swap(sortThis, q, n-m-1); //swap A[q] w/ A[n-m-1]
			//check to see if pairwise sort remains in tact
			if(sortThis[p] > sortThis[n-p-1])
				swap(sortThis, p, n-p-1);
			if(sortThis[q] < sortThis[n-q-1])
				swap(sortThis, q, n-q-1);
			//reset min and max
			min = Integer.MAX_VALUE;
			max = -1;
			m++;
		}
		//final step, check middle two elements.
		if(sortThis[n/2-1] > sortThis[n/2])
			swap(sortThis, n/2-1, n/2);
		
	}//end of deSort
	
	//simple swapping method
	public static void swap(int[] A, int x, int y){
		int temp;
		temp = A[x];
		A[x] = A[y];
		A[y] = temp;
		
	}
}//end of class