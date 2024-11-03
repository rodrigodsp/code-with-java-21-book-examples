package com.example.chapter3;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class WorkingWithStrings {
    
    public static void main(String[] args) {
        
        String email = "rodrigo.padua@RDSPCorp.com";
        String email2 = "rodrigo.dsp@gmail.com";

        String phoneNumber = "444-867-5309";
        String phoneNumber2 = "444-188-2300";

        // get string positions
        int dotPos = email.indexOf('.');
        int atPos = email.indexOf("@");

        String firstName = email.substring(0, dotPos);
        String lastName = email.substring(dotPos + 1, atPos);

        firstName = properCase(firstName);
        lastName = properCase(lastName);

        int dot2Pos = atPos + email.substring(atPos + 1).indexOf('.') + 1;
        String company = email.substring(atPos + 1, dot2Pos);

        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("Company: " + company);
        System.out.println("Email: " + email.toLowerCase());
        System.out.println("Phone 1: " + phoneNumber);
        System.out.println("Phone 2: " + phoneNumber2);

        if (firstName.equals("Rodrigo")) {
            System.out.println("Your name is Rodrigo!");
        } else {
            System.out.println("Sorry, your name is NOT Rodrigo.");
        }

        if (isBusinessEmail(email.toLowerCase())) {
            System.out.println(email + " is valid!");
        } else {
            System.out.println(email + " is not valid!");
        }
        if (isBusinessEmail(email2.toLowerCase())) {
            System.out.println(email2 + " is valid!");
        } else {
            System.out.println(email2 + " is not valid!");
        }

        if (lastName.toUpperCase().startsWith("PA")) {
            System.out.println(firstName + "'s last name of " + lastName + " does indeed start with Pa.");
        }

        if (phoneNumber.contains("-188-")) {
            System.out.println(phoneNumber + " is a valid 188 number!");
        }
        if (phoneNumber2.contains("-188-")) {
            System.out.println(phoneNumber2 + " is a valid 188 number!");
        }

        Pattern phone188Pattern = Pattern.compile("[0-9]{3}\\-188\\-[0-9]{4}");

        Matcher phone188Matcher = phone188Pattern.matcher(phoneNumber);
        Matcher phone188Matcher2 = phone188Pattern.matcher(phoneNumber2);

        if (phone188Matcher.find()) {
            System.out.println(phoneNumber + " is a valid 188 number! (regex)");
        }
        if (phone188Matcher2.find()) {
            System.out.println(phoneNumber2 + " is a valid 188 number! (regex)");
        }

        if (isValidPhoneNumber(phoneNumber)) {
            System.out.println(phoneNumber + " is a valid phone number!");
        }
        if (isValidPhoneNumber(phoneNumber2)) {
            System.out.println(phoneNumber2 + " is a valid phone number!");
        }

        //

        String nickname = "Toria";
        String nickname2 = "Vicky";
        String robert = "Robert";
        String nickname3 = "Rob";
        String nickname4 = "Bob";

        System.out.println("\nBob pattern:");
        Pattern bobPattern = Pattern.compile("[B|R]ob");

        matchName(bobPattern, nickname);
        matchName(bobPattern, nickname2);
        matchName(bobPattern, nickname3);
        matchName(bobPattern, nickname4);
        matchName(bobPattern, robert);
        matchName(bobPattern, firstName);

        System.out.println("\nVictoria pattern:");
        Pattern victoriaPattern = Pattern.compile("[Vic|][[T|t]oria]");

        matchName(victoriaPattern, nickname);
        matchName(victoriaPattern, nickname2);
        matchName(victoriaPattern, nickname3);
        matchName(victoriaPattern, nickname4);
        matchName(victoriaPattern, robert);
        matchName(victoriaPattern, firstName);

    }

    private static String properCase(String name) {
        char firstLetter = Character.toUpperCase(name.charAt(0));
        return firstLetter + name.substring(1);
    }

    private static boolean isBusinessEmail(String email) {
        boolean validEmail = true;

        if (email.endsWith("@gmail.com")) {
            validEmail = false;
        } else if (email.endsWith(".edu")) {
            validEmail = false;
        }

        return validEmail;
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {

        Pattern validPhonePattern = Pattern.compile("[0-9]{3}\\-[0-9]{3}\\-[0-9]{4}");
        Matcher phoneMatcher = validPhonePattern.matcher(phoneNumber);

        return phoneMatcher.find();
    }

    private static void matchName(Pattern pattern, String name) {

        Matcher matcher = pattern.matcher(name);

        if (matcher.find()) {
            System.out.println("Match found! Welcome " + name + "!");
        } else {
            System.out.println("Sorry " + name + ", no match found.");
        }
    }
}
