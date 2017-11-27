package com.kovac.app.sessions;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mindminews1 on 10/8/17.
 */

public class UserSession {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    // shared pref mode
    int PRIVATE_MODE = 0;
    // Shared preferences file name
    private static final String PREF_NAME = "kovac_pref";
    private static final String EMAIL = "email";
    private static final String USER_ID = "userid";
    private static final String USER_NAME  = "user_name";
    private static final String PASSWORD="password";
    private static final String FIRST_NAME="first_name";
    private static final String SURNAME="surname";
    private static final String MOBILE="mobile";
    private static final String GENDER="gender";
    private static final String DOB="date_of_birth";
    private static final String STREET_ADDRESS="street_address";
    private static final String SUBURB="suburb";
    private static final String STATE="state";
    private static final String POST_CODE="post_code";
    private static final String MARITAL_STATUS="marital_status";
    private static final String DEPENDANT_CHILDREN="dependant_children";

    private static final String EMPLOYMENT_STATUS = "employment_status";
    private static final String OCCUPATION = "occupation";
    private static final String TEXT_FILE_NUMBER = "text_file_number";
    private static final String ANNUAL_INCOME = "annual_income";

    private static final String SUPER_FUND_NAME = "super_fund_name";
    private static final String MEMBER_NUMBER = "member_number";
    private static final String CURRENT_VALUE = "current_value";

    private static final String NUMBER_OF_LAYOUT = "number_of_layout";
    //dynamic field session
    private static final String SUPER_FUND_NAME1 = "super_fund_name1";
    private static final String MEMBER_NUMBER1 = "member_number1";
    private static final String CURRENT_VALUE1 = "current_value1";

    private static final String SUPER_FUND_NAME2 = "super_fund_name2";
    private static final String MEMBER_NUMBER2 = "member_number2";
    private static final String CURRENT_VALUE2 = "current_value2";

    private static final String SUPER_FUND_NAME3 = "super_fund_name3";
    private static final String MEMBER_NUMBER3 = "member_number3";
    private static final String CURRENT_VALUE3 = "current_value3";

    private static final String SUPER_FUND_NAME4 = "super_fund_name4";
    private static final String MEMBER_NUMBER4 = "member_number4";
    private static final String CURRENT_VALUE4 = "current_value4";

    private static final String SUPER_FUND_NAME5 = "super_fund_name5";
    private static final String MEMBER_NUMBER5 = "member_number5";
    private static final String CURRENT_VALUE5 = "current_value5";

    private static final String SUPER_FUND_NAME6 = "super_fund_name6";
    private static final String MEMBER_NUMBER6 = "member_number6";
    private static final String CURRENT_VALUE6 = "current_value6";

    private static final String SUPER_FUND_NAME7 = "super_fund_name7";
    private static final String MEMBER_NUMBER7 = "member_number7";
    private static final String CURRENT_VALUE7 = "current_value7";

    private static final String SUPER_FUND_NAME_DATA = "super_fund_name_data";
    private static final String MEMBER_NUMBER_DATA = "member_number_data";
    private static final String CURRENT_VALUE_DATA = "current_value_data";

    private static final String INVESTMENT_OPTION = "investment_option";
    private static final String SUPERANNUATION_STATUS = "superannuation_status";

    private static final String INSURANCE_COVER = "insurance_cover";
    private static final String SMOKER = "smoker";
    private static final String HEIGHT = "height";
    private static final String WEIGHT = "weight";
    private static final String LIFE_INSURANCE_STATUS = "life_insurance_status";
    private static final String LIFE_INSURANCE_VALUE = "life_insurance_value";
    private static final String DISABILITY_INSURANCE_STATUS = "disability_insurance_status";
    private static final String DISABILITY_INSURANCE_VALUE = "disability_insurance_value";
    private static final String INCOME_PROTECTION_STATUS = "income_protection_status";
    private static final String INCOME_PROTECTION_VALUE = "income_protection_value";
    private static final String TRAUMA_INSURANCE_STATUS = "trauma_insurance_status";
    private static final String TRAUMA_INSURANCE_VALUE = "trauma_insurance_value";

