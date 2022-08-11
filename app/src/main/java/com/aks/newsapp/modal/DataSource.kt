package com.aks.newsapp.modal

import me.bush.translator.Language

class DataSource {

    fun loadLanguage() : HashMap<String, Language> {
        return hashMapOf(
            "Andhra Pradesh" to Language.TELUGU,
            "Arunachal Pradesh" to Language.TELUGU,
            "Assam" to Language.BENGALI,
            "Bihar" to Language.HINDI,
            "Chhattisgarh" to Language.HINDI,
            "Goa" to Language.ENGLISH,
            "Gujarat" to Language.GUJARATI,
            "Haryana" to Language.HINDI,
            "Himachal Pradesh" to Language.HINDI,
            "Jharkhand" to Language.HINDI,
            "Karnataka" to Language.KANNADA,
            "Kerala" to Language.MALAYALAM,
            "Madhya Pradesh" to Language.HINDI,
            "Maharashtra" to Language.MARATHI,
            "Manipur" to Language.ENGLISH,
            "Meghalaya" to Language.ENGLISH,
            "Mizoram" to Language.ENGLISH,
            "Nagaland" to Language.ENGLISH,
            "Odisha" to Language.ODIA,
            "Punjab" to Language.PUNJABI,
            "Rajasthan" to Language.HINDI,
            "Sikkim" to Language.ENGLISH,
            "Tamil Nadu" to Language.TAMIL,
            "Telangana" to Language.TELUGU,
            "Tripura" to Language.BENGALI,
            "Uttar Pradesh" to Language.HINDI,
            "Uttarakhand" to Language.HINDI,
            "West Bengal" to Language.BENGALI,

            "Andaman and Nicobar Islands" to Language.ENGLISH,
            "Chandigarh" to Language.ENGLISH,
            "Dadra and Nagar Haveli" to Language.GUJARATI,
            "Daman and Diu" to Language.MARATHI,
            "Delhi" to Language.HINDI,
            "Lakshadweep" to Language.MALAYALAM,
            "Jammu and Kashmir" to Language.HINDI,
            "Ladakh" to Language.HINDI,
            "Puducherry" to Language.TAMIL,

            "" to Language.ENGLISH
        )
    }

}