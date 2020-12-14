/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.library;

import java.util.regex.Pattern;

/**
 *
 * @author Allen
 */
public class ValidData {

    public static Pattern patternTextNoneUnicode = Pattern.compile("[a-zA-Z]+$");
    public static Pattern patternUserName = Pattern.compile("[a-zA-Z0-9]+$");
    public static Pattern patternPassword = Pattern.compile("[a-zA-Z0-9]+$"); // @"^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{2,20})";
    public static Pattern patternPin = Pattern.compile("[0-9]+$");
    public static Pattern patternFName = Pattern.compile("^([a-zA-Z0-9 ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵ]*)([a-zA-Z0-9 ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵ]*)+$");
    public static Pattern patternText = Pattern.compile("([a-zA-Z0-9 ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵ .:]*)([a-zA-Z0-9 ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵ .:]*)+$");
    public static Pattern patternAlphabet = Pattern.compile("([a-zA-Z0-9 ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵ .:]*)([a-zA-Z0-9 ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵ .:]*)+$");
    public static Pattern patternPhone = Pattern.compile("((\\+84)|(09|01[2|6|8|9]))+([0-9]{8,12})\b$");
    public static Pattern patternTel = Pattern.compile("^((\\+\\d{1,3}(-| )?\\(?\\d\\)?(-| )?\\d{1,3})|(\\(?\\d{2,3}\\)?))(-| )?(\\d{3,4})(-| )?(\\d{4})(( x| ext)\\d{1,5}){0,1}$");
    public static Pattern patternEmail = Pattern.compile("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    public static Pattern patternRoll = Pattern.compile("[a-zA-Z0-9]+$");
    public static Pattern patternDate_DDMMYY = Pattern.compile("^([0]?[0-9]|[12][0-9]|[3][01])[./-]([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})$");
    public static Pattern patternDate_DDMMYY_HHMMSS = Pattern.compile("^([0]?[0-9]|[12][0-9]|[3][01])[./-]([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2}) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$");
    public static Pattern patternDate_YYMMDD = Pattern.compile("^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$"); // yyyy-MM-dd
    public static Pattern patternDate_MMDDYY = Pattern.compile("^([0]?[1-9]|[1][0-2])[./-]([0]?[0-9]|[12][0-9]|[3][01])[./-]([0-9]{4}|[0-9]{2})$"); //mm/dd/yyyy
    public static Pattern patternDate_MMDDYY_HHMMSS = Pattern.compile("^([0]?[1-9]|[1][0-2])[./-]([0]?[0-9]|[12][0-9]|[3][01])[./-]([0-9]{4}|[0-9]{2}) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$"); //mm/dd/yyyy
    public static Pattern patternDate2 = Pattern.compile("^(0?[1-9]|1[0-2])[-/](0?[1-9]|[12][0-9]|3[01])[-/](19[5-9][0-9]|20[0-4][0-9]|2050)$"); // yyyy-MM-dd
    public static Pattern patternTimekeeper = Pattern.compile("^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$"); // yyyy-MM-dd
    public static Pattern patternYoutube = Pattern.compile("(?:.+?)?(?:\\/v\\/|watch\\/|\\?v=|\\&v=|youtu\\.be\\/|\\/v=|^youtu\\.be\\/)([a-zA-Z0-9_-]{11})+");
    // librari extensions file
}
