/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.type;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Allen
 */
public class InternetProvider {

    public static final String VIETTEL = "VT";
    public static final String VINA_PHONE = "MOBI";
    public static final String MOBI_PHONE = "VINA";

//
//    public List<IntProviderOption> InternetProviderData() {
//        List<IntProviderOption> intProviderOptions = new ArrayList<>();
//        intProviderOptions.add(new IntProviderOption(1, "VT", "Viettel"));
//        intProviderOptions.add(new IntProviderOption(2, "MOBI", "Mobi Phone"));
//        intProviderOptions.add(new IntProviderOption(3, "VINA", "Vina Phone"));
//        return intProviderOptions;
//    }
    public List<OptionListModel> InternetProviderData = Arrays.asList(
            new OptionListModel(1, "VT", "Viettel"),
            new OptionListModel(2, "MOBI", "Mobi Phone"),
            new OptionListModel(3, "VINA", "Vina Phone")
    );
    public List<OptionListModel> CompProviderData = Arrays.asList(
            new OptionListModel(1, "COMP01", "Partner 01"),
            new OptionListModel(2, "COMP01", "Partner 02"),
            new OptionListModel(3, "COMP01", "Partner 03")
    );
}