    private static final String OWN_HOME = "own_home";
    private static final String INVESTMENT_PROPERTY = "investment_property";
    private static final String SHARE_PORTFOLIO = "share_portfolio";
    private static final String OTHER_ASSETS = "other_assets";
    private static final String HOME_LOAN = "home_loan";
    private static final String INVESTMENT_LOANS = "investment_loans";
    private static final String CREDIT_CARDS = "credit_cards";
    private static final String OTHER_LIABILITIES = "other_liabilities";


    private static final String RISK_PROFILE = "risk_profile";
    private static final String RISK_PROFILE_ID = "risk_profile_id";

    private static final String IDENTIFICATION = "identification";

    private static final String DISCLAIMER = "disclaimer";

    private static final String NEW = "new";

    private static final String DEPENDENT_CHILDREN_DATA = "user_dependant_children_data";

    private static final String FIRST_CHILDREN_NAME = "firstChildrenName";
    private static final String FIRST_CHILDREN_DOB = "firstChildrenDob";
    private static final String SECOND_CHILDREN_NAME = "secondChildrenName";
    private static final String SECOND_CHILDREN_DOB = "secondChildrenDob";
    private static final String THIRD_CHILDREN_NAME = "thirdChildrenName";
    private static final String THIRD_CHILDREN_DOB = "thirdChildrenDob";
    private static final String FOURTH_CHILDREN_NAME = "fourthChildrenName";
    private static final String FOURTH_CHILDREN_DOB = "fourthChildrenDob";
    private static final String FIFTH_CHILDREN_NAME = "fifthChildrenName";
    private static final String FIFTH_CHILDREN_DOB = "fifthChildrenDob";
    private static final String SIXTH_CHILDREN_NAME = "sixthChildrenName";
    private static final String SIXTH_CHILDREN_DOB = "sixthChildrenDob";

