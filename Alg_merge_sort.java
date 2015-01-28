/* Merge-Sort-sol1
 * Liability: take too much space
 * advantage: no need to focus on index because every time call on new array start from zero
 * Merge-Sort-sol2
 * take care of the index, every time call based on original array
 */
 
 public class Alg_merge_Sort{
		public static void main(String[] args){
			Alg_merge_Sort test = new Alg_merge_Sort();
			int[] a = {9,7,8,4,2,3,5};
			
			test.mergeSort_2(a,0,6);
			for(int i = 0;i<a.length;i++){
				System.out.print(a[i]);
			}
		
		}
	 
	 
	 
	 public void mergeSort(int[] arr,int start, int end){
		 // every time call mergeSort based on a new array not original a[i]
		int mid = (start+end)/2;
		//System.out.println(start+" "+mid+" "+end);
		int[] half_1 = new int[mid-start+1];// start to mid
		int[] half_2 = new int[end-mid];//mid + 1 to end
		for(int i = 0;i<mid-start+1;i++){
			half_1[i] = arr[i];
		}
		for(int j = 0;j<end - mid;j++){
			half_2[j] = arr[j+mid+1];
		}
		if(start==end){
			//merge(arr,half_1,half_2);
		}
		else{
			mergeSort(half_1,0,half_1.length-1);
			mergeSort(half_2,0,half_2.length-1);
			merge(arr,half_1,half_2);
		}
		
	 }
	 
	 public void merge(int[] arr,int[] a1,int[] a2){
		 int i = 0;
		 int j = 0;
		 int k = 0;
		 for( i = 0;i<arr.length;i++){
				System.out.print(arr[i]);
			}
		 
		 System.out.println("AFTER");
		 i=0;
		 while(i<a1.length && j<a2.length){
			 if( a1[i] <= a2[j]){
				 arr[k] = a1[i];
				 i++;
			 }
			 else {
				 arr[k] = a2[j];
				 j++;
			 }
			 k++;
		 }
		 
		 if( i == a1.length){
			 for(;j<a2.length;j++){
				 arr[k] = a2[j];
				 k++;
			 }
		 }
		 
		 if(j == a2.length){
			 for(;i<a1.length;i++){
				 arr[k] = a1[i];
				 k++;
			 }
		 }
		 for( i = 0;i<arr.length;i++){
				System.out.print(arr[i]);
			}
		 
		 System.out.println();
	 }
	
	
	public void mergeSort_2(int [] arr, int start, int end){
		//save space to last step: merge, every time call this based on original a[i]
		if (start <end){
		
			int mid = (start + end)/2;
			mergeSort_2(arr, start, mid);
			mergeSort_2(arr,mid+1,end);
			merge_2(arr,start, mid, end);
		}
	}
	
	public void merge_2(int [] arr, int start, int mid, int end){
		//System.out.println("\n"+start+" "+mid+" "+end);

		int left[] = new int[mid-start+1];
		int right[] = new int [end-mid];
		for(int i = 0;i< mid-start+1;i++){
			left[i] = arr[start+i];
		}
		for(int j = 0;j<end-mid;j++){
			right[j] = arr[j+mid+1];
		}
		
		int i = 0;
		int j = 0;
		int k = start;
		
		while(i<mid-start+1 && j<end-mid){
			if(left[i]<right[j]){
				arr[k] = left[i];
				i++;
			}
			else{
				arr[k] = right[j];
				j++;
			}
			k++;
		}
		
		if( i == left.length){
			 for(;j<right.length;j++){
				 arr[k] = right[j];
				 k++;
			 }
		 }
		 
		 if(j == right.length){
			 for(;i<left.length;i++){
				 arr[k] = left[i];
				 k++;
			 }
		 }
//		for( i = 0;i<arr.length;i++){
//			System.out.print(arr[i]);
//		}
	}
}
		