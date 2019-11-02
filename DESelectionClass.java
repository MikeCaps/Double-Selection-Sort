import java.util.*;
public class DESelectionClass {

	public static void main(String[] args) {
		Random gen = new Random();
		final int SAMPLE_SIZE = 50;
		final int N = 1000;
		double start1, start2, end1, end2;
		double selSum = 0;
		double doubleSelSum = 0;
		double selAvg, doubleSelAvg;
		
		/*loop collects 50 mean sort times, for selection sort and double ended 
		  selection sort, of an array of 1000 ints */
		for(int x = 0; x < 50; x++) {
			for(int i = 0; i < SAMPLE_SIZE; i++) {
				int[] regSelection = new int[N];
				int[] doubleSelection = new int[N];
				for(int j = 0; j < N; j++) {
					regSelection[j] = gen.nextInt(100);
					doubleSelection[j] = regSelection[j];
				}
				
				start1 = System.nanoTime();
				selectionSort(regSelection);
				end1 = System.nanoTime();
			
				start2 = System.nanoTime();
				doubleSelSort(doubleSelection);
				end2 = System.nanoTime();
			
				selSum += (end1 - start1);
				doubleSelSum += (end2 - start2);
			}
		
			selAvg = selSum/SAMPLE_SIZE;
			doubleSelAvg = doubleSelSum/SAMPLE_SIZE;
			selSum = 0;
			doubleSelSum = 0;
	
			System.out.println(selAvg/1000000 + "\t" + doubleSelAvg/1000000);
		}
	}//end of main
	
	public static void doubleSelSort(int [] A){
		final int n = A.length;
		int p = 0; //for left side of array
		int q = 0;//for right side of array
		int m = 0;
		int min, max;
		//pairwise sorting loop
		for(int i = 0; i < n/2; i++){
			if(A[i] > A[n-i-1])
				swap(A, i, n-i-1);
		} 
		/*System.out.println("Pairwise Sort:");//test print pairwise sort
		for(int i = 0; i < N; i++){
			System.out.print(sortThis[i] + ", ");
		}	*/		
		while(m < n/2-1){
			//find min index
			min = A[m];
			p = m;//we will always swap p and m therefore we must reset p to m
			//just in case min ends up in the correct place coincidentally.
			for(int i = m+1; i < n/2; i++){
				if(A[i] <= min){
					min = A[i];
					p = i;
				}
			}
			//find max index
			max = A[n-m-1];
			q = n-m-1;//must reset q as well to avoid extraneous swaps.
			for(int j = n-m-2; j >= n/2; j--){
				if(A[j] >= max){
					max = A[j];
					q = j;
				}
			}
			swap(A, p, m);//swap A[p] and A[m]
			swap(A, q, n-m-1); //swap A[q] w/ A[n-m-1]
			//check to see if pairwise sort remains in tact
			if(A[p] > A[n-p-1])
				swap(A, p, n-p-1);
			if(A[q] < A[n-q-1])
				swap(A, q, n-q-1);
			//reset min and max
			m++;
		}
		//final step, check middle two elements.
		if(A[n/2-1] > A[n/2])
			swap(A, n/2-1, n/2);
	}//end of deSort
	
	public static void selectionSort(int A[]) {
		final int n = A.length;
		int min, minIndex = 0;
		for(int i = 0; i < n; i++) {
			min = A[i];
			minIndex = i;
			for(int j = i+1; j < n; j++) {
				if(A[j] < min) {
					min = A[j];
					minIndex = j;
				}
			}
			swap(A, i, minIndex);
		}
	}//end of selection sort
	
	//swapping method
	public static void swap(int[] A, int x, int y){
		int temp;
		temp = A[x];
		A[x] = A[y];
		A[y] = temp;
	}//end of swap
	
	//for bedug purposes 
	public static void printArray2(int A[]) {
		for(int i = 0; i < A.length; i++) {
			System.out.print(A[i] + " ");
			if(i % 10 == 0)
				System.out.println();
		}
	}//end of printArray

}//end of class
