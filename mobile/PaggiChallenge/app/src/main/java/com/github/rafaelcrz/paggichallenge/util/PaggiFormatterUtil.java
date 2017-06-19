package com.github.rafaelcrz.paggichallenge.util;

import android.content.Context;

import net.danlew.android.joda.DateUtils;

import org.joda.time.DateTime;
import org.joda.time.IllegalInstantException;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by Rafael Felipe on 15/06/2017.
 */

public class PaggiFormatterUtil {

    public static String WITH_SECOND_PATTERN = "yyyy-MM-dd HH:mm:ss";

    //Esse formato seria para o retorno com millesegundos, porem não foi implementado
    //public static String WITH_MILLISECOND_PATTERN = "yyyy-MM-dd hh:mm:ss:SSSSSSZ";//309226Z

    /**
     * Recebe uma data em String e um formato
     * @param context Contexto da app, que é usado na lib JodaTime
     * @param stringDate Data string
     * @param patter Pattern
     * @return string data formattada
     * @throws IllegalInstantException
     */
    public static String formatStringDate(Context context, String stringDate, String patter) throws IllegalInstantException {
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern(patter);
        DateTime dateTime = dateTimeFormat.parseDateTime(stringDate);
        String dateStringFromatted = DateUtils.formatDateTime(context, dateTime, DateUtils.FORMAT_ABBREV_ALL | DateUtils.FORMAT_SHOW_YEAR);
        return dateStringFromatted;
    }

    /**
     * Formata os valores de moeda
     * @param value Valor da moeda
     * @return Moeda formatada em string
     * @throws Exception Gera excessao
     */
    public static String currencyFormatter(int value) throws Exception {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
        return decimalFormat.format(value);
    }
}
