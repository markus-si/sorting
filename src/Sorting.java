import java.io.BufferedReader;
import java.io.FileReader;

public class Sorting {

	int maxPersons = 10000;
	Person[] unSortedBest;
	Person[] unSortedAvg;
	Person[] unSortedWorst;

	public static void main(String[] args) {
		Sorting s = new Sorting();
		s.start();

	}

	private void start() {
		try {
			unSortedBest = readAndSetUp("best", maxPersons);
			unSortedAvg = readAndSetUp("avg", maxPersons);
			unSortedWorst = readAndSetUp("worst", maxPersons);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Person[] copy = new Person[maxPersons];
		Person[] sorted = new Person[maxPersons];
		long start, stop;
		
		for(int i = 0; i < 4; i++){
			
			System.out.println();
			System.out.println((i+1)+"-call of the sorting methods ");
			System.arraycopy(unSortedBest, 0, copy, 0, maxPersons);
			start = System.currentTimeMillis();
			insertionSort(copy);
			stop = System.currentTimeMillis();
			System.out.print("InsertionSort Best:"+(stop-start)+"ms\t ");

			System.arraycopy(unSortedWorst, 0, copy, 0, maxPersons);
			start = System.currentTimeMillis();
			insertionSort(copy);
			stop = System.currentTimeMillis();
			System.out.print("Worst:"+(stop-start)+"ms\t ");
			
			System.arraycopy(unSortedAvg, 0, copy, 0, maxPersons);
			//for (int j = 0; j<5; j++){
				//System.out.println(copy[j].toString());
			//}
			start = System.currentTimeMillis();
			insertionSort(copy);
			stop = System.currentTimeMillis();
			System.out.print("Avg:"+(stop-start)+"ms\t ");
			
			System.out.println();
			System.arraycopy(unSortedBest, 0, copy, 0, maxPersons);
			start = System.currentTimeMillis();
			sorted = mergeSort(unSortedBest);
			stop = System.currentTimeMillis();
			System.out.print("MergeSort     Best:"+(stop-start)+"ms   \t ");

			System.arraycopy(unSortedWorst, 0, copy, 0, maxPersons);
			start = System.currentTimeMillis();
			sorted = mergeSort(unSortedWorst);
			stop = System.currentTimeMillis();
			System.out.print("Worst:"+(stop-start)+"ms\t ");

			System.arraycopy(unSortedAvg, 0, copy, 0, maxPersons);
			start = System.currentTimeMillis();
			sorted = mergeSort(copy);
			stop = System.currentTimeMillis();
			System.out.print("Avg:"+(stop-start)+"ms\t ");
			System.out.println();
		}


	}
	public Person[] readAndSetUp(String fileName, int maxEntries) throws Exception{

		String csv = "emp_"+fileName+".csv";
		BufferedReader br = new BufferedReader(new FileReader(csv));

		String str = br.readLine();
		String[] splittedStr;

		Person[] personen = new Person[maxEntries];

		for(int i = 0; (i < maxEntries) && (str != null); i++){
			splittedStr = str.split("\\s+");
			personen[i]=(new Person(splittedStr[0], splittedStr[1], (int)(Math.random()*100)));
			str = br.readLine();
		}
		/*System.out.println(fileName);
		for(int i = 0; i < 10; i++){
			System.out.println(personen.get(i).toString());
		}
		 */
		br.close();
		return personen;
	}



	private Person[] bubbleSort(Person[] array){
		boolean sortiert = false;
		int obergrenze = array.length;
		while(!sortiert)
		{
			sortiert = true;
			obergrenze--;
			for (int i = 0; i < obergrenze; i++)
			{
				if(array[i].compareTo(array[i+1]) > 0)
				{
					Person temp = array[i+1];
					array[i+1] = array[i];
					array[i] = temp;
					sortiert = false;
				}
			}
		}
		return array;
	}
	
	private void insertionSort(Person[] array){
		for (int i = 1; i < array.length; i++){
			int index = 0;
			while(array[index].compareTo(array[i]) < 0){
				index++;
			}
			Person temp = array[i];
			for(int j = i; j > index; j--){
				array[j] = array[j-1];
			}
			array[index] = temp;

		}
	}


	private Person[] mergeSort(Person[] a){
		if (a.length > 1){
			Person[] a1 = new Person[a.length/2];
			Person[] a2 = new Person[a.length-a1.length];

			System.arraycopy(a, 0, a1, 0, a1.length);
			System.arraycopy(a, a1.length,a2, 0, a2.length);

			return merge(mergeSort(a1), mergeSort(a2));
		}else{
			return a;
		}
	}

	private Person[] merge(Person[] a, Person[] b) {
		int posa=0, posb=0, pos=0;
		Person[] erg = new Person[a.length+b.length];
		while(posa < a.length && posb < b.length){
			if(a[posa].compareTo(b[posb]) <= 0)
			{
				erg[pos]=a[posa];
				posa++;
				pos++;

			}else
			{
				erg[pos]=b[posb];
				posb++;
				pos++;
			}
		}
		while(posa < a.length)
		{
			erg[pos]=a[posa]; posa++; pos++;
		}
		while(posb < b.length)
		{
			erg[pos]=b[posb]; posb++; pos++;
		}
		return erg;
	}

}
