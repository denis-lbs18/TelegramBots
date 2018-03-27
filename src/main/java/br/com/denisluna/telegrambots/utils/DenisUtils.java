package br.com.denisluna.telegrambots.utils;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

	public static boolean isNumeric(String s) {
		try {
			Long.parseLong(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

}
