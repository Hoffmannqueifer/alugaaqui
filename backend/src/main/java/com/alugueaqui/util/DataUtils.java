package com.alugueaqui.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DataUtils {

    // Formata uma data para uma string no formato padrão (dd/MM/yyyy)
    public static String formataDateDiaMesAno(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    // Formata uma data e hora para uma string no formato padrão (dd/MM/yyyy HH:mm:ss)
    public static String formataDateHora(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

    // Converte uma string em um objeto LocalDate no formato padrão (dd/MM/yyyy)
    public static LocalDate converterStringToData(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateStr, formatter);
    }

    // Converte uma string em um objeto LocalDateTime no formato padrão (dd/MM/yyyy HH:mm:ss)
    public static LocalDateTime converterStringToDataHora(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    // Calcula a diferença em dias entre duas datas
    public static long calcularDiferecaDeDias(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    // Converte uma Date para LocalDate
    public static LocalDate converterDateToLocalDate(Date date) {
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    // Converte uma LocalDate para Date
    public static Date converterLocalDateToDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    // Converte uma Date para LocalDateTime
    public static LocalDateTime converterDateToLocalDateTime(Date date) {
        return new java.sql.Timestamp(date.getTime()).toLocalDateTime();
    }

    // Converte uma LocalDateTime para Date
    public static Date converterLocalDateTimeToDate(LocalDateTime localDateTime) {
        return java.sql.Timestamp.valueOf(localDateTime);
    }

    // Converte uma string para Date usando um padrão específico
    public static Date stringToDataPorPadrao(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateStr);
    }

    // Converte uma Date para string usando um padrão específico
    public static String dataToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    // Adiciona dias a uma data
    public static LocalDate addDiasNaData(LocalDate date, int days) {
        return date.plusDays(days);
    }

    // Adiciona horas a uma data e hora
    public static LocalDateTime addHoraDataHora(LocalDateTime dateTime, int hours) {
        return dateTime.plusHours(hours);
    }

}
