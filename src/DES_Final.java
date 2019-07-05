public class DES_Final {

	public static int[] DES(int[] textoSimples, int[] chave56Bits) {
		if (textoSimples.length != 64 || chave56Bits.length != 64) {
			System.err.println("O Tamanho não é 64!");
		}

		int[][] kn = gerarChavesPorRodada(chave56Bits);
		int[] textoCifrado = encodeData(textoSimples, kn);

		return textoCifrado;
	}

	private static int[][] gerarChavesPorRodada(int[] key) {
		int[] p_k = permutaChavePrincipal(key);
		int[][] cndn = gerarCnDn(p_k);
		return permutaCnDn(cndn);
	}

	private static int[] permutaChavePrincipal(int[] chave) {
		int[] chave_permutada = new int[56];
		chave_permutada[0] = chave[56];
		chave_permutada[1] = chave[48];
		chave_permutada[2] = chave[40];
		chave_permutada[3] = chave[32];
		chave_permutada[4] = chave[24];
		chave_permutada[5] = chave[16];
		chave_permutada[6] = chave[8];
		chave_permutada[7] = chave[0];
		chave_permutada[8] = chave[57];
		chave_permutada[9] = chave[49];
		chave_permutada[10] = chave[41];
		chave_permutada[11] = chave[33];
		chave_permutada[12] = chave[25];
		chave_permutada[13] = chave[17];
		chave_permutada[14] = chave[9];
		chave_permutada[15] = chave[1];
		chave_permutada[16] = chave[58];
		chave_permutada[17] = chave[50];
		chave_permutada[18] = chave[42];
		chave_permutada[19] = chave[34];
		chave_permutada[20] = chave[26];
		chave_permutada[21] = chave[18];
		chave_permutada[22] = chave[10];
		chave_permutada[23] = chave[2];
		chave_permutada[24] = chave[59];
		chave_permutada[25] = chave[51];
		chave_permutada[26] = chave[43];
		chave_permutada[27] = chave[35];
		chave_permutada[28] = chave[62];
		chave_permutada[29] = chave[54];
		chave_permutada[30] = chave[46];
		chave_permutada[31] = chave[38];
		chave_permutada[32] = chave[30];
		chave_permutada[33] = chave[22];
		chave_permutada[34] = chave[14];
		chave_permutada[35] = chave[6];
		chave_permutada[36] = chave[61];
		chave_permutada[37] = chave[53];
		chave_permutada[38] = chave[45];
		chave_permutada[39] = chave[37];
		chave_permutada[40] = chave[29];
		chave_permutada[41] = chave[21];
		chave_permutada[42] = chave[13];
		chave_permutada[43] = chave[5];
		chave_permutada[44] = chave[60];
		chave_permutada[45] = chave[52];
		chave_permutada[46] = chave[44];
		chave_permutada[47] = chave[36];
		chave_permutada[48] = chave[28];
		chave_permutada[49] = chave[20];
		chave_permutada[50] = chave[12];
		chave_permutada[51] = chave[4];
		chave_permutada[52] = chave[27];
		chave_permutada[53] = chave[19];
		chave_permutada[54] = chave[11];
		chave_permutada[55] = chave[3];
		return chave_permutada;
	}

	private static int[][] gerarCnDn(int[] p_k) {
		int[][] cn = new int[17][28];
		int[][] dn = new int[17][28];

		System.arraycopy(p_k, 0, cn[0], 0, 28);
		System.arraycopy(p_k, 28, dn[0], 0, 28);

		for (byte i = 1; i < 17; i++) {
			for (byte j = 0; j < 26; j++) {
				if (i != 1 && i != 2 && i != 9 && i != 16) {

					cn[i][j] = cn[i - 1][j + 2];
					dn[i][j] = dn[i - 1][j + 2];
				} else {

					cn[i][j] = cn[i - 1][j + 1];
					dn[i][j] = dn[i - 1][j + 1];
				}
			}

			if (i != 1 && i != 2 && i != 9 && i != 16) {
				cn[i][26] = cn[i - 1][0];
				cn[i][27] = cn[i - 1][1];
				dn[i][26] = dn[i - 1][0];
				dn[i][27] = dn[i - 1][1];
			} else {
				cn[i][26] = cn[i - 1][27];
				cn[i][27] = cn[i - 1][0];
				dn[i][26] = dn[i - 1][27];
				dn[i][27] = dn[i - 1][0];
			}
		}

		int[][] cndn = new int[16][56];
		for (byte i = 0; i < 16; i++) {
			for (byte j = 0; j < 28; j++) {
				cndn[i][j] = cn[i + 1][j];
				cndn[i][j + 28] = dn[i + 1][j];
			}
		}
		return cndn;
	}

	private static int[][] permutaCnDn(int[][] cndn) {
		int[][] kn = new int[16][48];
		for (byte i = 0; i < 16; i++) {
			kn[i][0] = cndn[i][13];
			kn[i][1] = cndn[i][16];
			kn[i][2] = cndn[i][10];
			kn[i][3] = cndn[i][23];
			kn[i][4] = cndn[i][0];
			kn[i][5] = cndn[i][4];
			kn[i][6] = cndn[i][2];
			kn[i][7] = cndn[i][27];
			kn[i][8] = cndn[i][14];
			kn[i][9] = cndn[i][5];
			kn[i][10] = cndn[i][20];
			kn[i][11] = cndn[i][9];
			kn[i][12] = cndn[i][22];
			kn[i][13] = cndn[i][18];
			kn[i][14] = cndn[i][11];
			kn[i][15] = cndn[i][3];
			kn[i][16] = cndn[i][25];
			kn[i][17] = cndn[i][7];
			kn[i][18] = cndn[i][15];
			kn[i][19] = cndn[i][6];
			kn[i][20] = cndn[i][26];
			kn[i][21] = cndn[i][19];
			kn[i][22] = cndn[i][12];
			kn[i][23] = cndn[i][1];
			kn[i][24] = cndn[i][40];
			kn[i][25] = cndn[i][51];
			kn[i][26] = cndn[i][30];
			kn[i][27] = cndn[i][36];
			kn[i][28] = cndn[i][46];
			kn[i][29] = cndn[i][54];
			kn[i][30] = cndn[i][29];
			kn[i][31] = cndn[i][39];
			kn[i][32] = cndn[i][50];
			kn[i][33] = cndn[i][44];
			kn[i][34] = cndn[i][32];
			kn[i][35] = cndn[i][47];
			kn[i][36] = cndn[i][43];
			kn[i][37] = cndn[i][48];
			kn[i][38] = cndn[i][38];
			kn[i][39] = cndn[i][55];
			kn[i][40] = cndn[i][33];
			kn[i][41] = cndn[i][52];
			kn[i][42] = cndn[i][45];
			kn[i][43] = cndn[i][41];
			kn[i][44] = cndn[i][49];
			kn[i][45] = cndn[i][35];
			kn[i][46] = cndn[i][28];
			kn[i][47] = cndn[i][31];
		}
		return kn;
	}

	private static int[] encodeData(int[] plaintext, int[][] kn) {
		int[] IP = permutacaoInicialTextoSimples(plaintext);
		int[] R16L16 = performDESRounds(IP, kn);

		int[] textoCifrado = new int[64];
		for (byte i = 0; i < 8; i++) {
			textoCifrado[(8 * i) + 0] = R16L16[(8 * 5) - (i + 1)];
			textoCifrado[(8 * i) + 1] = R16L16[(8 * 1) - (i + 1)];
			textoCifrado[(8 * i) + 2] = R16L16[(8 * 6) - (i + 1)];
			textoCifrado[(8 * i) + 3] = R16L16[(8 * 2) - (i + 1)];
			textoCifrado[(8 * i) + 4] = R16L16[(8 * 7) - (i + 1)];
			textoCifrado[(8 * i) + 5] = R16L16[(8 * 3) - (i + 1)];
			textoCifrado[(8 * i) + 6] = R16L16[(8 * 8) - (i + 1)];
			textoCifrado[(8 * i) + 7] = R16L16[(8 * 4) - (i + 1)];
		}

		return textoCifrado;
	}

	private static int[] permutacaoInicialTextoSimples(int[] textoSimples) {
		int[] IP = new int[64];
		for (byte i = 0; i < 8; i++) {
			IP[(8 * 5) - (i + 1)] = textoSimples[(8 * i) + 0];
			IP[(8 * 1) - (i + 1)] = textoSimples[(8 * i) + 1];
			IP[(8 * 6) - (i + 1)] = textoSimples[(8 * i) + 2];
			IP[(8 * 2) - (i + 1)] = textoSimples[(8 * i) + 3];
			IP[(8 * 7) - (i + 1)] = textoSimples[(8 * i) + 4];
			IP[(8 * 3) - (i + 1)] = textoSimples[(8 * i) + 5];
			IP[(8 * 8) - (i + 1)] = textoSimples[(8 * i) + 6];
			IP[(8 * 4) - (i + 1)] = textoSimples[(8 * i) + 7];
		}
		return IP;
	}

	private static int[] performDESRounds(int[] IP, int[][] kn) {
		int[][] Ln = new int[17][32];
		int[][] Rn = new int[17][32];

		System.arraycopy(IP, 0, Ln[0], 0, 32);
		System.arraycopy(IP, 32, Rn[0], 0, 32);

		int[][] mangler_result = new int[16][32];

		for (byte i = 1; i < 17; i++) {

			Ln[i] = Rn[i - 1];

			mangler_result[i - 1] = mangler(Rn[i - 1], kn[i - 1]);
			for (byte j = 0; j < 32; j++) {
				Rn[i][j] = Ln[i - 1][j] ^ mangler_result[i - 1][j];
			}
		}

		int[] R16L16 = new int[64];
		for (byte i = 0; i < 32; i++) {
			R16L16[i] = Rn[16][i];
			R16L16[i + 32] = Ln[16][i];
		}
		return R16L16;
	}

	private static int[] mangler(int[] block, int[] key) {
		int[] E = expandirBlock(block);

		int[] result = new int[48];
		for (byte i = 0; i < 48; i++) {
			result[i] = E[i] ^ key[i];
		}
		int[][] B = new int[8][6];
		for (int i = 0; i < 8; i++) {
			System.arraycopy(result, i * 6, B[i], 0, 6);
		}

		int[] sbox_output = lookupSBoxes(B);
		return permutateSBoxOutput(sbox_output);
	}

	private static int[] expandirBlock(int[] block) {
		int[] E = new int[48];
		E[0] = block[31];
		for (byte i = 1; i < 6; i++)
			E[i] = block[i - 1];
		for (byte i = 6; i < 12; i++)
			E[i] = block[i - 3];
		for (byte i = 12; i < 18; i++)
			E[i] = block[i - 5];
		for (byte i = 18; i < 24; i++)
			E[i] = block[i - 7];
		for (byte i = 24; i < 30; i++)
			E[i] = block[i - 9];
		for (byte i = 30; i < 36; i++)
			E[i] = block[i - 11];
		for (byte i = 36; i < 42; i++)
			E[i] = block[i - 13];
		for (byte i = 42; i < 47; i++)
			E[i] = block[i - 15];
		E[47] = block[0];
		return E;
	}

	private static int[] lookupSBoxes(int[][] B) {

		byte[][][] SBOX = {
				{ { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
						{ 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
						{ 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
						{ 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } },
				{ { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
						{ 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
						{ 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
						{ 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } },
				{ { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
						{ 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
						{ 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
						{ 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } },
				{ { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
						{ 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
						{ 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
						{ 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } },
				{ { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
						{ 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
						{ 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
						{ 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } },
				{ { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
						{ 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
						{ 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
						{ 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } },
				{ { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
						{ 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
						{ 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
						{ 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } },
				{ { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
						{ 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
						{ 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
						{ 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } } };

		String[] linhas_binario = new String[8];
		String[] colunas_binario = new String[8];
		for (byte i = 0; i < 8; i++) {
			linhas_binario[i] = "" + B[i][0] + B[i][5];
			colunas_binario[i] = "" + B[i][1] + B[i][2] + B[i][3] + B[i][4];
		}

		int[] linhas_decimal = new int[8];
		int[] colunas_decimal = new int[8];
		for (byte i = 0; i < 8; i++) {
			linhas_decimal[i] = Integer.parseInt(linhas_binario[i], 2);
			colunas_decimal[i] = Integer.parseInt(colunas_binario[i], 2);
		}

		String[] valores_sbox = new String[8];
		for (byte i = 0; i < 8; i++) {
			valores_sbox[i] = Integer.toBinaryString(SBOX[i][linhas_decimal[i]][colunas_decimal[i]]);
		}
		for (byte i = 0; i < 8; i++) {
			while (valores_sbox[i].length() < 4) {
				valores_sbox[i] = "0" + valores_sbox[i];
			}
		}

		int[] sbox_saida = new int[32];
		for (byte i = 0; i < 8; i++) {
			for (byte j = 0; j < 4; j++) {
				sbox_saida[j + (i * 4)] = Integer.parseInt(valores_sbox[i].substring(j, j + 1));
			}
		}
		return sbox_saida;
	}

	private static int[] permutateSBoxOutput(int[] sbox_saida) {
		int[] m_resultado = new int[32];
		m_resultado[0] = sbox_saida[15];
		m_resultado[1] = sbox_saida[6];
		m_resultado[2] = sbox_saida[19];
		m_resultado[3] = sbox_saida[20];
		m_resultado[4] = sbox_saida[28];
		m_resultado[5] = sbox_saida[11];
		m_resultado[6] = sbox_saida[27];
		m_resultado[7] = sbox_saida[16];
		m_resultado[8] = sbox_saida[0];
		m_resultado[9] = sbox_saida[14];
		m_resultado[10] = sbox_saida[22];
		m_resultado[11] = sbox_saida[25];
		m_resultado[12] = sbox_saida[4];
		m_resultado[13] = sbox_saida[17];
		m_resultado[14] = sbox_saida[30];
		m_resultado[15] = sbox_saida[9];
		m_resultado[16] = sbox_saida[1];
		m_resultado[17] = sbox_saida[7];
		m_resultado[18] = sbox_saida[23];
		m_resultado[19] = sbox_saida[13];
		m_resultado[20] = sbox_saida[31];
		m_resultado[21] = sbox_saida[26];
		m_resultado[22] = sbox_saida[2];
		m_resultado[23] = sbox_saida[8];
		m_resultado[24] = sbox_saida[18];
		m_resultado[25] = sbox_saida[12];
		m_resultado[26] = sbox_saida[29];
		m_resultado[27] = sbox_saida[5];
		m_resultado[28] = sbox_saida[21];
		m_resultado[29] = sbox_saida[10];
		m_resultado[30] = sbox_saida[3];
		m_resultado[31] = sbox_saida[24];
		return m_resultado;
	}

	static void array_imprimir(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (arr.length == 64) {
				if ((i + 1) % 8 == 0)
					System.out.print(" ");
			} else if (arr.length == 56) {
				if ((i + 1) % 7 == 0)
					System.out.print(" ");
			} else if (arr.length == 48) {
				if ((i + 1) % 6 == 0)
					System.out.print(" ");
			} else if (arr.length == 32) {
				if ((i + 1) % 4 == 0)
					System.out.print(" ");
			} else if (arr.length == 28) {
				if ((i + 1) % 7 == 0)
					System.out.print(" ");
			} else if (arr.length == 24) {
				if ((i + 1) % 8 == 0)
					System.out.print(" ");
			} else if (arr.length == 8) {
				if ((i + 1) % 1 == 0)
					System.out.print(" ");
			} else {
				if ((i + 1) % 8 == 0)
					System.out.print(" ");
			}
		}
		System.out.println();
	}

	static void array_imprimir(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			array_imprimir(arr[i]);
		}
		System.out.println();
	}
}