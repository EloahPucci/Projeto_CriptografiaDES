import java.util.Locale;
import java.util.Scanner;

public class DES {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.ENGLISH);
		Locale.setDefault(new Locale("en", "US"));
		
		int vetorDeTeste[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
				11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
				21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
				31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
				41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
				51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
				61, 62, 63, 64};
		
		char mensagem[] = sc.next().toCharArray();
		String mensagemBinario = "";
		
		for(int i = 0; i < mensagem.length; i++) {
			mensagemBinario += Integer.toBinaryString(mensagem[i]);
		}
		
		char teste[] = mensagemBinario.toCharArray();
		
		
		
		
		Tabela tbIP =  new Tabela();
		
		int resultado[] = tbIP.trabalhandoTabelaIP(vetorDeTeste);
		
		for(int i = 0; i < 64; i++) {
			System.out.print(resultado[i] + " ");
		}		
		
	}

}