package com.ayman.searchengine.ui.home;

import android.content.SharedPreferences;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Countries {
    private static final String COUNTRY_KEY = "COUNTRY";
    private static final int EGYPT_INDEX = 64;
    private static final List<Pair<String, String>> countryCodeAndName = new ArrayList<Pair<String, String>>() {{
        add(new Pair<>("af", "Afghanistan"));
        add(new Pair<>("al", "Albania"));
        add(new Pair<>("dz", "Algeria"));
        add(new Pair<>("as", "American Samoa"));
        add(new Pair<>("ad", "Andorra"));
        add(new Pair<>("ao", "Angola"));
        add(new Pair<>("ai", "Anguilla"));
        add(new Pair<>("aq", "Antarctic"));
        add(new Pair<>("ag", "Antigua"));
        add(new Pair<>("ar", "Argentina"));
        add(new Pair<>("am", "Armenia"));
        add(new Pair<>("aw", "Aruba"));
        add(new Pair<>("ac", "Ascension Island"));
        add(new Pair<>("au", "Australia"));
        add(new Pair<>("at", "Austria"));
        add(new Pair<>("az", "Azerbaijan"));
        add(new Pair<>("bs", "Bahamas"));
        add(new Pair<>("bh", "Bahrain"));
        add(new Pair<>("bd", "Bangladesh"));
        add(new Pair<>("bb", "Barbados"));
        add(new Pair<>("by", "Belarus"));
        add(new Pair<>("be", "Belgium"));
        add(new Pair<>("bz", "Belize"));
        add(new Pair<>("bj", "Benin"));
        add(new Pair<>("bm", "Bermuda"));
        add(new Pair<>("bt", "Bhutan"));
        add(new Pair<>("bo", "Bolivia"));
        add(new Pair<>("bq", "Bonaire"));
        add(new Pair<>("ba", "Bosnia and Herzegovina"));
        add(new Pair<>("bw", "Botswana"));
        add(new Pair<>("br", "Brazil"));
        add(new Pair<>("vg", "Britische Virgin Islands"));
        add(new Pair<>("io", "British Indian Ocean Territory"));
        add(new Pair<>("bn", "Brunei"));
        add(new Pair<>("bg", "Bulgaria"));
        add(new Pair<>("bf", "Burkina Faso"));
        add(new Pair<>("bi", "Burundi"));
        add(new Pair<>("kh", "Cambodia"));
        add(new Pair<>("cm", "Cameroon"));
        add(new Pair<>("ca", "Canada"));
        add(new Pair<>("cv", "Cape Verde"));
        add(new Pair<>("ky", "Cayman Islands"));
        add(new Pair<>("cf", "Central African Republic"));
        add(new Pair<>("td", "Chad"));
        add(new Pair<>("cl", "Chile"));
        add(new Pair<>("cn", "China"));
        add(new Pair<>("cx", "Christmas Island"));
        add(new Pair<>("cc", "Cocos Islands"));
        add(new Pair<>("co", "Colombia"));
        add(new Pair<>("km", "Comoros"));
        add(new Pair<>("ck", "Cook Islands"));
        add(new Pair<>("cr", "Costa Rica"));
        add(new Pair<>("hr", "Croatia"));
        add(new Pair<>("cu", "Cuba"));
        add(new Pair<>("cw", "Curaçao"));
        add(new Pair<>("cy", "Cyprus"));
        add(new Pair<>("cz", "Czech Republic"));
        add(new Pair<>("ci", "Côte d’Ivoire"));
        add(new Pair<>("cd", "Democratic Republic of the Congo"));
        add(new Pair<>("dk", "Denmark"));
        add(new Pair<>("dj", "Djibuti"));
        add(new Pair<>("dm", "Dominica"));
        add(new Pair<>("do", "Dominican Republic"));
        add(new Pair<>("ec", "Ecuador"));
        add(new Pair<>("eg", "Egypt"));
        add(new Pair<>("sv", "El Salvador"));
        add(new Pair<>("gq", "Equatorial Guinea"));
        add(new Pair<>("er", "Eritrea"));
        add(new Pair<>("ee", "Estonia"));
        add(new Pair<>("et", "Ethiopia"));
        add(new Pair<>("eu", "European Union"));
        add(new Pair<>("fk", "Falkland Islands"));
        add(new Pair<>("fo", "Faroe"));
        add(new Pair<>("fj", "Fiji"));
        add(new Pair<>("fi", "Finland"));
        add(new Pair<>("fr", "France"));
        add(new Pair<>("gf", "French Guiana"));
        add(new Pair<>("pf", "French Polynesia"));
        add(new Pair<>("tf", "French Southern"));
        add(new Pair<>("ga", "Gabon"));
        add(new Pair<>("gm", "Gambia"));
        add(new Pair<>("ge", "Georgia"));
        add(new Pair<>("de", "Germany"));
        add(new Pair<>("gh", "Ghana"));
        add(new Pair<>("gi", "Gibraltar"));
        add(new Pair<>("gr", "Greece"));
        add(new Pair<>("gl", "Greenland"));
        add(new Pair<>("gd", "Grenada"));
        add(new Pair<>("gp", "Guadeloupe"));
        add(new Pair<>("gu", "Guam"));
        add(new Pair<>("gt", "Guatemala"));
        add(new Pair<>("gg", "Guernsey"));
        add(new Pair<>("gn", "Guinea"));
        add(new Pair<>("gw", "Guinea-Bissau"));
        add(new Pair<>("gy", "Guyana"));
        add(new Pair<>("ht", "Haiti"));
        add(new Pair<>("hm", "Heard Island"));
        add(new Pair<>("hn", "Honduras"));
        add(new Pair<>("hk", "Hong Kong"));
        add(new Pair<>("hu", "Hungary"));
        add(new Pair<>("is", "Iceland"));
        add(new Pair<>("in", "India"));
        add(new Pair<>("id", "Indonesia"));
        add(new Pair<>("ir", "Iran"));
        add(new Pair<>("iq", "Iraq"));
        add(new Pair<>("ie", "Ireland"));
        add(new Pair<>("im", "Isle of Man"));
        add(new Pair<>("il", "Israel"));
        add(new Pair<>("it", "Italy"));
        add(new Pair<>("jm", "Jamaica"));
        add(new Pair<>("jp", "Japan"));
        add(new Pair<>("je", "Jersey"));
        add(new Pair<>("jo", "Jordan"));
        add(new Pair<>("kz", "Kazakhstan"));
        add(new Pair<>("ke", "Kenya"));
        add(new Pair<>("ki", "Kiribati"));
        add(new Pair<>("kw", "Kuwait"));
        add(new Pair<>("kg", "Kyrgyzstan"));
        add(new Pair<>("la", "Laos"));
        add(new Pair<>("lv", "Latvia"));
        add(new Pair<>("lb", "Lebanon"));
        add(new Pair<>("ls", "Lesotho"));
        add(new Pair<>("lr", "Liberia"));
        add(new Pair<>("ly", "Libya"));
        add(new Pair<>("li", "Liechtenstein"));
        add(new Pair<>("lt", "Lithuania"));
        add(new Pair<>("lu", "Luxembourg"));
        add(new Pair<>("mo", "Macau"));
        add(new Pair<>("mk", "Macedonia"));
        add(new Pair<>("mg", "Madagascar"));
        add(new Pair<>("mw", "Malawi"));
        add(new Pair<>("my", "Malaysia"));
        add(new Pair<>("mv", "Maldives"));
        add(new Pair<>("ml", "Mali"));
        add(new Pair<>("mt", "Malta"));
        add(new Pair<>("ma", "Marocco"));
        add(new Pair<>("mh", "Marshall Islands"));
        add(new Pair<>("mq", "Martinique"));
        add(new Pair<>("mr", "Mauritania"));
        add(new Pair<>("mu", "Mauritius"));
        add(new Pair<>("yt", "Mayotte"));
        add(new Pair<>("mx", "Mexico"));
        add(new Pair<>("fm", "Micronesia"));
        add(new Pair<>("md", "Moldova"));
        add(new Pair<>("mc", "Monaco"));
        add(new Pair<>("mn", "Mongolia"));
        add(new Pair<>("me", "Montenegro"));
        add(new Pair<>("ms", "Montserrat"));
        add(new Pair<>("mz", "Mozambique"));
        add(new Pair<>("mm", "Myanmar"));
        add(new Pair<>("na", "Namibia"));
        add(new Pair<>("nr", "Nauru"));
        add(new Pair<>("np", "Nepal"));
        add(new Pair<>("nl", "Netherlands"));
        add(new Pair<>("nc", "New Caledonia"));
        add(new Pair<>("nz", "New Zealand"));
        add(new Pair<>("ni", "Nicaragua"));
        add(new Pair<>("ne", "Niger"));
        add(new Pair<>("ng", "Nigeria"));
        add(new Pair<>("nu", "Niue"));
        add(new Pair<>("nf", "Norfolk Island"));
        add(new Pair<>("kp", "North Korea"));
        add(new Pair<>("mp", "Northern Mariana Islands"));
        add(new Pair<>("no", "Norway"));
        add(new Pair<>("om", "Oman"));
        add(new Pair<>("pk", "Pakistan"));
        add(new Pair<>("pw", "Palau"));
        add(new Pair<>("ps", "Palestine"));
        add(new Pair<>("pa", "Panama"));
        add(new Pair<>("pg", "Papua New Guinea"));
        add(new Pair<>("py", "Paraguay"));
        add(new Pair<>("pe", "Peru"));
        add(new Pair<>("ph", "Philippines"));
        add(new Pair<>("pn", "Pitcairn Islands"));
        add(new Pair<>("pl", "Poland"));
        add(new Pair<>("pt", "Portugal"));
        add(new Pair<>("pr", "Puerto Rico"));
        add(new Pair<>("qa", "Qatar"));
        add(new Pair<>("cg", "Republic of the Congo"));
        add(new Pair<>("ro", "Romania"));
        add(new Pair<>("ru", "Russia"));
        add(new Pair<>("rw", "Rwanda"));
        add(new Pair<>("re", "Réunion"));
        add(new Pair<>("mf", "Saint Martin"));
        add(new Pair<>("pm", "Saint Pierre and Miquelon"));
        add(new Pair<>("bl", "Saint-Barthélemy"));
        add(new Pair<>("ws", "Samoa"));
        add(new Pair<>("sm", "San Marino"));
        add(new Pair<>("sa", "Saudi Arabia"));
        add(new Pair<>("sn", "Senegal"));
        add(new Pair<>("rs", "Serbia"));
        add(new Pair<>("sc", "Seychelles"));
        add(new Pair<>("sl", "Sierra Leone"));
        add(new Pair<>("sg", "Singapore"));
        add(new Pair<>("sx", "Sint Maarten"));
        add(new Pair<>("sk", "Slovakia"));
        add(new Pair<>("si", "Slovenia"));
        add(new Pair<>("sb", "Solomon Islands"));
        add(new Pair<>("so", "Somalia"));
        add(new Pair<>("za", "South Africa"));
        add(new Pair<>("gs", "South Georgia"));
        add(new Pair<>("kr", "South Korea"));
        add(new Pair<>("ss", "South Sudan"));
        add(new Pair<>("su", "Soviet Union"));
        add(new Pair<>("es", "Spain"));
        add(new Pair<>("lk", "Sri Lanka"));

    }};

    public static String[] getCountryNames() {
        String[] countryNames = new String[countryCodeAndName.size()];
        for (int i = 0; i < countryNames.length; i++) {
            countryNames[i] = countryCodeAndName.get(i).second;
        }
        return countryNames;
    }

    public static String getCountryCode(int position) {
        return countryCodeAndName.get(position).first;
    }

    public static int getSavedCountryIndex(SharedPreferences preferences) {
        return preferences.getInt(COUNTRY_KEY, EGYPT_INDEX);
    }

    public static String getSavedCountryCode(SharedPreferences preferences) {
        return getCountryCode(getSavedCountryIndex(preferences));
    }

    public static void saveCountryIndex(SharedPreferences preferences, int index) {
        preferences.edit().putInt(COUNTRY_KEY, index).apply();
    }
}
