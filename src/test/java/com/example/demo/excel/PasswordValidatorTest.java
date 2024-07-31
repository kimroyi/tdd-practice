package com.example.demo.excel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidatorTest {

    // 유효한 비밀번호 문자를 위한 정규 표현식
    private static final String VALID_CHAR_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$";

    // 일반적인 연속 키보드 시퀀스 배열
    private static final String[] SEQUENCES = {
            "0123456789", "abcdefghijklmnopqrstuvwxyz", "qwertyuiop", "asdfghjkl", "zxcvbnm",
            "0987654321", "zyxwvutsrqponmlkjihgfedcba", "poiuytrewq", "lkjhgfdsa", "mnbvcxz"
    };

    public static void main(String[] args) {
        // 테스트 비밀번호
        String[] testPasswords = {
                "Abc123!", "111111", "121212", "qwerty", "Aa1@345", "A1b@cde", "Password123!", "qwe123RT!"
        };

        for (String password : testPasswords) {
            System.out.println("Password: " + password + " - " + (isValidPassword(password) ? "Valid" : "Invalid"));
        }
    }

    /**
     * 비밀번호가 유효한지 확인합니다.
     *
     * @param password 확인할 비밀번호
     * @return 비밀번호가 유효하면 true, 그렇지 않으면 false
     */
    public static boolean isValidPassword(String password) {
        if (!containsValidCharacters(password)) {
            System.out.println("비밀번호는 소문자, 대문자, 숫자, 특수문자를 각각 하나 이상 포함해야 합니다.");
            return false;
        }
        if (isRepeatedString(password)) {
            System.out.println("비밀번호는 동일한 문자열을 반복할 수 없습니다.");
            return false;
        }
        if (isRepeatedPattern(password)) {
            System.out.println("비밀번호는 두 문자열을 세 번 이상 연속으로 반복할 수 없습니다.");
            return false;
        }
        if (isSequentialKeys(password)) {
            System.out.println("비밀번호는 세 개의 연속적인 키보드 키를 포함할 수 없습니다.");
            return false;
        }
        return true;
    }

    /**
     * 비밀번호가 유효한 문자를 포함하고 있는지 확인합니다.
     *
     * @param password 확인할 비밀번호
     * @return 유효한 문자를 포함하고 있으면 true, 그렇지 않으면 false
     */
    private static boolean containsValidCharacters(String password) {
        Pattern pattern = Pattern.compile(VALID_CHAR_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * 비밀번호가 동일한 문자열로 이루어져 있는지 확인합니다.
     *
     * @param password 확인할 비밀번호
     * @return 동일한 문자열로 이루어져 있으면 true, 그렇지 않으면 false
     */
    private static boolean isRepeatedString(String password) {
        return password.matches("(.)\\1{5,}");
    }

    /**
     * 비밀번호가 두 문자열 패턴을 세 번 이상 연속으로 반복하는지 확인합니다.
     *
     * @param password 확인할 비밀번호
     * @return 두 문자열 패턴이 세 번 이상 연속으로 반복되면 true, 그렇지 않으면 false
     */
    private static boolean isRepeatedPattern(String password) {
        return password.matches("(..?)\\1{2,}");
    }

    /**
     * 비밀번호가 세 개의 연속적인 키보드 키를 포함하고 있는지 확인합니다.
     *
     * @param password 확인할 비밀번호
     * @return 연속적인 키보드 키를 포함하고 있으면 true, 그렇지 않으면 false
     */
    private static boolean isSequentialKeys(String password) {
        for (String seq : SEQUENCES) {
            for (int i = 0; i < seq.length() - 2; i++) {
                String subSeq = seq.substring(i, i + 3);
                if (password.toLowerCase().contains(subSeq)) {
                    return true;
                }
            }
        }
        return false;
    }
}
