package br.com.denisluna.utils;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.text.*;

public class DenisUtils {
	public static String removeAcentos(String texto) {
		return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	public static String retornaDiadasemana() {
		Calendar c = Calendar.getInstance();
		Date data = new Date();
		c.setTime(data);

		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		String dia_da_semana = "";

		switch (dayOfWeek) {
		case 1:
			dia_da_semana = "Um bom domingo";
			break;
		case 2:
			dia_da_semana = "Uma boa segunda";
			break;
		case 3:
			dia_da_semana = "Uma boa terça";
			break;
		case 4:
			dia_da_semana = "Uma boa quarta";
			break;
		case 5:
			dia_da_semana = "Uma boa quinta";
			break;
		case 6:
			dia_da_semana = "Uma boa sexta";
			break;
		case 7:
			dia_da_semana = "Um bom sábado";
			break;
		}
		return dia_da_semana;
	}

	public static String retornaMeses(String datainicial) {
		Calendar c = Calendar.getInstance();
		Date datafim = new Date();
		c.setTime(datafim);

		SimpleDateFormat datastring = new SimpleDateFormat("dd MM yyyy");
		double resultado = 0;
		String retorno = "";

		try {
			Date date1 = datastring.parse(datainicial);
			Date date2 = c.getTime();
			long diff = date2.getTime() - date1.getTime();
			resultado = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			resultado = resultado / 30;
			retorno = String.valueOf((int) resultado) + " meses!";
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return retorno;

	}

	public static void gravaUsuarios(String grupo, Usuario usuario) throws IOException {

		Set<String> arquivo = new HashSet<String>();
		Scanner s = new Scanner(grupo + ".txt");

		while (s.hasNextLine()) {
			arquivo.add(s.nextLine());
		}

		s.close();
		arquivo.add(usuario.toString());
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(grupo + ".txt"), "UTF-8"));
			for (String s1 : arquivo) {
				pw.println(s1);
			}
			pw.flush();
		} finally {
			pw.close();
		}
	}

}
