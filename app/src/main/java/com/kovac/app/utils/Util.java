package com.kovac.app.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mindminews1 on 14/8/17.
 */

public class Util {

    /**
     * User Registration Login detail api
     */
    public static final String REGISTRATION_LOGIN_DETAIL_URL = "http://kovacadvisory.com.au/api/rest/user";
    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_PASSWORD = "user_password";

    public static final String HALFFORM = "halfform";

    public static final String USERID = "userid";
    public static final String USER_FNAME = "user_fname";
    public static final String USER_LNAME = "user_lname";
    public static final String USER_MOBILENO = "user_mobileno";
    public static final String USER_GENDER = "user_gender";
    public static final String USER_DOB = "user_dob";
    public static final String USER_ADDRESS = "user_address";
    public static final String USER_SUBURB = "user_suburb";
    public static final String USER_STATE = "user_state";
    public static final String USER_POSTCODE = "user_postcode";
    public static final String USER_MARITAL_STATUS = "user_marital_status";
    public static final String USER_DEPENDANT_CHILDREN = "user_dependant_children";
    public static final String USER_DEPENDANT_CHILDREN_DATA = "user_dependant_children_data";
    public static final String EMP_STATUS = "emp_status";
    public static final String EMP_OCCUPATION = "emp_occupation";
    public static final String EMP_TAX_FILE_NO = "emp_tax_file_no";
    public static final String EMP_ANNUAL_INCOME = "emp_annual_income";
    public static final String EMP_SUPER_FUND_NAME = "emp_super_fund_name";
    public static final String EMP_MEMBER_NO = "emp_member_no";
    public static final String EMP_CURRENT_VALUE = "emp_current_value";
    public static final String EMP_INVESTMENT_OPTION = "emp_investment_option";
    public static final String EMP_SUPERANNUATION_STATUS = "emp_superannuation_status";
    public static final String EMP_INSURANCE_COVER = "emp_insurance_cover";
    public static final String EMP_SMOKER = "emp_smoker";
    public static final String EMP_HEIGHT = "emp_height";
    public static final String EMP_WEIGHT = "emp_weight";
    public static final String EMP_LIFE_INSURANCE = "emp_life_insurance";
    public static final String EMP_LIFE_INSURANCE_VALUE = "emp_life_insurance_value";
    public static final String EMP_DISABILITY_INSURANCE = "emp_disability_insurance";
    public static final String EMP_DISABILITY_INSURANCE_VALUE = "emp_disability_insurance_value";
    public static final String EMP_INCOME_PROTECTION = "emp_income_protection";
    public static final String EMP_INCOME_PROTECTION_VALUE = "emp_income_protection_value";
    public static final String EMP_TRAUMA_INSURANCE = "emp_trauma_insurance";
    public static final String EMP_TRAUMA_INSURANCE_VALUE = "emp_trauma_insurance_value";
    public static final String EMP_OWN_HOME = "emp_own_home";
    public static final String EMP_INVESTMENT_PROPERTY = "emp_investment_property";
    public static final String EMP_SHARE_PORTFOLIO = "emp_share_portfolio";
    public static final String EMP_OTHER_ASSETS = "emp_other_assets";
    public static final String EMP_HOME_LOAN = "emp_home_loan";
    public static final String EMP_CREDIT_CARDS = "emp_credit_cards";
    public static final String EMP_INVESTMENT_LOANS = "emp_investment_loans";
    public static final String EMP_OTHER_LIABILITIES = "emp_other_liabilities";
    public static final String EMP_RISK_PROFILE = "emp_risk_profile";
    public static final String EMP_IDENTIFICATION = "emp_identification";
    public static final String EMP_DECLAIMER = "emp_disclaimer";

    public static final String FINANCIAL_ADVISER = "financial_adviser";
    public static final String EMP_PERSONAL_ADVISER = "emp_personal_adviser";
    public static final String NEW = "new";


    public static final String LOGIN_URL = "https://appw.xplan.iress.com.au/touch/client";

    public static final String WEBSITE_URL = "http://kovacadvisory.com.au/kovac-smart-super/";

    /**
     * Already user exist
     */

    public static final String ALREADY_EXIST_URL = "http://kovacadvisory.com.au/api/rest/user";
    public static final String EXIST = "exist";


    // validating email id
    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    public static boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
    }

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        String img= Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
        String header="data:image/"+compressFormat+";base64,";
        Log.e("Settngs Fragment","header- "+header);
        Log.e("Settngs Fragment","Image- "+img);
        return img;
        //return header.concat(img);
    }

    public static Bitmap scaleBitmap(Bitmap bitmapToScale, float newWidth, float newHeight) {
        if(bitmapToScale == null)
            return null;
        //get the original width and height
        int width = bitmapToScale.getWidth();
        int height = bitmapToScale.getHeight();
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(newWidth / width, newHeight / height);

        // recreate the new Bitmap and set it back
        return Bitmap.createBitmap(bitmapToScale, 0, 0, bitmapToScale.getWidth(), bitmapToScale.getHeight(), matrix, true);
    }

    /**
     * Hides the soft keyboard
     */
    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