    public UserSession(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void clearUserSession(){
        editor.clear();
        editor.commit();
    }

    public void setEmail(String email) {
        editor.putString(EMAIL, email);
        editor.commit();
    }

    public String getEmail() {
        return pref.getString(EMAIL, "");
    }


    public void setUserId(String userId) {
        editor.putString(USER_ID, userId);
        editor.commit();
    }

    public String getUserId() {
        return pref.getString(USER_ID, "");
    }


    public void setUserName(String userName) {
        editor.putString(USER_NAME, userName);
        editor.commit();
    }

    public String getUserName() {
        return pref.getString(USER_NAME, "");
    }

    public void setPassword(String password) {
        editor.putString(PASSWORD, password);
        editor.commit();
    }

    public String getPassword() {
        return pref.getString(PASSWORD, "");
    }

    public void setFirstName(String firstName) {
        editor.putString(FIRST_NAME, firstName);
        editor.commit();
    }

    public String getFirstName() {
        return pref.getString(FIRST_NAME, "");
    }

    public void setSurname(String surname) {
        editor.putString(SURNAME, surname);
        editor.commit();
    }

    public String getSurname() {
        return pref.getString(SURNAME, "");
    }

    public void setGender(String gender) {
        editor.putString(GENDER, gender);
        editor.commit();
    }

    public String getMobile() {
        return pref.getString(MOBILE, "");
    }

    public void setMobile(String mobile) {
        editor.putString(MOBILE, mobile);
        editor.commit();
    }

    public String getGender() {
        return pref.getString(GENDER, "");
    }

    public void setDob(String dob) {
        editor.putString(DOB, dob);
        editor.commit();
    }

    public String getDob() {
        return pref.getString(DOB, "");
    }

    public void setStreetAddress(String streetAddress) {
        editor.putString(STREET_ADDRESS, streetAddress);
        editor.commit();
    }

    public String getStreetAddress() {
        return pref.getString(STREET_ADDRESS, "");
    }

    public void setSuburb(String suburb) {
        editor.putString(SUBURB, suburb);
        editor.commit();
    }

    public String getSuburb() {
        return pref.getString(SUBURB, "");
    }

    public void setState(String state) {
        editor.putString(STATE, state);
        editor.commit();
    }

    public String getState() {
        return pref.getString(STATE, "");
    }

    public void setPostCode(String postCode) {
        editor.putString(POST_CODE, postCode);
        editor.commit();
    }

    public String getPostCode() {
        return pref.getString(POST_CODE, "");
    }

    public void setMaritalStatus(String maritalStatus) {
        editor.putString(MARITAL_STATUS, maritalStatus);
        editor.commit();
    }

    public String getMaritalStatus() {
        return pref.getString(MARITAL_STATUS, "");
    }

    public void setDependantChildren(String dependantChildren) {
        editor.putString(DEPENDANT_CHILDREN, dependantChildren);
        editor.commit();
    }

    public String getDependantChildren() {
        return pref.getString(DEPENDANT_CHILDREN, "");
    }

    public void setEmploymentStatus(String employmentStatus) {
        editor.putString(EMPLOYMENT_STATUS, employmentStatus);
        editor.commit();
    }

    public String getEmploymentStatus() {
        return pref.getString(EMPLOYMENT_STATUS, "");
    }


    public void setOccupation(String occupation) {
        editor.putString(OCCUPATION, occupation);
        editor.commit();
    }

    public String getOccupation() {
        return pref.getString(OCCUPATION, "");
    }

    public void setTextFileNumber(String textFileNumber) {
        editor.putString(TEXT_FILE_NUMBER, textFileNumber);
        editor.commit();
    }

    public String getTextFileNumber() {
        return pref.getString(TEXT_FILE_NUMBER, "");
    }

    public void setAnnualIncome(String annualIncome) {
        editor.putString(ANNUAL_INCOME, annualIncome);
        editor.commit();
    }

    public String getAnnualIncome() {
        return pref.getString(ANNUAL_INCOME, "");
    }

    public void setSuperFundName(String superFundName) {
        editor.putString(SUPER_FUND_NAME, superFundName);
        editor.commit();
    }

    public String getSuperFundName() {
        return pref.getString(SUPER_FUND_NAME, "");
    }

    public void setMemberNumber(String memberNumber) {
        editor.putString(MEMBER_NUMBER, memberNumber);
        editor.commit();
    }

    public String getMemberNumber() {
        return pref.getString(MEMBER_NUMBER, "");
    }

    public void setCurrentValue(String currentValue) {
        editor.putString(CURRENT_VALUE, currentValue);
        editor.commit();
    }

    public String getCurrentValue() {
        return pref.getString(CURRENT_VALUE, "");
    }


    public void setNumberOfLayout(String numberOfLayout) {
        editor.putString(NUMBER_OF_LAYOUT, numberOfLayout);
        editor.commit();
    }

    public String getNumberOfLayout() {
        return pref.getString(NUMBER_OF_LAYOUT, "");
    }

    /* Dynamic field session start */
    public void setSuperFundName1(String superFundName1) {
        editor.putString(SUPER_FUND_NAME1, superFundName1);
        editor.commit();
    }

    public String getSuperFundName1() {
        return pref.getString(SUPER_FUND_NAME1, "");
    }

    public void setMemberNumber1(String memberNumber1) {
        editor.putString(MEMBER_NUMBER1, memberNumber1);
        editor.commit();
    }

    public String getMemberNumber1() {
        return pref.getString(MEMBER_NUMBER1, "");
    }

    public void setCurrentValue1(String currentValue1) {
        editor.putString(CURRENT_VALUE1, currentValue1);
        editor.commit();
    }

    public String getCurrentValue1() {
        return pref.getString(CURRENT_VALUE1, "");
    }


    public void setSuperFundName2(String superFundName2) {
        editor.putString(SUPER_FUND_NAME2, superFundName2);
        editor.commit();
    }

    public String getSuperFundName2() {
        return pref.getString(SUPER_FUND_NAME2, "");
    }

    public void setMemberNumber2(String memberNumber2) {
        editor.putString(MEMBER_NUMBER2, memberNumber2);
        editor.commit();
    }

    public String getMemberNumber2() {
        return pref.getString(MEMBER_NUMBER2, "");
    }

    public void setCurrentValue2(String currentValue2) {
        editor.putString(CURRENT_VALUE2, currentValue2);
        editor.commit();
    }

    public String getCurrentValue2() {
        return pref.getString(CURRENT_VALUE2, "");
    }


    public void setSuperFundName3(String superFundName3) {
        editor.putString(SUPER_FUND_NAME3, superFundName3);
        editor.commit();
    }

    public String getSuperFundName3() {
        return pref.getString(SUPER_FUND_NAME3, "");
    }

    public void setMemberNumber3(String memberNumber3) {
        editor.putString(MEMBER_NUMBER3, memberNumber3);
        editor.commit();
    }

    public String getMemberNumber3() {
        return pref.getString(MEMBER_NUMBER3, "");
    }

    public void setCurrentValue3(String currentValue3) {
        editor.putString(CURRENT_VALUE3, currentValue3);
        editor.commit();
    }

    public String getCurrentValue3() {
        return pref.getString(CURRENT_VALUE3, "");
    }


    public void setSuperFundName4(String superFundName4) {
        editor.putString(SUPER_FUND_NAME4, superFundName4);
        editor.commit();
    }

    public String getSuperFundName4() {
        return pref.getString(SUPER_FUND_NAME4, "");
    }

    public void setMemberNumber4(String memberNumber4) {
        editor.putString(MEMBER_NUMBER4, memberNumber4);
        editor.commit();
    }

    public String getMemberNumber4() {
        return pref.getString(MEMBER_NUMBER4, "");
    }

    public void setCurrentValue4(String currentValue4) {
        editor.putString(CURRENT_VALUE4, currentValue4);
        editor.commit();
    }

    public String getCurrentValue4() {
        return pref.getString(CURRENT_VALUE4, "");
    }

    public void setSuperFundName5(String superFundName5) {
        editor.putString(SUPER_FUND_NAME5, superFundName5);
        editor.commit();
    }

    public String getSuperFundName5() {
        return pref.getString(SUPER_FUND_NAME5, "");
    }

    public void setMemberNumber5(String memberNumber5) {
        editor.putString(MEMBER_NUMBER5, memberNumber5);
        editor.commit();
    }

    public String getMemberNumber5() {
        return pref.getString(MEMBER_NUMBER5, "");
    }

    public void setCurrentValue5(String currentValue5) {
        editor.putString(CURRENT_VALUE5, currentValue5);
        editor.commit();
    }

    public String getCurrentValue5() {
        return pref.getString(CURRENT_VALUE5, "");
    }

    public void setSuperFundName6(String superFundName6) {
        editor.putString(SUPER_FUND_NAME6, superFundName6);
        editor.commit();
    }

    public String getSuperFundName6() {
        return pref.getString(SUPER_FUND_NAME6, "");
    }

    public void setMemberNumber6(String memberNumber6) {
        editor.putString(MEMBER_NUMBER6, memberNumber6);
        editor.commit();
    }

    public String getMemberNumber6() {
        return pref.getString(MEMBER_NUMBER6, "");
    }

    public void setCurrentValue6(String currentValue6) {
        editor.putString(CURRENT_VALUE6, currentValue6);
        editor.commit();
    }

    public String getCurrentValue6() {
        return pref.getString(CURRENT_VALUE6, "");
    }

    public void setSuperFundName7(String superFundName7) {
        editor.putString(SUPER_FUND_NAME7, superFundName7);
        editor.commit();
    }

    public String getSuperFundName7() {
        return pref.getString(SUPER_FUND_NAME7, "");
    }

    public void setMemberNumber7(String memberNumber7) {
        editor.putString(MEMBER_NUMBER7, memberNumber7);
        editor.commit();
    }

    public String getMemberNumber7() {
        return pref.getString(MEMBER_NUMBER7, "");
    }

    public void setCurrentValue7(String currentValue7) {
        editor.putString(CURRENT_VALUE7, currentValue7);
        editor.commit();
    }

    public String getCurrentValue7() {
        return pref.getString(CURRENT_VALUE7, "");
    }

    public void setSuperFundNameData(String superFundNameData) {
        editor.putString(SUPER_FUND_NAME_DATA, superFundNameData);
        editor.commit();
    }

    public String getSuperFundNameData() {
        return pref.getString(SUPER_FUND_NAME_DATA, "");
    }

    public void setMemberNumberData(String memberNumberData) {
        editor.putString(MEMBER_NUMBER_DATA, memberNumberData);
        editor.commit();
    }

    public String getMemberNumberData() {
        return pref.getString(MEMBER_NUMBER_DATA, "");
    }

    public void setCurrentValueData(String currentValueData) {
        editor.putString(CURRENT_VALUE_DATA, currentValueData);
        editor.commit();
    }

    public String getCurrentValueData() {
        return pref.getString(CURRENT_VALUE_DATA, "");
    }

    /* Dynamic field session end */

    public void setInvestmentOption(String investmentOption) {
        editor.putString(INVESTMENT_OPTION, investmentOption);
        editor.commit();
    }

    public String getInvestmentOption() {
        return pref.getString(INVESTMENT_OPTION, "");
    }

    public void setSuperannuationStatus(String superannuationStatus) {
        editor.putString(SUPERANNUATION_STATUS, superannuationStatus);
        editor.commit();
    }

    public String getSuperannuationStatus() {
        return pref.getString(SUPERANNUATION_STATUS, "");
    }

    public void setInsuranceCover(String insuranceCover) {
        editor.putString(INSURANCE_COVER, insuranceCover);
        editor.commit();
    }

    public String getInsuranceCover() {
        return pref.getString(INSURANCE_COVER, "");
    }

    public void setSmoker(String smoker) {
        editor.putString(SMOKER, smoker);
        editor.commit();
    }

    public String getSmoker() {
        return pref.getString(SMOKER, "");
    }

    public void setHeight(String height) {
        editor.putString(HEIGHT, height);
        editor.commit();
    }

    public String getHeight() {
        return pref.getString(HEIGHT, "");
    }

    public void setWeight(String weight) {
        editor.putString(WEIGHT, weight);
        editor.commit();
    }

    public String getWeight() {
        return pref.getString(WEIGHT, "");
    }

    public void setLifeInsuranceStatus(String lifeInsuranceStatus) {
        editor.putString(LIFE_INSURANCE_STATUS, lifeInsuranceStatus);
        editor.commit();
    }

    public String getLifeInsuranceStatus() {
        return pref.getString(LIFE_INSURANCE_STATUS, "");
    }

    public void setLifeInsuranceValue(String lifeInsuranceValue) {
        editor.putString(LIFE_INSURANCE_VALUE, lifeInsuranceValue);
        editor.commit();
    }

    public String getLifeInsuranceValue() {
        return pref.getString(LIFE_INSURANCE_VALUE, "");
    }

    public void setDisabilityInsuranceStatus(String disabilityInsuranceStatus) {
        editor.putString(DISABILITY_INSURANCE_STATUS, disabilityInsuranceStatus);
        editor.commit();
    }

    public String getDisabilityInsuranceStatus() {
        return pref.getString(DISABILITY_INSURANCE_STATUS, "");
    }

    public void setDisabilityInsuranceValue(String disabilityInsuranceValue) {
        editor.putString(DISABILITY_INSURANCE_VALUE, disabilityInsuranceValue);
        editor.commit();
    }

    public String getDisabilityInsuranceValue() {
        return pref.getString(DISABILITY_INSURANCE_VALUE, "");
    }

    public void setIncomeProtectionStatus(String incomeProtectionStatus) {
        editor.putString(INCOME_PROTECTION_STATUS, incomeProtectionStatus);
        editor.commit();
    }

    public String getIncomeProtectionStatus() {
        return pref.getString(INCOME_PROTECTION_STATUS, "");
    }

    public void setIncomeProtectionValue(String incomeProtectionValue) {
        editor.putString(INCOME_PROTECTION_VALUE, incomeProtectionValue);
        editor.commit();
    }

    public String getIncomeProtectionValue() {
        return pref.getString(INCOME_PROTECTION_VALUE, "");
    }

    public void setTraumaInsuranceStatus(String traumaInsuranceStatus) {
        editor.putString(TRAUMA_INSURANCE_STATUS, traumaInsuranceStatus);
        editor.commit();
    }

    public String getTraumaInsuranceStatus() {
        return pref.getString(TRAUMA_INSURANCE_STATUS, "");
    }

    public void setTraumaInsuranceValue(String traumaInsuranceValue) {
        editor.putString(TRAUMA_INSURANCE_VALUE, traumaInsuranceValue);
        editor.commit();
    }

    public String getTraumaInsuranceValue() {
        return pref.getString(TRAUMA_INSURANCE_VALUE, "");
    }

    public void setOwnHome(String ownHome) {
        editor.putString(OWN_HOME, ownHome);
        editor.commit();
    }

    public String getOwnHome() {
        return pref.getString(OWN_HOME, "");
    }

    public void setInvestmentProperty(String investmentProperty) {
        editor.putString(INVESTMENT_PROPERTY, investmentProperty);
        editor.commit();
    }

    public String getInvestmentProperty() {
        return pref.getString(INVESTMENT_PROPERTY, "");
    }

    public void setSharePortfolio(String sharePortfolio) {
        editor.putString(SHARE_PORTFOLIO, sharePortfolio);
        editor.commit();
    }

    public String getSharePortfolio() {
        return pref.getString(SHARE_PORTFOLIO, "");
    }

    public void setOtherAssets(String otherAssets) {
        editor.putString(OTHER_ASSETS, otherAssets);
        editor.commit();
    }

    public String getOtherAssets() {
        return pref.getString(OTHER_ASSETS, "");
    }

    public void setHomeLoan(String homeLoan) {
        editor.putString(HOME_LOAN, homeLoan);
        editor.commit();
    }

    public String getHomeLoan() {
        return pref.getString(HOME_LOAN, "");
    }

    public void setInvestmentLoans(String investmentLoans) {
        editor.putString(INVESTMENT_LOANS, investmentLoans);
        editor.commit();
    }

    public String getInvestmentLoans() {
        return pref.getString(INVESTMENT_LOANS, "");
    }

    public void setCreditCards(String creditCards) {
        editor.putString(CREDIT_CARDS, creditCards);
        editor.commit();
    }

    public String getCreditCards() {
        return pref.getString(CREDIT_CARDS, "");
    }

    public void setOtherLiabilities(String otherLiabilities) {
        editor.putString(OTHER_LIABILITIES, otherLiabilities);
        editor.commit();
    }

    public String getOtherLiabilities() {
        return pref.getString(OTHER_LIABILITIES, "");
    }

    public void setRiskProfile(String riskProfile) {
        editor.putString(RISK_PROFILE, riskProfile);
        editor.commit();
    }

    public String getRiskProfile() {
        return pref.getString(RISK_PROFILE, "");
    }

    public void setRiskProfileId(String riskProfileId) {
        editor.putString(RISK_PROFILE_ID, riskProfileId);
        editor.commit();
    }

    public String getRiskProfileId() {
        return pref.getString(RISK_PROFILE_ID, "");
    }

    public void setIdentification(String identification) {
        editor.putString(IDENTIFICATION, identification);
        editor.commit();
    }

    public String getIdentification() {
        return pref.getString(IDENTIFICATION, "");
    }

    public void setDisclaimer(String disclaimer) {
        editor.putString(DISCLAIMER, disclaimer);
        editor.commit();
    }

    public String getDisclaimer() {
        return pref.getString(DISCLAIMER, "");
    }

    public void setNew(String newUser) {
        editor.putString(NEW, newUser);
        editor.commit();
    }

    public String getNew() {
        return pref.getString(NEW, "");
    }

    public void setDependentChildrenData(String dependentChildrenData) {
        editor.putString(DEPENDENT_CHILDREN_DATA, dependentChildrenData);
        editor.commit();
    }

    public String getDependentChildrenData() {
        return pref.getString(DEPENDENT_CHILDREN_DATA, "");
    }

    public void setFirstChildrenName(String firstChildrenName) {
        editor.putString(FIRST_CHILDREN_NAME, firstChildrenName);
        editor.commit();
    }
    public String getFirstChildrenName() {
        return pref.getString(FIRST_CHILDREN_NAME, "");
    }

    public void setFirstChildrenDob(String firstChildrenDob) {
        editor.putString(FIRST_CHILDREN_DOB, firstChildrenDob);
        editor.commit();
    }
    public String getFirstChildrenDob() {
        return pref.getString(FIRST_CHILDREN_DOB, "");
    }

    public void setSecondChildrenName(String secondChildrenName) {
        editor.putString(SECOND_CHILDREN_NAME, secondChildrenName);
        editor.commit();
    }
    public String getSecondChildrenName() {
        return pref.getString(SECOND_CHILDREN_NAME, "");
    }

    public void setSecondChildrenDob(String secondChildrenDob) {
        editor.putString(SECOND_CHILDREN_DOB, secondChildrenDob);
        editor.commit();
    }
    public String getSecondChildrenDob() {
        return pref.getString(SECOND_CHILDREN_DOB, "");
    }

    public void setThirdChildrenName(String thirdChildrenName) {
        editor.putString(THIRD_CHILDREN_NAME, thirdChildrenName);
        editor.commit();
    }
    public String getThirdChildrenName() {
        return pref.getString(THIRD_CHILDREN_NAME, "");
    }

    public void setThirdChildrenDob(String thirdChildrenDob) {
        editor.putString(THIRD_CHILDREN_DOB, thirdChildrenDob);
        editor.commit();
    }
    public String getThirdChildrenDob() {
        return pref.getString(THIRD_CHILDREN_DOB, "");
    }

    public void setFourthChildrenName(String fourthChildrenName) {
        editor.putString(FOURTH_CHILDREN_NAME, fourthChildrenName);
        editor.commit();
    }
    public String getFourthChildrenName() {
        return pref.getString(FOURTH_CHILDREN_NAME, "");
    }

    public void setFourthChildrenDob(String fourthChildrenDob) {
        editor.putString(FOURTH_CHILDREN_DOB, fourthChildrenDob);
        editor.commit();
    }
    public String getFourthChildrenDob() {
        return pref.getString(FOURTH_CHILDREN_DOB, "");
    }

    public void setFifthChildrenName(String fifthChildrenName) {
        editor.putString(FIFTH_CHILDREN_NAME, fifthChildrenName);
        editor.commit();
    }
    public String getFifthChildrenName() {
        return pref.getString(FIFTH_CHILDREN_NAME, "");
    }

    public void setFifthChildrenDob(String fifthChildrenDob) {
        editor.putString(FIFTH_CHILDREN_DOB, fifthChildrenDob);
        editor.commit();
    }
    public String getFifthChildrenDob() {
        return pref.getString(FIFTH_CHILDREN_DOB, "");
    }

    public void setSixthChildrenName(String sixthChildrenName) {
        editor.putString(SIXTH_CHILDREN_NAME, sixthChildrenName);
        editor.commit();
    }
    public String getSixthChildrenName() {
        return pref.getString(SIXTH_CHILDREN_NAME, "");
    }

    public void setSixthChildrenDob(String sixthChildrenDob) {
        editor.putString(FIFTH_CHILDREN_DOB, sixthChildrenDob);
        editor.commit();
    }
    public String getSixthChildrenDob() {
        return pref.getString(SIXTH_CHILDREN_DOB, "");
    }
}
