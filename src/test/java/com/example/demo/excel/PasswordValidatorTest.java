package com.example.demo.excel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordValidatorTest {

    // 유효한 비밀번호 문자를 위한 정규 표현식
    private static final String VALID_CHAR_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$";

    // 일반적인 연속 키보드 시퀀스 배열
    private static final String[] SEQUENCES = {
            "0123456789", "abcdefghijklmnopqrstuvwxyz", "qwertyuiop", "asdfghjkl", "zxcvbnm",
            "0987654321", "zyxwvutsrqponmlkjihgfedcba", "poiuytrewq", "lkjhgfdsa", "mnbvcxz"
    };

    @Test
    @DisplayName("여러 비밀번호 유효성을 검사하고 결과 출력")
    void testValidatePasswords() {
        // 테스트할 비밀번호 목록
        String[] testPasswords = {
                "Abc123!", "111111", "121212", "qwerty", "Aa1@345", "A1b@cde", "Password123!", "qwe123RT!", "121212Rr!"
        };

        // 각 비밀번호의 유효성을 검사하고 콘솔에 결과 출력
        for (String password : testPasswords) {
            System.out.println("Password: " + password + " - " + (isValidPassword(password) ? "Valid" : "Invalid"));
        }
    }

    @Test
    @DisplayName("유효한 비밀번호 테스트")
    public void testValidPasswords() {
        assertTrue(isValidPassword("Aa1@56"));
        assertTrue(isValidPassword("Valid1!"));
        assertTrue(isValidPassword("Str0ng#"));
    }

    @Test
    @DisplayName("길이가 충분하지 않은 비밀번호 테스트")
    public void testInvalidPasswordsDueToLength() {
        assertFalse(isValidPassword("Aa1!"));
    }

    @Test
    @DisplayName("문자 유형이 누락된 비밀번호 테스트")
    public void testInvalidPasswordsDueToMissingCharacterTypes() {
        assertFalse(isValidPassword("aaaaaa!"));
        assertFalse(isValidPassword("AAAAAA!"));
        assertFalse(isValidPassword("111111!"));
        assertFalse(isValidPassword("!!!!!!aA1"));
    }

    @Test
    @DisplayName("반복된 문자열이 포함된 비밀번호 테스트")
    public void testInvalidPasswordsDueToRepeatedString() {
        assertFalse(isValidPassword("111111"));
        assertFalse(isValidPassword("2222Rr!"));
    }

    @Test
    @DisplayName("반복된 패턴이 포함된 비밀번호 테스트")
    public void testInvalidPasswordsDueToRepeatedPattern() {
        assertFalse(isValidPassword("121212Rr!"));
        assertFalse(isValidPassword("abcabcabcD1!"));
    }

    @Test
    @DisplayName("연속적인 키보드 문자가 포함된 비밀번호 테스트")
    public void testInvalidPasswordsDueToSequentialKeys() {
        assertFalse(isValidPassword("qwertyR1!"));
        assertFalse(isValidPassword("asdfgH1!"));
        assertFalse(isValidPassword("zxcvbnR1!"));
    }

    @Test
    @DisplayName("기타 유효하지 않은 비밀번호 테스트")
    public void testAdditionalInvalidPasswords() {
        assertFalse(isValidPassword("qwerRr!"));
        assertFalse(isValidPassword("aaaaaA1!"));
        assertFalse(isValidPassword("12345A!"));
        assertFalse(isValidPassword("!!!!aA1"));
    }

    /**
     * 비밀번호가 유효한지 확인합니다.
     *
     * @param password 확인할 비밀번호
     * @return 비밀번호가 유효하면 true, 그렇지 않으면 false
     */
    public boolean isValidPassword(String password) {
        if (!containsValidCharacters(password)) {
            System.out.println("비밀번호는 소문자, 대문자, 숫자, 특수문자를 각각 하나 이상 포함해야 합니다.");
            return false;
        }
        if (isRepeatedString(password)) {
            System.out.println("비밀번호는 동일한 문자열을 반복할 수 없습니다.");
            return false;
        }
        if (isRepeatedPattern(password)) {
            System.out.println("비밀번호는 문자열이 세 번 이상 반복될 수 없습니다.");
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
    private boolean containsValidCharacters(String password) {
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
    private boolean isRepeatedString(String password) {
        return password.matches("(.)\\1{2,}");
    }

    /**
     * 비밀번호가 두 문자 이상의 문자열 패턴을 세 번 이상 연속으로 반복하는지 확인합니다.
     *
     * @param password 확인할 비밀번호
     * @return 문자열 패턴이 세 번 이상 연속으로 반복되면 true, 그렇지 않으면 false
     */
    private boolean isRepeatedPattern(String password) {
        int length = password.length();
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j <= length; j++) {
                String substring = password.substring(i, j);
                String repeated = substring + substring + substring;
                if (password.contains(repeated)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 비밀번호가 세 개의 연속적인 키보드 키를 포함하고 있는지 확인합니다.
     *
     * @param password 확인할 비밀번호
     * @return 연속적인 키보드 키를 포함하고 있으면 true, 그렇지 않으면 false
     */
    private boolean isSequentialKeys(String password) {
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
