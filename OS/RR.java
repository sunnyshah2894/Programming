import java.io.IOException;
import java.util.Scanner;

public class RR {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the no. of Processes: ");
		int n = sc.nextInt();
		int p[] = new int[n];
		int a[] = new int[n];
		int o[] = new int[n];
		int j = 0;
		int sum = 0;
		System.out.println("Enter The Quantum time: ");
		int q = sc.nextInt();
		for (int i = 0; i < n; i++) {
			System.out.println("Enter the Arrival And Burst Time of Process "
					+ (i + 1) + ": ");
			o[i] = i + 1;
			a[i] = sc.nextInt();
			p[i] = sc.nextInt();
			sum += p[i];
			j = i;
			int tempa = a[i];
			int tempp = p[i];
			int tempo = o[i];
			while (j > 0 && a[j - 1] > tempa) {
				a[j] = a[j - 1];
				p[j] = p[j - 1];
				o[j] = o[j - 1];
				j--;
			}
			a[j] = tempa;
			p[j] = tempp;
			o[j] = tempo;
		}
		int time = 0;
		int endtime = 0;
		while (sum - q*n > 0) {
			for (int i = 0; i < n; i++) {
				if (p[i] != 0) {
					endtime = time + q;
					if (p[i] - q < 0)
						endtime = p[i] + time;
					System.out.println("Process " + o[i]+ " will execute from " + time + " to " + endtime);
					time = endtime;
					p[i] -= q;
					if (p[i] < 0)
						p[i] = 0;
				}
			}
		}
	}
}